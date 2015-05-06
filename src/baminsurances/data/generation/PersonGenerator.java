package baminsurances.data.generation;

import java.io.File;
import java.util.List;

import baminsurances.data.*;
import baminsurances.util.CsvReader;
import baminsurances.util.TxtReader;

/**
 * Provides methods for generating complete {@link Person} objects and its data fields.
 * The class uses external resources to generate these data, and they are
 * loaded in the constructor. 
 * 
 * @author martin
 */
public class PersonGenerator {
    private List<String> boysNames;
    private List<String> girlsNames;
    private List<String> lastNames;
    private List<String> zipCodes;
    private List<String> streetAddressStarts;
    private List<String> streetAddressEndings;
    
    /**
     * Creates a new Person object generator.
     */
    public PersonGenerator() {
        boysNames = loadBoysNames();
        girlsNames = loadGirlsNames();
        lastNames = loadLastNames();
        zipCodes = loadZipCodes();
        streetAddressStarts = loadStreetAddressStarts();
        streetAddressEndings = loadStreetAddressEndings();
    }
    
    public Person generatePerson() {
        String firstName = generateFirstName();
        String lastName = generateLastName();
        return new Person(
                generateBirthNo(),
                firstName,
                lastName,
                generateTelephoneNo(),
                generateEmail(firstName, lastName),
                generateZipCode(),
                generateStreetAddress());
    }
    
    private List<String> loadBoysNames() {
        String filepath =
                PersonGenerator.class.getClassLoader().getResource(
                        "guttenavn.csv").getPath();
        CsvReader reader = new CsvReader(new File(filepath), ";");
        return reader.getValuesInColumn(0);
    }
    
    private List<String> loadGirlsNames() {
        String filepath =
                PersonGenerator.class.getClassLoader().getResource(
                        "jentenavn.csv").getPath();
        CsvReader reader = new CsvReader(new File(filepath), ";");
        return reader.getValuesInColumn(0);
    }
    
    private List<String> loadLastNames() {
        String filepath =
                PersonGenerator.class.getClassLoader().getResource(
                        "etternavn.csv").getPath();
        CsvReader reader = new CsvReader(new File(filepath), ";");
        return reader.getValuesInColumn(1);
    }
    
    private List<String> loadZipCodes() {
        String filepath =
                PersonGenerator.class.getClassLoader().getResource(
                        "postnummer.csv").getPath();
        CsvReader reader = new CsvReader(new File(filepath), ";");
        return reader.getValuesInColumn(0);        
    }
    
    private List<String> loadStreetAddressStarts() {
        String startsFilepath =
                PersonGenerator.class.getClassLoader().getResource(
                        "gateadresse1.txt").getPath();
        TxtReader reader = new TxtReader(new File(startsFilepath));
        return reader.getLines();
    }
    
    private List<String> loadStreetAddressEndings() {
        String endingsFilepath =
                PersonGenerator.class.getClassLoader().getResource(
                        "gateadresse2.txt").getPath();
        TxtReader reader = new TxtReader(new File(endingsFilepath));
        return reader.getLines();
    }
    
    /**
     * Generates and returns a valid Norwegian birth number.
     * 
     * @return a valid Norwegian birth number
     */
    public String generateBirthNo() {
        int day = (int) (Math.random() * 28) + 1;
        String dayString = day < 10 ? "0" + String.valueOf(day) : String.valueOf(day);
        int month = (int) (Math.random() * 12) + 1;
        String monthString = month < 10 ? "0" + String.valueOf(month) : String.valueOf(month);
        int year = (int) (Math.random() * 99) + 1;
        String yearString = year < 10 ? "0" + String.valueOf(year) : String.valueOf(year);
        String dateOfBirth = dayString + monthString + yearString;
        
        String individualNoString;
        int controllNo1;
        int controllNo2;
        do {
            int individualNo = (int) (Math.random() * 999) + 1;
            String zeroes = "";
            if (individualNo < 10) {
                zeroes += "00";
            } else if (individualNo < 100) {
                zeroes += "0";
            }
            individualNoString = zeroes + String.valueOf(individualNo);
            
            controllNo1 = generateControllNo1(dateOfBirth + individualNoString);
            if (controllNo1 == 11) {
                controllNo1 = 0;
            }
            
            controllNo2 = generateControllNo2(dateOfBirth + individualNoString,
                    controllNo1);
            if (controllNo2 == 11) {
                controllNo2 = 0;
            }
            
        } while (controllNo1 == 10 || controllNo2 == 10);
        
        return dateOfBirth + individualNoString + String.valueOf(controllNo1) +
                String.valueOf(controllNo2);
    }
    
