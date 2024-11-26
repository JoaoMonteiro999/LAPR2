package pt.ipp.isep.dei.esoft.project.serialization;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.dtos.AuthDTO;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static pt.ipp.isep.dei.esoft.project.serialization.ReadAndWriteBinaryFile.readBinaryFile;

public class LoadSerializationFiles implements Runnable{

    public void run() {
        //addSerialization();
    }

    /**
     * Method for the serialization
     */
    private void addSerialization(){
        addRoleSer();
        addAuthUserSer("employeeCredentials.txt"); //for employees
        addAuthUserSer("email.txt"); //for clients
        addAgencySer();
        addClientSer();
        addEmployeeSer();
        addPropertySer();
        addOrderSer();
        addAnnouncementSer();
        addRequestSer();

    }

    /**
     * Method for the employee serialization
     */
    private void addEmployeeSer(){
        List<Employee> employees = (List<Employee>) readBinaryFile("serialization/employee.ser");
        if (employees != null){
            for (Employee emp:employees) {
                Repositories.getInstance().getEmployeeRepository().add(emp);
            }
        }
    }

    /**
     * Method for the property serialization
     */
    private void addPropertySer() {
        PropertiesRepository propertiesRepository = Repositories.getInstance().getPropertiesRepository();
        List<Property> properties = (List<Property>) readBinaryFile("serialization/property.ser");
        if (properties != null) {
            for (Property property : properties) {
                propertiesRepository.add(property);
            }
        }
    }

    /**
     * Method for the client serialization
     */
    private void addClientSer() {
        ClientRepository clientRepository = Repositories.getInstance().getClientRepository();
        List<Client> clients = (List<Client>) readBinaryFile("serialization/client.ser");

        if (clients != null) {
            if (clientRepository.getClients() != null) {
                for (Client client : clients) {
                    clientRepository.addClient(client);
                }
            }
        }
    }

    /**
     * Method for the users serialization
     */
    private void addAuthUserSer(String fileName){
        List<AuthDTO> auths = readUserDTOsFromFile(fileName);
        for (AuthDTO auth : auths) {
            if(auth.getRoles().length == 1){
                Repositories.getInstance().getAuthenticationRepository().addUserWithRole(auth.getName(), auth.getEmail(),auth.getPassword(),auth.getRoles()[0]);

            }else{
                Repositories.getInstance().getAuthenticationRepository().addUserWithRoles(auth.getName(), auth.getEmail(),auth.getPassword(),auth.getRoles());
            }

        }
    }

    /**
     * Method for reading the files to serialize
     */
    private List<AuthDTO> readUserDTOsFromFile(String filename) {
        List<AuthDTO> auths = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));

            for (String line : lines) {

                String[] parts = line.split(";");
                if (parts.length == 4   ) {
                    String name = parts[0].trim();
                    String email = parts[1].trim();
                    String password = parts[2].trim();

                    String roles = parts[3].trim();
                    roles = roles.replace("[", "").replace("]", "").trim();
                    String [] dividedRoles = roles.split(",");

                    AuthDTO authDTO = new AuthDTO(name, email, password,dividedRoles);
                    auths.add(authDTO);
                }
            }

        } catch (IOException e) {
            //Print nothing
        }

        return auths;
    }

    /**
     * Method for the request serialization
     */
    private void addRequestSer() {
        RequestsRepository requestsRepository = Repositories.getInstance().getRequestsRepository();
        List<Request> requests = (List<Request>) readBinaryFile("serialization/request.ser");

        if (requests != null) {
            if (requestsRepository.getRequestList() != null) {
                for (Request request : requests) {
                    requestsRepository.add(request);
                }
            }
        }
    }

    /**
     * Method for the announcement serialization
     */
    private void addAnnouncementSer() {
        SaleAnnouncementRepository saleAnnouncementRepository = Repositories.getInstance().getSaleAnnouncementRepository();
        List<SaleAnnouncement> announcements = (List<SaleAnnouncement>) readBinaryFile("serialization/announcement.ser");

        if (announcements != null) {
            for (SaleAnnouncement announcement : announcements) {
                saleAnnouncementRepository.add(announcement);
            }
        }
    }

    /**
     * Method for the order serialization
     */
    private void addOrderSer() {
        OrderRepository orderRepository = Repositories.getInstance().getOrderRepository();
        List<Order> orders = (List<Order>) readBinaryFile("serialization/order.ser");

        if (orders != null) {
            for (Order order : orders) {
                orderRepository.addOrder(order);
            }
        }
    }

    /**
     * Method for the agency serialization
     */
    private void addAgencySer(){
        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyRepository();
        List<Agency> agencies = (List<Agency>) readBinaryFile("serialization/agency.ser");

        if (agencies != null){
            for (Agency agency : agencies) {
                agencyRepository.add(agency);
            }
        }
    }

    /**
     * Method for the role serialization
     */
    private void addRoleSer(){
        RoleRepository roleRepository = Repositories.getInstance().getRoleRepository();
        List<Role> roles = (List<Role>) readBinaryFile("serialization/role.ser");

        if (roles != null){
            for (Role role : roles) {
                roleRepository.add(role);
            }
        }
    }
}
