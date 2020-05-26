package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.Discount;
import se.kth.iv1350.model.SingleItem;

/**
 * @author Alexander Broms
 * @version 1.1
 * Written 2020-05-27
 *
 * A very simple representation of the view which sends requests (method calls) to a {@link Controller}.
 */
public class View {
    private Controller controller;

    /**
     * Constructor for the {@link View}.
     * @param controller the controller that the view interacts with.
     */
    public View(Controller controller){
        this.controller = controller;
    }


    /**
     * Begin transaction by welcoming the customer, giving the customer the name of the cashier,
     * and requesting that items are scanned.
     * @param cashierName
     */
    public void startNewSale(String cashierName){
        System.out.println("Sale started.\n");
        System.out.println("Welcome! My name is " + cashierName + ". Please scan your items.\n");
        controller.startSale(cashierName);
    }

    /**
     * Tells controller that an item with given itemID and quantity is being
     * scanned.
     * @param itemID identification number for item we are trying to scan.
     * @param quantity number of items with {@code itemID} we wish to scan.
     */
    public void scanItem(int itemID, int quantity){

        SingleItem scannedItem = controller.scanItem(itemID, quantity);
        System.out.println("Scanned " + quantity + " " + scannedItem.getItemDTO().getItemName());
        System.out.println("Running total: " + controller.getCurrentSale().getTotalPrice().getValue());
    }

    /**
     * Request a discount for a customer with a given {@code customerID}.
     * @param customerID the ID number provided by the customer.
     */
    public void requestDiscount(int customerID){
        System.out.println("\nDiscount requested with customer ID: " + customerID);
        Discount foundDiscount = controller.requestDiscount(customerID);
        if(foundDiscount != null){
            System.out.println("Discount found and applied!");
        }
        else {
            System.out.println("Could not find a discount for customer with ID: " + customerID);
        }
    }

    /**
     * View tells controller to end its current sale.
     */
    public void endSale(){
        Amount priceToPay = controller.endSale();
        System.out.println("\nSale ended. Your total is " + priceToPay.getValue() + " credits.");
    }

    /**
     * Sends an {@link Amount} to the controller to pay for a sale.
     * @param payment the {@link Amount} being used to pay.
     */
    public void enterPayment(Amount payment){
        System.out.println("Entered payment: " + payment.getValue());
        Amount change = controller.enterPayment(payment);
        if(change.getValue() >= 0) {
            System.out.println("\nPayment successful. Your change is " + change.getValue() + " credits.");
            System.out.println("Sales log and inventory have been updated.");
        }
        else {
            System.out.println("\nPayment unsuccessful. You are " + (change.getValue() * -1) + " credits short.");
        }
        System.out.println("There is now " + controller.getRegister().getMoneyInRegister().getValue() + " credits in the cash register.");
    }
}
