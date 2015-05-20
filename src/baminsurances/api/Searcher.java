package baminsurances.api;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import baminsurances.data.BoatInsurance;
import baminsurances.data.CarInsurance;
import baminsurances.data.ClaimAdvice;
import baminsurances.data.Customer;
import baminsurances.data.DataBank;
import baminsurances.data.Employee;
import baminsurances.data.HolidayHomeInsurance;
import baminsurances.data.HomeInsurance;
import baminsurances.data.Insurance;
import baminsurances.data.TravelInsurance;

/**
 * The class that handles all forms of data search. Provides methods for
 * finding customers, insurances and claim advices.
 * 
 * @author Martin Jackson
 */
public class Searcher {
    private DataBank dataBank = DataBank.getInstance();
    
    
    /***********************************************
     *                                             * 
     *               Helper methods                *
     *                                             *
     ***********************************************/
    
    /**
     * Helper method to reduce the given list of predicates into one.
     * 
     * @param predicates the list of predicates to reduce
     * @return the given list of predicates reduced to one
     */
    private <T> Predicate<T> reducePredicates(List<Predicate<T>> predicates) {
        return predicates.stream()
                         .reduce(Predicate::and)
                         .orElse(p -> true);
    }
    
    
    /***********************************************
     *                                             * 
     *           Searching for customers           *
     *                                             *
     ***********************************************/
    
