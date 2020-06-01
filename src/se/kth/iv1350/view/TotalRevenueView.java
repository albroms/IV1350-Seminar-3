package se.kth.iv1350.view;


import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.RevenueObserver;

/**
 * @author Alexander Broms
 * @version 0.1
 * Written 2020-06-01
 *
 * A view class responsible for displaying the total revenue earned while the program has been running.
 * The class should never call the controller or any other class, but instead be updated using the
 * Observer pattern. This view must show the total income on the user interface.
 */

public class TotalRevenueView implements RevenueObserver{

    /**
     * The method implemented from the RevenueObserver interface that prints the revenue
     * so far.
     * @param totalRevenue the total revenue to print
     */
    @Override
    public void newTotalRevenue(Amount totalRevenue) {
        printTotalRevenue(totalRevenue);
    }

    private void printTotalRevenue(Amount revenue){
        System.out.println("\n-----------Revenue Info----------");
        System.out.println("Total revenue earned so far:");
        System.out.println(revenue.getValue() + " credits");
        System.out.println("---------------------------------");
    }
}