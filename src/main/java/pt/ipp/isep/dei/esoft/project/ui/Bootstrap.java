package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.dtos.AuthDTO;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pt.ipp.isep.dei.esoft.project.serialization.ReadAndWriteBinaryFile.readBinaryFile;

public class Bootstrap implements Runnable {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public void run() {
        //addRoles();
        //addAgencies();
        //addProperties();
        //addSaleAnnouncements();
        addUsers();
        //System.out.println(Arrays.toString(Repositories.getInstance().getClientRepository().getClients().toArray()));
        //addRequests();
        //addEmployee();
        //addOrders();
        //addVisitRequests();
    }

    private void addSaleAnnouncements() {
        //get property repository
        SaleAnnouncementRepository saleAnnouncementRepository = Repositories.getInstance().getSaleAnnouncementRepository();
        saleAnnouncementRepository.add(new SaleAnnouncement(new Property(250, 1540, 6500, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling(5,5,5)),new Commission(false,1000),45,"2020/07/07"));
        saleAnnouncementRepository.add(new SaleAnnouncement(new Property(670, 2300, 54000, new Address("Street", "London", "London", "UK", "30000"),new PropertyType(1), new BusinessType("Sale"), new Dwelling()),new Commission(false,1000),45,"2020/07/07"));
        saleAnnouncementRepository.add(new SaleAnnouncement (new Property(830, 530, 75000, new Address("Camino", "Barcelona", "Barcelona", "ES", "50000"),new PropertyType(2), new BusinessType("Rent"), new Dwelling()),new Commission(true,5),20,"2020/07/07"));
    }


    private void addRoles() {
        //TODO: add bootstrap Roles here

        //get role repository
        RoleRepository roleRepository = Repositories.getInstance().getRoleRepository();
        roleRepository.add(new Role("Agent"));
        roleRepository.add(new Role("Store Manager"));
        roleRepository.add(new Role("Store Network Manager"));
        roleRepository.add(new Role("Administrator"));
    }

    private void addAgencies(){
        //TODO: add bootstrap Roles here

        //get agency repository
        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyRepository();
        agencyRepository.add(new Agency(123,"Agency1","a@b.com","(555) 555-5555"));
        agencyRepository.add(new Agency(456,"Agency2","c@d.com","(999) 999-9999"));
        agencyRepository.add(new Agency(789,"Agency3","e@d.com","(777) 777-7777"));
    }

