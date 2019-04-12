package com.poison.shiro.security;

import com.poison.shiro.domain.Permission;
import com.poison.shiro.domain.Role;
import com.poison.shiro.domain.User;
import com.poison.shiro.service.user.PermissionMapper;
import com.poison.shiro.service.user.RoleMapper;
import com.poison.shiro.service.user.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################执行Shiro权限认证##################");
        User user = (User) principalCollection.getPrimaryPrincipal();
        if (user != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户的角色集合
            info.addRoles(user.getRoleStrList());
            //用户的权限集合
            info.addStringPermissions(user.getPermissionStrList());
            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }

    //登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        logger.info("验证当前Subject时获取到token为：" + token.toString());
        //通过token获取用户账号
        String username = token.getUsername();
        User hasUser = userMapper.findByUsername(username);
        if (hasUser != null) {
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            Long userId = hasUser.getId();
            List<Role> roleList = roleMapper.findByUserId(userId);//获取用户角色
            List<Permission> permissionList = permissionMapper.findByUserId(userId);//获取用户权限
            List<String> roleStrlist = new ArrayList<>();////用户的角色集合
            List<String> permissionStrlist = new ArrayList<>();//用户的权限集合
            for (Role role : roleList) {
                roleStrlist.add(role.getName());
            }
            for (Permission permission : permissionList) {
                permissionStrlist.add(permission.getName());
            }
            hasUser.setRoleStrList(roleStrlist);
            hasUser.setPermissionStrList(permissionStrlist);
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            return new SimpleAuthenticationInfo(hasUser, hasUser.getPassword(), getName());
        }
        return null;
    }


}
