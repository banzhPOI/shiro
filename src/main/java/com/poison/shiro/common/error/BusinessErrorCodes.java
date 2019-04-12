package com.poison.shiro.common.error;

public class BusinessErrorCodes {

    public static CodeException getLoginErrorException() {
        return new CodeException(160001, "用户名或密码错误");
    }

    public static CodeException getUpdateError() {
        return new CodeException(160002, "操作未成功，请重试");
    }

    public static CodeException getInvalidTokenException() {
        return new CodeException(160003, "无效的token，请重新登录");
    }

    public static CodeException getExpiredTokenException() {
        return new CodeException(160004, "身份信息已过期，请重新登录");
    }
}
