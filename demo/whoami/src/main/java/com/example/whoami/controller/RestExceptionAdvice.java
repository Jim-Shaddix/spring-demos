package com.example.whoami.controller;

import com.example.whoami.exception.InvalidSizeSpecException;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * handles custom exceptioms thrown by the whoami application.
 */
@Log
@RestControllerAdvice
public class RestExceptionAdvice {

    @ExceptionHandler(InvalidSizeSpecException.class)
    public String invalidSizeSpec(InvalidSizeSpecException invalidSizeSpecException) {
        String errMsg = String.format("The specified payload return size is invalid: \"%s\"",
                invalidSizeSpecException.getSizeSpec());
        log.info(errMsg);
        return errMsg;
    }

}
