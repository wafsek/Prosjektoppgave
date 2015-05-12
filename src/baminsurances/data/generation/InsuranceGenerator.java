package baminsurances.data.generation;

import java.time.LocalDate;

import baminsurances.data.PaymentFrequency;

/**
 * Provides methods for generating {@link Insurance} objects.
 * 
 * @author Martin Jackson
 */
public class InsuranceGenerator {
    
    /**
     * Generates and returns an insurance's premium based on its amount.
     * 
     * @param amount the amount
     * @return a premium
     */
    public int generatePremium(int amount) {
        return (int) (Math.random() * amount) / 2; 
    }
    
    /**
     * Generates and returns an amount.
     * 
     * @return an amount
     */
    public int generateAmount() {
        return (int) (Math.random() * 5000) + 2000;
    }
    
    /**
     * Generates and returns a payment frequency.
     * 
     * @return a payment frequency
     */
    public PaymentFrequency generatePaymentFrequency() {
        PaymentFrequency[] frequencies = PaymentFrequency.values();
        return frequencies[(int) (Math.random() * frequencies.length)];
    }
    
    /**
     * Generates and returns a date after the given creation date of the
     * customer.
     * 
     * @param customerRegistrationDate the customer's creation date
     * @return a date after the given creation date of the customer
     */
    public LocalDate generateCreationDate(
            LocalDate customerRegistrationDate) {
        return DateGenerator.generateBeforeNowAndAfter(customerRegistrationDate);
    }
    
    /**
     * Generates and returns a cancellation date after the given registration
     * date for an insurance.
     * 
     * @param creationDate the creation date of the insurance
     * @return a date after the given one
     */
    public LocalDate generateCancellationDate(LocalDate creationDate) {
        return DateGenerator.generateBeforeNowAndAfter(creationDate);
    }
}
