package se.kth.iv1350.integration;

import se.kth.iv1350.model.Discount;
import java.util.ArrayList;
/**
 * @author Alexander Broms
 * @version 1.1
 * Written 2020-05-26
 *
 * The class that handles calls to the discount database. Since the
 */
public class DiscountDBHandler {
    private ArrayList<Discount> validDiscounts;

    /**
     * Searches the list for a discount that is valid for the customer with the given {@code customerID}.
     * @param customerID The ID number the customer has provided.
     * @return A discount that is valid for the customer with the provided ID number.
     */
    public Discount getValidDiscount(int customerID){
        for(Discount discount : validDiscounts){
            if(discount.getCustomerID() == customerID){
                System.out.println("Discount found!");
                return discount;
            }
        }
        System.out.println("No discount was found for customer with id: " + customerID + ".");
        return null;
    }

    /**
     * This constructor creates a small hard-coded list of valid discounts.
     */
    public DiscountDBHandler(){
        this.validDiscounts = new ArrayList<Discount>();

        validDiscounts.add(new Discount(123, 0.75)); //25% discount on sale for customer 123
        validDiscounts.add(new Discount(124, 0.80)); //20% discount on sale for customer 124
        validDiscounts.add(new Discount(125, 0.95)); // 5% discount on sale for customer 125
    }
}
