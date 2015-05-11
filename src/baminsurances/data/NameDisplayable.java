package baminsurances.data;

/**
 * An interface that is to be implemented by the different enums we use as data
 * fields in our classes.
 * <p>
 * Examples are {@link CarType}, {@link HomeType} and {@link PaymentFrequency}.
 * 
 * @author Martin Jackson
 */
public interface NameDisplayable {
    
    /**
     * Returns the display name.
     * 
     * @return the display name
     */
    public String getDisplayName();
}
