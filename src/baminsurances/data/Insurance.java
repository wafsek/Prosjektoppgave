package baminsurances.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * The root class in the Insurance hierarchy.
 * 
 * @author Martin Jackson
 */
public abstract class Insurance implements Comparable<Insurance>, Serializable {
    private static final long serialVersionUID = -5838064838237534933L;
    private static int nextInsuranceNo = 1;
    private int insuranceNo;
    private Employee employee;
    private int annualPremium;
    private int amount;
    private PaymentFrequency paymentFrequency;
    private String terms;
    private LocalDate creationDate;
    private LocalDate cancellationDate = null;
    private SortedMap<LocalDate, Integer> payments = new TreeMap<>();
    private List<ClaimAdvice> claimAdviceList = new ArrayList<>();

    /**
     * Creates a new insurance with the given values.
     * 
     * @param employee the employee who registered this insurance
     * @param annualPremium the annual premium
     * @param amount the amount insured
     * @param terms the terms
     */
    public Insurance(Employee employee, int annualPremium, int amount,
            PaymentFrequency paymentFrequency, String terms) {
        insuranceNo = nextInsuranceNo++;
        this.employee = employee;
        this.annualPremium = annualPremium;
        this.amount = amount;
        this.paymentFrequency = paymentFrequency;
        creationDate = LocalDate.now();
        this.terms = terms;
    }
    
    /**
     * Returns <code>true</code>, if the given object is an insurance, and has
     * the same insurance number as this insurance.
     * 
     * @return <code>true</code>, if the given object is an insurance, and has
     * the same insurance number as this insurance
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Insurance
                && this.insuranceNo == ((Insurance) obj).insuranceNo;
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
     * Reads the next insurance number from file, and sets it to the read
     * value. If not file is found, the value is set to 1.
     */
    public static void readNextInsuranceNo() {
        try (DataInputStream in = new DataInputStream(
                new FileInputStream("data/next_insurance_no.dta"))) {
            nextInsuranceNo = in.readInt();
        } catch (FileNotFoundException e) {
            nextInsuranceNo = 1;
        } catch (IOException e) {
            e.printStackTrace();
            nextInsuranceNo = 1;
        }
    }

