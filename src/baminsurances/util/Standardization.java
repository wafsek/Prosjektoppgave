package baminsurances.util;

/**
 * A utility class used for standardizing user input.
 * <p>
 * For example, if the user types in the first name "ole jacob", it will be
 * standardized to "Ole Jacob". Another example is car registration numbers.
 * "AB 12345" will be turned into "AB12345".
 * <p>
 * All the methods assume that the arguments are on the right format. The right
 * format is defined by the class {@link baminsurances.api.Validation}.
 * 
 * @author Martin Jackson
 */
public class Standardization {
    
    /**
     * The constructor is private, because one should never have to instantiate
     * this class, as it only contains static methods.
     */
    private Standardization() {
    }
    
    /**
     * Helper method uses by the other methods that standardize names. It takes
     * a <em>single</em> name, capitalizes the first letter, and lowercases the
     * rest.
     * <p>
     * If the given name already is on the standardized form, it will come out
     * the same. 
     * 
     * @param name the name to standardize
     * @return a standardized version of the name
     */
    private static String standardizeName(String name) {
        char first = Character.toUpperCase(name.charAt(0));
        return (String.valueOf(first)
                + name.substring(1).toLowerCase()).trim();
    }
    
    /**
     * Takes a first name, and returns a standardized version of it.
     * <p>
     * More specifically, it makes the first letter in the name (or both, if
     * double first name) and capitalizes it. The method also turns every other
     * letter into lower case. E.g: "oLE JacOb" becomes "Ole Jacob".
     * <p>
     * If the given first name already is on the standardized form, it will
     * come out the same. 
     * 
     * @param firstName the first name to standardize
     * @return a standardized version of the given first name
     */
    public static String standardizeFirstName(String firstName) {
        int spaceIndex = firstName.trim().indexOf(' ');
        
        if (spaceIndex != -1) { // Double name
            return standardizeName(firstName.substring(0, spaceIndex)) + " "
                    + standardizeName(firstName.substring(spaceIndex + 1));
        } else {
            return standardizeName(firstName);
        }
    }
    
    /**
     * Takes a last name, and returns a standardized version of it.
     * <p>
     * More specifically, it takes the first letter in the name, capitalizes
     * it, and lowercases the rest.
     * <p>
     * If the given last name already is on the standardized form, it will
     * come out the same.
     * 
     * @param lastName the last name to standardize
     * @return a standardized version of the last name
     */
    public static String standardizeLastName(String lastName) {
        return standardizeName(lastName);
    }
    
    /**
     * Takes a street address, and returns a standardized version of it.
     * <p>
     * More specifically, it capitalizes the first letter in every word, and
     * lowercases the rest. The exception is the street number, where any
     * present letter is capitalized. E.g: "pileStreDet 35c " becomes
     * "Pilestredet 35C".
     * <p>
     * If the given street address already is on the standardized form, it will
     * come out the same.
     * 
     * @param streetAddress the street address to standardize
     * @return a standardized street address
     */
    public static String standardizeStreetAddress(String streetAddress) {
        StringBuilder builder = new StringBuilder();
        int firstSpaceIndex = streetAddress.trim().indexOf(' ');
        
        if (firstSpaceIndex != -1) {
            String streetName = standardizeName(
                    streetAddress.substring(0, firstSpaceIndex));
            builder.append(streetName);
            builder.append(" ");
            
            int secondSpaceIndex =
                    streetAddress.substring(
                            firstSpaceIndex + 1).trim().indexOf(' ');
            
            if (secondSpaceIndex != -1) {
                String streetNameSecondPart =
                        standardizeName(streetAddress.substring(
                                firstSpaceIndex + 1, secondSpaceIndex)); 
                builder.append(streetNameSecondPart);
                builder.append(" ");
                builder.append(streetAddress.substring(secondSpaceIndex + 1));
            } else {
                String lastPart = streetAddress.substring(firstSpaceIndex + 1);
                /* The part after the space is either the second part of the
                 * name, or a street number.
                 */
                boolean isStreetNumber = lastPart.chars()
                                                 .anyMatch(Character::isDigit);
                if (isStreetNumber) {
                    builder.append(lastPart.toUpperCase().trim());
                } else {
                    builder.append(standardizeName(lastPart));
                }
            }
        } else {
            builder.append(standardizeName(streetAddress));
        }
        return builder.toString();
    }
    
    /**
     * Takes an email, and returns a standardized version of it.
     * <p>
     * More specifically, it lowercases all letters, and keeps the rest
     * unchanged. E.g: "OLA.Nordmann@START.nO" becomes "ola.nordmann@start.no".
     * <p>
     * If the given email is on the standardized form, it will come out the
     * same.
     * 
     * @param email the email to standardize
     * @return a standardized version of the email
     */
    public static String standardizeEmail(String email) {
        return email.trim().toLowerCase();
    }
    
    /**
     * Takes a car registration number, and returns a standardized version of it.
     * <p>
     * More specifically, it uppercases both letters and removes any space
     * between the letters and the numbers. E.g: "aB 12345" becomes "AB12345".
     * <p>
     * If the given registration number is on the standardized form, it will
     * come out the same.
     * 
     * @param registrationNo the registration number to standardize
     * @return a standardized version of the registration number
     */
    public static String standardizeCarRegistrationNo(String registrationNo) {
        return registrationNo.replace(" ", "").toUpperCase();
    }
    
    /**
     * Takes a boat registration number, and returns a standardized version of it.
     * <p>
     * More specifically, it uppercases all three letters and removes any space
     * between the letters and the numbers. E.g: "aBc 123" becomes "ABC123".
     * <p>
     * If the given registration number is on the standardized form, it will
     * come out the same.
     * 
     * @param registrationNo the registration number to standardize
     * @return a standardized version of the registration number
     */
    public static String standardizeBoatRegistrationNo(String registrationNo) {
        return registrationNo.replace(" ", "").toUpperCase();
    }
}
