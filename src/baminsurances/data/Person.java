package baminsurances.data;

/**
 * The root class in the Person hierarchy.
 * 
 * @author martin
 */
public class Person {
    private String birthNo;
    private String firstName;
    private String lastName;
    private String telephoneNo;
    private String zipCode;
    private String streetAddress;
    
    /**
     * Creates a new person with the given values.
     * 
     * @param birthNo birth number
     * @param firstName first name
     * @param lastName last name
     * @param telephoneNo telephone number
     * @param zipCode zip code
     * @param streetAddress street address
     * @throws IllegalArgumentException if any of the arguments are not on the
     * right format, see setter-methods for more info on this
     * @throws NullPointerException if any of the arguments are null
     */
    public Person(String birthNo, String firstName, String lastName,
            String telephoneNo, String zipCode, String streetAddress) {
        setBirthNo(birthNo);
        setFirstName(firstName);
        setLastName(lastName);
        setTelephoneNo(telephoneNo);
        setZipCode(zipCode);
        setStreetAddress(streetAddress);
    }
    
    protected Person() {
    }
    
    @Override
    public boolean equals(Object obj) {
        return this.birthNo.equals(((Person) obj).getBirthNo());
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
     * Sets this person's birth number to the given value, which should adhere
     * to the rules of a Norwegian birth number.
     * <p>
     * See the following link for more information:
     * <a href="http://en.wikipedia.org/wiki/National_identification_number#Norway">
     * http://en.wikipedia.org/wiki/National_identification_number#Norway</a>
     * 
     * @param birthNo new birth number
     * @throws IllegalArgumentException if given birth number is not a valid
     * one
     * @throws NullPointerException if argument is null
     */
    public void setBirthNo(String birthNo) {
        if (birthNo == null) {
            throw new NullPointerException("Cannot set birth number to null.");
        }
        
        if (!birthNo.matches("[0-9]{11}")) {
            throw new IllegalArgumentException("Birth number should " +
                    "be 11 digits long. Found: " + birthNo);
        }
        
        if (!hasValidControllNos(birthNo)) {
            throw new IllegalArgumentException("One of the control " +
                "numbers did not pass verification."); 
        }
        
        this.birthNo = birthNo;
    }
    
    private boolean hasValidControllNos(String birthNo) {
        int controllNo1 = Character.getNumericValue(birthNo.charAt(9));
        int controllNo2 = Character.getNumericValue(birthNo.charAt(10));
        
        /* Norwegian birth numbers are on the following format:
         * 
         * d1 d2 m1 m2 y1 y2 i1 i2 i3 controllNo1 controllNo2
         * (spaces for visibility)
         * 
         * d = day, m = month, y = year, i = individual number
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
        
        boolean validControllNo1 = controllNo1 == 11 - (3*d1 + 7*d2 + 6*m1 +
                1*m2 + 8*y1 + 9*y2 + 4*i1 + 5*i2 + 2*i3) % 11;
        
        boolean validControllNo2 = controllNo2 == 11 - (5*d1 + 4*d2 + 3*m1 +
                2*m2 + 7*y1 + 6*y2 + 5*i1 + 4*i2 + 3*i3 + 2*controllNo1) % 11;
        
        return validControllNo1 && validControllNo2;
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
     * Sets this person's first name to the given value, which should be a
     * string at least 2 characters long, and consisting solely of spaces,
     * hyphens and letters from the Norwegian alphabet. 
     * 
     * @param firstName new first name
     * @throws IllegalArgumentException if argument is not on right format
     * @throws NullPointerException if argument is null
     */
    public void setFirstName(String firstName) {
        if (firstName == null) {
            throw new NullPointerException("First name cannot be null.");
        }
        
        if (!firstName.matches("[a-zæøåA-ZÆØÅ -]{2,}")) {
            throw new IllegalArgumentException("First name should only " +
                    "consist of letters and spaces in case of more than " +
                    "one first name. Found: " + firstName);
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
     * Returns this person's last name, which should be at least 2 characters
     * long, and consist solely of letters from the Norwegian alphabet.
     * 
     * @param lastName new last name
     * @throws IllegalArgumentException if given last name is not on right
     * format
     * @throws NullPointerException if argument is null
     */
    public void setLastName(String lastName) {
        if (lastName == null) {
            throw new NullPointerException("Last name cannot be null.");
        }
        
        if(!lastName.matches("[a-zæøåA-ZÆØÅ]{2,}")) {
            throw new IllegalArgumentException("First name should only " +
                    "consist of letters. Found: " + lastName);
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
     * Sets this person's telephone number to the given value, which should be
     * a positive number of length 8.
     * 
     * @param telephoneNo new telephone number.
     * @throws IllegalArgumentException if given telephone number is not on
     * right format
     * @throws NullPointerException if argument is null
     */
    public void setTelephoneNo(String telephoneNo) {
        if (telephoneNo == null) {
            throw new NullPointerException("Telephone number cannot be null.");
        }
        
        if (!telephoneNo.matches("[0-9]{8}")) {
            throw new IllegalArgumentException("Telephone number " +
                    "should be a number of length 8. Found: " + telephoneNo);
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
     * Sets this person's zip code to the given value, which should be a
     * positive number of length 4.
     * 
     * @param zipCode new zip code
     * @throws IllegalArgumentException if given zip code is not on right
     * format
     * @throws NullPointerException if argument is null
     */
    public void setZipCode(String zipCode) {
        if (zipCode == null) {
            throw new NullPointerException("Zip code cannot be null.");
        }
        
        if (!zipCode.matches("[0-9]{4}")) {
            throw new IllegalArgumentException("Zip code should " +
                    "be a number of length 4. Found: " + zipCode);
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
        //TODO handle wrong input
        if (streetAddress == null) {
            throw new NullPointerException("Street address cannot be null.");
        }
        this.streetAddress = streetAddress;
    }
}
