package baminsurances.api;

import baminsurances.data.InsuranceDataBank;

/**
 * Created by baljit on 14.04.2015.
 * @author baljit sarai
 */
public class InsuranceServiceManager {
    
    private InsuranceDataBank dataBank;
    
    //private static final InsuranceServiceManager manager = new InsuranceServiceManager();
    
    
    private InsuranceServiceManager(){
    }
    
    private void setInsuranceDataBank(InsuranceDataBank dataBank){
        this.dataBank = dataBank;
    }
    
    public InsuranceDataBank getInsuranceDataBank(){
        return this.dataBank;
    }
    
    public InsuranceServiceManager(InsuranceDataBank dataBank){
        if(dataBank == null){
            throw new NullPointerException();
        }
        this.setInsuranceDataBank(dataBank);
    }
}
