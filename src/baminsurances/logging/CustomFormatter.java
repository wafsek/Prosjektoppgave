package baminsurances.logging;

import java.util.Date;
import java.util.logging.LogRecord;

/**
 * A custom formatter used for logging.
 * 
 * @author Baljit Sarai 
 */
public class CustomFormatter extends java.util.logging.Formatter {
    public CustomFormatter() { super(); }
    private static final String lineSep = System.getProperty("line.separator");
    
    @Override
    public String format(final LogRecord record) {
        String loggerName = record.getLoggerName();
        if(loggerName == null) {
            loggerName = "root";
        }
        StringBuilder output = new StringBuilder()
                .append("[")
                .append(record.getLevel()).append('|')
                .append(new Date(record.getMillis()))
                .append("]: ")
                .append(record.getSourceClassName())
                .append("| In Method -> "+record.getSourceMethodName())
                .append(" | ")
                .append(": ")
                .append(record.getMessage())
                .append(lineSep);
        return output.toString();
    }
}
