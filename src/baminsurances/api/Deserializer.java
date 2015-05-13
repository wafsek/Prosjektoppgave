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

        DataBank databank;

        try {
            File file = new File("data_bank.ser");
            if(!file.exists()) {
                file.createNewFile();
            }
            FileInputStream fin = new FileInputStream("data_bank.ser");
            ObjectInputStream ois = new ObjectInputStream(fin);
            databank = (DataBank) ois.readObject();
            ois.close();
            return databank;
        } catch(EOFException ex) {
            //This exception is thrown because the file is empty and has just been created.
            return null;
        } catch(IOException ex) {
            ex.printStackTrace();
            return null;
        }catch (ClassNotFoundException cnfex){
            cnfex.printStackTrace();
            return null;
        }catch (Exception ex){
            return null;
        }
    }
}
