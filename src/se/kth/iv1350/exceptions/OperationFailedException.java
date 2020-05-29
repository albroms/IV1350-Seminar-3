package se.kth.iv1350.exceptions;

/**
 * @author Alexander Broms
 * @version 1.0
 * Written 2020-05-29
 * A class that throws a more general kind of exception for when operations fail.
 * When thrown, it also provides the caught exception which caused OperationFailedException to be thrown.
 */
public class OperationFailedException extends Exception{
    private final Exception exception;


    /**
     * The constructor
     * @param caughtException the exception caught that caused some other method to throw {@link OperationFailedException}.
     */
    public OperationFailedException(Exception caughtException){
        super("Attempted operation caught exception \n'" + caughtException.toString() + "'\n and could not be completed.");
        this.exception = caughtException;
    }

    /**
     * Returns the specific exception which caused {@link OperationFailedException} to be thrown.
     * @return the exception in question.
     */
    public Exception getException(){
        return exception;
    }
}
