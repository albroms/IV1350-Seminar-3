package se.kth.iv1350.exceptions;

/**
 * An exception thrown when an item with a given item ID cannot be found.
 */
public class ItemNotFoundException extends Exception{
    private final int unknownItemID;

    /**
     * The constructor for this exception
     * @param unknownItemID the itemID which caused the exception to be thrown
     */
    public ItemNotFoundException(int unknownItemID){
        super("Unable to find item with ID " + unknownItemID + ".");
        this.unknownItemID = unknownItemID;
    }

    /**
     * Return the itemID that caused the exception to be thrown.
     * @return the itemID
     */
    public int getUnknownItemID(){
        return unknownItemID;
    }
}
