package baminsurances.data.generation;

import java.io.File;
import java.util.List;

import baminsurances.data.*;
import baminsurances.util.CvsReader;

public class PersonGenerator {
    
    public Person generate() {
        return new Person(
                generateBirthNo(),
                generateFirstName(),
                generateLastName(),
                generateTelephoneNo(),
                generateZipCode(),
                "");
    }
    
    /**
     * Generates and returns a valid Norwegian birth number.
     * 
     * @return a valid Norwegian birth number
     */
    public static String generateBirthNo() {
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
    private static int generateControllNo1(String birthNo) {
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
    private static int generateControllNo2(String birthNo, int controllNo1) {
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
    public static String generateFirstName() {
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
    public static String generateBoysName() {
        String filepath =
                PersonGenerator.class.getClassLoader().getResource("guttenavn.csv").getPath();
        CvsReader reader = new CvsReader(new File(filepath), ";");
        List<String> boysNames = reader.getValuesInColumn(0);
        int index = (int) (Math.random() * boysNames.size());
        System.out.println("Guttenavn: " + boysNames.get(index));
        return boysNames.get(index);
    }
    
    /**
     * Generates and returns a girls name.
     * 
     * @return a girls name
     */
    public static String generateGirlsName() {
        String filepath =
                PersonGenerator.class.getClassLoader().getResource("jentenavn.csv").getPath();
        CvsReader reader = new CvsReader(new File(filepath), ";");
        List<String> girlsNames = reader.getValuesInColumn(0);
        int index = (int) (Math.random() * girlsNames.size());
        System.out.println("Jentenavn: " + girlsNames.get(index));
        return girlsNames.get(index);
    }
    
    /**
     * Generates and returns a last name.
     * 
     * @return a last name
     */
    public static String generateLastName() {
        String filepath =
                PersonGenerator.class.getClassLoader().getResource("etternavn.csv").getPath();
        CvsReader reader = new CvsReader(new File(filepath), ";");
        List<String> lastNames = reader.getValuesInColumn(1);
        int index = (int) (Math.random() * lastNames.size());
        System.out.println("Etternavn: " + lastNames.get(index));
        return lastNames.get(index);
    }
    
    public static String generateTelephoneNo() {
        String telephoneNo = "";
        for (int i = 0; i < 8; i++) {
            telephoneNo += String.valueOf((int) (Math.random() * 10));
        }
        System.out.println(telephoneNo);
        return telephoneNo;
    }
    
    public static String generateZipCode() {
        String filepath =
                PersonGenerator.class.getClassLoader().getResource("postnummer.csv").getPath();
        CvsReader reader = new CvsReader(new File(filepath), "\t");
        List<String> zipCodes = reader.getValuesInColumn(0);
        int index = (int) (Math.random() * zipCodes.size());
        System.out.println(zipCodes.get(index));
        return zipCodes.get(index);
    }
}
