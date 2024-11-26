package pt.ipp.isep.dei.esoft.project.domain.dtos;

public class AuthDTO {

    private String name;
    private String email;
    private String password;

    private String[] roles;

    public AuthDTO(String name, String email, String password,String []roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String [] getRoles(){
        return roles;
    }
}