package baminsurances.data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The root class in the Person hierarchy.
 * 
 * @author Martin Jackson
 */
public class Person implements Comparable<Person>, Serializable{
    private static final long serialVersionUID = 6981784780352951569L;
    private String birthNo;
    private String firstName;
    private String lastName;
    private String telephoneNo;
    private String email;
    private String zipCode;
    private String streetAddress;
    
    /**
     * Creates a new person with the given values.
     * 
     * @param birthNo birth number
     * @param firstName first name
     * @param lastName last name
     * @param telephoneNo telephone number
     * @param email the email
     * @param zipCode zip code
     * @param streetAddress street address
     * @throws IllegalArgumentException if birthNo is not a number of length 11
     * @throws NullPointerException if any of the arguments are null
     */
    public Person(String birthNo, String firstName, String lastName,
            String telephoneNo, String email,String zipCode,
            String streetAddress) {
        setBirthNo(birthNo);
        setFirstName(firstName);
        setLastName(lastName);
        setTelephoneNo(telephoneNo);
        setEmail(email);
        setZipCode(zipCode);
        setStreetAddress(streetAddress);
    }
    
    /**
     * Returns <code>true</code>, if the given person has the same birth number
     * as this one.
     * 
     * @return <code>true</code>, if the given person has the same birth number
     * as this one
     */
    @Override
    public boolean equals(Object obj) {
        return this.birthNo.equals(((Person) obj).getBirthNo());
    }
    
    /**
     * Returns a hash code value for this person, equal to the person's birth
     * number.
     * 
     * @return a hash code value for this person, equal to the person's birth
     * number 
     */
    @Override
    public int hashCode() {
        return Integer.parseInt(birthNo);
    }
    
    /**
     * Compares this person with the give person, by comparing their birth
     * numbers. Returns a negative number if this person is less than the
     * given one, zero if it is equal, and positive if is greater.
     * 
     * @return a negative number if this person is less than the given one,
     * zero if it is equal, and positive if is greater
     */
    @Override
    public int compareTo(Person p) {
        return Integer.parseInt(this.birthNo) - Integer.parseInt(p.birthNo);
    }
    
    /**
     * Returns this person's date of birth.
     * 
     * @return this person's date of birth
     */
    public LocalDate getDateOfBirth() {
        int day = Integer.parseInt(birthNo.substring(0, 2));
        int month = Integer.parseInt(birthNo.substring(2, 4));
        int first2DigitsOfYear; // unknown for now
        int last2DigitsOfYear = Integer.parseInt(birthNo.substring(4, 6));
        
        int individualNo = Integer.parseInt(birthNo.substring(6, 9));
        
        /**
         * For information on this algorithm, see the following link (Norwegian):
         * 
         * http://www.skatteetaten.no/no/Person/Folkeregister/Fodsel-og-navnevalg/Barn-fodt-i-Norge/Fodselsnummer/
         */
        if (individualNo < 500) {
            first2DigitsOfYear = 19;
        } else if (individualNo < 750 &&
                last2DigitsOfYear >= 54 && last2DigitsOfYear <= 99) {
            first2DigitsOfYear = 18;
        } else if (individualNo >= 900 && individualNo < 1000 &&
                last2DigitsOfYear >= 40 && last2DigitsOfYear <= 99) { 
            first2DigitsOfYear = 19;
        } else {
            first2DigitsOfYear = 20;
        }
        int year = Integer.parseInt(
                String.valueOf(first2DigitsOfYear) + String.valueOf(last2DigitsOfYear));
        
        return LocalDate.of(year, month, day);
    }
    
    /**
     * Returns a char indicating what gender this person is.
     * 
     * @return 'F' if female, 'M' if male
     */
    public char getGender() {
        /*
         * At the time of writing (12th April 2015), Norway does
         * not recognize any other genders than male and female. This
         * means we could have made this method return a binary value.
         * Nevertheless, we have decided to return a char instead of
         * true or false, so that in the case a third gender is
         * recognized in the future, it will be easier to adapt to.
         */
        if (Character.getNumericValue(birthNo.charAt(8)) % 2 == 0) {
            return 'F';
        } else {
            return 'M';
        }
    }
    
    /**
     * Returns this person's birth number.
     * 
     * @return this person's birth number
     */
    public String getBirthNo() {
        return birthNo;
    }

    /**
     * Sets this person's birth number to the given value.
     * 
     * @param birthNo new birth number
     * @throws IllegalArgumentException if given birth number is not a number
     * of length 11
     * @throws NullPointerException if argument is null
     */
    public void setBirthNo(String birthNo) {
        if (birthNo == null) {
            throw new NullPointerException("Cannot set birth number to null.");
        }
        if (!birthNo.matches("[0-9]{11}")) {
            throw new IllegalArgumentException("Birth number should " +
                    "be 11 digits long. Found arg: " + birthNo);
        }
        this.birthNo = birthNo;
    }
    
    /**
     * Returns this person's first name.
     * 
     * @return this person's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets this person's first name to the given value. 
     * 
     * @param firstName new first name
     * @throws NullPointerException if argument is null
     */
    public void setFirstName(String firstName) {
        if (firstName == null) {
            throw new NullPointerException("First name cannot be null.");
        }
        this.firstName = firstName;
    }

    /**
     * Returns this person's last name.
     * 
     * @return this person's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns this person's last name.
     * 
     * @param lastName new last name
     * @throws NullPointerException if argument is null
     */
    public void setLastName(String lastName) {
        if (lastName == null) {
            throw new NullPointerException("Last name cannot be null.");
        }
        this.lastName = lastName;
    }

    /**
     * Returns this person's telephone number.
     * 
     * @return this person's telephone number.
     */
    public String getTelephoneNo() {
        return telephoneNo;
    }
    
    /**
     * Sets this person's email address to the given value.
     * 
     * @param email the new email
     * @throws NullPointerException if argument is null
     */
    public void setEmail(String email) {
        if (email == null) {
            throw new NullPointerException("Email cannot be null.");
        }
        this.email = email;
    }
    
    /**
     * Returns this person's email address.
     * 
     * @return this person's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets this person's telephone number to the given value.
     * 
     * @param telephoneNo new telephone number.
     * @throws NullPointerException if argument is null
     */
    public void setTelephoneNo(String telephoneNo) {
        if (telephoneNo == null) {
            throw new NullPointerException("Telephone number cannot be null.");
        }
        this.telephoneNo = telephoneNo;
    }

    /**
     * Returns the zip code for the area in which this person lives.
     * 
     * @return zip code for the area in which this person lives
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets this person's zip code to the given value.
     * 
     * @param zipCode new zip code
     * @throws NullPointerException if argument is null
     */
    public void setZipCode(String zipCode) {
        if (zipCode == null) {
            throw new NullPointerException("Zip code cannot be null.");
        }
        this.zipCode = zipCode;
    }

    /**
     * Returns this person's street address.
     * 
     * @return this person's street address.
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets this person's street address to the given value.
     * 
     * @param streetAddress new street address
     * @throws NullPointerException if argument is null
     */
    public void setStreetAddress(String streetAddress) {
        if (streetAddress == null) {
            throw new NullPointerException("Street address cannot be null.");
        }
        this.streetAddress = streetAddress;
    }
    
    @Override
    public String toString() {
        int age = LocalDate.now().getYear() - getDateOfBirth().getYear();
        return birthNo + ", " +
               firstName + " " + lastName + ", " +
               age + " years old, " +
               telephoneNo + ", " +
               email + ", " +
               zipCode + " " + streetAddress;
    }
}
