package baminsurances.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import baminsurances.data.ClaimAdvice;
import baminsurances.data.DataBank;
import baminsurances.data.Insurance;

/**
 * The class that handles the writing and reading of saved data.
 * 
 * @author Martin Jackson
 */
public class SavedDataHandler {
    
    /**
     * Deserializes the file 'data_bank.ser' in the folder 'data', and returns
     * it. If not found, <code>null</code> is returned.
     * 
     * @return the deserialized data bank, or <code>null</code> if not found
     */
    public DataBank readDataBank() {
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
    
    /**
     * Serializes the data bank to the location 'data/data_bank.ser'.
     */
    public void writeDataBank() {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("data/data_bank.ser"))) {
            out.writeObject(DataBank.getInstance());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Reads and sets the next insurance number, saved at
     * 'data/next_insurance_no.dta'.
     */
    public void readNextInsuranceNo() {
        try (DataInputStream in = new DataInputStream(
                new FileInputStream("data/next_insurance_no.dta"))) {
            Insurance.setNextInsuranceNo(in.readInt());
        } catch (FileNotFoundException e) {
            Insurance.setNextInsuranceNo(1);
        } catch (IOException e) {
            e.printStackTrace();
            Insurance.setNextInsuranceNo(1);
        }
    }
    
    /**
     * Writes the next insurance number to 'data/next_insurance_no.dta'.
     */
    public void writeNextInsuranceNo() {
        try (DataOutputStream out = new DataOutputStream(
                new FileOutputStream("data/next_insurance_no.dta"))) {
            out.writeInt(Insurance.getNextInsuranceNo());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads and sets the next damage number for claim advices, saved at
     * 'data/next_damage_no.dta'.
     */
    public void readNextDamageNo() {
        try (DataInputStream in = new DataInputStream(
                new FileInputStream("data/next_damage_no.dta"))) {
            ClaimAdvice.setNextDamageNo(in.readInt());
        } catch (FileNotFoundException e) {
            ClaimAdvice.setNextDamageNo(1);
        } catch (IOException e) {
            e.printStackTrace();
            ClaimAdvice.setNextDamageNo(1);
        }
    }

    /**
     * Writes the next damage number for claim advices
     * to 'data/next_insurance_no.dta'.
     */
    public void writeNextDamageNo() {
        try (DataOutputStream out = new DataOutputStream(
                new FileOutputStream("data/next_damage_no.dta"))) {
            out.writeInt(ClaimAdvice.getNextDamageNo());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
