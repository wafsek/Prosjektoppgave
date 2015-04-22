package baminsurances.api;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Created by baljit on 22.04.2015.
 */
public class CustomFilter implements Filter {
    public boolean isLoggable(LogRecord record) {
        return  record.getLevel() == Level.SEVERE;
    }
}





