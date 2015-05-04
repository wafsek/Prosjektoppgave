package baminsurances.data.generation;

import baminsurances.data.BoatType;

/**
 * Provides methods for generating BoatInsurance objects.
 * 
 * @author martin
 */
public class BoatInsuranceGenerator {
    
    /**
     * Generates and returns a string with terms for a boat insurance.
     * 
     * @return a string with terms for a boat insurance
     */
    public String generateTerms() {
        double n = Math.random();
        if (n < 0.33) {
            return "Ansvar";
        } else if (n < 0.67) {
            return "Delkasko";
        } else {
            return "Kasko";
        }
    }
    
    /**
     * Generates and returns a Norwegian registration number for a boat.
     *  
     * @return a Norwegian registration number for a boat
     */
    public String generateRegistrationNo() {
        char[] legalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int index1 = (int) (Math.random() * legalLetters.length);
        int index2 = (int) (Math.random() * legalLetters.length);
        int index3 = (int) (Math.random() * legalLetters.length);
        String digits = "";
        for (int i = 0; i < 3; i++) {
            digits += String.valueOf((int) (Math.random() * 10));
        }
        
        return String.valueOf(legalLetters[index1]) +
               String.valueOf(legalLetters[index2]) +
               String.valueOf(legalLetters[index3]) + " " +
               digits;
    }
    
    /**
     * Generates and returns a {@link BoatType}.
     * 
     * @return a {@link BoatType}
     */
    public BoatType generateType() {
        BoatType[] types = BoatType.values();
        return types[(int) (Math.random() * types.length)];
    }
    
    /**
     * Generates and returns a boat brand.
     * 
     * @return a boat brand
     */
    public String generateBrand() {
        String[] brands = {"Brig", "Buster", "FinnMaster", "Grand", "Pioner",
                "Yamarin", "Steady", "Aquaquick", "GH", "Hasle",
                "Hasle Summer Fun", "Honda", "Kimple", "Mercury",
                "Oceanrunner", "Quicksilver", "River", "Suzumar", "Terhi",
                "Viking", "Walker Bay", "With", "Zodiac"};
        return brands[(int) (Math.random() * brands.length)];
    }
    
    /**
     * Generates and returns a boat model.
     * 
     * @return a boat model
     */
    public String generateModel() {
        // may not be actual models
        String[] models = {"620 F", "460-1", "530 F", "LS2", "RD 350 YPVS",
                "TZR", "8 Mini", "14 Active"};
        return models[(int) (Math.random() * models.length)];
    }
    
    /**
     * Generates and returns a length in feet between 10 (inclusive) and 40
     * (exclusive).
     * 
     * @return a length in feet between 10 (inclusive) and 40 (exclusive).
     */
    public int generateLengthInFeet() {
        return (int) (Math.random() * 30) + 10;
    }
    
    /**
     * Generates and returns a production year between 1970 (inclusive) and
     * 2015 (exclusive).
     * 
     * @return a production year between 1970 (inclusive) and 2015 (exclusive)
     */
    public int generateProductionYear() {
        return (int) (Math.random() * 35) + 1970;
    }
    
    /**
     * Generates and returns a motor type for a boat.
     * 
     * @return a motor type for a boat
     */
    public String generateMotorType() {
        String[] types = {"Innenbords", "Utenbords"};
        return types[(int) (Math.random() * types.length)];
    }
    
    /**
     * Generates and returns a horse power between (inclusive) and (exclusive).
     * 
     * @return a horse power between 5 (inclusive) and 50 (exclusive).
     */
    public int generateHorsePower() {
        return (int) (Math.random() * 45) + 5;
    }
}
