package com.poison.shiro.security;

import com.poison.shiro.common.error.BusinessErrorCodes;
import com.poison.shiro.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyShiro {
    private Logger logger = LoggerFactory.getLogger(MyShiro.class);

    public void userLoginActionWithMyRealm(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        logger.info("用户：" + username + "正在登录");
        //组建Subject主体.
        Subject subject = SecurityUtils.getSubject();
        //创建 token 令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //用户登录操作.
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            //登录失败原因 1 用户不存在 2 用户密码不正确
            String ex = e.getClass().getName();
            if (UnknownAccountException.class.getName().equals(ex)) {
                logger.info("用户名不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(ex)) {
                logger.info("密码错误");
            } else {
                logger.error("未知错误");
            }
            throw BusinessErrorCodes.getLoginErrorException();
        }
    }
}
