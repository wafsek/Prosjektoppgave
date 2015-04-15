package baminsurances.api;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by baljit  on 15.04.2015.
 * @author baljit sarai 
 */
public class Config{
    
    private static String applicationName;
    private static String dataBankFilePath;
    
    
    private static Properties getProperties(){
        
        Properties prop = new Properties();
        InputStream input = null;

        try{

            input = new FileInputStream("C:\\workspace\\git-projects\\prosjektoppgave-programutvikling-2015\\src\\baminsurances\\api\\config.properties");
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
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
