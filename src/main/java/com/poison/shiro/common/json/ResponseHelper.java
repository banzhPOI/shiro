package com.poison.shiro.common.json;

public class ResponseHelper {
    public static <T> Response<T> createSuccessResponse() {
        return createResponse(ReturnInfo.SUCCESS, null);
    }

    public static <T> Response<T> createSuccessResponse(T payload) {
        Response<T> response = createResponse(ReturnInfo.SUCCESS, null);
        response.setPayload(payload);
        return response;
    }

    public static <T> Response<T> createExceptionResponse(Exception e) {
        String desc = e.getMessage() == null ? e.getClass().getName() : e.getMessage();
        return createResponse(-1, desc);
    }

    public static <T> Response<T> createResponse(int code, String description) {
        Response<T> response = new Response();
        if (code != -999999) {
            response.setCode(code);
        }

        if (description != null) {
            response.setDescription(description);
        }

        return response;
    }
}
