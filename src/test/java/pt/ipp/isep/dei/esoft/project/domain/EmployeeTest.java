package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test void ensureTwoEmployeesWithSameAttributeValuesEquals(){

        Address address = new Address("Street","City","District","STT","12345");
        List<Role> roles = Arrays.asList(new Role("Agent"), new Role("Store Manager"));
        Agency agency = new Agency(1,"Agency1", "a@b.com","(999) 555-9876");

        Employee employee1 = new Employee("Employee","123456789", "123456789", "(999) 555-1234", "a@b.com", address, roles, agency);
        Employee employee2 = new Employee("Employee","123456789", "123456789", "(999) 555-1234", "a@b.com", address, roles, agency);
        assertEquals(employee1,employee2);
    }

    @Test void ensureEmployeeWithDifferentAttributeValuesNotEquals(){

        Address address = new Address("Street","City","District","STT","12345");
        List<Role> roles = Arrays.asList(new Role("Agent"), new Role("Store Manager"));
        Agency agency = new Agency(1,"Agency1", "a@b.com","(999) 555-9876");

        Employee employee1 = new Employee("EmployeeOne","123456789","123456789","(999) 555-1234", "a@b.com", address, roles, agency);
        Employee employee2 = new Employee("EmployeeTwo","987654321","987654321","(999) 555-1234", "a@b.com", address, roles, agency);
        assertNotEquals(employee1,employee2);
    }

    @Test void ensureEmployeeDoesNotEqualNull() {

        Address address = new Address("Street","City","District","STT","12345");
        List<Role> roles = Arrays.asList(new Role("Agent"), new Role("Store Manager"));
        Agency agency = new Agency(1,"Agency1", "a@b.com","(999) 555-9876");

        Employee employee1 = new Employee("Employee","123456789","123456789","(999) 555-1234", "a@b.com", address, roles, agency);
        assertNotEquals(employee1, null);
    }

    @Test void ensureEmployeeDoesNotEqualOtherObject() {

        Address address = new Address("Street","City","District","STT","12345");
        List<Role> roles = Arrays.asList(new Role("Agent"), new Role("Store Manager"));
        Agency agency = new Agency(1,"Agency1", "a@b.com","(999) 555-9876");

        Employee employee1 = new Employee("Employee","123456789","123456789","(999) 555-1234", "a@b.com", address, roles, agency);
        assertNotEquals(employee1, new Object());
    }

    @Test void ensureTheSameObjectIsEqual() {

        Address address = new Address("Street","City","District","STT","12345");
        List<Role> roles = Arrays.asList(new Role("Agent"), new Role("Store Manager"));
        Agency agency = new Agency(1,"Agency1", "a@b.com","(999) 555-9876");

        Employee employee1 = new Employee("Employee","123456789","123456789","(999) 555-1234", "a@b.com", address, roles, agency);
        assertEquals(employee1, employee1);
    }

    @Test void ensureCloneWorks(){

        Address address = new Address("Street","City","District","STT","12345");
        List<Role> roles = Arrays.asList(new Role("Agent"), new Role("Store Manager"));
        Agency agency = new Agency(1,"Agency1", "a@b.com","(999) 555-9876");

        Employee employee = new Employee("Employee","123456789","123456789","(999) 555-1234", "a@b.com", address, roles, agency);
        Employee clone = employee.clone();
        assertEquals(employee,clone);
    }
}