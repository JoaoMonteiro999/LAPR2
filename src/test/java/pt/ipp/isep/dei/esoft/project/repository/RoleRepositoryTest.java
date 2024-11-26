package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Role;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RoleRepositoryTest {

    @Test void getRoleBySelectionEmptyList() {
        RoleRepository roleRepository = new RoleRepository();
        String chosenRole = "Agent";
        assertThrows(IllegalArgumentException.class,
                () -> roleRepository.getRoleBySelection(chosenRole));
    }

    @Test void ensureNewRoleSuccessfullyAdded(){
        RoleRepository roleRepository = new RoleRepository();
        String chosenRole = "Agent";
        Role role = new Role(chosenRole);
        roleRepository.add(role);
    }

    @Test void ensureGetRoleForExistingAgency() {
        RoleRepository roleRepository = new RoleRepository();
        String chosenRole = "Agent";
        Role role1 = new Role(chosenRole);
        roleRepository.add(role1);
        Role role2 = roleRepository.getRoleBySelection(chosenRole);
        assertEquals(role1, role2);
    }

    @Test void ensureGetRoleFailsForNonExistingAgency() {
        RoleRepository roleRepository = new RoleRepository();
        String chosenRole1 = "Agent";
        Role role1 = new Role(chosenRole1);
        roleRepository.add(role1);
        String chosenRole2 = "Store Manager";
        assertThrows(IllegalArgumentException.class,
                () -> roleRepository.getRoleBySelection(chosenRole2));
    }

    @Test void ensureGetRolesReturnsAnImmutableList() {
        RoleRepository roleRepository = new RoleRepository();
        String chosenRole1 = "Agent";
        Role role1 = new Role(chosenRole1);
        roleRepository.add(role1);

        assertThrows(UnsupportedOperationException.class,
                () -> roleRepository.getRoles().add(new Role("Store Manager")));
    }

    @Test void ensureGetRolesReturnsTheCorrectList() {
        //Arrange
        RoleRepository roleRepository = new RoleRepository();
        String chosenRole = "Agent";
        Role role = new Role(chosenRole);
        roleRepository.add(role);
        int expectedSize = 1;

        //Act
        int size = roleRepository.getRoles().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(role, roleRepository.getRoles().get(size - 1));
    }

    @Test void ensureAddingDuplicateRoleFails() {
        //Arrange
        RoleRepository roleRepository = new RoleRepository();
        Role role = new Role("Agent");
        //Add the first task
        roleRepository.add(role);

        //Act
        Optional<Role> duplicateRole = roleRepository.add(role);

        //Assert
        assertTrue(duplicateRole.isEmpty());
    }

    @Test void ensureAddingDifferentRolesWorks() {
        //Arrange
        RoleRepository roleRepository = new RoleRepository();
        Role role1 = new Role("Agent");
        Role role2 = new Role("Store Manager");
        //Add the first task
        roleRepository.add(role1);

        //Act
        Optional<Role> result = roleRepository.add(role2);

        //Assert
        assertEquals(role2, result.get());
    }
}