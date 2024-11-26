package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Role;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RegisterEmployeeControllerIT {

    @Test void ensureRegisterEmployeeWorks(){
        //Arrange
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        RoleRepository roleRepository = new RoleRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        //Fill Role Repository
        roleRepository.add(new Role("Agent"));

        //Fill Agency Repository
        Agency agency = new Agency(123,"Agency1","a@b.com","(999) 999-9999");
        agencyRepository.add(agency);

        //Add authentication for user john.doe@this.company.com
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Main Administrator", "john.doe@this.company.com", "admin",
                AuthenticationController.ROLE_ADMIN);


        RegisterEmployeeController controller =
                new RegisterEmployeeController(roleRepository,agencyRepository,employeeRepository,authenticationRepository);

        //Act
        List<String> chosenRoles = Arrays.asList("Agent","Store Manager");
        Address address = new Address("Street","City","District","STT","12345");
        Optional<Employee> newEmployee =
                controller.registerEmployee("EmployeeOne", "123456789",
                        "123456789", "(123) 123-1234", "a@b.com", address, chosenRoles,123);
    }

    @Test void ensureGetRolesWork() {
        //Arrange
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        RoleRepository roleRepository = new RoleRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        //Fill Role Repository
        Role role1 = new Role("Agent");
        roleRepository.add(role1);

        Role role2 = new Role("Store Manager");
        roleRepository.add(role2);

        ArrayList<Role> expectedRole = new ArrayList<Role>();
        expectedRole.add(role1);
        expectedRole.add(role2);

        //Add authentication for user john.doe@this.company.com
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Main Administrator", "john.doe@this.company.com", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.doLogin("john.doe@this.company.com", "admin");

        RegisterEmployeeController controller =
                new RegisterEmployeeController(roleRepository,agencyRepository, employeeRepository, authenticationRepository);

        //Act
        List<Role> roles = controller.getRoles();

        assertArrayEquals(expectedRole.toArray(), roles.toArray());
    }

    @Test void ensureGetAgenciesWork() {
        //Arrange
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        RoleRepository roleRepository = new RoleRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        //Fill Agency Repository
        Agency agency1 = new Agency(123,"Agency1","a@b.com","(111) 555-1234");
        agencyRepository.add(agency1);

        Agency agency2 = new Agency(987,"Agency2","c@d.com","(999) 999-9999");
        agencyRepository.add(agency2);

        ArrayList<Agency> expectedAgency = new ArrayList<Agency>();
        expectedAgency.add(agency1);
        expectedAgency.add(agency2);

        //Add authentication for user john.doe@this.company.com
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Main Administrator", "john.doe@this.company.com", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.doLogin("john.doe@this.company.com", "admin");

        RegisterEmployeeController controller =
                new RegisterEmployeeController(roleRepository,agencyRepository, employeeRepository, authenticationRepository);

        //Act
        List<Agency> agencies = controller.getAgencies();

        assertArrayEquals(expectedAgency.toArray(), agencies.toArray());
    }
}