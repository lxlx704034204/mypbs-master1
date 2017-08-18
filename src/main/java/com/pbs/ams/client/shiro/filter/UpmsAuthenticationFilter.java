package com.pbs.ams.client.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.pbs.ams.client.shiro.session.UpmsSessionDao;
import com.pbs.ams.client.util.RequestParameterUtil;
import com.pbs.ams.common.constant.UpmsConstant;
import com.pbs.ams.common.constant.UpmsEnum;
import com.pbs.ams.common.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 重写authc过滤器
 * Created by ams on 2017/3/11.
 */
public class UpmsAuthenticationFilter extends AuthenticationFilter {

    private final static Logger _log = LoggerFactory.getLogger(UpmsAuthenticationFilter.class);

    public UpmsAuthenticationFilter(){System.out.println("---test-test-0-UpmsAuthenticationFilter.java--self-: " );}

//    指示：
//    AMS_UPMS_CLIENT_SESSION_ID("pbs-ams-management-client-session-id"),     // 局部会话key                  for:UpmsSessionDao/UpmsAuthenticationFilter
//    AMS_UPMS_CLIENT_SESSION_IDS ("pbs-ams-management-client-session-ids"),  // 单点同一个code所有局部会话key for:UpmsSessionDao/UpmsAuthenticationFilter

    @Autowired
    private UpmsSessionDao upmsSessionDao;


    @Override // change from zheng
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        System.out.println("---test-test-1-UpmsAuthenticationFilter.java--isAccessAllowed(...)-: " ); //
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        // 判断请求类型
        session.setAttribute(UpmsConstant.UPMS_TYPE, UpmsEnum.UPMSTYPE.getString());
        return "client".equals(UpmsEnum.UPMSTYPE.getString()) ? validateClient(request, response) : "server".equals(UpmsEnum.UPMSTYPE.getString()) && subject.isAuthenticated();
    }








    @Override   // change from zheng
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("---test-test-1-UpmsAuthenticationFilter.java--onAccessDenied(...)-: " ); //
        // server需要登录
        if ("server".equals(UpmsEnum.UPMSTYPE.getString())) { //   http://localhost:1111
            WebUtils.toHttp(response).sendRedirect(UpmsEnum.SSO_SERVER_URL.getStringBuilder()+"/sso/login");
            return false;
        }
        UpmsEnum.SSO_SERVER_URL.getStringBuilder().append("/sso/index").append("?").append("appid").append("=").append(UpmsEnum.APPID.getString());
        // 回跳地址
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        StringBuffer backurl = httpServletRequest.getRequestURL();
        String queryString = httpServletRequest.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            backurl.append("?").append(queryString);
        }
        UpmsEnum.SSO_SERVER_URL.getStringBuilder().append("&").append("backurl").append("=").append(URLEncoder.encode(backurl.toString(), "utf-8"));
        WebUtils.toHttp(response).sendRedirect(UpmsEnum.SSO_SERVER_URL.getString());
        return false;
    }


    /**
     * 认证中心登录成功带回code
     * @param request
     */
    private boolean validateClient(ServletRequest request, ServletResponse response) { // change from zheng 1
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        int timeOut = (int) session.getTimeout() / 1000;
        // 判断局部会话是否登录
        String cacheClientSession = RedisUtil.get(UpmsEnum.AMS_UPMS_CLIENT_SESSION_ID.getString() + "_" + session.getId());
        if (StringUtils.isNotBlank(cacheClientSession)) {
            // 更新code有效期
            RedisUtil.set(UpmsEnum.AMS_UPMS_CLIENT_SESSION_ID.getString() + "_" + sessionId, cacheClientSession, timeOut);
            Jedis jedis = RedisUtil.getJedis();
            jedis.expire(UpmsEnum.AMS_UPMS_CLIENT_SESSION_IDS.getString() + "_" + cacheClientSession, timeOut);
            jedis.close();
            // 移除url中的code参数
            if (null != request.getParameter("code")) {
                String backUrl = RequestParameterUtil.getParameterWithOutCode(WebUtils.toHttp(request));
                HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                try {
                    httpServletResponse.sendRedirect(backUrl);
                } catch (IOException e) {
                    _log.error("局部会话已登录，移除code参数跳转出错：", e);
                }
            } else {
                return true;
            }
        }
        // 判断是否有认证中心code
        String code = request.getParameter("upms_code");
        // 已拿到code
        if (StringUtils.isNotBlank(code)) {
            // HttpPost去校验code
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(UpmsEnum.SSO_SERVER_URL.getStringBuilder().append("/sso/code").toString());

                List<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("code", code));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse httpResponse = httpclient.execute(httpPost);
                if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = httpResponse.getEntity();
                    JSONObject result = JSONObject.parseObject(EntityUtils.toString(httpEntity));
                    if (1 == result.getIntValue("code") && result.getString("data").equals(code)) {
                        // code校验正确，创建局部会话
                        RedisUtil.set(UpmsEnum.AMS_UPMS_CLIENT_SESSION_ID.getString() + "_" + sessionId, code, timeOut);
                        // 保存code对应的局部会话sessionId，方便退出操作
                        RedisUtil.sadd(UpmsEnum.AMS_UPMS_CLIENT_SESSION_IDS.getString() + "_" + code, sessionId, timeOut);
                        _log.debug("当前code={}，对应的注册系统个数：{}个", code, RedisUtil.getJedis().scard(UpmsEnum.AMS_UPMS_CLIENT_SESSION_IDS.getString() + "_" + code));
                        // 移除url中的token参数
                        String backUrl = RequestParameterUtil.getParameterWithOutCode(WebUtils.toHttp(request));
                        // 返回请求资源
                        try {
                            // client无密认证
                            String username = request.getParameter("upms_username");
                            subject.login(new UsernamePasswordToken(username, ""));
                            HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                            httpServletResponse.sendRedirect(backUrl);
                            return true;
                        } catch (IOException e) {
                            _log.error("已拿到code，移除code参数跳转出错：", e);
                        }
                    } else {
                        _log.warn(result.getString("data"));
                    }
                }
            } catch (IOException e) {
                _log.error("验证token失败：", e);
            }
        }
        return false;
    }

}
