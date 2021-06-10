package com.mysunpic.app.common.exception;

import com.mysunpic.app.common.BaseResponse;
import com.mysunpic.app.common.consts.MessageCodeConstants;
import com.mysunpic.app.common.consts.MessageConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory
            .getLogger(GlobalExceptionHandler.class.getName());
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse defaultErrorHandler(Exception e) {
        logger.error(e.getMessage(), e);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(MessageCodeConstants.MESSAGE_E20001);
        response.setStatusMsg(MessageConstants.MESSAGE_E20001);
        return response;
    }
}
