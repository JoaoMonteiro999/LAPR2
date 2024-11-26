package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Role;

import java.io.IOException;
import java.util.*;

public class RegisterEmployeeUI implements Runnable{

    private final RegisterEmployeeController controller = new RegisterEmployeeController();
    private String name;
    private String passportCardNum;
    private String taxNumber;
    private String phoneNumber;
    private String emailAddress;

    private Address address;
    private List<String> chosenRoles;
    private Integer chosenAgencyId;

    private RegisterEmployeeController getController(){
        return controller;
    }

    public void run(){
        System.out.println("Register Employee");

        requestData();

        chosenRoles = displayAndSelectRoles();
        chosenAgencyId = displayAndSelectAgency();

        submitData();
    }

    private void submitData() {
        Optional<Employee> employee = Optional.empty();

        try {
            employee = getController().registerEmployee(name, passportCardNum, taxNumber,
                    phoneNumber, emailAddress, address, chosenRoles, chosenAgencyId);

        } catch (IllegalArgumentException e) {
            System.out.println("Employee data is invalid!");

        } finally {

            if (employee.isPresent()) {

                try {
                    controller.saveEmployeeCredentials(name,emailAddress,chosenRoles);

                } catch (IOException e) {
                    System.out.println("Could not save employee credentials.");
                }

                System.out.println("Employee successfully registered!");
            } else {
                System.out.println("Employee not registered!");
            }
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
                    throw new IllegalArgumentException("\nPassport Card Number is out of range! The passport card number must have 9 digits.\n");
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

                if (phoneNumber.matches("\\(\\d{3}\\)\\s\\d{3}-?\\d{4}") || phoneNumber.matches("\\d{3}-\\d{3}-?\\d{4}")) {
                    return phoneNumber;
                } else {
                    throw new IllegalArgumentException("\nPhone number is invalid! Please enter a phone number in the following format: (999) 999-9999 or 999-999-9999.\n");
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

    /**
     * Method to display a list of roles and select one or more role
     * @return
     */
    private List<String> displayAndSelectRoles() {
        List<Role> roles = controller.getRoles();
        int listSize = roles.size();

        Scanner input = new Scanner(System.in);
        List<String> chosenRoles = new ArrayList<>();

        while (true) {
            System.out.println("Select a role:");
            displayRoleOptions(roles);
            System.out.println("\n0 - Next");

            try {
                int answer = input.nextInt();
                if (answer == 0) {
                    if (chosenRoles.isEmpty()) {
                        System.out.println("You need to select at least one role!\n");
                        continue;
                    }
                    break;
                } else if (answer < 1 || answer > listSize) {
                    System.out.println("Invalid option. Please try again.\n");
                    continue;
                }

                String roleName = roles.get(answer - 1).getRolesBySelection();
                if (chosenRoles.contains(roleName)) {
                    System.out.println("Role already selected. Please choose a different role.\n");
                    continue;
                }

                chosenRoles.add(roleName);
                System.out.println("Role \"" + roleName + "\" added.\n");

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.\n");
                input.nextLine(); // Clear the input buffer
            }
        }

        return chosenRoles;
    }

    /**
     * Method to display a list of agencies and select one agency
     * @return
     */
    private Integer displayAndSelectAgency() {
        List<Agency> agencies = controller.getAgencies();
        int listSize = agencies.size();

        Scanner input = new Scanner(System.in);
        int answer = -1;

        while (true) {
            try {
                System.out.println("Select an agency:");
                displayAgencyOptions(agencies);
                answer = input.nextInt();

                if (answer < 1 || answer > listSize) {
                    System.out.println("Invalid option. Please try again.\n");
                    continue;
                }

                Integer chosenAgencyId = agencies.get(answer - 1).getAgenciesBySelection();
                return chosenAgencyId;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.\n");
                input.nextLine(); // Clear the input buffer
            }
        }
    }


    /**
     * Method to display the roles as a menu with number options to select
     * @param roles
     */
    private void displayRoleOptions(List<Role> roles) {
        int i = 1;
        for (Role role : roles) {
            System.out.println(i + " - " + role.getRolesBySelection());
            i++;
        }
    }

    /**
     * Method to display the agencies as a menu with number options to select
     * @param agencies
     */
    private void displayAgencyOptions(List<Agency> agencies) {
        int i = 1;
        for (Agency agency : agencies) {
            System.out.println(i + " - " + agency.getDesignation() + " (" + agency.getAgenciesBySelection() + ")");
            i++;
        }
    }

}