    private void addProperties(){
        //TODO: add bootstrap Properties here

        //get property repository
        PropertiesRepository propertiesRepository = Repositories.getInstance().getPropertiesRepository();
        propertiesRepository.add(new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling(5,5,5)));
        propertiesRepository.add(new Property(300, 300, 3000, new Address("Street", "London", "London", "UK", "30000"),new PropertyType(1), new BusinessType("Sale"), new Dwelling()));
        propertiesRepository.add(new Property(500, 500, 5000, new Address("Camino", "Barcelona", "Barcelona", "ES", "50000"),new PropertyType(2), new BusinessType("Rent"), new Dwelling()));

    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE, AuthenticationController.ROLE_EMPLOYEE);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_STORE_MANAGER, AuthenticationController.ROLE_STORE_MANAGER);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_STORE_NETWORK_MANAGER, AuthenticationController.ROLE_STORE_NETWORK_MANAGER);

        authenticationRepository.addUserWithRole("Administrator", "admin@this.app", "ADMIN12",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "EMP1234",
                AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("Josefino", "agent@this.app", "AGENT12",
                AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Client", "client@this.app", "ABCd123",
                AuthenticationController.ROLE_CLIENT);

        authenticationRepository.addUserWithRole("ClientTwo", "client2@this.app", "CDEf789",
                AuthenticationController.ROLE_CLIENT);

        authenticationRepository.addUserWithRole("StoreManager", "sm@this.app", "SMm1234",
                AuthenticationController.ROLE_STORE_MANAGER);

        authenticationRepository.addUserWithRole("StoreNetworkManager", "snm@this.app", "SNM1234",
                AuthenticationController.ROLE_STORE_NETWORK_MANAGER);

        authenticationRepository.addUserWithRoles("Person", "person@this.app", "person", new String[]{AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_AGENT});

        //add the clients
        ClientRepository clientRepository =  Repositories.getInstance().getClientRepository();
        clientRepository.addClient(new Client("Client","111222222","222333444","(555) 555-5555", "client@this.app", new Address("Rua", "Porto", "Porto", "PT", "10000"),"ABCd123"));
        clientRepository.addClient(new Client("ClientTwo","111222223","222333445","(555) 555-5556", "client2@this.app", new Address("Rua", "Porto", "Porto", "PT", "10000"),"CDEf789"));


        //add agent
        EmployeeRepository employeeRepository  = Repositories.getInstance().getEmployeeRepository();
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));
        employeeRepository.add(new Employee("Josefino","123123123","321321321","123-123-1234","agent@this.app",new Address("Rua", "Vila", "Porto", "PT", "20000"),roles,new Agency(123,"designation","agencia@gmail.com","999-999-9999")));

    }

    private void addRequests(){
        RequestsRepository requestsRepository = Repositories.getInstance().getRequestsRepository();
        requestsRepository.add(new Request(new Property(200, 200, 2000, new Address("Rua", "Vila", "Porto", "PT", "20000"),new PropertyType(0), new BusinessType("Sale"), new Dwelling()),"20/05/2010"));
        requestsRepository.add(new Request(new Property(400, 400, 4000, new Address("Ruita", "Povoa", "Porto", "PT", "40000"),new PropertyType(1), new BusinessType("Rent"), new Dwelling()),"10/09/2020"));
        requestsRepository.add(new Request(new Property(600, 600, 6000, new Address("Ruazona", "Leiria", "Lisboa", "PT", "60000"),new PropertyType(2), new BusinessType("Rent"), new Dwelling()),"05/05/2015"));
        requestsRepository.add(new Request(new Property(600, 600, 6000, new Address("Ruazona", "Leiria", "Lisboa", "PT", "60000"),new PropertyType(2), new BusinessType("Sale"), new Dwelling()),"20/05/2010"));
        requestsRepository.add(new Request(new Property(600, 600, 6000, new Address("Ruazona", "Leiria", "Lisboa", "PT", "60000"),new PropertyType(1), new BusinessType("Sale"), new Dwelling()),"29/05/2010"));
        requestsRepository.add(new Request(new Property(600, 600, 6000, new Address("Ruazona", "Leiria", "Lisboa", "PT", "60000"),new PropertyType(0), new BusinessType("Sale"), new Dwelling()),"30/09/2010"));
        requestsRepository.add(new Request(new Property(600, 600, 6000, new Address("Ruazona", "Leiria", "Lisboa", "PT", "60000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling())));
    }

//    private void addEmployee(){
//        //TODO: add employees bootstrap here
//        //get employee repository
//        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
//
//        //get role repository
//        RoleRepository roleRepository = Repositories.getInstance().getRoleRepository();
//
//        //get agency repository
//        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyRepository();
//
//        //get authentication repository
//        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
//
//        //Create and add agency
//        Agency agency = new Agency(123, "Agency", "a@b.com", "(555) 555-5555");
//        agencyRepository.add(agency);
//
//        List<Role> availableRoles = roleRepository.getRoles();
//        List<Role> roles = Arrays.asList(new Role("Agent"), new Role("Store Manager"));
//
//        Employee employee = new Employee("Employee",123456789,123456789,"(555) 555-5555", "a@b.com", new Address("Street","City","District","STT",12345),roles,agency);
//
//        agency.registerEmployee("Employee",123456789,123456789,"(555) 555-5555", "a@b.com", new Address("Street","City","District","STT",12345),roles,agency);
//        employeeRepository.add(employee);
//
//        authenticationRepository.addUserWithRole(employee.getName(),employee.getEmailAddress(),PasswordGenerator.generatePassword(),AuthenticationController.ROLE_AGENT);
//    }

    private void addOrders(){
        OrderRepository orderRepository = Repositories.getInstance().getOrderRepository();
        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyRepository();

        Property property1 = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling(5,5,5));
        Property property2 = new Property(300, 300, 3000, new Address("Street", "London", "London", "UK", "30000"),new PropertyType(1), new BusinessType("Sale"), new Dwelling());
        Property property3 = new Property(500, 500, 5000, new Address("Camino", "Barcelona", "Barcelona", "ES", "50000"),new PropertyType(2), new BusinessType("Rent"), new Dwelling());

        Agency agency1 = new Agency(999,"Agency1","a@b.com","(555) 555-5555");
        Agency agency2 = new Agency(888,"Agency2","c@d.com","(999) 999-9999");
        Agency agency3 = new Agency(777,"Agency3","e@d.com","(777) 777-7777");

        Order order1 = new Order(property1,1000,"client2@this.app");
        Order order2 = new Order(property2, 3000, "client@this.app");
        Order order3 = new Order(property3, 5000, "client2@this.app");

        agency1.addOrder(order1);
        agency2.addOrder(order2);
        agency3.addOrder(order3);

        agencyRepository.add(agency1);
        agencyRepository.add(agency2);
        agencyRepository.add(agency3);

        //mark 2 orders accepted
        orderRepository.getOrders().get(0).setOrderAccepted(true);
        orderRepository.getOrders().get(1).setOrderAccepted(true);

    }



    private void addVisitRequests() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = "2023-06-25";
        VisitScheduleRequestRepository visitScheduleRequestRepository = Repositories.getInstance().getVisitScheduleRequestRepository();
        for (int i = 1; i < 6; i++) {
            List<ScheduleSlot> scheduleSlotList = new ArrayList<>();
            LocalDate localDate = LocalDate.parse(dateString, formatter);
            ScheduleSlot slot = new ScheduleSlot(localDate, 8 + (2 * i), 10 + (2 * i));
            scheduleSlotList.add(slot);
            visitScheduleRequestRepository.addVisitScheduleRequest(new VisitScheduleRequest(new Property(i * 100, i * 545, i * 2000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(0), new BusinessType("Rent"), new Dwelling(i, i, i)), new Client("Client", "111222222", "222333444", "(555) 555-5555", "client@this.app", new Address("Rua", "Porto", "Porto", "PT", "10000"), "ABCd123"), scheduleSlotList));
            if (i == 1) {
                dateString = "2023-06-10";
            } else if (i == 2) {
                dateString = "2023-06-05";
            } else if (i == 3) {
                dateString = "2023-06-01";
            } else if (i == 4) {
                dateString = "2023-06-15";
            }
        }
    }




}
