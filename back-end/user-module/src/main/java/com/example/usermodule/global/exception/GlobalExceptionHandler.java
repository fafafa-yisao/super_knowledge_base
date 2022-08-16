package com.example.usermodule.global.exception;

import com.example.usermodule.exception.DefaultException;
import com.example.usermodule.global.api.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理，暂时只对主动抛出异常进行了处理
 * TODO 1. 增加 Validated 系列抛出的异常处理  2.系统异常入库
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DefaultException.class)
    public Result<String> handleException(Exception e) {
        return Result.error(e.getMessage());
    }

}
