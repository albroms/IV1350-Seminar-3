package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.ExternalSystemHandler;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.view.View;

/**
 * @author Alexander Broms
 * @version 1.0
 * Written 2020-05-26
 * The Main class.
 */
public class Main {
    /**
     * The main method
     * @param args potential arguments given when executing the program.
     */
    public static void main(String[] args) {
        ExternalSystemHandler externalSystems = new ExternalSystemHandler();
        Controller controller = new Controller(externalSystems);
        View view = new View(controller);
        view.startNewSale("Jerry"); //sale started
        view.scanItem(1, 1); //first item scanned
        view.scanItem(1, 2); //scan same item again
        view.scanItem(4, 1); //scan another item
        //view.scanItem(123, 1); //invalid ID
        //view.requestDiscount(1111); //invalid customerID
        view.requestDiscount(123);
        view.endSale();
        view.enterPayment(new Amount(20)); //user pays
    }
}
