package se.kth.iv1350.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Alexander Broms
 * @version 1.0
 * Written 2020-05-26
 *
 * A class that creates a string that displays a time and date.
 */
public class TimeAndDate {
    private DateTimeFormatter dtf;
    private LocalDateTime time;
    private String formattedTime;

    /**
     * The constructor that creates an instance of {@link TimeAndDate} that can be used elsewhere.
     */
    public TimeAndDate(){
        this.dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.time = LocalDateTime.now();
        this.formattedTime = time.format(dtf);
    }

    /**
     * Returns the string representation of the time and date when the object was instantiated.
     * @return the string representation of a time and date
     */
    public String getFormattedTime(){
        return formattedTime;
    }
}
