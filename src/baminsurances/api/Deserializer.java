package baminsurances.api;

import baminsurances.data.DataBank;

import java.io.*;

/**
 * Handles the deserialization of the stored data.
 * 
 * @author Baljit Sarai 
 */
public class Deserializer {

    public DataBank deserialize() {

        DataBank bokRegister;

        try {
            File file = new File(Config.getDataBankFilePath());
            if(!(file.exists())) {
                file.createNewFile();
            }
            FileInputStream fin = new FileInputStream(Config.getDataBankFilePath());
            ObjectInputStream ois = new ObjectInputStream(fin);
            bokRegister = (DataBank) ois.readObject();
            ois.close();
            return bokRegister;
        } catch(EOFException ex) {
            //This exception is thrown because the file is empty and has just been created.
            return null;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
