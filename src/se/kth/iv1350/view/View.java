package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.Amount;

/**
 * @author Alexander Broms
 * @version 1.0
 * Written 2020-05-26
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
        controller.scanItem(itemID, quantity);
    }

    /**
     * Request a discount for a customer with a given {@code customerID}.
     * @param customerID the ID number provided by the customer.
     */
    public void requestDiscount(int customerID){
        System.out.println("Discount requested with customer ID: " + customerID);
        controller.requestDiscount(customerID);
    }

    /**
     * View tells controller to end its current sale.
     */
    public void endSale(){
        Amount priceToPay = controller.endSale();
        System.out.println("\nSale ended. Your total is " + priceToPay.getAmount() + " credits.");
    }

    /**
     * Sends an {@link Amount} to the controller to pay for a sale.
     * @param payment the {@link Amount} being used to pay.
     */
    public void enterPayment(Amount payment){
        System.out.println("Entered payment: " + payment.getAmount());
        Amount change = controller.enterPayment(payment);
        if(change.getAmount() >= 0) {
            System.out.println("\nPayment successful. Your change is " + change.getAmount() + " credits.");
        }
        else {
            System.out.println("\nPayment unsuccessful. You are " + (change.getAmount() * -1) + " credits short.");
        }
    }
}
