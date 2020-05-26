package se.kth.iv1350.integration;

import se.kth.iv1350.model.Discount;
import se.kth.iv1350.model.Receipt;
import se.kth.iv1350.model.SingleItem;

/**
 * @author Alexander Broms
 * @version 1.0
 * Written 2020-05-26
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
     */
    public SingleItem findItem(int itemID){
        SingleItem foundItem = inventorySystem.findItem(itemID);

        return foundItem;
    }

    /**
     * Instructs the discount system to produce a {@link Discount} that applies to a customer.
     * @param customerID an ID number representing a customer.
     * @return the relevant discount for the specified customer
     */
    public Discount findDiscount(int customerID){
        Discount validDiscount = discountSystem.getValidDiscount(customerID);
        if(validDiscount != null){
            return validDiscount; //valid discount found
        }
        else{
            return null; //no discount found
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
}
