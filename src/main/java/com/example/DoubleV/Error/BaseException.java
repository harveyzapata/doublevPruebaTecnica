package com.example.DoubleV.Error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {

    public static int NULL_VALUE = 1;
    protected int error;
    protected String message;
    public static final int START_ERROR = 1000;

    public BaseException(){}

    public BaseException(int error) {
        this.error = error;
    }

    public BaseException(int error, String message) {
        this.error = error;
        this.message = message;
    }
    public BaseException(ErrorMessage errorInfo){
        this.message = errorInfo.getMessage();
        this.error = errorInfo.getCode();
    }

    public BaseException(String message) {
        this.message = message;
    }
}
