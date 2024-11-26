package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RoleRepository {

    private final List<Role> roles = new ArrayList<>();

    /**
     * This method returns an existing Role by its description.
     *
     * @param chosenRole The description of the role to be created.
     * @return The role.
     * @throws IllegalArgumentException if the role does not exist, which should never happen.
     */
    public Role getRoleBySelection(String chosenRole) {
        Role newRole = new Role(chosenRole);
        Role role = null;
        if (roles.contains(newRole)) {
            role = roles.get(roles.indexOf(newRole));
        }
        if (role == null) {
            throw new IllegalArgumentException(
                    "Role [" + chosenRole + "] does not exist.");
        }
        return role;
    }

    /**
     * This method adds a role to the list of roles
     * @param role
     * @return
     */
    public Optional<Role> add(Role role) {

        Optional<Role> newRole = Optional.empty();
        boolean operationSuccess = false;

        if (validateRole(role)) {
            newRole = Optional.of(role.clone());
            operationSuccess = roles.add(newRole.get());
        }

        if (!operationSuccess) {
            newRole = Optional.empty();
        }

        return newRole;
    }

    /**
     * This method checks if the role already exists in the list of roles
     * @param role
     * @return true/false
     */
    private boolean validateRole(Role role) {
        boolean isValid = !roles.contains(role);
        return isValid;
    }



    /**
     * This method returns a defensive (immutable) copy of the list of roles.
     *
     * @return The list of roles.
     */
    public List<Role> getRoles() {
        return List.copyOf(roles);
    }
}
