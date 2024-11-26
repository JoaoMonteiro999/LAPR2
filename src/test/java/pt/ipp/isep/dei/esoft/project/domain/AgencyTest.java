package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgencyTest {

    @Test void testEqualsSameObject(){
        Agency agency = new Agency(123,"Agency1","a@b.com","(111) 555-1234");
        assertEquals(agency,agency);
    }

    @Test void testEqualsDifferentClass(){
        Agency agency = new Agency(123,"Agency1","a@b.com","(111) 555-1234");
        assertNotEquals("",agency);
    }

    @Test void testEqualsNull() {
        Agency agency = new Agency(123,"Agency1","a@b.com","(111) 555-1234");
        assertNotEquals(null, agency);
    }

    @Test void testEqualsDifferentObject(){
        Agency agency1 = new Agency(123,"Agency1","a@b.com","(111) 555-1234");
        Agency agency2 = new Agency(123,"Agency1","a@b.com","(111) 555-1234");
        assertEquals(agency1,agency2);
    }

    @Test void ensureEqualsFailsForDifferentObjectType(){
        Agency agency1 = new Agency(123,"Agency1","a@b.com","(111) 555-1234");
        Agency agency2 = new Agency(126,"Agency2","c@d.com","(111) 555-1234");
        assertNotEquals(agency1,agency2);
    }

    @Test void ensureEqualsFailsWhenComparingNull(){
        Agency agency = new Agency(123,"Agency1","a@b.com","(111) 555-1234");
        assertNotEquals(agency,null);
    }

    @Test void ensureEqualsSuccessWhenComparingSameObject(){
        Agency agency = new Agency(123,"Agency1","a@b.com","(111) 555-1234");
        assertEquals(agency,agency);
    }

    @Test void ensureGetAgenciesBySelectionWorks(){
        Agency agency = new Agency(123,"Agency1","a@b.com","(111) 555-1234");
        assertEquals(123,agency.getAgenciesBySelection());
    }

    @Test void ensureThatRegisterEmployeeWorks() {
        Address address = new Address("Street", "City", "District", "STT","12345");
        List<Role> roles = Arrays.asList(new Role("Agent"), new Role("Store Manager"));
        Agency agency = new Agency(123,"Agency1","a@b.com","(111) 555-1234");

        Employee expected = new Employee("Mike","123456789","987654321","(999) 555-1234","c@d.com",address,roles,agency);

        Optional<Employee> employee = agency.registerEmployee("Mike","123456789","987654321","(111) 555-1234","c@d.com",address,roles,agency);

        assertNotNull(employee);
        assertTrue(employee.isPresent());
        assertEquals(expected,employee.get());
    }

    @Test void ensureAddingDuplicateEmployeeFails(){
        Address address = new Address("Street", "City", "District", "STT","12345");
        List<Role> roles = Arrays.asList(new Role("Agent"), new Role("Store Manager"));
        Agency agency = new Agency(123,"Agency1","a@b.com","(111) 555-1234");

        Optional<Employee> originalEmployee = agency.registerEmployee("Mike","123456789","123456789","(999) 555-1234","c@d.com",address,roles,agency);

        Optional<Employee> duplicateEmployee = agency.registerEmployee("Mike","123456789","123456789","(999) 555-1234","c@d.com",address,roles,agency);

        assertTrue(duplicateEmployee.isEmpty());
    }


    @Test void ensureCloneWorks() {
        Agency agency = new Agency(123,"Agency1","a@b.com","(111) 555-1234");
        Agency clone = agency.clone();
        assertEquals(agency,clone);
    }
}