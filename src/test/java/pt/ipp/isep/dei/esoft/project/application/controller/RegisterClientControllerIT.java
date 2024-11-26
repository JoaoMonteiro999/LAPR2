package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.ClientRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Optional;

class RegisterClientControllerIT {

    @Test
    void ensureRegisterClientWorks(){
        //Arrange
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        ClientRepository clientRepository = new ClientRepository();


        RegisterClientController controller =
                new RegisterClientController();

        //Act
        Address address = new Address("Street","City","District","STT","12345");
        Optional<Client> newClient =
                controller.registerClient("ClientOne", "123456789",
                        "123456789", "(123) 123-1234", "a@b.com", address, "qwPAS12");
    }

}