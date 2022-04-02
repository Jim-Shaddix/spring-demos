package com.example.whoami.controller;

import com.example.whoami.exception.InvalidSizeSpecException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidSizeSpecException.class)
    public String invalidSizeSpec(InvalidSizeSpecException invalidSizeSpecException) {
        return String.format("The specified payload return size is invalid: \"%s\"",
                invalidSizeSpecException.getSizeSpec());
    }

}
