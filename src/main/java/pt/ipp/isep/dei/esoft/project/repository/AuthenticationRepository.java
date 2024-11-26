package pt.ipp.isep.dei.esoft.project.repository;


import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

import java.io.Serializable;

public class AuthenticationRepository {

    /**
     * Authentication facade
     */
    private final AuthFacade authenticationFacade = new AuthFacade();

    /**
     * This method logs in the user
     * @param email
     * @param pwd
     * @return if the user is logged in or not (true/false)
     */
    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }

    /**
     * This method logs out the user in session
     */
    public void doLogout() {
        authenticationFacade.doLogout();
    }

    /**
     * This method gets the current user in session
     * @return user in session
     */
    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    /**
     * This method adds a role to the user
     * @param id
     * @param description
     * @return true/false
     */
    public boolean addUserRole(String id, String description) {
        return authenticationFacade.addUserRole(id, description);
    }

    /**
     * This method adds an user with a specific role
     * @param name
     * @param email
     * @param pwd
     * @param roleId
     * @return true/false
     */
    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        return authenticationFacade.addUserWithRole(name, email, pwd, roleId);
    }

    /**
     * This method adds an user with multiple roles
     * @param name
     * @param email
     * @param pwd
     * @param rolesId
     * @return true/false
     */
    public boolean addUserWithRoles(String name, String email, String pwd, String[] rolesId){
        return authenticationFacade.addUserWithRoles(name, email, pwd, rolesId);
    }

}
