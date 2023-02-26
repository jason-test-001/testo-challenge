package com.testo.config;


import com.testo.service.LoginService;
import com.testo.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


/**
 * user realm
 *
 * @author:heyaolei
 * @create: 2023-02-20
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    /**
     * user role and permission call back function
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        List<Map<String, Object>> powerList = loginService.getUserPower(userName);

        for (Map<String, Object> powerMap : powerList) {
            simpleAuthorizationInfo.addRole(String.valueOf(powerMap.get("roleName")));
            simpleAuthorizationInfo.addStringPermission(String.valueOf(powerMap.get("permissionsName")));
        }
        return simpleAuthorizationInfo;
    }

    /**
     * verify username and password from token are correct or not 。call back function
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        String userName = authenticationToken.getPrincipal().toString();
        SysUser sysUser = loginService.queryUser(userName);
        if (sysUser == null) {
            return null;
        } else {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, sysUser.getPassword().toString(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
