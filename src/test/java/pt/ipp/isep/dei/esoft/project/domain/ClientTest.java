package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void ensureTwoClientsWithSameAttributeValuesEquals(){

        Address address = new Address("Street","City","District","STT","12345");

        Client client1 = new Client("ClientOne","123456789", "123456789", "(999) 555-1234", "a@b.com", address, "qwPAS12");
        Client client2 = new Client("ClientTwo","123456789", "123456789", "(999) 555-1234", "a@b.com", address, "qwPAS12");
        assertEquals(client1,client2);
    }

    @Test void ensureClientWithDifferentAttributeValuesNotEquals(){

        Address address = new Address("Street","City","District","STT","12345");

        Client client1 = new Client("ClientOne","123456789", "123456789", "(999) 555-1234", "a@b.com", address, "qwPAS12");
        Client client2 = new Client("ClientTwo","987654321", "987654321", "(999) 555-1234", "a@b.com", address, "rtPAS12");
        assertNotEquals(client1,client2);
    }

    @Test void ensureClientDoesNotEqualNull() {

        Address address = new Address("Street","City","District","STT","12345");

        Client client = new Client("ClientOne","123456789","123456789","(999) 555-1234", "a@b.com", address,"qwPAS12");
        assertNotEquals(client, null);
    }

    @Test void ensureClientDoesNotEqualOtherObject() {

        Address address = new Address("Street","City","District","STT","12345");

        Client client = new Client("ClientOne","123456789","123456789","(999) 555-1234", "a@b.com", address, "qwPAS12");
        assertNotEquals(client, new Object());
    }

    @Test void ensureTheSameObjectIsEqual() {

        Address address = new Address("Street","City","District","STT","12345");

        Client client = new Client("ClientOne","123456789","123456789","(999) 555-1234", "a@b.com", address, "qwPAS12");
        assertEquals(client, client);
    }

    @Test void ensureCloneWorks(){

        Address address = new Address("Street","City","District","STT","12345");

        Client client = new Client("ClientOne","123456789","123456789","(999) 555-1234", "a@b.com", address, "qwPAS12");
        Client clone = client.clone();
        assertEquals(client,clone);
    }

}