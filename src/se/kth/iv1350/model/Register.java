package se.kth.iv1350.model;

import java.util.ArrayList;

/**
 * @author Alexander Broms
 * @version 2.0
 * Written 2020-06-01
 *
 * The class representing a cash register. When instantiated it is loaded with an amount of money.
 * The public interface for this class include the constructor, the {@code pay} method, and the getter
 * which returns the {@link Amount} of money currently in the register.
 */
public class Register {
    private Amount moneyInRegister;
    private final Amount moneyOriginallyInRegister;
    private ArrayList<RevenueObserver> revenueObservers;
    private Amount totalRevenue;

    /**
     * Create an instance of Register loaded with an amount of money.
     * @param amount The amount of money to load the register with.
     */
    public Register(double amount){
        this.moneyInRegister = new Amount(amount);
        this.moneyOriginallyInRegister = new Amount(amount);
        revenueObservers = new ArrayList<>();
    }

    /**
     * Create an instance of Register pre-loaded with 1000 credits.
     */
    public Register(){
        this.moneyInRegister = new Amount(1000);
        this.moneyOriginallyInRegister = new Amount(moneyInRegister.getValue());
        revenueObservers = new ArrayList<>();
    }

    /**
     * Returns the {@link Amount} of money the register currently contains.
     * @return the {@link Amount} of money in the register
     */
    public Amount getMoneyInRegister(){
        return moneyInRegister;
    }

    /**
     * Get the {@link Amount} that was in the register from the start.
     * @return The {@link Amount} of money that was in the register when the register was created.
     */
    public Amount getMoneyOriginallyInRegister(){ return moneyOriginallyInRegister; }

    /**
     * Handle the payment of a given sale using the amount received by the customer.
     * Once the change has been calculated, add and remove the right amount of money from
     * the register.
     * @param amountReceived the {@link Amount} received by the customer.
     * @param saleToPayFor the {@link Sale} which is to be paid for.
     * @return the change to be given to the customer
     */
    public Amount pay(Amount amountReceived, Sale saleToPayFor){
        Amount amountDue = saleToPayFor.getTotalPrice();
        Amount changeDue = calculateChange(amountReceived, amountDue);

        addAmount(amountReceived);
        removeAmount(changeDue);
        this.totalRevenue = calculateChange(moneyInRegister, moneyOriginallyInRegister);
        notifyObservers();
        return changeDue;
    }

    /**
     * Add the specified observer to the list of observers that will
     * be notified when the total revenue changes.
     * @param revObs add a specified {@link RevenueObserver} to the Register's list of revenue observers.
     */
    public void addRevenueObserver(RevenueObserver revObs){
        revenueObservers.add(revObs);
    }

    /**
     * All observers in the given list are added to {@link Register}'s list of observers.
     * Duplicates are ignored.
     * @param observers the list of revenue observers
     */
    public void addRevenueObservers(ArrayList<RevenueObserver> observers){
        for(RevenueObserver obs : observers){
            if(!revenueObservers.contains(obs)){
                addRevenueObserver(obs);
            }
        }
    }

    /**
     * Private methods below
     */
    private void removeAmount(Amount amountToRemove){
        if(amountToRemove.getValue() > 0){
            this.moneyInRegister = new Amount(this.moneyInRegister.getValue() - amountToRemove.getValue());
        }
    }
    private void addAmount(Amount amountToAdd){
        if(amountToAdd.getValue() > 0){
            this.moneyInRegister = new Amount(this.moneyInRegister.getValue() + amountToAdd.getValue());
        }
    }

    /**
     * Calculate the {@link Amount} of change to give to the customer after payment.
     * This method can also be used to calculate revenue by having the <code>received</code> parameter be
     * the amount of money currently in the register and the <code>due</code> parameter be the amount of
     * money that was in the register when the {@link Register} was instantiated.
     * @param received money received
     * @param due money subtracted from received
     * @return change or other difference between the values in the given parameters
     */
    private Amount calculateChange(Amount received, Amount due){
        double change = received.getValue() - due.getValue();
        Amount changeAmount = new Amount(change);
        return changeAmount.roundOff(change);
    }


    private void notifyObservers(){
        for(RevenueObserver obs : revenueObservers){
            obs.newTotalRevenue(totalRevenue);
        }
    }
}
