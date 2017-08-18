package com.pbs.ams.web.controller;

import com.pbs.ams.client.shiro.session.UpmsSession;
import com.pbs.ams.client.shiro.session.UpmsSessionDao;
import com.pbs.ams.common.base.BaseController;
import com.pbs.ams.common.constant.ResultSet;
import com.pbs.ams.common.constant.StatusCode;
import com.pbs.ams.common.constant.UpmsEnum;
import com.pbs.ams.common.moduleCommon.UpmsResult;
import com.pbs.ams.common.moduleCommon.UpmsResultConstant;
import com.pbs.ams.common.util.RedisUtil;
import com.pbs.ams.web.model.UpmsSystemExample;
import com.pbs.ams.web.service.UpmsSystemService;
import com.pbs.ams.web.service.UpmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * 单点登录管理
 * Created by ams on 2016/12/10.
 */
@Controller
@RequestMapping("/sso")
@Api(value = "单点登录管理", description = "单点登录管理")  // UpmsInterceptor
public class SSOController extends BaseController {
    private final static Logger _log = LoggerFactory.getLogger(SSOController.class);

    @Autowired
    private UpmsSystemService upmsSystemService;

    @Autowired
    private UpmsUserService upmsUserService;

    @Autowired
    private UpmsSessionDao upmsSessionDao ;







    @ApiOperation(value = "认证中心首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) throws Exception {
        System.out.println("----test-1-SSOController.java-index(...)-: " ); //
        String appid = request.getParameter("appid");
        String backurl = request.getParameter("backurl");
        if (StringUtils.isBlank(appid)) {
            throw new RuntimeException("无效访问！");
        }
        // 判断请求认证系统是否注册
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andNameEqualTo(appid);
        long count = upmsSystemService.countByExample(upmsSystemExample);
        if (0 == count) {
            throw new RuntimeException(String.format("未注册的系统:%s", appid));
        }
        return "redirect:/sso/login?backurl=" + URLEncoder.encode(backurl, "utf-8");
    }

    @ApiOperation(value = "校验code")
    @RequestMapping(value = "/code", method = RequestMethod.POST)
    @ResponseBody
    public Object code(HttpServletRequest request) {
        System.out.println("----test-1-SSOController.java-code(...)-: " ); //
        String codeParam = request.getParameter("code");
        String code = RedisUtil.get(UpmsEnum.AMS_UPMS_SERVER_CODE.getString() + "_" + codeParam);
        if (StringUtils.isBlank(codeParam) || !codeParam.equals(code)) {
            new UpmsResult(UpmsResultConstant.FAILED, "无效code");//new ResultSet(StatusCode.INVALID_CODE);
        }
        return new UpmsResult(UpmsResultConstant.SUCCESS, code);//new ResultSet(StatusCode.ERROR_NONE);
    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        System.out.println("----test-1-SSOController.java-login(....GET)-: " ); //
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String serverSessionId = session.getId().toString();
        // 判断是否已登录，如果已登录，则回跳
        String code = RedisUtil.get(UpmsEnum.AMS_UPMS_SERVER_SESSION_ID.getString() + "_" + serverSessionId);
        // code校验值
        if (StringUtils.isNotBlank(code)) {
            // 回跳
            String backurl = request.getParameter("backurl");
            String username = (String) subject.getPrincipal();
            if (StringUtils.isBlank(backurl)) {
                backurl = "/";
            } else {
                if (backurl.contains("?")) {
                    backurl += "&upms_code=" + code + "&upms_username=" + username;
                } else {
                    backurl += "?upms_code=" + code + "&upms_username=" + username;
                }
            }
            _log.debug("认证中心帐号通过，带code回跳：{}", backurl);
            return "redirect:" + backurl;
        }
        return "/sso/login.jsp";
    }




