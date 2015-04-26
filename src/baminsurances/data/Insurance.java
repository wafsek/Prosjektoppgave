package baminsurances.data;

import java.util.Calendar;

/**
 * The root class in the Insurance hierarchy.
 * 
 * @author martin
 */
public abstract class Insurance {
    private static int nextInsuranceNo = 1;
    private int insuranceNo;
    private Employee employee;
    private long premium;
    private long amount;
    private Calendar creationDate;
    private Calendar cancellationDate = null;
    private String terms;
    //TODO skademeldinger

    /**
     * Creates a new insurance with the given values.
     * 
     * @param employee the employee who registered this insurance
     * @param premium
     * @param amount
     * @param terms
     */
    public Insurance(Employee employee, long premium, long amount,
            String terms) {
        insuranceNo = nextInsuranceNo++;
        this.employee = employee;
        this.premium = premium;
        this.amount = amount;
        creationDate = Calendar.getInstance();
        this.terms = terms;
    }
    
    /**
     * Returns <code>true</code>, if the given insurance has the same insurance
     * number as this insurance.
     * 
     * @return <code>true</code>, if the given insurance has the same insurance
     * number as this insurance
     */
    @Override
    public boolean equals(Object o) {
        return this.insuranceNo == ((Insurance) o).insuranceNo;
    }
    
    /**
     * Returns a hash code value for this insurance, equal to the insurance's
     * insurance number.
     * 
     * @return a hash code value for this insurance, equal to the insurance's
     * insurance number
     */
    @Override
    public int hashCode() {
        return insuranceNo;
    }
    
    /**
     * Returns the next insurance number for the Insurance class.
     * 
     * @return the next insurance number for the Insurance class.
     */
    public static int getNextInsuranceNo() {
        return nextInsuranceNo;
    }

    /**
     * Sets the next insurance number to be the given value. This method is
     * to be used in conjunction with reading insurances from a file. 
     * 
     * @param nextInsuranceNo the new insurance number
     */
    public static void setNextInsuranceNo(int nextInsuranceNo) {
        Insurance.nextInsuranceNo = nextInsuranceNo;
    }
    
    /**
     * Returns this insurance's annual premium.
     * 
     * @return this insurance's annual premium
     */
    public long getPremium() {
        return premium;
    }
    
    /**
     * Sets this insurance's annual premium to the given value.
     * 
     * @param premium the new premium
     */
    public void setPremium(long premium) {
        this.premium = premium;
    }
    
    /**
     * Returns the amount this insurance will cover.
     * 
     * @return the amount this insurance will cover.
     */
    public long getAmount() {
        return amount;
    }
    
    /**
     * Sets the amount this insurance will cover to the given value.
     * 
     * @param amount the new amount
     */
    public void setAmount(long amount) {
        this.amount = amount;
    }
    
    /**
     * Returns the terms for this insurance.
     * 
     * @return the terms for this insurance.
     */
    public String getTerms() {
        return terms;
    }
    
    /**
     * Sets this insurance's terms to the given string.
     * 
     * @param terms the new terms
     */
    public void setTerms(String terms) {
        this.terms = terms;
    }
    
    /**
     * Returns this insurance's identification number.
     * 
     * @return this insurance's identification number.
     */
    public int getInsuranceNo() {
        return insuranceNo;
    }
    
    /**
     * Returns the employee that created this insurance.
     * 
     * @return the employee that created this insurance
     */
    public Employee getEmployee() {
        return employee;
    }
    
    /**
     * Returns a Calendar object representing the date on which this insurance
     * was created.
     * 
     * @return a Calendar object representing the date on which this insurance
     * was created
     */
    public Calendar getCreationDate() {
        return creationDate;
    }
    
    /**
     * Cancels this insurance, and returns <code>true</code> if the insurance
     * was not already cancelled.
     * 
     * @return <code>true</code> if the insurance was not already cancelled
     */
    public boolean cancel() {
        if (isActive()) {
            cancellationDate = Calendar.getInstance();
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * If this insurance has been cancelled, this method returns a Calendar
     * object representing the cancellation date. Otherwise, <code>null</code>
     * is returned.
     * 
     * @return a Calendar object representing cthe ancellation date if the
     * insurance is cancelled, <code>null</code> otherwise
     */
    public Calendar getCancellationDate() {
        return cancellationDate;
    }
    
    /**
     * Returns <code>true</code> if this insurance is still active, or in other
     * words, still being paid for.
     * 
     * @return <code>true</code> if this insurance is still active.
     */
    public boolean isActive() {
        return cancellationDate == null;
    }
}
