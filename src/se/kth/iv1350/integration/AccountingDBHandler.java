package se.kth.iv1350.integration;

import se.kth.iv1350.model.Receipt;
import java.util.ArrayList;

/**
 * @author Alexander Broms
 * @version 1.0
 * Written 2020-05-26
 *
 * This class represents a system that communicates with a database for an accounting system that keeps
 * track of sales records.
 * Since the data layer was omitted, the class contains an {@link ArrayList} representing the database.
 */
public class AccountingDBHandler {
    private ArrayList<Receipt> completedSales;

    /**
     * The constructor for the class. Once initialized, an empty accounting system is also initialized.
     */
    public AccountingDBHandler(){
        this.completedSales = new ArrayList<Receipt>();
    }

    /**
     * Place a completed sale in the list of completed sales.
     * @param completedSale the {@link Receipt} instance that represents a completed sale.
     */
    public void storeSale(Receipt completedSale){
        this.completedSales.add(completedSale);
        System.out.println("Completed sale added to accounting system.");
    }
}
