package se.kth.iv1350.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.exceptions.DatabaseFailureException;
import se.kth.iv1350.exceptions.ItemNotFoundException;
import se.kth.iv1350.integration.ExternalSystemHandler;

import static org.junit.jupiter.api.Assertions.*;

class ExternalSystemHandlerTest {
    private ExternalSystemHandler exSys;

    @BeforeEach
    void setUp() {
        exSys = new ExternalSystemHandler();
    }

    @AfterEach
    void tearDown() {
        exSys = null;
    }

    /**
     * Test that ItemNotFoundException is thrown and contains the itemID that caused the exception to be thrown.
     */
    @Test
    void testItemNotFound() {
        int nonExistentID = 1000;
        String expResult = new ItemNotFoundException(nonExistentID).getMessage();
        String result = null;

        try{
            exSys.findItem(nonExistentID);
        }
        catch (ItemNotFoundException | DatabaseFailureException e){
            result = e.getMessage();
        }

        assertEquals(expResult, result, "Expected " + expResult + " and got " + result + ".");
    }

    /**
     * Test that the hard-coded itemID causes a DatabaseFailureException to be thrown.
     */
    @Test
    void testDatabaseFailure() {
        int databaseFailID = 404;
        String expResult = new DatabaseFailureException().getMessage();
        String result = null;

        try{
            exSys.findItem(databaseFailID);
        }
        catch (DatabaseFailureException | ItemNotFoundException e){
            result = e.getMessage();
        }
        
        assertEquals(expResult, result, "Expected " + expResult + " and got " + result + ".");
    }
}