package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller for registering an employee
 */
public class RegisterEmployeeController {

    /**
     * Role repository
     */
    private RoleRepository roleRepository = null;

    /**
     * Agency repository
     */
    private AgencyRepository agencyRepository = null;

    /**
     * Employee repository
     */
    private EmployeeRepository employeeRepository = null;

    /**
     * Authentication repository
     */
    private AuthenticationRepository authenticationRepository = null;

    /**
     * Default constructor
     */
    public RegisterEmployeeController(){
        getRoleRepository();
        getAgencyRepository();
        getEmployeeRepository();
        getAuthenticationRepository();
    }

    /**
     * Constructor
     * @param roleRepository
     * @param agencyRepository
     * @param employeeRepository
     * @param authenticationRepository
     */
    public RegisterEmployeeController(RoleRepository roleRepository,
                                      AgencyRepository agencyRepository, EmployeeRepository employeeRepository,
                                      AuthenticationRepository authenticationRepository){
        this.roleRepository = roleRepository;
        this.agencyRepository = agencyRepository;
        this.employeeRepository = employeeRepository;
        this.authenticationRepository = authenticationRepository;

    }

    /**
     * Get the role repository in the Repositories class
     * @return the role repository
     */
    private RoleRepository getRoleRepository() {
        if (roleRepository == null) {
            Repositories repositories = Repositories.getInstance();
            roleRepository = repositories.getRoleRepository();
        }
        return roleRepository;
    }

    /**
     * Get the agency repository in the Repositories class
     * @return the agency repository
     */
    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    /**
     * Get the employee repository in the Repositories class
     * @return the employee repository
     */
    private EmployeeRepository getEmployeeRepository(){
        if (employeeRepository == null){
            Repositories repositories = Repositories.getInstance();
            employeeRepository = repositories.getEmployeeRepository();
        }
        return employeeRepository;
    }

    /**
     * Get the authentication repository in the Repositories class
     * @return the authentication repository
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Responsible for registering the employee
     * @param name
     * @param passportCardNum
     * @param taxNumber
     * @param phoneNumber
     * @param emailAddress
     * @param address
     * @param chosenRoles
     * @param chosenAgencyId
     * @return the employee to be registered
     */
    public Optional<Employee> registerEmployee(String name, String passportCardNum, String taxNumber,
                                               String phoneNumber, String emailAddress, Address address, List<String> chosenRoles,
                                               Integer chosenAgencyId) {

        Optional<Employee> newEmployee = Optional.empty();
        try {
            List<Role> roles = new ArrayList<>();
            for (String chosenRole : chosenRoles) {
                Role role = getRolesBySelection(chosenRole);
                roles.add(role);
            }

            Agency agency = getAgenciesBySelection(chosenAgencyId);
            newEmployee = Optional.empty();

            newEmployee = agency.registerEmployee(name, passportCardNum, taxNumber, phoneNumber, emailAddress, address, roles, agency);

        } catch (IllegalArgumentException e) {
            System.out.println("Employee data is invalid");
        } catch (Exception e){
            e.printStackTrace();
        }
        return newEmployee;
    }

    /**
     * Get the role that was chosen
     * @param chosenRole
     * @return the role
     */
    private Role getRolesBySelection(String chosenRole){
        RoleRepository roleRepository = getRoleRepository();

        Role chosenByRole = roleRepository.getRoleBySelection(chosenRole);
        return chosenByRole;
    }

    /**
     * Get the agency that was chosen
     * @param chosenAgencyId
     * @return the agency
     */
    private Agency getAgenciesBySelection(Integer chosenAgencyId){
        AgencyRepository agencyRepository = getAgencyRepository();

        Agency chosenAgencyById = agencyRepository.getAgencyBySelection(chosenAgencyId);
        return  chosenAgencyById;
    }

    /**
     * Get the list of roles in the role repository
     * @return the list of roles
     */
    public List<Role> getRoles(){
        RoleRepository roleRepository = getRoleRepository();
        return roleRepository.getRoles();
    }

    /**
     * Get the list of agencies in the agency repository
     * @return the list of agencies
     */
    public List<Agency> getAgencies(){
        AgencyRepository agencyRepository = getAgencyRepository();
        return agencyRepository.getAgencies();
    }

    /**
     * Generates a password for the employee and saves it in a txt file
     * @param name
     * @param emailAddress
     * @param chosenRoles
     * @throws IOException
     */
    public void saveEmployeeCredentials(String name, String emailAddress, List<String> chosenRoles) throws IOException {
        PasswordGenerator.savePasswordTxt(name,emailAddress,chosenRoles);
    }

}