    @ApiOperation(value = "退出登录")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        System.out.println("----test-1-SSOController.java-logout(...)-: " ); //
        // shiro退出登录
        SecurityUtils.getSubject().logout();
        // 跳回原地址
        String redirectUrl = request.getHeader("Referer");
        if (null == redirectUrl) {
            redirectUrl = "/";
        }
        return "redirect:" + redirectUrl;
    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        System.out.println("----test-1-SSOController.java-login(....POST)-: " ); //
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        if (StringUtils.isBlank(username)) {
            return new UpmsResult(UpmsResultConstant.EMPTY_USERNAME, "帐号不能为空！");//new ResultSet(StatusCode.EMPTY_USERNAME);
        }
        if (StringUtils.isBlank(password)) {
            return new UpmsResult(UpmsResultConstant.EMPTY_PASSWORD, "密码不能为空！");//new ResultSet(StatusCode.EMPTY_PASSWORD);
        }

        // securityManager: zheng-master\zheng-upms\zheng-upms-client\src\main\resources\applicationContext-shiro.xml
        //SecurityUtils.setSecurityManager(securityManager);//(注入SecurityManager)此句已经 静态配置在了applicationContext-shiro.xml中
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        // 判断是否已登录，如果已登录，则回跳，防止重复登录
        String hasCode = RedisUtil.get(UpmsEnum.AMS_UPMS_SERVER_SESSION_ID.getString() + "_" + sessionId);
        // code校验值
        if (StringUtils.isBlank(hasCode)) {
            // 使用shiro认证 //使用用户的登录信息创建令牌	 token可以理解为用户令牌，登录的过程被抽象为Shiro验证令牌是否具有合法身份以及相关权限。
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            try {
                if (BooleanUtils.toBoolean(rememberMe)) {
                    usernamePasswordToken.setRememberMe(true);
                } else {
                    usernamePasswordToken.setRememberMe(false);
                }

                // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
                // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
                // 所以这一步在调用login(token)方法时,它会调用 UpmsRealm.doGetAuthenticationInfo()方法中...
                subject.login(usernamePasswordToken);
            } catch (UnknownAccountException e) {
                return new UpmsResult(UpmsResultConstant.INVALID_USERNAME, "帐号不存在！");//new ResultSet(StatusCode.INVALID_USERNAME);
            } catch (IncorrectCredentialsException e) {
                return new UpmsResult(UpmsResultConstant.INVALID_PASSWORD, "密码错误！");//new ResultSet(StatusCode.INVALID_PASSWORD);
            } catch (LockedAccountException e) {
                return new UpmsResult(UpmsResultConstant.INVALID_ACCOUNT, "帐号已锁定！");//new ResultSet(StatusCode.ACCOUNT_LOCKED);
            }
            // 更新session状态
            upmsSessionDao.updateStatus(sessionId, UpmsSession.OnlineStatus.on_line);
            // 全局会话sessionId列表，供会话管理
            RedisUtil.lpush(UpmsEnum.AMS_UPMS_SERVER_SESSION_ID.getString(), sessionId);
            // 默认验证帐号密码正确，创建code
            String code = UUID.randomUUID().toString();
            // 全局会话的code
            RedisUtil.set(UpmsEnum.AMS_UPMS_SERVER_SESSION_ID.getString() + "_" + sessionId, code, (int) subject.getSession().getTimeout() / 1000);
            // code校验值
            RedisUtil.set(UpmsEnum.AMS_UPMS_SERVER_CODE.getString() + "_" + code, code, (int) subject.getSession().getTimeout() / 1000);
        }
        // 回跳登录前地址
        String backurl = request.getParameter("backurl");
        if (StringUtils.isBlank(backurl)) {
            return new UpmsResult(UpmsResultConstant.SUCCESS, "/");//new ResultSet(StatusCode.ERROR_NONE, "/manage/index");
        } else {
            return new UpmsResult(UpmsResultConstant.SUCCESS, backurl);//new ResultSet(StatusCode.ERROR_NONE, backurl);
        }
    }

}