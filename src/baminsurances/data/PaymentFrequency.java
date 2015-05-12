package baminsurances.data;

/**
 * Enum to represent a payment frequency for an insurance.
 * 
 * @author martin
 */
public enum PaymentFrequency implements NameDisplayable {
    MONTHLY("Monthly", 12),
    QUARTERLY("Quarterly", 4),
    SEMIANNUALLY("Semiannually", 2),
    ANNUALLY("Annually", 1);

    private final String displayName;
    private final int paymentsPerYear;

    private PaymentFrequency(String displayName, int paymentsPerYear) {
        this.displayName = displayName;
        this.paymentsPerYear = paymentsPerYear;
    }

    @Override
    public String getDisplayName() {
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
