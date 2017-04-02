package com.cz4013.binder;

/**
 * Created by danielseetoh on 4/1/17.
 */
public class NameDoesNotExistException extends Exception {

    /**
     * Constructor
     */
    public NameDoesNotExistException() {}

    /**
     * Constructor that takes in a message
     * @param message
     */
    public NameDoesNotExistException(String message)
    {
        super(message);
    }

}
