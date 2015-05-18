package baminsurances.api;

import baminsurances.data.DataBank;

import java.io.*;

/**
 * Handles the deserialization of the stored data.
 * 
 * @author Baljit Sarai 
 */
public class Deserializer {

    /**
     * Deserializes the file 'data_bank.ser' in the folder 'data', and returns
     * it. If not found, <code>null</code> is returned.
     * 
     * @return the deserialized data bank, or <code>null</code> if not found
     */
    public DataBank deserialize() {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("data/data_bank.ser"))) {
            return (DataBank) in.readObject();
        } catch (FileNotFoundException e) {
            /* Do nothing. If the method that calls this one receives a null,
             * it will take care of instantiating a new DataBank object.
             */
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
