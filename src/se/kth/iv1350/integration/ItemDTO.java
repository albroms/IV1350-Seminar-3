package se.kth.iv1350.integration;

import se.kth.iv1350.model.Amount;

/**
 * @author Alexander Broms
 * @version 1.0
 * Written 2020-05-26
 *
 * The class representing a DTO for different items. This class is immutable. Quantities or other mutable attributes
 * are instead handled by {@link se.kth.iv1350.model.SingleItem}.
 */
public class ItemDTO {
    private final String itemName;
    private final Amount itemPrice;
    private final String itemDescription;
    private final int itemID;
    private final double itemVAT;

    /**
     * The constructor for the DTO
     * @param itemName the name of the item.
     * @param itemPrice the price of the item.
     * @param itemDescription a description of the item.
     * @param itemID the item's ID number.
     * @param itemVAT the VAT rate for the item.
     */
    public ItemDTO(String itemName, Amount itemPrice, String itemDescription, int itemID, double itemVAT){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.itemID = itemID;
        this.itemVAT = itemVAT;
    }


    //getters

    /**
     * Get the name of the itemDTO.
     * @return the name of the item.
     */
    public String getItemName(){
        return itemName;
    }

    /**
     * Get the {@link Amount} representing the price of a single itemDTO.
     * @return the price of a single item.
     */
    public Amount getItemPrice(){
        return itemPrice;
    }

    /**
     * Get the description for an itemDTO.
     * @return a description of the item.
     */
    public String getItemDescription(){
        return itemDescription;
    }

    /**
     * Get the ID of an itemDTO
     * @return the ID
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Get the VAT-rate of an itemDTO.
     * @return the VAT-rate
     */
    public double getItemVAT() {
        return itemVAT;
    }
}
