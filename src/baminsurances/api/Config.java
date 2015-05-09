package baminsurances.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A class that manages different configurations of the software.
 * 
 * @author Baljit Sarai
 */
public class Config {
    
    private static String applicationName;
    private static String dataBankFilePath;
    
    private static int number;
    
    /**
     * Returns the properties for this config.
     * 
     * @return the properties for this config
     */
    private static Properties getProperties() {
        Properties prop = new Properties();
        try (InputStream inputStream =
                Config.class.getResourceAsStream("config.properties")) {
            prop.load(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }
    
    /**
     * Returns the application's name. Will among other things be shown in the
     * window title.
     * 
     * @returnt the application's name
     */
    public static String getApplicationName() {
        return getProperties().getProperty("applicationName");
    }
    
    /**
     * Returns the current console output option.
     * 
     * @return the current console output option
     */
    public static String getConsoleOutputOption() {
        return getProperties().getProperty("consoleOutput");
    }
    
    /**
     * Returns the file path of the data bank.
     * 
     * @return the file path of the data bank
     */
    public static String getDataBankFilePath() {
        return getProperties().getProperty("dataBankFilePath");
    }
    
    /**
     * Returns a string with the current logging level.
     * 
     * @return the current logging level
     */
    public static String getLoggingLevelString() {
        return getProperties().getProperty("loggingLevel");
    }
}
