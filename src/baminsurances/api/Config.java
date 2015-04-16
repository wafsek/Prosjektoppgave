package baminsurances.api;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.File;

/**
 * Created by baljit  on 15.04.2015.
 * @author baljit sarai 
 */
public class Config{
    
    private static String applicationName;
    private static String dataBankFilePath;
    
    
    private static Properties getProperties(){
        Properties prop = new Properties();
        try(InputStream inputStream = Config.class.getResourceAsStream("config.properties")){
            prop.load(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return prop;
    }
    
    public static void setProperties(){
        applicationName = getProperties().getProperty("applicationName");
        dataBankFilePath = getProperties().getProperty("dataBankFilePath");
        
    }

    public static String getApplicationName(){
        return applicationName;
    }

    public static String getDataBankFilePath(){
        return dataBankFilePath;
    }
    
    
    
    
}
