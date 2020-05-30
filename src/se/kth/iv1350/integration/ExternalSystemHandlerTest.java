package se.kth.iv1350.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.exceptions.DatabaseFailureException;
import se.kth.iv1350.exceptions.ItemNotFoundException;
import se.kth.iv1350.model.SingleItem;

import static org.junit.jupiter.api.Assertions.*;

class ExternalSystemHandlerTest {
    ExternalSystemHandler exSys;
    InventoryDBHandler inventory;

    @BeforeEach
    void setUp() {
        exSys = new ExternalSystemHandler();
        inventory = new InventoryDBHandler();
    }

    @AfterEach
    void tearDown() {
        exSys = null;
        inventory = null;
    }

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