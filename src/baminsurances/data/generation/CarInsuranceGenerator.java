package baminsurances.data.generation;

import java.io.File;
import java.util.List;
import baminsurances.data.CarType;
import baminsurances.util.CsvReader;

/**
 * Provides methods for generating CarInsurance objects.
 * 
 * @author martin
 */
public class CarInsuranceGenerator {
    List<String> brands;
    
    public CarInsuranceGenerator() {
        String filepath =
                PersonGenerator.class.getClassLoader().getResource(
                        "bilmerker.csv").getPath();
        CsvReader reader = new CsvReader(new File(filepath), ";");
        brands = reader.getValuesInColumn(0);
    }
    
    /**
     * Generates and returns a string with terms for a car insurance.
     * 
     * @return a string with terms for a car insurance
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
     * Generates and returns a Norwegian car registration number.
     * 
     * @return a Norwegian car registration number
     */
    public String generateRegistrationNo() {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int index1 = (int) (Math.random() * alphabet.length);
        int index2 = (int) (Math.random() * alphabet.length);
        String digits = "";
        for (int i = 0; i < 5; i++) {
            digits += String.valueOf((int) (Math.random() * 10));
        }
        
        return String.valueOf(alphabet[index1]) +
               String.valueOf(alphabet[index2]) + " " +
               digits;
    }
    
    /**
     * Generates and returns a {@link CarType}.
     * 
     * @return a {@link CarType}
     */
    public CarType generateType() {
        CarType[] types = CarType.values(); 
        return types[(int) (Math.random() * types.length)];
    }
    
    /**
     * Generates and returns a car brand.
     * 
     * @return a car brand
     */
    public String generateBrand() {
        return brands.get((int) (Math.random() * brands.size()));
    }
    
    /**
     * Generates and retuns a car model.
     * 
     * @return a car model
     */
    public String generateModel() {
        // may not be actual models that match a car brand
        String[] models = {"GT-R", "Leaf", "Skyline", "200SX", "Pixo",
                "Primera", "BX", "LN", "CX", "Berlingo", "2CV", "DS3",
                "Xantia", "Escort", "Fiesta", "Focus", "Cosworth", "Fairlane",
                "Falcon", "RS200", "Transit", "Granada", "Lightning",
                "Cosworth", "Sierra", "Capri", "Orion", "Model T", "NSX",
                "CB-1", "Civic", "HR-V", "Jazz", "Merak", "Biturbo", "Shamal",
                "GT", "Corsa", "Commodore", "Insignia", "Kadett", "Diplomat",
                "Admiral", "Astra"};
        return models[(int) (Math.random() * models.length)];
    }
    
    /**
     * Generates and returns a registration year between 1990 (inclusive) and
     * 2015 (exclusive).
     * 
     * @return a registration year between 1985 (inclusive) and 2015
     * (exclusive)
     */
    public int generateRegistrationYear() {
        return (int) (Math.random() * 30) + 1985;
    }
    
    /**
     * Generates and returns a yearly mileage between 5000 (inclusive) and
     * 55000 (exclusive).
     * 
     * @return a yearly mileage between 5000 (inclusive) and 55000 (exclusive)
     */
    public int generateYearlyMileage() {
        // rounded down to closest 500 with integer division
        return (int) ((Math.random() * 50000) + 5000) / 500 * 500;
    }
    
    /**
     * Generates and returns a price per kilometer between 3 (inclusive) and
     * 12 (exclusive).
     * 
     * @return a price per kilometer between 3 (inclusive) and 12 (exclusive)
     */
    public double generatePricePerKilometer() {
        return (Math.random() * 9) + 3;
    }
    
    /**
     * Generates and returns a bonus percentage between 0 (inclusive) and 80
     * (inclusive).
     * 
     * @return a bonus percentage between 0 (inclusive) and 80 (inclusive)
     */
    public int generateBonus() {
        // rounded down to closest 5 with integer division
        return (int) (Math.random() * 81) / 5 * 5;
    }
}
