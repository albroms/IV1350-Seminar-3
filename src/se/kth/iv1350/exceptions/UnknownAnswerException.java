package se.kth.iv1350.exceptions;

/**
 * @author Alexander Broms
 * @version 1.0
 * Written 2020-05-29
 * An exception that is thrown if an answer provided by the user cannot be handled by the program.
 */
public class UnknownAnswerException extends Exception{
    private final String unknownAnswer;

    /**
     * Create an instance of the exception given the String that caused the exception to be thrown.
     * @param unknownAnswer the answer of type String that caused the exception to be thrown.
     */
    public UnknownAnswerException(String unknownAnswer){
        super("The answer the user provided (" + unknownAnswer + ") was not known to the system.");
        this.unknownAnswer = unknownAnswer;
    }

    /**
     * Return the answer which caused the exception to be thrown
     * @return the answer which caused the problem
     */
    public String getUnknownAnswer(){
        return unknownAnswer;
    }
}
