package pt.ipp.isep.dei.esoft.project.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * Client class
 */
public class Client extends Person {

    /**
     * Client password
     */
    private String password;

    /**
     * Empty constructor
     */
    public Client(){
        super();
    }

    /**
     * Default constructor
     * @param name
     * @param passportCardNum
     * @param taxNumber
     * @param phoneNumber
     * @param emailAddress
     * @param address
     * @param password
     */
    public Client(String name, String passportCardNum, String taxNumber,  String phoneNumber, String emailAddress, Address address, String password) {
        super(name, passportCardNum, taxNumber, phoneNumber, emailAddress, address);
        setPassword(password);
    }

    /**
     * Get client password
     * @return
     */
    public String getPassword(){return password;}

    /**
     * Change password value
     * @param password
     */
    public void setPassword(String password) {
        if (password == null) {
            this.password = PasswordGenerator.generatePassword();

        } else if (!password.matches("^(?=.*[A-Z].*[A-Z])(?=.*[0-9].*[0-9])(?=.*[a-zA-Z]).{7}$")){
            throw new IllegalArgumentException("Password is invalid! Password: " + password);

        } else {
            this.password = password;
        }
    }

    /**
     * This method sends an email to the client through a txt file
     */
    public void sendEmail() {
        File myObj = new File("email.txt");

        if (!myObj.exists()) {
            System.out.println("A file to save the accounts hasn't been found! Creating one...");
            try {
                boolean fileCreated = myObj.createNewFile();
                if (fileCreated) {
                    System.out.println("File 'email.txt' created!");
                } else {
                    System.out.println("Failed to create the file 'email.txt'.");
                    return;
                }
            } catch (IOException e) {
                System.out.println("Failed to create the file 'email.txt': " + e.getMessage());
                return;
            }
        }

        FileWriter output;
        try {
            output = new FileWriter(myObj, true);
            output.write(getName() + "; " + getEmailAddress() + "; " + getPassword() + "; [Client]\n" );
            output.close();
        } catch (IOException e) {
            System.out.println("Failed to write to the file 'email.txt': " + e.getMessage());
        }
    }

    /**
     * Equals method
     * @param o
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        Client client = (Client) o;
        return getTaxNumber().equals(client.getTaxNumber());
    }

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Client clone() {
        return new Client(getName(), getPassportCardNum(), getTaxNumber(), getPhoneNumber(), getEmailAddress(), getAddress(), this.password);
    }

    /**
     * To string method
     * @return the string with the instance attribute values
     */
    @Override
    public String toString() {
        return "Client: " + super.toString() +
                " password='" + password +"'";
    }
}
