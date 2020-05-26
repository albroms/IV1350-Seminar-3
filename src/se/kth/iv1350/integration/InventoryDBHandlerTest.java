package se.kth.iv1350.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.Receipt;
import se.kth.iv1350.model.Sale;
import se.kth.iv1350.model.SingleItem;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryDBHandlerTest {
    InventoryDBHandler inventoryDBHandler;
    ArrayList<SingleItem> inventory;

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
    void testFindItem() {
        //SingleItem at index 0 will have itemID 1
        SingleItem expResult = inventory.get(0);
        SingleItem result = inventoryDBHandler.findItem(1);
        assertEquals(expResult, result, "Method did not fetch correct SingleItem.");
    }

    @Test
    void testNonExistentItem(){
        int invalidID = 999;
        SingleItem expResult = null;
        SingleItem result = inventoryDBHandler.findItem(invalidID);
        assertEquals(expResult, result, "The item found was not null.");
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