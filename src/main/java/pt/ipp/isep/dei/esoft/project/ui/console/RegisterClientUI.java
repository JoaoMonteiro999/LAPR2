package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterClientController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.Employee;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class RegisterClientUI implements Runnable{

    private final RegisterClientController controller = new RegisterClientController();
    private String name;
    private String passportCardNum;
    private String taxNumber;
    private String phoneNumber;
    private String emailAddress;

    private Address address;

    private String password;

    private RegisterClientController getController(){
        return controller;
    }

    public void run(){
        System.out.println("Register client");

        requestData();

        submitData();
    }

    private void submitData(){
        Optional<Client> client = getController().registerClient(name,passportCardNum,taxNumber,
                phoneNumber,emailAddress,address,password);

        if (client.isPresent()){
            controller.sendEmail(client.get());
            System.out.println("Client successfully registered!");
        } else {
            System.out.println("Client not registered!");
        }
    }

    private void requestData(){

        //Request the Name from the console
        name = requestName();

        //Request the Passport Card Number from the console
        passportCardNum = requestPassportCardNum();

        //Request the Tax Number from the console
        taxNumber = requestTaxNumber();

        //Request the Phone Number from the console
        phoneNumber = requestPhoneNumber();

        //Request the Email Address from the console
        emailAddress = requestEmailAddress();

        //Request the Address from the console
        AddressUI addressUI = new AddressUI();
        address = addressUI.start();

        //Request Password
        password = requestPassword();
    }

    private String requestName(){
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Name: ");
                String name = input.nextLine();

                if (name.matches("^[^0-9]+$")) {
                    return name;
                } else {
                    throw new IllegalArgumentException("\nName format is invalid! The name can only have alphabetic characters.\n");
                }

            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private String requestPassportCardNum() {

        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Passport Card Number: ");
                String passportCardNum = input.next();

                if (passportCardNum.length() == 9 || passportCardNum.matches("\\d{9}")) {
                    return passportCardNum;
                } else {
                    throw new IllegalArgumentException();
                }

            } catch (IllegalArgumentException e1){
                System.out.println("\nPassport Card Number is out of range! The passport card number must have 9 digits.\n");

            } catch (InputMismatchException e2) {
                System.out.println("\nPassport Card Number is invalid! The passport card number must have 9 digits.\n");
                input.next(); //clear the invalid input from the scanner
            }
        }
    }

    private String requestTaxNumber(){
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Tax Number: ");
                String taxNumber = input.next();

                if (taxNumber.length() == 9 || taxNumber.matches("\\d{9}")) {
                    return taxNumber;
                } else {
                    throw new IllegalArgumentException("\nTax Number is out of range! The tax number must have 9 digits.\n");
                }

            } catch (IllegalArgumentException e1){
                System.out.println(e1.getMessage());

            } catch (InputMismatchException e){
                System.out.println("\nTax number is invalid! The tax number must have 9 digits.\n");
                input.next();
            }
        }
    }

    private String requestPhoneNumber(){

        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Phone Number: ");
                String phoneNumber = input.nextLine();

                if (phoneNumber.matches("\\(\\d{3}\\)\\s\\d{3}-?\\d{4}") || phoneNumber.matches("\\d{3}-\\d{3}-?\\d{4}")){
                    return phoneNumber;
                } else {
                    throw new IllegalArgumentException("\nPhone number is invalid! Please enter a phone number in the following format: (999) 999-9999 or 999-999-9999\n");
                }

            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private String requestEmailAddress(){
        Scanner input = new Scanner(System.in);

        while (true) {

            try {
                System.out.print("Email Address: ");
                String emailAddress = input.nextLine();

                if (emailAddress.matches("^(.+)@(.+)$")) {
                    return emailAddress;
                } else {
                    throw new IllegalArgumentException("\nEmail address is invalid! The email address must be in the following format: username@domainname.extension\n");
                }

            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private String requestPassword(){
        Scanner input = new Scanner(System.in);

        while (true){

            try {
                System.out.print("Password: ");
                String password = input.nextLine();

                if (password.matches("^(?=.*[A-Z].*[A-Z])(?=.*[0-9].*[0-9])(?=.*[a-zA-Z]).{7}$")){
                    return password;
                } else {
                    throw new IllegalArgumentException("\nPassword is invalid! The password must contain seven alphanumeric characters, including three capital letters and two digits.");
                }

            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
