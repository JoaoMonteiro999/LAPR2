package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

public class Role implements Serializable {

    private static final long serialVersionUID = 8679935378572580864L;

    private final String chosenRole;

    /**
     * This is the constructor of the role.
     *
     * @return The name of the role.
     */
    public Role(String chosenRole){

        if (chosenRole == null || !(chosenRole.trim().length() > 0)){
            throw new IllegalArgumentException("Chosen role is invalid!");
        }
        this.chosenRole = chosenRole;
    }

    /**
     * This method verifies if two roles are equal.
     * @return true or false
     */
    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (!(o instanceof Role)){
            return false;
        }
        Role that = (Role) o;
        return chosenRole.equals(that.chosenRole);
    }

    /**
     * This method returns the name of the role.
     *
     * @return The name of the role.
     */
    public String getRolesBySelection() {
        return this.chosenRole;
    }

    /**
     * Clone method.
     *
     * @return A clone of the role.
     */
    public Role clone() {
        return new Role(this.chosenRole);
    }

    @Override
    public String toString() {
        return "Role{" +
                "chosenRole='" + chosenRole + '\'' +
                '}';
    }
}
