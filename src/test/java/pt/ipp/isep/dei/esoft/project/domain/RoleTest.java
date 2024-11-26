package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test void testEqualsSameObject() {
        Role role = new Role("Store Manager");
        assertEquals(role, role);
    }

    @Test void testEqualsDifferentClass() {
        Role role = new Role("Store Manager");
        assertNotEquals("", role);
    }

    @Test void testEqualsNull() {
        Role role = new Role("Store Manager");
        assertNotEquals(null, role);
    }

    @Test void testEqualsDifferentObject() {
        Role role1 = new Role("Store Manager");
        Role role2 = new Role("Administrator");
        assertNotEquals(role1, role2);
    }

    @Test void testEqualsSameObjectSameDescription() {
        Role role1 = new Role("Store Manager");
        Role role2 = new Role("Store Manager");
        assertEquals(role1, role2);
    }

    @Test void testEqualsForDifferentObjectType() {
        Role role = new Role("Store Manager");
        assertNotEquals(role, new Object());
    }

    @Test void ensureGetRolesBySelectionWorks() {
        Role role = new Role("Store Manager");
        assertEquals("Store Manager", role.getRolesBySelection());
    }

    @Test void ensureCloneWorks() {
        Role role = new Role("Store Manager");
        Role clone = role.clone();
        assertEquals(role, clone);
    }

}