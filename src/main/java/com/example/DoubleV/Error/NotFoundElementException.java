package com.example.DoubleV.Error;
import  com.example.DoubleV.Error.ErrorMessage;

public class NotFoundElementException extends BaseException {

    public NotFoundElementException(String message) {
        this.message = message;
    }

    public NotFoundElementException(int error) {
        super(error);
    }

    public NotFoundElementException(int error, String message) {
        this.error = error;
        this.message = message;
    }

    public NotFoundElementException(ErrorMessage errorInfo){
        this.message = errorInfo.getMessage();
        this.error = errorInfo.getCode();
    }
}
