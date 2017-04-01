package com.cz4013.binder;

/**
 * Created by danielseetoh on 4/1/17.
 */
public class NameDoesNotExistException extends Exception {

    //Parameterless Constructor
    public NameDoesNotExistException() {}

    //Constructor that accepts a message
    public NameDoesNotExistException(String message)
    {
        super(message);
    }

}
