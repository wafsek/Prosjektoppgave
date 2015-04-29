package baminsurances.api;

/**
 * A utility class used for validation of several data types.
 * <p>
 * Some of these types are:
 * <ul>
 *  <li>Birth numbers</li>
 *  <li>Zip codes</li>
 *  <li>Phone numbers</li>
 *  <li>Names</li>
 *  <li>Addresses</li>
 * </ul>
 * <p>
 * To see a detailed guide for what's considered OK for each data type, see
 * their respective validator-methods.
 * 
 * @author martin
 */
public class Validation {
    
    /**
     * The constructor of this class is private, because one should never have
     * to instantiate it.
     */
    private Validation() {
    }
    
    /**
     * Returns <code>true</code> if the given gender is a valid one. It is
     * valid if it is either 'M' or 'F'. At time of writing (16th April 2015),
     * Norway does not recognize a third gender.
     * 
     * @param gender the gender to validate
     * @return <code>true</code> if the given gender is a valid one
     */
    public static boolean isValidGender(char gender) {
        return gender == 'M' || gender == 'F';
    }
    
    /**
     * Returns <code>true</code> if the given birth number is a valid one.
     * @param birthNo the birth number to validate
     * @return <code>true</code> if the given birth number is a valid one
     * @see <a href="http://no.wikipedia.org/wiki/F%C3%B8dselsnummer">Norwegian
     * Wikipedia</a> page for Norwegian birth numbers
     * @see <a href=http://en.wikipedia.org/wiki/National_identification_number#Norway>
     * English Wikipedia</a> page for Norwegian birth numbers (less detailed)
     */
    public static boolean isValidBirthNo(String birthNo) {
        return birthNoIsOnValidFormat(birthNo) &&
                birthNoHasValidControllNos(birthNo);
    }
    
    /**
     * Returns <code>true</code> if the given birth number is on the right
     * format, which is a number of 11 digits.
     * @param birthNo the birth number to validate
     * @return <code>true</code> if the given birth number is on the right
     * format
     * @see <a href="http://no.wikipedia.org/wiki/F%C3%B8dselsnummer">Norwegian
     * Wikipedia</a> page for Norwegian birth numbers
     * @see <a href=http://en.wikipedia.org/wiki/National_identification_number#Norway>
     * English Wikipedia</a> page for Norwegian birth numbers (less detailed)
     */
    public static boolean birthNoIsOnValidFormat(String birthNo) {
        return birthNo.matches("[0-9]{11}");
    }
    
    /**
     * Returns <code>true</code> if the given birth number's controll numbers
     * are valid ones.
     * <p>
     * A given birth number has valid controll numbers if:<br>
     *
     * Controll number 1 = 11 - ((3 × d1 + 7 × d2 + 6 × m1 + 1 × m2 + 8 × å1 +
     *                     9 × å2 + 4 × i1 + 5 × i2 + 2 × i3) mod 11)<br>
     *                     
     * Controll number 2 = 11 - ((5 × d1 + 4 × d2 + 3 × m1 + 2 × m2 + 7 × å1 +
     *                     6 × å2 + 5 × i1 + 4 × i2 + 3 × i3 + 2 × k1) mod 11).
     * 
     * 
     * @param birthNo the birth number to validate
     * @return <code>true</code> if the given birth number's controll numbers
     * are valid ones
     * @see <a href="http://no.wikipedia.org/wiki/F%C3%B8dselsnummer">Norwegian
     * Wikipedia</a> page for Norwegian birth numbers
     * @see <a href=http://en.wikipedia.org/wiki/National_identification_number#Norway>
     * English Wikipedia</a> page for Norwegian birth numbers (less detailed)
     */
    public static boolean birthNoHasValidControllNos(String birthNo) {
        int controllNo1 = Character.getNumericValue(birthNo.charAt(9));
        int controllNo2 = Character.getNumericValue(birthNo.charAt(10));
        
        /* Norwegian birth numbers are on the following format:
         * 
         * d1 d2 m1 m2 y1 y2 i1 i2 i3 controllNo1 controllNo2
         * (spaces for visibility)
         * 
         * d = day, m = month, y = year, i = individdual number
         */
        int d1 = Character.getNumericValue(birthNo.charAt(0));
        int d2 = Character.getNumericValue(birthNo.charAt(1));
        int m1 = Character.getNumericValue(birthNo.charAt(2));
        int m2 = Character.getNumericValue(birthNo.charAt(3));
        int y1 = Character.getNumericValue(birthNo.charAt(4));
        int y2 = Character.getNumericValue(birthNo.charAt(5));
        int i1 = Character.getNumericValue(birthNo.charAt(6));
        int i2 = Character.getNumericValue(birthNo.charAt(7));
        int i3 = Character.getNumericValue(birthNo.charAt(8));
        
        int controllNo1Result = 11 - (3*d1 + 7*d2 + 6*m1 + 1*m2 + 8*y1 + 9*y2 +
                4*i1 + 5*i2 + 2*i3) % 11;
        if (controllNo1Result == 11) {
            controllNo1Result = 0;
        } else if (controllNo1Result == 10) {
            return false;
        }
        
        int controllNo2Result = 11 - (5*d1 + 4*d2 + 3*m1 + 2*m2 + 7*y1 + 6*y2 +
                5*i1 + 4*i2 + 3*i3 + 2*controllNo1Result) % 11;
        if (controllNo2Result == 11) {
            controllNo2Result = 0;
        } else if (controllNo2Result == 10) {
            return false;
        }
        
        return controllNo1Result == controllNo1 && controllNo2Result == controllNo2;
    }
    
