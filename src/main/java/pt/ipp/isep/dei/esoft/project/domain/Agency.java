package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Agency class
 */
public class Agency implements Serializable{

    private static final long serialVersionUID = 5469623497322685802L;

    /**
     * Agency id
     */
    private Integer id;

    /**
     * Agency name
     */
    private String designation;

    /**
     * Agency email address
     */
    private String emailAddress;

    /**
     * Agency phone number
     */
    private String phoneNumber;

    /**
     * Agency address
     */
    private Address address;

    /**
     * Agency list of employees
     */
    List<Employee> employees = new ArrayList<>();

    /**
     * Agency list of orders
     */
    List<Order> orders = new ArrayList<>();

    /**
     * Empty constructor
     */
    public Agency(){
    }

    /**
     * Constructor
     * @param id
     * @param designation
     * @param emailAddress
     * @param phoneNumber
     * @param address
     */
    public Agency(Integer id, String designation, String emailAddress, String phoneNumber, Address address){
        setId(id);
        setDesignation(designation);
        setEmailAddress(emailAddress);
        setPhoneNumber(phoneNumber);
        setAddress(address);
    }

    /**
     * Constructor
     */
    public Agency(Integer id, String designation, String emailAddress, String phoneNumber) {
        setId(id);
        setDesignation(designation);
        setEmailAddress(emailAddress);
        setPhoneNumber(phoneNumber);
    }

    /**
     * This method returns the id of the agency.
     * @return id
     */
    public Integer getAgenciesBySelection() {
        return id;
    }

    /**
     * Changes the id value
     * @param id
     */
    public void setId(Integer id) {
        if (!(id > 0)){
            throw new IllegalArgumentException("Agency ID is invalid!");
        }
        this.id = id;
    }

    /**
     * This method returns the name of the agency.
     * @return designation
     */
    public String getDesignation(){
        return designation;
    }

    /**
     * Change the designation value
     * @param designation
     */
    public void setDesignation(String designation) {
        if (designation == null || !(designation.trim().length() > 0)){
            throw new IllegalArgumentException("Agency designation is invalid!");
        }
        this.designation = designation;
    }

    /**
     * Get the agency email address
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Change the email address value
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        if (!(emailAddress.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9._%-]+\\.[A-Za-z]{2,}$"))){
            throw new IllegalArgumentException("Email Address is invalid!");
        }
        this.emailAddress = emailAddress;
    }

    /**
     * Get the phone number
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Change phone number value
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        if (!(phoneNumber.matches("\\(\\d{3}\\)\\s\\d{3}-\\d{4}") || phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}"))){
            throw new IllegalArgumentException("Phone Number is invalid");
        }
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the agency address
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Change the address
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Get the employee list
     * @return the list of employees
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * Get the list of orders
     * @return the list of orders
     */
    public List<Order> getOrders(){return orders;}

    /**
     * This method registers a new employee.
     *
     * @param name              The name of the employee to be registered.
     * @param passportCardNum   The passport card number of the employee to be registered.
     * @param taxNumber         The tax number of the employee to be registered.
     * @param phoneNumber       The phone number of the employee to be registered.
     * @param address           The address of the employee to be registered.
     * @param roles             The roles of the employee to be registered.
     * @param agency            The agency of the employee to be registered.
     * @return
     */
    public Optional<Employee> registerEmployee(String name, String passportCardNum, String taxNumber,
                                               String phoneNumber, String emailAddress, Address address, List<Role> roles, Agency agency){

        Optional<Employee> optionalValue = Optional.empty();

        Employee employee = new Employee(name,passportCardNum,taxNumber,phoneNumber,emailAddress,address,roles,agency);

        if (addEmployee(employee)){
            optionalValue = Optional.of(employee);
        }

        return optionalValue;
    }

    /**
     * Get the employee repository from the Repositories class
     * @return the employee repository
     */
    public EmployeeRepository getEmployeeRepository(){
        return Repositories.getInstance().getEmployeeRepository();
    }

    /**
     * This method adds an employee to the list of employees.
     *
     * @param employee The employee to be added.
     * @return True if the employee was added successfully.
     */
    private boolean addEmployee(Employee employee) {
        boolean success = false;

        EmployeeRepository employeeRepository = getEmployeeRepository();

        if (validateEmployee(employee, employeeRepository)) {
            success = employees.add(employee.clone());
            employeeRepository.add(employee);
        }
        return success;
    }

    /**
     * This method validates the employee, checking for duplicates.
     *
     * @param employee The employee to be validated.
     * @return True if the employee is valid.
     */
    private boolean validateEmployee(Employee employee, EmployeeRepository employeeRepository) {
        return employeesDoNotContain(employee, employeeRepository);
    }

    /**
     * This method checks if the employee is already in the list of employee.
     *
     * @param employee The employee to be checked.
     * @return True if the employee is not in the list of employee.
     */
    private boolean employeesDoNotContain(Employee employee, EmployeeRepository employeeRepository) {
        return !(employees.contains(employee)) && !(employeeRepository.getEmployees().contains(employee));
    }

    /**
     * This method adds the order to the orders list and to the order repository
     * @param order
     * @return true/false
     */
    public boolean addOrder(Order order){
        boolean success = false;
        OrderRepository orderRepository = Repositories.getInstance().getOrderRepository();

        if (validateOrder(order, orderRepository)) {
            success = orders.add(order);
            orderRepository.addOrder(order);
        }
        return success;
    }

    /**
     * Checks if the order is already in the order list or in the order repository
     * @param order
     * @param orderRepository
     * @return
     */
    private boolean validateOrder(Order order, OrderRepository orderRepository){
        return !(orders.contains(order)) && !(orderRepository.getOrders().contains(order));
    }

    /**
     * This method verifies if two agencies are equal based on their id.
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Agency)) {
            return false;
        }
        Agency that = (Agency) o;
        return id.equals(that.id);
    }

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Agency clone(){
        Agency clone = new Agency(this.id, this.designation, this.emailAddress, this.phoneNumber);

        for (Employee in : this.employees) {
            clone.employees.add(in.clone());
        }

        for (Order in : this.orders) {
            clone.orders.add(in.clone());
        }

        return clone;
    }

    /**
     * To string method
     * @return the string with the instance attribute values
     */
    @Override
    public String toString() {
        return "Agency{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}' + orders;
    }
}
