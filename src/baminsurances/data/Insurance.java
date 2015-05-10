package baminsurances.data;

import java.time.LocalDate;

/**
 * The root class in the Insurance hierarchy.
 * 
 * @author Martin Jackson
 */
public abstract class Insurance implements Comparable<Insurance> {
    private static int nextInsuranceNo = 1;
    private int insuranceNo;
    private Employee employee;
    private int premium;
    private int amount;
    private LocalDate creationDate;
    private LocalDate cancellationDate = null;
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
    public Insurance(Employee employee, int premium, int amount,
            String terms) {
        insuranceNo = nextInsuranceNo++;
        this.employee = employee;
        this.premium = premium;
        this.amount = amount;
        creationDate = LocalDate.now();
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
     * Compares this insurance with the given insurance, by comparing their
     * insurance numbers. Returns a negative number if this insurance is less
     * than the given one, zero if it is equal, and positive if is greater.
     * 
     * @return a negative number if this insurance is less than the given one,
     * zero if it is equal, and positive if is greater 
     */
    @Override
    public int compareTo(Insurance i) {
        return this.insuranceNo - i.insuranceNo;
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
    public int getPremium() {
        return premium;
    }
    
    /**
     * Sets this insurance's annual premium to the given value.
     * 
     * @param premium the new premium
     */
    public void setPremium(int premium) {
        this.premium = premium;
    }
    
    /**
     * Returns the amount this insurance will cover.
     * 
     * @return the amount this insurance will cover.
     */
    public int getAmount() {
        return amount;
    }
    
    /**
     * Sets the amount this insurance will cover to the given value.
     * 
     * @param amount the new amount
     */
    public void setAmount(int amount) {
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
     * Returns the date on which this insurance was created
     * 
     * @return the date on which this insurance was created
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(LocalDate date) {
        creationDate = date;
    }
    
    /**
     * Cancels this insurance, and returns <code>true</code> if the insurance
     * was not already cancelled.
     * 
     * @return <code>true</code> if the insurance was not already cancelled
     */
    public boolean cancel() {
        if (isActive()) {
            cancellationDate = LocalDate.now();
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * If this insurance has been cancelled, this method returns the
     * cancellation date. Otherwise, <code>null</code> is returned.
     * 
     * @return the cancellation date if the insurance is cancelled,
     * <code>null</code> otherwise
     */
    public LocalDate getCancellationDate() {
        return cancellationDate;
    }
    
    public void setCancellationDate(LocalDate date) {
        cancellationDate = date;
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
    
    /**
     * Returns the total amount paid for this insurance.
     * 
     * @return the total amount paid for this insurance
     */
    public int getTotalAmountPaid() {
        if (isActive()) {
            return amount * creationDate.until(LocalDate.now()).getMonths() + 1;
        } else {
            return amount * creationDate.until(cancellationDate).getMonths() + 1;
        }
    }
}
