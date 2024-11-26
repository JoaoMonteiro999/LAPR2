package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Employee class
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = 6149865098878372888L;

    /**
     * The name of the employee
     */
    private String name;

    /**
     * The passport card number of the employee
     */
    private String passportCardNum;

    /**
     * The tax number of the employee
     */
    private String taxNumber;

    /**
     * The phone number of the employee
     */
    private String phoneNumber;

    /**
     * The email address of the employee
     */
    private String emailAddress;

    /**
     * The address of the employee
     */
    private Address address;

    /**
     * The roles the employee has
     */
    private List<Role> roles;

    /**
     * The agency which the employee belongs
     */
    private Agency agency;

    /**
     * This method is the constructor of the employee.
     */
    public Employee(String name, String passportCardNum, String taxNumber, String phoneNumber, String emailAddress, Address address, List<Role> roles, Agency agency) {
        setName(name);
        setPassportCardNum(passportCardNum);
        setTaxNumber(taxNumber);
        setPhoneNumber(phoneNumber);
        setEmailAddress(emailAddress);
        setAddress(address);
        setRoles(roles);
        setAgency(agency);
    }

    /**
     * This method is the constructor of the employee.
     */
    public Employee(String name, String passportCardNum, String taxNumber, String phoneNumber, String emailAddress, List<Role> roles, Agency agency) {
        setName(name);
        setPassportCardNum(passportCardNum);
        setTaxNumber(taxNumber);
        setPhoneNumber(phoneNumber);
        setEmailAddress(emailAddress);
        setRoles(roles);
        setAgency(agency);
    }

    /**
     * This method is the constructor of the employee.
     */
    public Employee(String name,String phoneNumber,List<Role> roles){
        setName(name);
        setPhoneNumber(phoneNumber);
        setRoles(roles);
    }

    /**
     * Empty constructor
     */
    public Employee(){
    }

    /**
     * Get the employee name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Change the name
     * @param name
     */
    public void setName(String name) {
        if (!name.matches("^[^0-9]+$")) {
            throw new IllegalArgumentException("Name is invalid!");
        }
        this.name = name;
    }

    /**
     * Get the passport card number
     * @return passport card number
     */
    public String getPassportCardNum() {
        return passportCardNum;
    }

    /**
     * Change the passport card number
     * @param passportCardNum
     */
    public void setPassportCardNum(String passportCardNum) {
        passportCardNum = passportCardNum.replaceAll("-", "");
        if (passportCardNum.length() != 9 || !passportCardNum.matches("\\d{9}")){
            throw new IllegalArgumentException("Passport Card Number is invalid!");
        }
        this.passportCardNum = passportCardNum;
    }

    /**
     * Get the tax number
     * @return tax number
     */
    public String getTaxNumber() {
        return taxNumber;
    }

    /**
     * Change the tax number
     * @param taxNumber
     */
    public void setTaxNumber(String taxNumber) {
        taxNumber = taxNumber.replaceAll("-", "");
        if (taxNumber.length() != 9 || !taxNumber.matches("\\d{9}")){
            throw new IllegalArgumentException("Tax Number is invalid!");
        }
        this.taxNumber = taxNumber;
    }

    /**
     * Get the phone number
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Change the phone number
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("\\(\\d{3}\\)\\s\\d{3}-?\\d{4}") && !phoneNumber.matches("\\d{3}-\\d{3}-?\\d{4}")){
            throw new IllegalArgumentException("Phone Number is invalid");
        }
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the email address
     * @return
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Change the email address
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        if (!(emailAddress.matches("^(.+)@(.+)$"))){
            throw new IllegalArgumentException("Email Address is invalid!");
        }
        this.emailAddress = emailAddress;
    }

    /**
     * Get the address
     * @return
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Change the address
     * @param address
     */
    public void setAddress(Address address) {
        if (address == null){
            throw new IllegalArgumentException("Address is invalid!");
        }
        this.address = address;
    }

    /**
     * Get the employee roles
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * A method to show the employee roles
     */
    public void showRoles() {
        System.out.print("Roles: ");

        for (Role role : roles) {
            System.out.print(role.getRolesBySelection() + " ");
        }
    }

    /**
     * Change the roles
     * @param roles
     */
    public void setRoles(List<Role> roles) {
        if (roles == null){
            throw new IllegalArgumentException("Role is invalid");
        }
        this.roles = roles;
    }

    /**
     * Get the employee agency
     * @return
     */
    public Agency getAgency() {
        return agency;
    }

    /**
     * Change the employee agency
     * @param agency
     */
    public void setAgency(Agency agency) {
        if (agency == null){
            throw new IllegalArgumentException("Agency is invalid");
        }
        this.agency = agency;
    }

    /**
     * This method verifies if two employees are equal based on the tax number.
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return taxNumber.equals(employee.taxNumber);
    }

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Employee clone() {
        return new Employee(this.name, this.passportCardNum, this.taxNumber, this.phoneNumber, this.emailAddress, this.address, this.roles, this.agency);
    }

    /**
     * To string method
     * @return the string with the instance attribute values
     */
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", passportCardNum=" + passportCardNum +
                ", taxNumber=" + taxNumber +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", address=" + address +
                ", roles=" + roles +
                ", agency=" + agency +
                '}';
    }
}
