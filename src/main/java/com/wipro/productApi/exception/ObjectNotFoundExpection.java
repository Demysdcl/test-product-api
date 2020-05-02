package com.wipro.productApi.exception;

public class ObjectNotFoundExpection extends RuntimeException{
    public ObjectNotFoundExpection(String message) {
        super(message);
    }
}
