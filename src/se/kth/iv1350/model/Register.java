package se.kth.iv1350.model;

/**
 * @author Alexander Broms
 * @version 1.0
 * Written 2020-05-26
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
        this.moneyInRegister = new Amount(this.moneyInRegister.getAmount() - amountToRemove.getAmount());
    }
    private void addAmount(Amount amountToAdd){
        this.moneyInRegister = new Amount(this.moneyInRegister.getAmount() + amountToAdd.getAmount());
    }
    private double calculateChange(double received, double due){
        double change = received - due;
        //smelly, also exists in Sale.java
        double roundOff = (double) Math.round(change * 100) / 100; //round off to two decimal places

        return roundOff;
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
     * @param amountReceived the {@link Amount} received by the customer.
     * @param saleToPayFor the {@link Sale} which is to be paid for.
     */
    public Amount pay(Amount amountReceived, Sale saleToPayFor){
        double received = amountReceived.getAmount();
        double amountDue = saleToPayFor.getTotalPrice().getAmount();
        double changeDue = calculateChange(received, amountDue);

        Amount moneyToAdd = new Amount(received);
        Amount moneyToRemove = new Amount(changeDue);

        //add and remove money from the register.
        addAmount(moneyToAdd);
        removeAmount(moneyToRemove);

        System.out.println("There is now " + moneyInRegister.getAmount() + " credits in the cash register");

        return new Amount(changeDue);
    }
}
