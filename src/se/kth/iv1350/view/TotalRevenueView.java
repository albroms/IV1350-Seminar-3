package se.kth.iv1350.view;


import se.kth.iv1350.model.RevenueObserver;

/**
 * @author Alexander Broms
 * @version 0.1
 *
 * A view class responsible for displaying the total revenue earned while the program has been running.
 * The class should never call the controller or any other class, but instead be updated using the
 * Observer pattern. This view must show the total income on the user interface.
 */

public class TotalRevenueView implements RevenueObserver{

    @Override
    public void newTotalRevenue() {

    }
}