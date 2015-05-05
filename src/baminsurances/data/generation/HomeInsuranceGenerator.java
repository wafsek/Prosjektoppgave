package baminsurances.data.generation;

import baminsurances.data.HomeType;

/**
 * Provides methods for generating {@link HomeInsurance} objects.
 * 
 * @author martin
 */
public class HomeInsuranceGenerator {
    
    /**
     * Generates and returns terms for a home insurance.
     * 
     * @return terms for a home insurance
     */
    public String generateTerms() {
        String basis = "Rettshjelp, brannskader, "
                + "vann- og rørskader, tyveri og hærværk, naturskade";
        if (Math.random() < 0.4) {
            return basis + "kunstnerisk utsmykning, "
                    + "skadeverk dyr og insekter, sopp- og råteskade, "
                    + "følgeskader av håndverkerfeil.";
        } else {
            return basis + ".";
        }
    }
    
    /**
     * Generates and returns a construction year between 1960 (inclusive) and
     * 2015 (exclusive).
     * 
     * @return a construction year between 1960 (inclusive) and 2015
     * (exclusive)
     */
    public int generateConstructionYear() {
        return (int) (Math.random() * 55) + 1960;
    }
    
    /**
     * Generates and returns a {@link HomeType}.
     * 
     * @return a {@link HomeType}
     */
    public HomeType generateHomeType() {
        HomeType[] types = HomeType.values();
        return types[(int) (Math.random() * types.length)];
    }
    
    /**
     * Generates and returns a building material.
     * 
     * @return a building material
     */
    public String generateBuildingMaterial() {
        String[] materials = {"Tre", "Stein", "Metall", "Betong", "Gips"};
        return materials[(int) (Math.random() * materials.length)];
    }
    
    /**
     * Generates and returns a home standard.
     * 
     * @return a home standard
     */
    public String generateStandard() {
        String[] standards = {"Dårlig", "Normal", "Bedre", "Høy"};
        return standards[(int) (Math.random() * standards.length)];
    }
    
    /**
     * Generates and returns an area in square meters between 40 (inclusive)
     * and 500 (exclusive).
     * 
     * @return an area in square meters between 40 (inclusive) and 500
     * (exclusive)
     */
    public int generateSquareMeters() {
        return (int) (Math.random() * 460) + 40; 
    }
    
    /**
     * Generates and returns a home amount between 400 (inclusive) and 1400
     * (exclusive).
     * 
     * @return a home amount between 400 (inclusive) and 1400 (exclusive)
     */
    public int generateHomeAmount() {
        return (int) (Math.random() * 1000) + 400;
    }
    
    /**
     * Generates and returns a contents amount between 400 (inclusive) and 1400
     * (exclusive).
     * 
     * @return a contents amount between 400 (inclusive) and 1400 (exclusive)
     */
    public int generateContentsAmount() {
        return (int) (Math.random() * 1000) + 400;
    }
}
