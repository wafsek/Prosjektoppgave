package baminsurances.data;

import baminsurances.security.Authorization;

/**
 * Represents an employee working for the company.
 * 
 * @author Martin Jackson
 */
public class Employee extends Person {
    private static final long serialVersionUID = -1320643100908863211L;
    private String username;
    private String password;
    private Authorization authorization;

    /**
     * Creates a new employee with the given values.
     * 
     * @param birthNo the employee's birth number
     * @param firstName the employee's first name
     * @param lastName the employee's last name
     * @param telephoneNo the employee's telephone number
     * @param email the employee's email
     * @param zipCode the employee's zip code
     * @param streetAddress the employee's street address
     * @throws NullPointerException if any argument is null
     */
    public Employee(String birthNo, String firstName, String lastName,
            String telephoneNo, String email, String zipCode,
            String streetAddress, String username, String password,
            Authorization authorization) {
        super(birthNo, firstName, lastName, telephoneNo, email, zipCode,
                streetAddress);
        setUsername(username);
        setPassword(password);
        setAuthorization(authorization);
    }

    /**
     * Returns this employee's username.
     * 
     * @return this employee's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets this employee's username to the given value.
     * 
     * @param username the new username
     * @throws NullPointerException if argument is null
     */
    public void setUsername(String username) {
        if (username == null) {
            throw new NullPointerException("Username cannot be null.");
        }
        this.username = username;
    }

    /**
     * Returns this employee's password.
     * 
     * @return this employee's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets this employee's password to the given value.
     * 
     * @param password the new employee
     * @throws NullPointerException if argument is null
     */
    public void setPassword(String password) {
        if (password == null) {
            throw new NullPointerException("Username cannot be null.");
        }
        this.password = password;
    }

    /**
     * Returns the authorization of this employee.
     * 
     * @return the authorization of this employee
     */
    public Authorization getAuthorization() {
        return authorization;
    }

    /**
     * Sets the autorization of this employee to the given one.
     * 
     * @param authorization the new authorization
     * @throws NullPointerException if argument is null
     */
    public void setAuthorization(Authorization authorization) {
        if (authorization == null) {
            throw new NullPointerException("Authorization cannot be null.");
        }
        this.authorization = authorization;
    }
}
