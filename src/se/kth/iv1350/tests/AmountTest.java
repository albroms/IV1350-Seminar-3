package se.kth.iv1350.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.model.Amount;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {
    private Amount intAmount;
    private Amount doubleSameAsIntAmount;
    private Amount doubleAmountToRoundUp;
    private Amount doubleAmountToRoundDown;

    @BeforeEach
    void setUp() {
        intAmount = new Amount(1);
        doubleAmountToRoundUp = new Amount(1.999);
        doubleAmountToRoundDown = new Amount(0.001);
        doubleSameAsIntAmount = new Amount(1.0);
    }

    @AfterEach
    void tearDown() {
        intAmount = null;
        doubleAmountToRoundUp = null;
    }


    @Test
    void testSetValue() {
        Amount copyOfIntAmount = new Amount(1);
        intAmount.setValue(3);

        boolean expResult = false;
        boolean result = (intAmount.getValue() == copyOfIntAmount.getValue());
        assertEquals(expResult, result, "Value has not been properly updated");
    }

    @Test
    void testRoundUp() {
        Amount expAmount = new Amount(2.00);
        Amount actualAmount = doubleAmountToRoundUp.roundOff(doubleAmountToRoundUp.getValue());
        double expResult = expAmount.getValue();
        double result = actualAmount.getValue();
        assertEquals(expResult, result, "Did not round up to expected result.");
    }

    @Test
    void testRoundDown(){
        Amount expAmount = new Amount(0.0);
        Amount actualAmount = doubleAmountToRoundDown.roundOff(doubleAmountToRoundDown.getValue());
        double expResult = expAmount.getValue();
        double result = actualAmount.getValue();
        assertEquals(expResult, result, "Did not round down to expected result.");
    }

    @Test
    void testDifferentValueEntries(){
        boolean expResult = true;
        boolean result = (doubleSameAsIntAmount.getValue() == intAmount.getValue());
        assertEquals(expResult, result, "Values in the Amount objects did not match.");

    }
}