package baminsurances.data.generation;

import java.util.List;
import baminsurances.data.BoatInsurance;
import baminsurances.data.CarInsurance;
import baminsurances.data.Customer;
import baminsurances.data.Employee;
import baminsurances.data.HolidayHomeInsurance;
import baminsurances.data.HomeInsurance;
import baminsurances.data.Insurance;
import baminsurances.data.TravelInsurance;

/**
 * Provides methods for generating Insurance objects.
 * 
 * @author martin
 */
public class InsuranceGenerator {
    
    /**
     * Generates and returns an insurance's premium based on its amount.
     * 
     * @param amount the amount
     * @return a premium
     */
    public int generatePremium(int amount) {
        return (int) (Math.random() * amount) / 2; 
    }
    
    /**
     * Generates and returns an amount.
     * 
     * @return an amount
     */
    public int generateAmount() {
        return (int) (Math.random() * 5000) + 2000;
    }
    
    /*
    public String generateTerms(String insuranceType) {
        double x = Math.random();
        switch (insuranceType) {
            case "CAR":
                return carGen.generateTerms();
            case "BOAT":
                if (x < 0.2) {
                    return "Super";
                } else if (x < 0.6) {
                    return "Kasko";
                } else {
                    return "Delkasko";
                }
            case "HOME":
                String homeBasis = "Rettshjelp, brannskader, "
                        + "vann- og rørskader, tyveri og hærværk, naturskade";
                if (x < 0.4) {
                    return homeBasis + "kunstnerisk utsmykning, "
                            + "skadeverk dyr og insekter, sopp- og råteskade, "
                            + "følgeskader av håndverkerfeil.";
                } else {
                    return homeBasis + ".";
                }
            case "HOLIDAYHOME":
                if (x < 0.3) {
                    return homeBasis;
                } else {
                    return homeBasis;
                }
            case "TRAVEL":
                String travelBasis = "Reisesyke, ulykke, ansvarsforsikring, reisegods";
                if (x < 0.4) {
                    return travelBasis + ", avbestilling, forsinkelse, dyr.";
                } else {
                    return travelBasis + ".";
                }
            default:
                return null;
        }
    }*/
}
