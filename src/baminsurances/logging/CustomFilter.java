package baminsurances.logging;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * A customer filter used for logging.
 * 
 * @author Baljit Sarai 
 */
public class CustomFilter implements Filter {
    public boolean isLoggable(LogRecord record) {
        return record.getLevel() == Level.SEVERE;
    }
}