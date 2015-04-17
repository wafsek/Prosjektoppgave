package baminsurances.api;

import baminsurances.data.InsuranceDataBank;

import java.io.*;

/**
 * Created by baljit on 15.04.2015.
 * @author baljit 
 */
public class Deserializer {

    public InsuranceDataBank deserialze(){

        InsuranceDataBank bokRegister;

        try{
            File file = new File(Config.getDataBankFilePath());
            if(!(file.exists()))
            {
                file.createNewFile();
            }
            FileInputStream fin = new FileInputStream(Config.getDataBankFilePath());
            ObjectInputStream ois = new ObjectInputStream(fin);
            bokRegister = (InsuranceDataBank) ois.readObject();
            ois.close();
            return bokRegister;

        }catch(EOFException ex)
        {
            //This exception is thrown because the file is empty and has just been created.
            return null;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
