package pt.ipp.isep.dei.esoft.project.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.isep.lei.esoft.auth.domain.model.Email;


public class ClientRepository{

    public List<Client> clients = new ArrayList<>();

    public List<Client> getClients() {
        return this.clients;
    }

    /**
     * Get client from the list of clients
     * @param email
     * @return client
     */
    public Client getClient(Email email){
        for(Client c: clients){
            if (c.getEmailAddress().equals(email.getEmail()))
                return c;
        }
        return null;
    }

    /**
     * Checks if the client already exists using the email
     * @param email
     * @return true/false
     */
    public Boolean clientExists(String email){
        for(Client c: clients){
            if (c.getEmailAddress().equals(email)) {
                return true;
            }
        }
        return null;


    }

    /**
     * This method adds a client to the lists of clients
     * @param client
     * @return true/false
     */
    public boolean addClient(Client client){
        boolean success = false;
        if (validateClient(client)) {
            success = clients.add(client.clone());
        }
        return success;
    }

    /**
     * This method adds a client to the lists of clients
     * @param client
     * @return client
     */
    public Optional<Client> add(Client client) {

        Optional<Client> newClient = Optional.empty();
        boolean operationSuccess = false;

        if (validateClient(client)) {
            newClient = Optional.of(client.clone());
            operationSuccess = clients.add(newClient.get());
        }

        if (!operationSuccess) {
            newClient = Optional.empty();
        }

        return newClient;
    }

    /**
     * This method checks if the client is already in the list of clients
     * @param client
     * @return true/false
     */
    public boolean validateClient(Client client){
        return !clients.contains(client);
    }

    /**
     * This methods registers the client
     * @param name
     * @param passportCardNum
     * @param taxNumber
     * @param phoneNumber
     * @param emailAddress
     * @param address
     * @param password
     * @return client
     */
    public Optional<Client> registerClient(String name, String passportCardNum, String taxNumber, String phoneNumber, String emailAddress, Address address, String password){

        Optional<Client> optionalValue = Optional.empty();

        Client client = new Client(name,passportCardNum,taxNumber,phoneNumber,emailAddress,address,password);

        if (addClient(client)){
            addClientAsUser(client);
            optionalValue = Optional.of(client);
        }
        return optionalValue;

    }

    /**
     * This method adds the registered client as an user
     * @param client
     */
    private void addClientAsUser(Client client){
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserWithRole(client.getName(), client.getEmailAddress(), client.getPassword(), AuthenticationController.ROLE_CLIENT);
    }
}
