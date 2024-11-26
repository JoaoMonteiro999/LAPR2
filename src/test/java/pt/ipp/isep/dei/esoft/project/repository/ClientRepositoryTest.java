package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Client;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ClientRepositoryTest {

    @Test void ensureNewClientSuccessfullyAdded(){
        ClientRepository clientRepository = new ClientRepository();
        Address address = new Address("Street","City","District","STT","12345");
        Client client = new Client("Client","123456789","123456789","(555) 555-5555","a@b.com",address,"qwPAS12");
        clientRepository.addClient(client);
    }

//    @Test void ensureGetClientsReturnsAnImmutableList() {
//        ClientRepository clientRepository = new ClientRepository();
//        Address address = new Address("Street","City","District","STT","12345");
//        Client client = new Client("Client","123456789","123456789","(555) 555-5555","a@b.com",address,"qwPAS12");
//        clientRepository.addClient(client);
//
//        assertThrows(UnsupportedOperationException.class,
//                () -> clientRepository.getClients().add(new Client("ClientTwo","987654321","987654321","(999) 999-9999","c@d.com",address,"rtPAS12")));
//    }

    @Test
    void ensureGetClientsReturnsTheCorrectList() {
        //Arrange
        ClientRepository clientRepository = new ClientRepository();
        Address address = new Address("Street","City","District","STT","12345");
        Client client = new Client("Client","123456789","123456789","(555) 555-5555","a@b.com",address,"qwPAS12");
        clientRepository.add(client);
        int expectedSize = 1;

        //Act
        int size = clientRepository.getClients().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(client, clientRepository.getClients().get(size - 1));
    }

    @Test
    void ensureAddingDuplicateClientFails() {
        //Arrange
        ClientRepository clientRepository = new ClientRepository();
        Address address = new Address("Street","City","District","STT","12345");
        Client client = new Client("Client","123456789","123456789","(555) 555-5555","a@b.com",address,"qwPAS12");
        //Add the first client
        clientRepository.add(client);

        //Act
        Optional<Client> duplicateClient = clientRepository.add(client);

        //Assert
        assertTrue(duplicateClient.isEmpty());
    }

    @Test
    void ensureAddingDifferentClientsWorks() {
        //Arrange
        ClientRepository clientRepository = new ClientRepository();
        Address address = new Address("Street","City","District","STT","12345");

        Client client1 = new Client("Client","123456789","123456789","(555) 555-5555","a@b.com",address,"qwPAS12");
        Client client2 = new Client("ClientTwo","987654321","987654321","(999) 999-9999","c@d.com",address,"rtPAS12");
        //Add the first client
        clientRepository.add(client1);

        //Act
        Optional<Client> result = clientRepository.add(client2);

        //Assert
        assertEquals(client2, result.get());
    }
}