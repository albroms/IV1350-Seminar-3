package se.kth.iv1350.model;

/**
 * @author Alexander Broms
 * @version 1.2
 * Written 2020-05-27
 *
 * The class representing a cash register. When instantiated it is loaded with an amount of money.
 * The public interface for this class include the constructor, the {@code pay} method, and the getter
 * which returns the {@link Amount} of money currently in the register.
 */
public class Register {
    private Amount moneyInRegister;

    /**
     * Create an instance of Register loaded with an amount of money.
     * @param amount The amount of money to load the register with.
     */
    public Register(double amount){
        this.moneyInRegister = new Amount(amount);
    }

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
    private Amount calculateChange(Amount received, Amount due){
        double change = received.getValue() - due.getValue();
        Amount changeAmount = new Amount(change);
        return changeAmount.roundOff(change);
    }

    /**
     * Returns the {@link Amount} of money the register currently contains.
     * @return the {@link Amount} of money in the register
     */
    public Amount getMoneyInRegister(){
        return moneyInRegister;
    }

    /**
     * Handle the payment of a given sale using the amount received by the customer.
     * Once the change has been calculated, add and remove the right amount of money from
     * the register.
     * @param amountReceived the {@link Amount} received by the customer.
     * @param saleToPayFor the {@link Sale} which is to be paid for.
     * @return the change to be given to the customer
     */
    public Amount pay(Amount amountReceived, Sale saleToPayFor){
        Amount received = amountReceived;
        Amount amountDue = saleToPayFor.getTotalPrice();
        Amount changeDue = calculateChange(received, amountDue);

        addAmount(received);
        removeAmount(changeDue);

        return changeDue;
    }
}
