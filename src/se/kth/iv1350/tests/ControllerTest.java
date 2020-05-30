package se.kth.iv1350.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.exceptions.DatabaseFailureException;
import se.kth.iv1350.exceptions.ItemNotFoundException;
import se.kth.iv1350.exceptions.OperationFailedException;
import se.kth.iv1350.integration.ExternalSystemHandler;
import se.kth.iv1350.model.SingleItem;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Controller ctrl;

    @BeforeEach
    void setUp() {
        ctrl = new Controller(new ExternalSystemHandler());
    }

    @AfterEach
    void tearDown() {
        ctrl = null;
    }

    /**
     * Test that OperationFailedException was thrown due to catching a DatabaseFailureException
     */
    @Test
    void testOperationFailedDBFailure() {
        boolean expResult = true;
        boolean result = false;
        try{
            ctrl.scanItem(404, 0);
        }
        catch(OperationFailedException e){
            result = e.getException() instanceof DatabaseFailureException;
        }
        assertEquals(expResult, result, "Received exception was not an instance of the expected exception.");
    }

    /**
     * Test that OperationFailedException was thrown due to catching an ItemNotFoundException.
     */
    @Test
    void testOperationFailedUnknownItem(){
        int unknownItemID = 1000;
        boolean expResult = true;
        boolean result = false;

        try{
            ctrl.scanItem(unknownItemID, 0);
        }
        catch (OperationFailedException e){
            result = e.getException() instanceof ItemNotFoundException;
        }
        assertEquals(expResult, result, "Received exception was not an instance of the expected exception.");
    }
}