    /**
     * Returns <code>true</code> if the given first name is a valid one. For it
     * to be valid, it has to consist of one or two strings of text
     * @param firstName
     * @return <code>true</code> if the given first name is valid one
     */
    public static boolean isValidFirstName(String firstName) {
        return firstName.matches("[A-ZÆØÅ][a-zæøå]+([ -][A-ZÆØÅ][a-zæøå]+)?");
    }
    
    /**
     * Returns <code>true</code> if the given last name is a valid one. For it
     * to be valid, it has to consist of one leading uppercase letter, followed
     * by any number of lowercase letters. All the letters have to be in the
     * Norwegian alphabet.
     * 
     * @param lastName the last name to validate
     * @return <code>true</code> if the given last name is a valid one
     */
    public static boolean isValidLastName(String lastName) {
        return lastName.matches("[A-ZÆØÅ][a-zæøå]+");
    }
    
    /**
     * Returns <code>true</code> if the given zip code is a valid one. For it
     * to be valid, it has to be a positive integer of length 4.
     * 
     * @param zipCode the zip code to validate
     * @return <code>true</code> if the given zip code is a valid one
     */
    public static boolean isValidZipCode(String zipCode) {
        return consistsOnlyOfNumbers(zipCode) && isOfLength(zipCode, 4);
    }
    
    /**
     * Returns <code>true</code> if the given telephone number is a valid one.
     * For it to be valid, it has to be a positive integer of length 8.
     * 
     * @param telephoneNo the telephone number to validate
     * @return <code>true</code> if the given telephone number is a valid one
     */
    public static boolean isValidTelephoneNo(String telephoneNo) {
        return consistsOnlyOfNumbers(telephoneNo) && isOfLength(telephoneNo, 8);
    }
    
    /**
     * Returns <code>true</code> if the given value is a number.
     * 
     * @param value the value to validate
     * @return <code>true</code> if the given value is a number
     */
    public static boolean consistsOnlyOfNumbers(String value) {
        return value.length() > 0 && value.chars()
                                          .mapToObj(i -> (char) i)
                                          .allMatch(Character::isDigit);
    }
    
    /**
     * Returns <code>true</code> if the given string is of the given length.
     * 
     * @param text the string to check
     * @param length the length to check for
     * @return <code>true</code> if the given string is of the given length
     */
    public static boolean isOfLength(String text, int length) {
        return text.length() == length;
    }
    
    /**
     * Returns <code>true</code> if the given street address is a valid one.
     * For it to be valid, it has to start with a capital letters, and can be
     * followed by numbers, hyphens, spaces and letters from the Norwegian
     * alphabet.
     * 
     * @param address the address to validate
     * @return <code>true</code> if the given address is a valid one
     */
    public static boolean isValidAddress(String address) {
        return address.matches("[A-ZÆØÅ][a-zæøå0-9 -]");
    }
    
    /**
     * Returns <code>true</code> if the given string consists solely of
     * letters.
     * 
     * @param text the string to check
     * @return <code>true</code> if the given string consists solely of letters
     */
    public static boolean consistsOnlyOfLetters(String text) {
        return text.chars()
                   .mapToObj(i -> (char) i)
                   .allMatch(Character::isLetter);
    }
}
