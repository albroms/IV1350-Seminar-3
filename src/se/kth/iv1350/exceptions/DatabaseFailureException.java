package se.kth.iv1350.exceptions;

/**
 * @author Alexander Broms
 * @version 1.0
 * Written 2020-05-29
 * A simple exception that is thrown when communication with the database fails.
 */
public class DatabaseFailureException extends Exception{
    /**
     * A simple constructor that calls on super to create a message for the exception.
     */
    public DatabaseFailureException(){
        super("Program failed to establish a connection with the database.");
    }
}
