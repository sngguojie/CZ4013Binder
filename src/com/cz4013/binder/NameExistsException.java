package com.cz4013.binder;

/**
 * Created by danielseetoh on 4/1/17.
 */
public class NameExistsException extends Exception {

    /**
     * Constructor
     */
    public NameExistsException(){}

    /**
     * Constructor that takes in a message
     * @param message
     */
    public NameExistsException(String message){
        super(message);
    }
}
