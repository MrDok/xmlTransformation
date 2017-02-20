package com.company.exceptions;

/**
 * Created by user on 20.02.2017.
 */
public class ValidationException extends Exception{
    public ValidationException(){
        super();
    }

    public ValidationException(String message){
        super(message);
    }
}
