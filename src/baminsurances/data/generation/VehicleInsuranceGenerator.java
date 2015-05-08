package baminsurances.data.generation;

import baminsurances.data.Person;

public class VehicleInsuranceGenerator {
    
    /**
     * Has a 10% chance of return a new Person object, and a 90% chance of
     * returning the given Person.
     * 
     * @param p the person
     * @return the given person, or a new one
     */
    public Person generateVehicleOwner(Person p) {
        if (Math.random() < 0.1) {
            return new PersonGenerator().generatePerson();
        } else {
            return p;
        }
    }
}
