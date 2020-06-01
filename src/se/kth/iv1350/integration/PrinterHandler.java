package se.kth.iv1350.integration;

import se.kth.iv1350.model.Receipt;
import se.kth.iv1350.model.Sale;
import se.kth.iv1350.model.SingleItem;

import java.util.ArrayList;

/**
 * @author Alexander Broms
 * @version 1.2
 * Written 2020-05-29
 *
 * Class intended for communication with a printer. Since there is no printer to communicate with.
 * The functionality has been included in this class.
 */
class PrinterHandler {
    /**
     * Method responsible for printing the receipt
     * @param receipt the object containing the relevant data for the printer.
     */
    void printReceipt(Receipt receipt){
        System.out.println("\nYour receipt:");
        System.out.println(receipt.getStoreName());
        System.out.println(receipt.getStoreAddress());
        printSale(receipt.getSale());
        System.out.println("Amount paid: " + receipt.getAmountPaid().getValue() + " credits");
        System.out.println("Change given: " + receipt.getChange().getValue() + " credits");
    }

    private void printSale(Sale saleToPrint){
        System.out.println("Cashier: " + saleToPrint.getCashier());
        System.out.println("Date: " + saleToPrint.getTimeOfSale());
        System.out.println("Your items:");
        printScannedItems(saleToPrint);
        System.out.println("Total: " + saleToPrint.getTotalPrice().getValue() + " credits, Total VAT: " + saleToPrint.getTotalVATPrice().getValue() + " credits");
    }

    private void printScannedItems(Sale saleToPrint){
        ArrayList<SingleItem> itemsToPrint = saleToPrint.getScannedItems();
        for(SingleItem item : itemsToPrint){
            System.out.println(
                    item.getItemDTO().getItemName() + ": " + item.getQuantity() + " pcs"
                            + " Subtotal: " + item.getItemTotal().getValue() + " credits,"
                            + " VAT: " + item.calculateVAT().getValue() + " credits"
            );
        }
    }
}