    /**
     * Takes a list of customer predicates, and returns a list of all customers
     * which satisfy these.
     * 
     * @param predicates the list of predicates
     * @return a list of customers which satisfy the predicates
     */
    public List<Customer> findCustomers(List<Predicate<Customer>> predicates) {
        Predicate<Customer> predicate = reducePredicates(predicates);
        return dataBank.getCustomerList()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
    
    /* Customer predicates */
    
    public static final Predicate<Customer> isActiveCustomer() {
        return cus -> cus.isActive();
    }
    
    public static final Predicate<Customer> isNotActiveCustomer() {
        return isActiveCustomer().negate();
    }
            
    public static final Predicate<Customer> isTotalCustomer() {
        return cus -> cus.isTotalCustomer();
    }
    
    public static final Predicate<Customer> isNotTotalCustomer() {
        return isTotalCustomer().negate();
    }
            
    public static final Predicate<Customer> hasInsurance() {
        return cus -> !cus.getInsurances().isEmpty();
    }
    
    public static final Predicate<Customer> hasNoInsurance() {
        return hasInsurance().negate();
    }

    public static final Predicate<Customer> customerHasClaimAdvice() {
        return cus -> cus.getInsurances().stream()
                                       .anyMatch(ins ->
                                           !ins.getClaimAdvices().isEmpty());
    }

    public static final Predicate<Customer> customerDoesntHaveClaimAdvice() {
        return customerHasClaimAdvice().negate();
    }

    public static Predicate<Customer> isOlderThan(int age) {
        return cus -> cus.getAge() >= age;
    }
    
    public static Predicate<Customer> isYoungerThan(int age) {
        return isOlderThan(age).negate();
    }

    public static Predicate<Customer> birthNoStartsWith(String s) {
        return cus -> cus.getBirthNo().startsWith(s.trim());
    }
            
    public static Predicate<Customer> firstNameStartsWith(String s) {
        return cus -> cus.getFirstName().toLowerCase().startsWith(
                s.trim().toLowerCase());
    }
    
    public static Predicate<Customer> lastNameStartsWith(String s) {
        return cus -> cus.getLastName().toLowerCase().startsWith(
                s.trim().toLowerCase());
    }
    
    public static Predicate<Customer> telephoneNoStartsWith(String s) {
        return cus -> cus.getTelephoneNo().startsWith(s.trim());
    }
    
    public static Predicate<Customer> emailStartsWith(String s) {
        return cus -> cus.getStreetAddress().toLowerCase().startsWith(
                    s.trim().toLowerCase());
    }
    
    public static Predicate<Customer> zipCodeStartsWith(String s) {
        return cus -> cus.getZipCode().startsWith(s.trim());
    }
    
    public static Predicate<Customer> streetAddressStartsWith(String s) {
        return cus -> cus.getStreetAddress().toLowerCase().startsWith(
                s.trim().toLowerCase());
    }
    
    public static Predicate<Customer> registeredBefore(LocalDate date) {
        return cus -> cus.getRegistrationDate().isBefore(date);
    }
    
    public static Predicate<Customer> registeredAfter(LocalDate date) {
        return registeredBefore(date).negate();
    }
    
    public static Predicate<Customer> hasInsuranceOfType(
            Class<? extends Insurance> type) {
        return cus -> cus.hasInsuranceType(type);
    }
    
    public static <T extends Insurance> Predicate<Customer> hasActiveInsuranceOfType(
            Class<T> type) {
        return cus -> cus.hasActiveInsuranceType(type);
    }

    
    /***********************************************
     *                                             * 
     *           Searching for insurances          *
     *                                             *
     ***********************************************/
    
    /**
     * Takes a list of customer and insurance predicates, and returns a list of
     * all insurances which satisfy these.
     * 
     * @param cusPredicates the customer predicates
     * @param insPredicates the insurance predicates 
     * @return a list of insurances which match the predicates
     */
    public List<Insurance> findInsurances(List<Predicate<Customer>> cusPredicates,
            List<Predicate<Insurance>> insPredicates) {
        Predicate<Insurance> insPredicate =
                reducePredicates(insPredicates);
        return findCustomers(cusPredicates)
                .stream()
                .flatMap(cus -> cus.getInsurances().stream())
                .filter(insPredicate)
                .collect(Collectors.toList());
    }
    
    /* Insurance predicates */
    
    public static final Predicate<Insurance> isActiveInsurance() {
        return ins -> ins.isActive();
    }
    
    public static final Predicate<Insurance> isNotActiveInsurance() {
        return isActiveInsurance().negate();
    }

    public static final Predicate<Insurance> hasClaimAdvice() {
        return ins -> !ins.getClaimAdvices().isEmpty();
    }
    
    public static final Predicate<Insurance> hasNoClaimAdvice() {
        return hasClaimAdvice().negate();
    }

    public static final Predicate<Insurance> insuranceNoStartsWith(String s) {
        return ins -> String.valueOf(ins.getInsuranceNo()).startsWith(s);
    }

    public static final Predicate<Insurance> insuranceTermsContains(String s) {
        return ins -> ins.getTerms().toLowerCase().contains(s);
    }
    
    public static final Predicate<Insurance> insurancePaidLessThan(int n) {
        return ins -> ins.getTotalPaid() < n;
    }

    public static final Predicate<Insurance> insurancePaidMoreThan(int n) {
        return insurancePaidLessThan(n).negate();
    }

    public static final Predicate<Insurance> insuranceRegisteredBefore(
            LocalDate date) {
        return ins -> ins.getCreationDate().isBefore(date);
    }
    
    public static final Predicate<Insurance> insuranceRegisteredAfter(
            LocalDate date) {
        return insuranceRegisteredBefore(date).negate();
    }

    public static final Predicate<Insurance> isOfType(
            Class<? extends Insurance> type) {
        return ins -> type.isInstance(type);
    }
    
    public static final Predicate<Insurance> registeredByEmployee(Employee emp) {
        return ins -> ins.getEmployee().equals(emp);
    }
    
    
    /***********************************************
     *                                             * 
     *         Searching for claim advices         *
     *                                             *
     ***********************************************/
    
    /**
     * Takes a list of customer, insurance and claim advice predicates, and
     * returns a list of all claim advices which satisfy these.
     * 
     * @param cusPredicates the customer predicates
     * @param insPredicates the insurance predicates
     * @param claimPredicats the claim advice predicates 
     * @return a list of claim advices which match the predicates
     */
    public List<ClaimAdvice> findClaimAdvices(
            List<Predicate<Customer>> cusPredicates,
            List<Predicate<Insurance>> insPredicates,
            List<Predicate<ClaimAdvice>> claimPredicates) {
        
        Predicate<ClaimAdvice> claimPredicate =
                reducePredicates(claimPredicates);
        
        return findInsurances(cusPredicates, insPredicates)
                .stream()
                .flatMap(ins -> ins.getClaimAdvices().stream())
                .filter(claimPredicate)
                .collect(Collectors.toList());
    }
    
    /* Claim advice predicates */
    
    public static final Predicate<ClaimAdvice> compensationReceived() {
        return ca -> ca.getCompensationAmount() > 0;
    }
    
    public static final Predicate<ClaimAdvice> compensationNotReceived() {
        return compensationReceived().negate();
    }
    
    public static final Predicate<ClaimAdvice> damageTypeStartsWith(String s) {
        return ca -> ca.getDamageType().toLowerCase().startsWith(
                s.trim().toLowerCase());
    }
    
    public static final Predicate<ClaimAdvice> damageDescriptionContains(String s) {
        return ca -> ca.getDamageDescription().toLowerCase().contains(
                s.trim().toLowerCase());
    }
    
    
    /***********************************************
     *                                             * 
     *         Methods used for statistics         *
     *                                             *
     ***********************************************/
    
    /**
     * Returns the current number of customers in the data bank.
     * 
     * @return the current number of customers in the data bank
     */
    public int getNumCustomers() {
        return dataBank.getCustomerList().size();
    }
    
    /**
     * Returns the current number of insurances in the data bank.
     * 
     * @return the current number of insurances in the data bank
     */
    public int getNumInsurances() {
        int num = 0;
        for (Customer cus : dataBank.getCustomerList()) {
            num += cus.getInsurances().size();
        }
        return num;
    }
    
    /**
     * Returns a sorted map where the keys are years, and the values are the
     * sum of all payments received that year.
     * 
     * @return a sorted map where the keys are years, and the values are the
     * sum of all payments received that year
     */
    public SortedMap<Integer, Integer> getSumPaymentsPerYear() {
        SortedMap<Integer, Integer> result = new TreeMap<>();
        
        for (Customer cus : dataBank.getCustomerList()) {
            for (Insurance ins : cus.getInsurances()) {
                for (Map.Entry<LocalDate, Integer> payment :
                        ins.getPayments().entrySet()) {
                    int year = payment.getKey().getYear();
                    Integer current = result.get(year);
                    if (current == null) {
                        current = 0;
                    }
                    
                    result.put(year, current + payment.getValue());
                }
            }
        }
        
        if (result.isEmpty()) {
            return result;
        }
        
        /* Initializing all "empty" keys between the min and max value to zero,
         * so that there are no empty intervals.
         */
        for (int year = result.firstKey() + 1; year < result.lastKey(); year++) {
            if (result.get(year) == null) {
                result.put(year, 0);
            }
        }
        
        return result;
    }
    
    /**
     * Returns a map where the keys are months, and the values are the number
     * of claim advices for that month, across all years.
     * 
     * @return a map where the keys are months, and the values are the number
     * of claim advices for that month, across all years
     */
    public Map<String, Integer> numClaimAdvicesPerMonth() {
        Map<Integer, Integer> temp = new HashMap<>();
        for (int i = 1; i <= 12; i++) {
            temp.put(i, 0);
        }
        
        for (Customer cus : dataBank.getCustomerList()) {
            for (Insurance ins : cus.getInsurances()) {
                for (ClaimAdvice ca : ins.getClaimAdvices()) {
                    int month = ca.getDateOfDamage().getMonthValue(); 
                    temp.put(month, temp.get(month) + 1);   
                }
            }
        }
        
        Map<String, Integer> result = new LinkedHashMap<>();
        result.put("Januar",    temp.get(1));
        result.put("Februar",   temp.get(2));
        result.put("Mars",      temp.get(3));
        result.put("April",     temp.get(4));
        result.put("Mai",       temp.get(5));
        result.put("Juni",      temp.get(6));
        result.put("Juli",      temp.get(7));
        result.put("August",    temp.get(8));
        result.put("September", temp.get(9));
        result.put("Oktober",   temp.get(10));
        result.put("November",  temp.get(11));
        result.put("Desember",  temp.get(12));
        return result;
    }
    
    /**
     * Returns a map where the keys are genders, and the values are the number
     * of claim advices for that gender.
     * 
     * @return a map where the keys are genders, and the values are the number
     * of claim advices for that gender
     */
    public Map<String, Integer> numClaimAdvicesPerGender() {
        Map<String, Integer> result = new HashMap<>();
        
        // Map used to find the right display name based on class.
        Map<Character, String> displayNames = new HashMap<>();
        displayNames.put('M', "Menn");
        displayNames.put('F', "Kvinner");
        
        /* Initializing all keys with a value of 0, so that they are included
         * even if no customer of that gender exists.
         */
        result.put("Menn", 0);
        result.put("Kvinner", 0);
        
        for (Customer cus : dataBank.getCustomerList()) {
            char gender = cus.getGender();
            for (Insurance ins : cus.getInsurances()) {
                result.put(displayNames.get(gender),
                        result.get(displayNames.get(gender)) +
                        ins.getClaimAdvices().size());
            }
        }
        return result;
    }
    
    /**
     * Returns a sorted map where the keys are different ages, and the values
     * are the number of customers per age.
     * 
     * @return a sorted map where the keys are different ages, and the values
     * are the number of customers per age
     */
    public Map<String, Integer> numCustomersPerAgeGroup() {
        SortedMap<Integer, Integer> temp = new TreeMap<>();
        
        for (Customer cus : dataBank.getCustomerList()) {
            int age = cus.getAge();
            Integer currentNum = temp.get(age);
            if (currentNum == null) {
                currentNum = 0;
            }
            temp.put(age, currentNum + 1);
        }
        
        if (temp.isEmpty()) {
            return new HashMap<>();
        }
        
        /* Initializing all "empty" keys between the min and max value to zero,
         * so that there are no empty intervals.
         */
        for (int age = 18; age <= 100; age++) {
            if (temp.get(age) == null) {
                temp.put(age, 0);
            }
        }
        
        /* Finally creating the map that is to be returned. Its keys are
         * age groups represented by strings.
         * 
         * E.g: "18-20", "21-30", "31-40", "41-50", ..., "91-100".
         */
        Map<String, Integer> result = new LinkedHashMap<>();
        result.put("18-20", temp.get(18));
        result.put("18-20", result.get("18-20") + temp.get(19));
        result.put("18-20", result.get("18-20") + temp.get(20));
        
        for (int i = 21; i <= 100; i += 10) {
            String key = String.valueOf(i) + "-" + String.valueOf(i + 9);
            for (int j = i; j <= i + 9; j++) {
                Integer currentNum = result.get(key);
                if (currentNum == null) {
                    currentNum = 0;
                }
                result.put(key, currentNum + temp.get(j));
            }
        }
        return result;
    }
    
    /**
     * Returns a sorted map where the keys are different ages, and the values
     * are the number of insurances per age.
     * 
     * @return a sorted map where the keys are different ages, and the values
     * are the number of insurances per age
     */
    public SortedMap<Integer, Integer> numInsurancesPerAge() {
        SortedMap<Integer, Integer> result = new TreeMap<>();
        
        for (Customer cus : dataBank.getCustomerList()) {
            int age = cus.getAge();
            Integer currentNum = result.get(age);
            if (currentNum == null) {
                currentNum = 0;
            }
            result.put(age, currentNum + cus.getInsurances().size());
        }
        
        /*
         * Initializing all "empty" keys between the min and max value to zero,
         * so that there are no empty intervals.
         */
        for (int age = result.firstKey() + 1; age < result.lastKey(); age++) {
            if (result.get(age) == null) {
                result.put(age, 0);
            }
        }
        return result;
    }
    
    /**
     * Returns a map where the keys are different ages, and the values are the
     * number of insurances per age.
     * 
     * @return a map where the keys are different ages, and the values are the
     * number of insurances per age
     */
    public SortedMap<String, Integer> numInsurancesPerZipCode() {
        SortedMap<String, Integer> result = new TreeMap<>();
        for (Customer cus : dataBank.getCustomerList()) {
            String zipCode = cus.getZipCode();
            Integer currentNum = result.get(zipCode);
            if (currentNum == null) {
                currentNum = 0;
            }
            result.put(zipCode, currentNum + cus.getInsurances().size());
        }
        return result;
    }
    
    /**
     * Returns a map where they keys are regions, and the values are the
     * number of insurances for customers living in that region.
     * 
     * @return a map where they keys are regions, and the values are the
     * number of insurances for customers living in that region
     */
    public Map<String, Integer> numInsurancesPerRegion() {
        /* Helper map to determine region based on the first two digits
         * of a zip code.
         */
        Map<String, String> zipCodeToRegion = getZipCodesToRegion();
        
        Map<String, Integer> result = new HashMap<>();
        /* Initializing all regions, so that they are included even if there
         * are no customers with insurances in that region.
         */
        for (String region : zipCodeToRegion.values()) {
            result.put(region, 0);
        }
        
        for (Customer cus : dataBank.getCustomerList()) {
            String region =
                    zipCodeToRegion.get(cus.getZipCode().substring(0, 2));
            Integer currentNum = result.get(region);
            if (currentNum == null) {
                currentNum = 0;
            }
            result.put(region, currentNum + cus.getInsurances().size());
        }
        return result;
    }
    
    /**
     * Returns a map where the key is a region, and the value is the number of
     * customers living in that region.
     * 
     * @return a map where the key is a region, and the value is the number of
     * customers living in that region
     */
    public Map<String, Integer> numCustomersPerRegion() {
        /* Helper map to determine region based on the first two digits
         * of a zip code.
         */
        Map<String, String> zipCodeToRegion = getZipCodesToRegion();
        
        Map<String, Integer> result = new HashMap<>();
        /* Initializing all regions, so that they are included even if there
         * are no customers with insurances in that region.
         */
        for (String region : zipCodeToRegion.values()) {
            result.put(region, 0);
        }
        
        for (Customer cus : dataBank.getCustomerList()) {
            String region =
                    zipCodeToRegion.get(cus.getZipCode().substring(0, 2));
            Integer currentNum = result.get(region);
            if (currentNum == null) {
                currentNum = 0;
            }
            result.put(region, currentNum + cus.getInsurances().size());
        }
        return result;
    }
    
    /**
     * A helper method that returns a map where the keys arethe first two
     * digits of a zip code, and the values are the related regions.
     * 
     * @return a map where the keys arethe first two digits of a zip code, and
     * the values are the related regions
     */
    private Map<String, String> getZipCodesToRegion() {
        Map<String, String> zipCodeToRegion = new HashMap<>();
        
        /* ØSTLANDET:
         * Oslo, Akershus, Østfold, Buskerud, Hedmark, Oppland, Oslo, Telemark,
         * Vestfold
         */
        for (int i = 0; i <= 39; i++) {
            String code = String.valueOf(i);
            if (i < 10) {
                code = "0" + code;
            }
            zipCodeToRegion.put(code, "Østlandet");
        }
        
        /* SØRLANDET
         * Aust-Agder og Vest-Agder
         */
        for (int i = 44; i <= 49; i++) {
            zipCodeToRegion.put(String.valueOf(i), "Sørlandet");
        }
        
        /* VESTLANDET
         * Hordaland, Møre og Romsdal, Rogaland og Sogn og Fjordane
         */
        for (int i = 40; i <= 43; i++) {
            zipCodeToRegion.put(String.valueOf(i), "Vestlandet");
        }
        for (int i = 50; i <= 69; i++) {
            zipCodeToRegion.put(String.valueOf(i), "Vestlandet");
        }
        
        /* TRØNDELAG
         * Nord-Trøndelag og Sør-Trøndelag
         */
        for (int i = 70; i <= 79; i++) {
            zipCodeToRegion.put(String.valueOf(i), "Trøndelag");
        }
        
        /* NORD-NORGE
         * Finnmark, Nordland og Troms
         */
        for (int i = 80; i <= 99; i++){
            zipCodeToRegion.put(String.valueOf(i), "Nord-Norge");
        }
        return zipCodeToRegion;
    }
    
    /**
     * Returns a map where the keys are genders, and the values are the number
     * of insurances per gender.
     * 
     * @return a map where the keys are genders, and the values are the number
     * of insurances per gender
     */
    public Map<String, Integer> numInsurancesPerGender() {
        Map<String, Integer> result = new HashMap<>();
        
        // Map used to find the right display name based on class.
        Map<Character, String> displayNames = new HashMap<>();
        displayNames.put('M', "Menn");
        displayNames.put('F', "Kvinner");
        
        /* Initializing all keys with a value of 0, so that they are included
         * even if no customer of that gender exists.
         */
        result.put("Menn", 0);
        result.put("Kvinner", 0);
        
        for (Customer cus : dataBank.getCustomerList()) {
            char gender = cus.getGender();
            result.put(displayNames.get(gender),
                    result.get(displayNames.get(gender)) +
                    cus.getInsurances().size());
        }
        return result;
    }
    
    /**
     * Returns a map where the outer key is a gender, and the outer value is a
     * new map. In this new map, the key is a type of insurance, and the value
     * is the number of insurances of that type for a gender.
     * 
     * @return a map representing the number of an insurance type to a gender
     */
    public Map<String, TreeMap<String, Integer>> numInsuranceTypesPerGender() {
        Map<String, TreeMap<String, Integer>> result = new HashMap<>();
        
        Map<Class<? extends Insurance>, String> insuranceClassToString =
                getInsuranceClassToString();
        
        // Map used to find the right display name based on class.
        Map<Character, String> displayNames = new HashMap<>();
        displayNames.put('M', "Menn");
        displayNames.put('F', "Kvinner");
        
        /* Initializing all keys with a value of 0, so that they are included
         * even if no customer of that gender exists.
         */
        for (String gender : displayNames.values()) {
            TreeMap<String, Integer> inner = new TreeMap<>();
            for (String type : insuranceClassToString.values()) {
                inner.put(type, 0);
            }
            result.put(gender, inner);
        }
        
        for (Customer cus : dataBank.getCustomerList()) {
            char gender = cus.getGender();
            Map<String, Integer> inner = result.get(gender);
            
            for (Insurance ins : cus.getInsurances()) {

                String type = insuranceClassToString.get(ins.getClass());                
                Integer currentNum = inner.get(type);
                if (currentNum == null) {
                    currentNum = 0;
                }
                
                inner.put(type, currentNum + 1);
            }
        }
        
        return result;
    }
    
    /**
     * Returns a map where the keys are types of insurances, and the values are
     * the number of insurances of that type.
     * 
     * @return a map where the keys are types of insurances, and the values are
     * the number of insurances of that type
     */
    public Map<String, Integer> numInsurancesPerType() {
        Map<String, Integer> result = new HashMap<>();
        
        // Map used to find the right display name based on class.
        Map<Class<? extends Insurance>, String> displayNames =
                getInsuranceClassToString();
        
        /* Initializing all keys with a value of 0, so that they are included
         * even if no insurance exists of that type.
         */
        for (String type : displayNames.values()) {
            result.put(type, 0);
        }
        
        for (Customer cus : dataBank.getCustomerList()) {
            for (Insurance ins : cus.getInsurances()) {
                Class<? extends Insurance> type = ins.getClass();
                result.put(displayNames.get(type),
                        result.get(displayNames.get(type)) + 1);
            }
        }
        return result;
    }
    
    /**
     * Returns a map of a map, where the outer key is a region, and the outer
     * value is a new map. In this new map, the key is a type of insurance, and
     * the value is the number of insurances of that type.
     * 
     * @return a map of a map, representing the number of an insurance type in
     * a region
     */
    public Map<String, TreeMap<String, Integer>> getInsuranceTypesPerRegion() {
        Map<String, TreeMap<String, Integer>> result =
                new HashMap<String, TreeMap<String, Integer>>();
        
        Map<String, String> zipCodeToRegion = getZipCodesToRegion();
        Map<Class<? extends Insurance>, String> insuranceClassToString =
                getInsuranceClassToString();
        
        /**
         * Initalizing all values to 0, so that they are included even if the
         * region does not have any insurances of that type. 
         */
        for (String region : zipCodeToRegion.values()) {
            TreeMap<String, Integer> inner = new TreeMap<>();
            
            for (String type : insuranceClassToString.values()) {
                inner.put(type, 0);
            }
            result.put(region, inner);
        }
        
        for (Customer cus : dataBank.getCustomerList()) {
            String region = zipCodeToRegion.get(
                    cus.getZipCode().substring(0, 2));
            for (Insurance ins : cus.getInsurances()) {

                Map<String, Integer> inner = result.get(region);

                String type = insuranceClassToString.get(ins.getClass());                
                Integer currentNum = inner.get(type);
                if (currentNum == null) {
                    currentNum = 0;
                }
                
                inner.put(type, currentNum + 1);
            }
        }
        
        return result;
    }
    
    /**
     * Returns a map of a map, where the outer key is a year, and the outer
     * value is a new map. In this new map, the key is a type of insurance, and
     * the value is the sum of payments for that insurance for a year.
     * 
     * @return a map of a map, representing the sum of payments for insurance
     * type during a year
     */
    public Map<Integer, TreeMap<String, Integer>> getPaymentsPerInsuranceTypePerYear() {
        Map<Integer, TreeMap<String, Integer>> result = new HashMap<>();
        
        Map<Class<? extends Insurance>, String> insuranceClassToString =
                getInsuranceClassToString();
        
        /**
         * Initalizing all values to 0, so that they are included even if the
         * region does not have any insurances of that type. 
         */
        for (int i = 1997; i <= LocalDate.now().getYear(); i++) {
            TreeMap<String, Integer> inner = new TreeMap<>();
            
            for (String type : insuranceClassToString.values()) {
                inner.put(type, 0);
            }
            result.put(i, inner);
        }
        
        for (Customer cus : dataBank.getCustomerList()) {
            for (Insurance ins : cus.getInsurances()) {
                String type = insuranceClassToString.get(ins.getClass());
                
                for (Map.Entry<LocalDate, Integer> payments :
                        ins.getPayments().entrySet()) {
                    int year = payments.getKey().getYear();
                    Map<String, Integer> inner = result.get(year);
                    
                    Integer currentNum = inner.get(type);
                    if (currentNum == null) {
                        currentNum = 0;
                    }
                    
                    inner.put(type, currentNum + payments.getValue());
                }
            }
        }
        return result;
    }
    
    /**
     * Helper method that returns a map where the key is a subclass of
     * Insurance, and the values are a string representation of that
     * insurance subclass.
     * 
     * @return a map where the key is a subclass of Insurance, and the values
     * are a string representation of that insurance subclass
     */
    private Map<Class<? extends Insurance>, String> getInsuranceClassToString() {
        Map<Class<? extends Insurance>, String> insuranceClassToString =
                new HashMap<>();
        insuranceClassToString.put(CarInsurance.class, "Bilforsikring");
        insuranceClassToString.put(BoatInsurance.class, "Båtforsikring");
        insuranceClassToString.put(HomeInsurance.class, "Boligforsikring");
        insuranceClassToString.put(HolidayHomeInsurance.class, "Fritidsboligforsikring");
        insuranceClassToString.put(TravelInsurance.class, "Reiseforsikring");
        return insuranceClassToString;
    }
}
