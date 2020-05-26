package se.kth.iv1350.model;

/**
 * @author Alexander Broms
 * @version 1.0
 * Written 2020-05-26
 *
 * A class used to represent an amount in the POS system.
 * This is to avoid the use of primitive data types in other classes in the system.
 * Internally, the amount will be represented as a double.
 * It has two constructors so the class can handle integers and doubles as input when being instantiated.
 */
public class Amount {

    private double amount;

    /**
     * constructor used if given a double to specify amount
     * @param doubleAmount the amount that the object should contain
     */
    public Amount(double doubleAmount){
        this.amount = doubleAmount;
    }

    /**
     * constructor used if given an integer to specify amount
     * @param intAmount the amount that the object should contain
     */
    public Amount(int intAmount){
        this.amount = intAmount;
    }

    //getters
    /**
     * returns the double value held by an instance of {@link Amount}
     * @return the double value contained within the {@link Amount}
     */
    public double getAmount(){
        return amount;
    }

    //setters
    /**
     * Set the amount in an instance of {@link Amount}
     * @param amount the amount we wish to assign to the {@link Amount} instance.
     */
    public void setAmount(double amount){
        this.amount = amount;
    }

    /**
     * Same as the other {@code setAmount} method, but takes an integer instead.
     * @param amount the amount we wish to assign to the {@link Amount} instance.
     */
    public void setAmount(int amount){
        this.amount = amount;
    }
}
