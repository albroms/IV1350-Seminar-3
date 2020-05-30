package se.kth.iv1350.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.exceptions.ItemNotFoundException;
import se.kth.iv1350.integration.InventoryDBHandler;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.Receipt;
import se.kth.iv1350.model.Sale;
import se.kth.iv1350.model.SingleItem;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the non-trivial methods in InventoryDBHandler.java
 * testNonExistentItem has been modified from Seminar 3 to compile and pass
 * with the exception handling for Seminar 4.
 */
class InventoryDBHandlerTest {
    private InventoryDBHandler inventoryDBHandler;
    private ArrayList<SingleItem> inventory;

    @BeforeEach
    void setUp() {
        inventoryDBHandler = new InventoryDBHandler();
        inventory = inventoryDBHandler.getInventory();
    }

    @AfterEach
    void tearDown() {
        inventoryDBHandler = null;
        inventory = null;
    }

    @Test
    void testFindItem() throws ItemNotFoundException {
        //SingleItem at index 0 will have itemID 1
        SingleItem expResult = inventory.get(0);
        SingleItem result = inventoryDBHandler.findItem(1);
        assertEquals(expResult, result, "Method did not fetch correct SingleItem.");
    }

    @Test
    void testNonExistentItem() {
        int invalidID = 999;
        String expResult = "Item not found.";
        String result = null;
        try{
            inventoryDBHandler.findItem(invalidID);
        }
        catch (ItemNotFoundException e){
            result = "Item not found.";
        }
        assertEquals(expResult, result, "Expected " + expResult + " but got " + result + ".");
    }

    @Test
    void testUpdateInventory() {
        Amount testPayment = new Amount(2);
        Amount testChange = new Amount(0.01);
        Sale testSale = new Sale("Test");
        testSale.addItem(inventory.get(0).getItemDTO(), 1);
        Receipt testReceipt = testSale.prepareReceipt(testPayment, testChange);
        inventoryDBHandler.updateInventory(testReceipt);
        int expResult = 3;
        int result = inventory.get(0).getQuantity();
        assertEquals(expResult, result, "Quantity did not decrease by 1.");
    }
}