    /**
     * Writes the next insurance number to file.
     */
    public static void writeNextInsuranceNo() {
        try (DataOutputStream out = new DataOutputStream(
                new FileOutputStream("data/next_insurance_no.dta"))) {
            out.writeInt(nextInsuranceNo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Resets the next insurance number.
     * <p>
     * This method is to be used when testing the software, and you want to
     * reset the program. 
     */
    public static void resetNextInsuranceNo() {
        try {
            Files.delete(new File("data/next_insurance_no.dta").toPath());
            nextInsuranceNo = 0;
        } catch (NoSuchElementException e) {
            nextInsuranceNo = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns this insurance's annual premium.
     * 
     * @return this insurance's annual premium
     */
    public int getAnnualPremium() {
        return annualPremium;
    }
    
    /**
     * Sets this insurance's annual premium to the given value.
     * 
     * @param annualPremium the new premium
     */
    public void setAnnualPremium(int annualPremium) {
        this.annualPremium = annualPremium;
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
     * Returns the payment frequency of this insurance.
     * 
     * @return the payment frequency of this insurance
     */
    public PaymentFrequency getPaymentFrequency() {
        return paymentFrequency;
    }
    
    /**
     * Sets the payment frequency of the insurance to the given one.
     * 
     * @param paymentFrequency the new payment frequency
     * @throws NullPointerException if argument is null
     */
    public void setPaymentFrequency(PaymentFrequency paymentFrequency) {
        if (paymentFrequency == null) {
            throw new NullPointerException("Payment frequency cannot be null.");
        }
        this.paymentFrequency = paymentFrequency;
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
    
    /**
     * Sets the creation date of this insurance to the given date.
     * <p>
     * While this is a method that should not exist, it is necessary for the
     * generator classes. If not for this method, all generated insurances
     * would be created at the same time.
     * 
     * @param date the new creation date
     * @throws NullPointerException if argument is null
     */
    public void setCreationDate(LocalDate date) {
        if (date == null) {
            throw new NullPointerException("Creation date cannot be null.");
        }
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
    
    /**
     * Sets the cancellation date of this insurance to the given date.
     * <p>
     * While this is a method that should not exist, it is necessary for the
     * generator classes. If not for this method, all generated insurances
     * would be cancelled at the same time.
     * 
     * @param date the new cancellation date
     * @throws NullPointerException if argument is null
     */
    public void setCancellationDate(LocalDate date) {
        if (date == null) {
            throw new NullPointerException("Cancellation date cannot be null.");
        }
        cancellationDate = date;
    }
    
    /**
     * Adds a claim advice to this insurance.
     * 
     * @param ca the claim advice to add
     * @throws NullPointerException if argument is null
     */
    public void addClaimAdvice(ClaimAdvice ca) {
        if (ca == null) {
            throw new NullPointerException("Claim advice cannot be null.");
        }
        claimAdviceList.add(ca);
    }
    
    /**
     * Returns a list of claim advices for this insurance.
     * 
     * @return a list of claim advices for this insurance
     */
    public List<ClaimAdvice> getClaimAdvices() {
        return claimAdviceList;
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
     * Returns a sorted map representing the date of payments, along with the
     * amount paid.
     * <p>
     * The map is sorted by the natural ordering of {@link LocalDate}.
     * 
     * @return a sorted map representing the date of payments, along with the
     * amount paid
     */
    public SortedMap<LocalDate, Integer> getPayments() {
        return payments;
    }
    
    /**
     * Returns the total amount paid for this insurance.
     * 
     * @return the total amount paid for this insurance
     */
    public int getTotalPaid() {
        return payments.values().stream()
                                .mapToInt(Integer::intValue)
                                .sum();
    }
    
    /**
     * Returns the amount of each payment.
     * <p>
     * The return value of this method changes when the payment frequency or
     * annual premium is changed.
     * 
     * @return the amount of each payment
     */
    public int getAmountPerPayment() {
        return annualPremium / paymentFrequency.getPaymentsPerYear();
    }
    
    /**
     * Returns the date of the next payment, or <code>null</code> if cancelled.
     * 
     * @return the date of the next payment, or <code>null</code> if cancelled
     */
    public LocalDate getNextPaymentDate() {
        if (!isActive()) {
            return null;
        } else if (payments.isEmpty()) {
            return getCreationDate();
        } else {
            return payments.lastKey().plusMonths(getMonthsBetweenPayments());
        }
    }
    
    public int getMonthsBetweenPayments() {
        return  12 / paymentFrequency.getPaymentsPerYear();
    }
    
    /**
     * Updates this insurance's payments by checking the last payment date, and
     * the payment frequency, to find out how many payments are missing. This
     * method is necessary, as no updates are made to the data while the
     * software is not being used.
     * <p>
     * The amount to be paid is calculated using the amount per payment, as
     * well as the given discount percentage.
     * 
     * @param discountPercentage the discount percentage
     * @throws IllegalArgumentException if discount percentage is not a number
     * between 0 and 100, both inclusive
     */
    public void updatePayments(int discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage "
                    + "should be a number between 0 and 100, both inclusive."
                    + "Found: " + discountPercentage);
        }
        
        if (getNextPaymentDate() == null) {
            return; // the insurance is cancelled, and has no next payment date
        }
        
        while (getNextPaymentDate().isBefore(LocalDate.now().plusDays(1))) {
            payments.put(getNextPaymentDate(),
                    getAmountPerPayment() * (1 - discountPercentage / 100));
        }
    }
    
    /**
     * Updates this insurance's payments by checking the last payment date, and
     * the payment frequency, to find out how many payments are missing. This
     * method is necessary, as no updates are made to the data while the
     * software is not being used.
     */
    public void updatePayments() {
        updatePayments(0);
    }
}
