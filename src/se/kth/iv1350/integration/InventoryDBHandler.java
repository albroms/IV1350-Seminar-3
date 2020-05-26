package se.kth.iv1350.integration;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.Receipt;
import se.kth.iv1350.model.SingleItem;

import java.util.ArrayList;

/**
 * @author Alexander Broms
 * @version 1.2
 * Written 2020-05-27
 *
 * This class represents a system that communicates with a database for an inventory of items.
 * Since the data layer was omitted, the class contains an {@link ArrayList} representing the database.
 */
public class InventoryDBHandler {
    private ArrayList<SingleItem> inventory;

    /**
     * This constructor creates an instance of the class with a small inventory we can use
     * when there is no actual database to call.
     */
    public InventoryDBHandler(){
        ItemDTO firstItem = new ItemDTO("Flaming Hot Cheese Snack", new Amount(1.99), "200g bag of spicy cheese snack.", 1, 0.25);
        ItemDTO secondItem = new ItemDTO("Dried Shrimp", new Amount(5.99), "200g bag of dried shrimp", 2, 0.25);
        ItemDTO thirdItem = new ItemDTO("Whole Fat Milk", new Amount(3), "1 liter carton of whole fat milk", 3, 0.12);
        ItemDTO fourthItem = new ItemDTO("Low-tax Water Bottle", new Amount(1), "A 50cl bottle of water", 4, 0.06);

        inventory = new ArrayList<SingleItem>();
        inventory.add(new SingleItem(firstItem, 4));
        inventory.add(new SingleItem(secondItem, 3));
        inventory.add(new SingleItem(thirdItem, 2));
        inventory.add(new SingleItem(fourthItem, 1));
    }

    /**
     * Searches the inventory for an item in the inventory that matches
     * the {@code itemID} given.
     * @param itemID the itemID for which we want to find a {@code singleItem}
     * @return An item that has a matching {@code itemID}, null if no match was found.
     */
    public SingleItem findItem(int itemID){
        for(SingleItem item : inventory){
            if(itemIDsMatch(item.getItemID(), itemID)){
                return item;
            }
        }
        return null;
    }

    /**
     * Return the entire inventory list.
     * @return the {@link ArrayList} that makes up the inventory.
     */
    public ArrayList<SingleItem> getInventory(){
        return inventory;
    }

    /**
     * Update the inventory using the given receipt
     * @param receipt the receipt containing the information needed to update the inventory.
     */
    public void updateInventory(Receipt receipt){
        ArrayList<SingleItem> scannedItems = receipt.getSale().getScannedItems();
        for(SingleItem item : inventory){
            for(SingleItem scannedItem : scannedItems){
                if(item.getItemDTO().equals(scannedItem.getItemDTO())){
                    item.setQuantity(item.getQuantity() - scannedItem.getQuantity());
                    break;
                }
            }
        }
    }

    private boolean itemIDsMatch(int firstID, int secondID){
        return firstID == secondID;
    }
}
