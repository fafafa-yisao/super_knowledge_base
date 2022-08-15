package com.example.usermodule.exception;

public class DefaultException extends RuntimeException{

    public DefaultException(String message) {
        super(message);
    }


    public static DefaultException exception(String msg){
        return new DefaultException(msg);
    }

}
