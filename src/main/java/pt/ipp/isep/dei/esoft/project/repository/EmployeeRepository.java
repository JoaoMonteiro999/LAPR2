package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    List<Employee> employees = new ArrayList<>();

    /**
     * This method adds an employee to the list of employees.
     *
     * @param employee The employee to be added.
     */
    public void add(Employee employee) {
        if (validateEmployee(employee)) {
            employees.add(employee.clone());
        }
    }

    /**
     * This method validates the employee, checking for duplicates.
     *
     * @param employee The employee to be validated.
     * @return True if the employee is valid.
     */
    private boolean validateEmployee(Employee employee) {
        return employeesDoNotContain(employee);
    }

    /**
     * This method checks if the employee is already in the list of employee.
     *
     * @param employee The employee to be checked.
     * @return True if the employee is not in the list of employee.
     */
    private boolean employeesDoNotContain(Employee employee) {
        return !employees.contains(employee);
    }

    /**
     * This method returns a list of all the employees.
     *
     * @return List of employees.
     */
    public List<Employee> getEmployees(){
        return employees;
    }
}
