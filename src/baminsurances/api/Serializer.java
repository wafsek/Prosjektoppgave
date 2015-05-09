package baminsurances.api;

import baminsurances.data.DataBank;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by baljit on 15.04.2015.
 * @author baljit 
 */
public class Serializer {

    public void serialize(DataBank dataBank){

        try{
            FileOutputStream fileOut = new FileOutputStream(Config.getDataBankFilePath());
            ObjectOutputStream oos = new ObjectOutputStream(fileOut);
            oos.writeObject(dataBank);
            oos.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
