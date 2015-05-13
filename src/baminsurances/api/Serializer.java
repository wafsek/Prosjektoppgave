package baminsurances.api;

import baminsurances.data.DataBank;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Handles the serialization of the stored data.
 * 
 * @author Baljit Sarai 
 */
public class Serializer {

    public void serialize(DataBank dataBank){

        try{
            FileOutputStream fileOut = new FileOutputStream(
                    Config.getDataBankFilePath());
            ObjectOutputStream oos = new ObjectOutputStream(fileOut);
            oos.writeObject(dataBank);
            oos.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
