package baminsurances.api;

/**
 * Created by baljit on 16.04.2015.
 * @author baljit sarai
 */
public class TimeCalculator {
    
    public static double millisecondsToDays(long milliseconds){
        return (milliseconds / (1000*60*60*24));
    }
    
}
