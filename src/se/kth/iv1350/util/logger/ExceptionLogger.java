package se.kth.iv1350.util.logger;

import se.kth.iv1350.util.TimeAndDate;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Alexander Broms
 * @version 1.0
 * Written 2020-05-29
 * This logger class is from lecture 13 part 1 on polymorphism and is part of
 * my solution for task 1 of seminar 4. Methods have been added to provide more functionality.
 */
public class ExceptionLogger {
    private PrintWriter logStream;

    /**
     * Create a new instance and log file.
     * Existing log file will be deleted.
     */
    public ExceptionLogger(){
        try{
            logStream = new PrintWriter(new FileWriter("error_log.txt"), true);
        }
        catch (IOException e){
            System.out.println("Cannot log.");
            e.printStackTrace();
        }
    }

    /**
     * Write the message of an exception in the log file and when the error occurred.
     * @param e the error which has a message to be logged
     */
    public void logExceptionMessage(Exception e){
        logStream.println("Error occurred: " + e.getMessage() + " " + new TimeAndDate().getFormattedTime());
    }
}
