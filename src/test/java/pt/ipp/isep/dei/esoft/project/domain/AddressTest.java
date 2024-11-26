package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test void testEqualsSameObject() {
        Address address = new Address("Street", "City", "District", "STT", "12345");
        assertEquals(address, address);
    }

    @Test void testEqualsDifferentClass() {
        Address address = new Address("Street", "City", "District", "STT", "12345");
        assertNotEquals("", address);
    }

    @Test void testEqualsNull() {
        Address address = new Address("Street", "City", "District", "STT", "12345");
        assertNotEquals(null, address);
    }

    @Test void testEqualsDifferentObject() {
        Address address1 = new Address("Street", "City", "District", "STT", "12345");
        Address address2 = new Address("Street", "City", "District", "STT", "12346");
        assertNotEquals(address1, address2);
    }

    @Test void testEqualsSameObjectSameAttributeValues() {
        Address address1 = new Address("Street", "City", "District", "STT", "12345");
        Address address2 = new Address("Street", "City", "District", "STT", "12345");
        assertEquals(address1, address2);
    }

    @Test void testEqualsForDifferentObjectType() {
        Address address = new Address("Street", "City", "District", "STT", "12345");
        assertNotEquals(address, new Object());
    }

    @Test void ensureCloneWorks(){
        Address address = new Address("Street", "City", "District", "STT", "12345");
        Address clone = address.clone();
        assertEquals(address,clone);
    }
}