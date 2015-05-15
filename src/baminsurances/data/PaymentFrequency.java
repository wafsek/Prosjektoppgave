package baminsurances.data;

/**
 * Enum to represent a payment frequency for an insurance.
 * 
 * @author martin
 */
public enum PaymentFrequency {
    MONTHLY("Månedlig", 12),
    QUARTERLY("Hver fjerde måned", 4),
    SEMIANNUALLY("Hver sjette måned", 2),
    ANNUALLY("Én gang i året", 1);

    private final String displayName;
    private final int paymentsPerYear;

    private PaymentFrequency(String displayName, int paymentsPerYear) {
        this.displayName = displayName;
        this.paymentsPerYear = paymentsPerYear;
    }

    @Override
    public String toString() {
        return displayName;
    }

    /**
     * Returns the number of payment per year for this payment frequency.
     * 
     * @return the number of payment per year for this payment frequency
     */
    public int getPaymentsPerYear() {
        return paymentsPerYear;
    }
}
