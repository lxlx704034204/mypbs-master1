package com.pbs.ams.client.shiro.realm;

import com.pbs.ams.common.constant.UpmsEnum;
import com.pbs.ams.common.util.MD5Util;
import com.pbs.ams.web.model.UpmsPermission;
import com.pbs.ams.web.model.UpmsRole;
import com.pbs.ams.web.model.UpmsUser;
import com.pbs.ams.web.service.UpmsApiService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户认证和授权
 * Created by ams on 2017/1/20.
 */
public class UpmsRealm extends AuthorizingRealm {

    private static Logger _log = LoggerFactory.getLogger(UpmsRealm.class);

    @Resource //zheng: @Autowired
    private UpmsApiService upmsApiService;

    /**授权：验证权限时调用RedisUtil
     * 为当前登录的Subject授予角色和权限：验证权限时调用
     *  经测试:本例中该方法的调用时机为需授权资源被访问时
     *  经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
     *  个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
     *  比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);

        // 当前用户所有角色
        List<UpmsRole> upmsRoles = upmsApiService.selectUpmsRoleByUpmsUserId(upmsUser.getUserId());
        Set<String> roles = new HashSet<>();
        for (UpmsRole upmsRole : upmsRoles) {
            if (StringUtils.isNotBlank(upmsRole.getName())) {
                roles.add(upmsRole.getName());
            }
        }

        // 当前用户所有权限
        List<UpmsPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
        Set<String> permissions = new HashSet<>();
        for (UpmsPermission upmsPermission : upmsPermissions) {
            if (StringUtils.isNotBlank(upmsPermission.getPermissionValue())) {
                permissions.add(upmsPermission.getPermissionValue());
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证：login时调用      本例中该方法的调用时机为SSOController.login()方法中执行subject.login(usernamePasswordToken)时
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        // client无密认证

        if ("client".equals(UpmsEnum.UPMSTYPE.getString())) {
            return new SimpleAuthenticationInfo(username, password, getName());
        }

        // 查询用户信息
        UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);

        if (null == upmsUser) {
            throw new UnknownAccountException();
        }
        //										      用户键入的password + (注册的时候 自动给密码加上随机的"salt"生成的新的密码)
        if (!upmsUser.getPassword().equals(MD5Util.MD5(password + upmsUser.getSalt()))) {//此方法 禁止了 从数据库直接插入数据 登陆app的渠道
            throw new IncorrectCredentialsException();
        }
        if (upmsUser.getLocked() == 1) {
            throw new LockedAccountException();
        }
//将查询到的用户账号和密码存放到 AuthenticationInfo,用于后面的权限判断。第三个参数传入realName。
        return new SimpleAuthenticationInfo(username, password, getName());
    }

}
