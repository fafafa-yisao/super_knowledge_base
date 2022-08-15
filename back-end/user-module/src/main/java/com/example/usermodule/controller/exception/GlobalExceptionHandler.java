package com.example.usermodule.controller.exception;

import com.example.usermodule.exception.DefaultException;
import com.example.usermodule.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DefaultException.class)
    public Result<String> handleException(Exception e) {
        return Result.error(e.getMessage());
    }

}
