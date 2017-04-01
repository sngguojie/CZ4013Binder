package com.cz4013.binder;

/**
 * Created by danielseetoh on 4/1/17.
 */
public class NameExistsException extends Exception {

    public NameExistsException(){}

    public NameExistsException(String message){
        super(message);
    }
}
