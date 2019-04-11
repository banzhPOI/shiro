package com.poison.shiro.common.error;

import com.poison.shiro.common.json.Response;
import com.poison.shiro.common.json.ResponseHelper;
import com.poison.shiro.common.json.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * General error handler for the application.
 */
@ControllerAdvice
class ControllerExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);


    /**
     * Handle exceptions thrown by handlers.
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Response<?> exception(Exception exception, WebRequest request) {
        logger.debug("Exception {}", exception.getMessage(), exception);
        return ResponseHelper.createExceptionResponse(exception);
    }


    /**
     * Handle exceptions thrown by handlers.
     */
    @ExceptionHandler(value = CodeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Response<?> exception(CodeException exception, WebRequest request) {
        logger.debug("CodeException code = {}, {}", exception.getCode(), exception.getMessage());
        return ResponseHelper.createResponse(exception.getCode(), exception.getMessage());

    }

    @ExceptionHandler(value = DataAccessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Response<?> exception(DataAccessException exception) {
        logger.debug("DataAccessException : ", exception);
        return ResponseHelper.createResponse(ReturnCode.EXCEPTION, "数据库访问异常：" + exception.getClass().getSimpleName());
    }
}