package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.ExternalSystemHandler;
import se.kth.iv1350.view.View;

import java.util.Scanner;

/**
 * @author Alexander Broms
 * @version 2.0
 * Written 2020-05-29
 * The Main class.
 * Now with code moved to the view and with user interactivity.
 */
public class Main {
    /**
     * The main method. Only the view needs to be called from the main method.
     * @param args potential arguments given when executing the program.
     */
    public static void main(String[] args){
        ExternalSystemHandler externalSystems = new ExternalSystemHandler();
        Controller controller = new Controller(externalSystems);
        View view = new View(controller, new Scanner(System.in));
        view.startNewSale("Pickle Rick");
    }
}
