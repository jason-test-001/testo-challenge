package com.testo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * handle exception
 *
 * @author:heyaolei
 * @create: 2023-02-20
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    /**
     * handle exception
     *
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public String ErrorHandler(AuthorizationException e) {
        log.error("permission verification failure！", e);
        return "you have no permission！";
    }
}
