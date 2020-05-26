package se.kth.iv1350.model;

import se.kth.iv1350.integration.ItemDTO;

/**
 * @author Alexander Broms
 * @version 1.1
 * Written 2020-05-26
 *
 * The class representing one or more items of a single type of {@link ItemDTO}.
 */
public class SingleItem {
    private final ItemDTO itemDTO;
    private int quantity;
    private Amount itemTotal;

    /**
     * The constructor
     * @param itemDTO the item which an instance will be concerning.
     * @param quantity the number of items of the {@code itemDTO} type.
     */
    public SingleItem(ItemDTO itemDTO, int quantity){
        this.itemDTO = itemDTO;
        this.quantity = quantity;
        this.itemTotal = new Amount(itemDTO.getItemPrice().getValue() * quantity);
    }

    /**
     * Calculates the {@link Amount} representing how much of the item total is VAT.
     * @return an {@link Amount} representing the total vat on the items of a certain type.
     */
    public Amount calculateVAT(){
        double amountVAT;
        amountVAT = itemTotal.getValue() * itemDTO.getItemVAT();
        return new Amount(amountVAT).roundOff(amountVAT);
    }

    //getters

    /**
     * Returns the {@link ItemDTO} of the {@code SingleItem}
     * @return the item type represented as an {@link ItemDTO}
     */
    public ItemDTO getItemDTO(){
        return itemDTO;
    }

    /**
     * Returns the quantity of an {@link ItemDTO} in {@link SingleItem}.
     * @return the quantity of an {@link ItemDTO}
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * Returns the total price of the items of a certain {@link ItemDTO}.
     * @return the total price of the items of the same type.
     */
    public Amount getItemTotal(){
        return itemTotal;
    }

    /**
     * Returns the item ID of the {@link ItemDTO} associated with the {@link SingleItem}.
     * @return the item ID
     */
    public int getItemID(){
        return getItemDTO().getItemID();
    }


    //setters

    /**
     * Set an entirely new {@code quantity} to an instance of the {@link SingleItem}.
     * @param newQuantity the new quantity of said item
     */
    public void setQuantity(int newQuantity){
        this.quantity = newQuantity;
    }

    /**
     * Increase the quantity of {@code SingleItem} by {@code quantity}.
     * @param quantity what we should increase the quantity with
     */
    public void increaseQuantity(int quantity) {
        this.quantity = this.quantity + quantity;
    }

    /**
     * Updates the total price of the items of type {@link ItemDTO}.
     */
    public void increaseItemTotal(){
        this.itemTotal.setValue(this.itemDTO.getItemPrice().getValue() * quantity);
    }
}
