package baminsurances.controller;

import baminsurances.data.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by baljit on 17.05.2015.
 */
public class StatisticController {
    
    static DataBank  dataBank = DataBank.getInstance();
    
    public static Map<Class<? extends Insurance>, Integer> numInsurancesPerType() {
        Map<Class<? extends Insurance>, Integer> result = new HashMap<>();
        
        /* Initializing all keys with a value of 0, so that they are included
         * even if no insurance exists of that type.
         */
        result.put(CarInsurance.class, 0);
        result.put(BoatInsurance.class, 0);
        result.put(HomeInsurance.class, 0);
        result.put(HolidayHomeInsurance.class, 0);
        result.put(TravelInsurance.class, 0);

        for (Customer cus : dataBank.getCustomerList()) {
            for (Insurance ins : cus.getInsurances()) {
                Class<? extends Insurance> type = ins.getClass();
                result.put(type, result.get(type) + 1);
            }
        }
        return result;
    }
    
    
}
