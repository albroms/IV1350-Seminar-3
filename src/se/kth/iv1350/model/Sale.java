package se.kth.iv1350.model;

import se.kth.iv1350.integration.ItemDTO;
import se.kth.iv1350.util.TimeAndDate;

import java.util.ArrayList;

/**
 * @author Alexander Broms
 * @version 1.2
 * Written 2020-05-27
 */
public class Sale {
    private final String cashier;
    private Amount totalPrice;
    private ArrayList<SingleItem> scannedItems;
    private final String timeOfSale = new TimeAndDate().getFormattedTime();

    /**
     * Constructor to create a new sale instance.
     * @param cashier the name of the cashier managing the sale.
     */
    public Sale(String cashier){
        this.cashier = cashier;
        this.scannedItems = new ArrayList<>();
        this.totalPrice = new Amount(0.0);
    }

    /**
     * Add an item to the current sale by taking an {@code itemDTO} and a {@code quantity}
     * and creating a new {@code SingleItem} if it hasn't already been added to the sale.
     * If the item already exists in the sale, only the quantity for the matching item is updated.
     *
     * @param item the ItemDTO we wish to add.
     * @param quantity the quantity of the item we wish to add.
     */
    public void addItem(ItemDTO item, int quantity){
        if(itemAlreadyAdded(item)){
            updateSingleItem(item, quantity);
        }
        else{
            SingleItem newItem = new SingleItem(item, quantity);
            this.scannedItems.add(newItem);
        }
        updateTotalPrice();
    }

    /**
     * Apply the given discount to the total sale price.
     * @param discount the discount to be applied
     */
    public void applyDiscount(Discount discount){
        double priceBeforeDiscount = this.totalPrice.getValue();
        double discountToApply = discount.getDiscountFactor();
        double priceAfterDiscount = priceBeforeDiscount * discountToApply;

        this.totalPrice = new Amount(priceAfterDiscount).roundOff(priceAfterDiscount);
    }

    /**
     * Call on {@link Receipt} constructor to create a receipt for the sale.
     * @return a new instance of {@link Receipt}
     */
    public Receipt prepareReceipt(Amount paid, Amount change){
        return new Receipt(this, paid, change);
    }

    //getters
    /**
     * A simple getter that returns the {@code totalPrice} as an {@code Amount}
     */
    public Amount getTotalPrice(){
        return totalPrice;
    }

    /**
     * Getter for the {@link ArrayList} of scanned items.
     * @return the {@link ArrayList} of items that have been scanned so far.
     */
    public ArrayList<SingleItem> getScannedItems(){
        return scannedItems;
    }

    /**
     * Returns the name of the cashier.
     * @return the cashier's name.
     */
    public String getCashier(){ return cashier; }

    /**
     * Returns the date and time for when the sale was started.
     * @return the date and time
     */
    public String getTimeOfSale(){ return timeOfSale;}

    /**
     * Returns the {@link Amount} of the total VAT for an entire {@link Sale} instance.
     * @return the {@link Amount} representing what part of the total price is VAT.
     */
    public Amount getTotalVATPrice(){
        double vAT = 0.0;
        for(SingleItem item : scannedItems){
            vAT += (item.getItemTotal().getValue() * item.getItemDTO().getItemVAT());
        }
        return new Amount(vAT).roundOff(vAT);
    }


    //private methods
    private boolean itemAlreadyAdded(ItemDTO item){
        for(SingleItem scannedItem : scannedItems){
            if(scannedItem.getItemDTO().equals(item)){
                return true;
            }
        }
        return false;
    }

    private void updateSingleItem(ItemDTO item, int quantity){
        for(SingleItem scannedItem : scannedItems){
            if(scannedItem.getItemDTO().equals(item)){
                scannedItem.increaseQuantity(quantity);
                scannedItem.increaseItemTotal();
            }
        }
    }
    private void updateTotalPrice(){
        double newTotal = 0.0; //prepare a variable for recount.
        for(SingleItem item : scannedItems){
            newTotal += item.getItemTotal().getValue();
        }
        this.totalPrice = new Amount(newTotal);
    }
}
