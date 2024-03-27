package com.javaweb.springbootnonjwt.customexception;

public class FiledRequiredException extends RuntimeException{
    public FiledRequiredException(String message){
        super(message);
    }

}
