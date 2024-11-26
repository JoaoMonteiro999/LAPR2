package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ClientRepository;

import java.util.Optional;

/**
 * Controller for registering a client
 */
public class RegisterClientController {

    /**
     * Repositories
     */
    private Repositories repository;

    /**
     * Client repository
     */
    private ClientRepository clientRepository;

    /**
     * Default constructor
     */
    public RegisterClientController(){
        this.repository = Repositories.getInstance();
        this.clientRepository = repository.getClientRepository();
    }

    /**
     * Responsible for registering the client
     * @param name
     * @param passportCardNum
     * @param taxNumber
     * @param phoneNumber
     * @param emailAddress
     * @param address
     * @param password
     * @return the client to be registered
     */
    public Optional<Client> registerClient(String name, String passportCardNum, String taxNumber, String phoneNumber, String emailAddress, Address address, String password){

        ClientRepository clientRepository = repository.getClientRepository();
        Optional<Client> newClient = Optional.empty();

        newClient = clientRepository.registerClient(name, passportCardNum, taxNumber, phoneNumber, emailAddress, address, password);

        return newClient;
    }

    /**
     * Calls method to send an email to the registered client
     * @param client
     */
    public void sendEmail(Client client) {
        client.sendEmail();
    }

}
