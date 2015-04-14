package baminsurances.data;

import java.util.Calendar;

/**
 * The root class in the Insurance hierarchy.
 * 
 * @author martin
 */
public class Insurance {
    private static int nextInsuranceNo = 1;
    private int insuranceNo;
    private Employee employee;
    private long premium;
    private long amount;
    private Calendar creationDate;
    private Calendar cancellationDate;
    private String terms;
    //TODO skademeldinger
    
    public static int getNextInsuranceNo() {
        return nextInsuranceNo;
    }
    
    public static void setNextInsuranceNo(int nextInsuranceNo) {
        Insurance.nextInsuranceNo = nextInsuranceNo;
    }
    
    public long getPremium() {
        return premium;
    }
    
    public void setPremium(long premium) {
        this.premium = premium;
    }
    
    public long getAmount() {
        return amount;
    }
    
    public void setAmount(long amount) {
        this.amount = amount;
    }
    
    public String getTerms() {
        return terms;
    }
    
    public void setTerms(String terms) {
        this.terms = terms;
    }
    
    public int getInsuranceNo() {
        return insuranceNo;
    }
    
    public Employee getEmployee() {
        return employee;
    }
    
    public Calendar getCreationDate() {
        return creationDate;
    }
    
    public Calendar getCancellationDate() {
        return cancellationDate;
    }
}
