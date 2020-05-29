package se.kth.iv1350.integration;

import se.kth.iv1350.exceptions.DatabaseFailureException;
import se.kth.iv1350.exceptions.ItemNotFoundException;
import se.kth.iv1350.model.Discount;
import se.kth.iv1350.model.Receipt;
import se.kth.iv1350.model.SingleItem;

/**
 * @author Alexander Broms
 * @version 2.0
 * Written 2020-05-29
 *
 * This class is responsible for calling the external systems.
 */
public class ExternalSystemHandler {
    private final AccountingDBHandler accountingSystem;
    private final InventoryDBHandler inventorySystem;
    private final PrinterHandler printingSystem;
    private final DiscountDBHandler discountSystem;

    /**
     * This constructor creates instances of the external system handlers that are controlled
     * by this main handler.
     */
    public ExternalSystemHandler(){
        this.accountingSystem = new AccountingDBHandler();
        this.inventorySystem = new InventoryDBHandler();
        this.printingSystem = new PrinterHandler();
        this.discountSystem = new DiscountDBHandler();
    }

    /**
     * Instructs the inventory system to produce a {@link SingleItem} that has a matching item ID.
     * @param itemID the item ID used to find a particular item.
     * @return the relevant item, null if no matching item was found.
     * @throws ItemNotFoundException if no item with the given parameter can be found in the inventory.
     * @throws DatabaseFailureException if the class cannot establish a connection to the database.
     */
    public SingleItem findItem(int itemID) throws ItemNotFoundException, DatabaseFailureException {
        if (itemID == 404) {
            throw new DatabaseFailureException();
        }
        return inventorySystem.findItem(itemID);
    }

    /**
     * Instructs the discount system to produce a {@link Discount} that applies to a customer.
     * @param customerID an ID number representing a customer.
     * @return the relevant discount for the specified customer, null if none is found.
     */
    public Discount findDiscount(int customerID){
        Discount validDiscount = discountSystem.getValidDiscount(customerID);
        if(isValid(validDiscount)){
            return validDiscount;
        }
        else{
            return null;
        }
    }

    /**
     * Finalizes the sale by updating the external systems and printing the receipt.
     * @param receipt the receipt containing necessary information to update external systems.
     */
    public void finalizeSale(Receipt receipt){
        this.accountingSystem.storeSale(receipt);
        this.inventorySystem.updateInventory(receipt);
        this.printingSystem.printReceipt(receipt);
    }

    private boolean isValid(Discount discount){
        return discount != null;
    }
}
