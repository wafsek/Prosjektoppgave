package baminsurances.data;

/**
 * An interface that is to be implemented by the different enums we use as data
 * fields in our classes. Examples are {@link CarType}, {@HomeType} and
 * {@link TravelRegion}.
 * 
 * @author Martin Jackson
 */
public interface HasNorwegianTranslation {
    
    /**
     * Returns a Norwegian translation for this enum.
     * 
     * @return a Norwegian translation for this enum
     */
    public String getNorwegianTranslation();
}
