package com.poison.shiro.common.json;

public interface ReturnCode {
    int SUCCESS = 0;
    int INVALID_TOKEN = 2001;
    int FAILED_SEND_SMS = 2002;
    int INVALID_LOGIN_INFO = 2;
    int NO_PERMISSION = 3;
    int EXCEPTION = -1;
    int BUSINESS_ERROR = -2;
    int USERNAME_EXIST = -3;
    int DUPLICATE_SUBMISSION = -4;
    int EXPERT_NO_APPLY = -5;
    int INVALID_INPUT = -10;
    int NEVER_USED_CODE = -999999;
}

