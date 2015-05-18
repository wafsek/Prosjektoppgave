package baminsurances.api;

import baminsurances.data.DataBank;
import baminsurances.logging.CustomLogger;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;


/**
 * Handles the serialization of the stored data.
 * 
 * @author Baljit Sarai 
 */
public class Serializer {
    CustomLogger logger = CustomLogger.getInstance();
    public void serialize(DataBank dataBank){

        try{
            FileOutputStream fileOut = new FileOutputStream(
                    Config.getDataBankFilePath());
            ObjectOutputStream oos = new ObjectOutputStream(fileOut);
            oos.writeObject(dataBank);
            oos.close();
            logger.log("Data Saved", Level.INFO);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
