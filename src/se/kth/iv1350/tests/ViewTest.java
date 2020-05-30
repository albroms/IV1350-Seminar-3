package se.kth.iv1350.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.exceptions.UnknownAnswerException;
import se.kth.iv1350.integration.ExternalSystemHandler;
import se.kth.iv1350.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {
    private View view;
    //private Scanner in;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        File unknownAnswerTestFile = new File("C:/Users/John/Desktop/OODesign/Seminar 3/src/se/kth/iv1350/tests/UnknownAnswerTestFile.txt");
        Controller ctrl = new Controller(new ExternalSystemHandler());
        view = new View(ctrl, new Scanner(unknownAnswerTestFile));
        ctrl.startSale("Test Dummy");
    }

    @AfterEach
    void tearDown() {
        view = null;
    }

    /**
     * Tests if the scanItem method in the View throws UnknownAnswerException when the program does not have logic
     * to handle the answer.
     */
    @Test
    void testUnknownAnswerException(){
        //placeholders that automatically fail since test is not finished.
        boolean expResult = true;
        boolean result = false;

        try{
            view.scanItem();
        }
        catch (UnknownAnswerException e){
            result = true;
        }

        assertEquals(expResult, result, "Test is not complete.");
    }
}