    /**
     * Uses the first 9 digits of a Norwegian birth number to generate and
     * return the first controll number (the 10th digit).
     * 
     * @param birthNo the first 9 digits of a Norwegian birth number
     * @return the first controll number (the 10th digit)
     */
    private int generateControllNo1(String birthNo) {
        int d1 = Character.getNumericValue(birthNo.charAt(0));
        int d2 = Character.getNumericValue(birthNo.charAt(1));
        int m1 = Character.getNumericValue(birthNo.charAt(2));
        int m2 = Character.getNumericValue(birthNo.charAt(3));
        int y1 = Character.getNumericValue(birthNo.charAt(4));
        int y2 = Character.getNumericValue(birthNo.charAt(5));
        int i1 = Character.getNumericValue(birthNo.charAt(6));
        int i2 = Character.getNumericValue(birthNo.charAt(7));
        int i3 = Character.getNumericValue(birthNo.charAt(8));
        
        return 11 - (3*d1 + 7*d2 + 6*m1 + 1*m2 + 8*y1 + 9*y2 + 4*i1 + 5*i2 +
                2*i3) % 11;
    }
    
    /**
     * Uses the first 10 digits of a Norwegian birth number to generate and
     * return the last controll number (the 11th digit).
     * 
     * @param birthNo the first 9 digits of a Norwegian birth number
     * @param controllNo1 the first controll number (and the 10th digit) in a
     * Norwegian number
     * @return the last controll number (the 11th digit)
     */
    private int generateControllNo2(String birthNo, int controllNo1) {
        int d1 = Character.getNumericValue(birthNo.charAt(0));
        int d2 = Character.getNumericValue(birthNo.charAt(1));
        int m1 = Character.getNumericValue(birthNo.charAt(2));
        int m2 = Character.getNumericValue(birthNo.charAt(3));
        int y1 = Character.getNumericValue(birthNo.charAt(4));
        int y2 = Character.getNumericValue(birthNo.charAt(5));
        int i1 = Character.getNumericValue(birthNo.charAt(6));
        int i2 = Character.getNumericValue(birthNo.charAt(7));
        int i3 = Character.getNumericValue(birthNo.charAt(8));
        
        return 11 - (5*d1 + 4*d2 + 3*m1 + 2*m2 + 7*y1 + 6*y2 + 5*i1 + 4*i2 +
                3*i3 + 2*controllNo1) % 11;
    }
    
    /**
     * Generates and returns a first name. Has a 50% chance of generating a
     * boys name, and a 50% chance of generating a girls name.
     * 
     * @return a first name
     */
    public String generateFirstName() {
        if (Math.random() < 0.5) {
            return generateBoysName();
        } else {
            return generateGirlsName();
        }
    }
    
    /**
     * Generates and returns a boys name.
     * 
     * @return a boys name
     */
    public String generateBoysName() {
        return boysNames.get((int) (Math.random() * boysNames.size()));
    }
    
    /**
     * Generates and returns a girls name.
     * 
     * @return a girls name
     */
    public String generateGirlsName() {
        return girlsNames.get((int) (Math.random() * girlsNames.size()));
    }
    
    /**
     * Generates and returns a last name.
     * 
     * @return a last name
     */
    public String generateLastName() {
        return lastNames.get((int) (Math.random() * lastNames.size()));
    }
    
    /**
     * Generates and returns a Norwegian telephone number.
     * 
     * @return a Norwegian telephone number
     */
    public String generateTelephoneNo() {
        String telephoneNo = "";
        
        // first digit can't be 0
        telephoneNo += String.valueOf((int) (Math.random() * 9) + 1);
        
        for (int i = 1; i < 8; i++) {
            telephoneNo += String.valueOf((int) (Math.random() * 10));
        }
        return telephoneNo;
    }
    
    /**
     * Uses a first name and last name to generate and return an email address.
     * 
     * @param firstName the first name
     * @param lastName the last name
     * @return an email address
     */
    public String generateEmail(String firstName, String lastName) {
        String[] hosts = {"hotmail", "gmail", "start", "msn", "yahoo",
                "online", "aol", "outlook"};
        String[] domains = {"no", "com"};
        
        firstName = firstName.toLowerCase()
                             .replace('ø', 'o')
                             .replace('å', 'a')
                             .replace("æ", "ae");
        lastName = lastName.toLowerCase()
                           .replace('ø', 'o')
                           .replace('å', 'a')
                           .replace("æ", "ae");
        
        String host = hosts[(int) (Math.random() * hosts.length)];
        String domain = domains[(int) (Math.random() * domains.length)];
        return firstName + "." + lastName + "@" + host + "." + domain;
    }
    
    /**
     * Generates and returns a Norwegian zip code.
     * 
     * @return a Norwegian zip code
     */
    public String generateZipCode() {
        return zipCodes.get((int) (Math.random() * zipCodes.size()));
    }
    
    /**
     * Generates and returns a Norwegian street address.
     * 
     * @return a Norwegian street address
     */
    public String generateStreetAddress() {
        int number = (int) (Math.random() * 99) + 1;
        String letter = ""; // has to be a String because chars cant be empty
        
        // 20% chance of not containing a letter after number
        if (Math.random() > 0.2) {
            char[] letters = "ABCDEFGHI".toCharArray();
            letter = String.valueOf(
                    letters[(int) (Math.random() * letters.length)]);
        }
        
        return streetAddressStarts.get(
                   (int) (Math.random() * streetAddressStarts.size())) +
               streetAddressEndings.get(
                   (int) (Math.random() * streetAddressEndings.size())) + " " +
               String.valueOf(number) + letter;
    }
}
