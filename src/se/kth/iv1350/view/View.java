package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.exceptions.*;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.Discount;
import se.kth.iv1350.model.SingleItem;
import se.kth.iv1350.util.logger.ExceptionLogger;

import java.util.Scanner;

/**
 * @author Alexander Broms
 * @version 2.2
 * Written 2020-06-03
 *
 * A very simple representation of the view which sends requests (method calls) to a {@link Controller}.
 * Now featuring error handling for Seminar 4.
 */
public class View {
    private final Controller controller;
    private final Scanner in;

    /**
     * Constructor for the {@link View}.
     * @param controller the controller that the view interacts with.
     */
    public View(Controller controller, Scanner input){
        this.controller = controller;
        this.in = input;
        controller.addRevenueObserver(new TotalRevenueView());
    }


    /**
     * Start the sale process with the name of the cashier at the register.
     * @param cashierName the name of the cashier manning the register.
     */
    public void start(String cashierName) throws UnknownAnswerException {
        startNewSale(cashierName);
        System.out.println("Would you like to start another sale? y/n");
        String answer = in.next();
        if(isValidAnswer(answer)){
            if (answerIsYes(answer)){
                start(cashierName);
            }
            else {
                System.out.println("Okay. Closing the Point of Sale. Goodbye!");
                in.close();
            }
        }
        else {
            throw new UnknownAnswerException(answer);
        }

    }

    private void startNewSale(String cashierName){
        System.out.println("Sale started.\n");
        System.out.println("Welcome! I'm " + cashierName + ". Please scan your items.\n");
        controller.startSale(cashierName);
        try{
            scanItem();
        }
        catch (UnknownAnswerException e){
            System.out.println("I'm sorry, I can't understand what you're trying to say.");
            ExceptionLogger errLogger = controller.getErrorLogger();
            errLogger.logExceptionMessage(e);
        }
    }

    /**
     * Tells controller that an item will be scanned.
     * @throws UnknownAnswerException when the user input cannot be understood by the program
     */
    public void scanItem() throws UnknownAnswerException {
        int itemID;
        int quantity;
        String answer;
        try{
            System.out.println("Please enter the item identifier:");
            itemID = in.nextInt();
            System.out.println("Please enter the item quantity:");
            quantity = in.nextInt();
            SingleItem scannedItem = controller.scanItem(itemID, quantity);
            System.out.println("Scanned " + quantity + " " + scannedItem.getItemDTO().getItemName() + ": " + scannedItem.getItemTotal().getValue());
            System.out.println("Running total: " + controller.getCurrentSale().getTotalPrice().getValue());
            System.out.println("Would you like to scan another item? y/n");
            answer = in.next();
            if(isValidAnswer(answer)){
                if (answerIsYes(answer)) {
                    scanItem();
                } else {
                    System.out.println("Alright, let's carry on then.");
                    requestDiscount();
                    endSale();
                }
            }
            else{
                throw new UnknownAnswerException(answer);
            }
        }
        catch (OperationFailedException ofe){
            if(ofe.getException() instanceof ItemNotFoundException){
                System.out.println("\nSorry, I couldn't find an item with the ID you provided. Try scanning another item.");
                scanItem();
            }

            if(ofe.getException() instanceof DatabaseFailureException) {
                System.out.println("\nSorry, I couldn't connect to the database for some reason. Let's try again.");
                scanItem();
            }
        }
    }

    /**
     * Request a discount for a customer with a given customer ID.
     */
    private void requestDiscount(){
        //Scanner in = new Scanner(System.in);
        System.out.println("\nIf you're eligible for a discount, please enter your customer ID number now:");
        System.out.println("Enter '0' if you are ineligible for a discount.");
        int customerID = in.nextInt();
        if(customerID == 0){return;}
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
    private void endSale(){
        Amount priceToPay = controller.endSale();
        System.out.println("\nSale ended. Your total is " + priceToPay.getValue() + " credits.");
        enterPayment();
    }

    /**
     * Sends an {@link Amount} to the controller to pay for a sale.
     */
    private void enterPayment(){
        System.out.println("Please pay the required amount.");
        Amount payment = new Amount(in.nextDouble());
        System.out.println("Entered payment: " + payment.getValue());
        Amount change = controller.enterPayment(payment);
        if(change.getValue() >= 0) {
            System.out.println("\nPayment successful. Your change is " + change.getValue() + " credits. Thank You!");
            //System.out.println("Sales log and inventory have been updated."); //remove
        }
        else {
            System.out.println("\nPayment unsuccessful. You are " + (change.getValue() * -1) + " credits short.");
        }
        //System.out.println("There is now " + controller.getRegister().getMoneyInRegister().getValue() + " credits in the cash register."); //remove
    }

    private boolean isValidAnswer(String answer){
        return answer.equals("yes") || answer.equals("y") || answer.equals("no") || answer.equals("n");
    }
    private boolean answerIsYes(String answer){
        return answer.equals("yes") || answer.equals("y");
    }
}
