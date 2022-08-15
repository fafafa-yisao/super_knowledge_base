package com.example.usermodule.exception;

/**
 * 用于区分系统异常和主动抛出异常
 * 便于后续追踪系统异常
 */
public class DefaultException extends RuntimeException{

    public DefaultException(String message) {
        super(message);
    }

    public static DefaultException exception(String msg){
        return new DefaultException(msg);
    }

}
