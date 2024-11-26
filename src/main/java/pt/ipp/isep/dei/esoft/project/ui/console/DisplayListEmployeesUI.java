package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class DisplayListEmployeesUI implements Runnable{

    /**
     * Displays a list of all employees.
     */
    public void run(){
        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println("     Employees Registered in the System:     ");
        System.out.println("---------------------------------------------");

        Repositories repositories = Repositories.getInstance();

        //Get Employee Repository
        EmployeeRepository employeeRepository = repositories.getEmployeeRepository();

        for (Employee employee : employeeRepository.getEmployees()) {
            if (employeeRepository.getEmployees() != null) {
                System.out.print("Name: " + employee.getName() + "\nEmail: " + employee.getEmailAddress() +
                        "\nTax Number: " + employee.getTaxNumber() +
                        "\nAgency: " + employee.getAgency().getDesignation() + "\n");
                employee.showRoles();
                System.out.println();
            }
            System.out.println("---------------------------------------------");
        }
        System.out.println("---------------------------------------------");
        System.out.println();
    }

}
