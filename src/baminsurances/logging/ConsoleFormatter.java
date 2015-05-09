package baminsurances.logging;

import java.util.Date;
import java.util.logging.LogRecord;

/**
 * A customer formatter for console output.
 * 
 * @author Baljit Sarai 
 */
public class ConsoleFormatter extends java.util.logging.Formatter {
    public ConsoleFormatter() { super(); }
    private static final String lineSep = System.getProperty("line.separator");
    
    @Override
    public String format(final LogRecord record) {
        String loggerName = record.getLoggerName();
        if(loggerName == null) {
            loggerName = "root";
        }
        StringBuilder output = new StringBuilder()
                .append(record.getMessage())
                .append(lineSep);
        return output.toString();
    }
}
