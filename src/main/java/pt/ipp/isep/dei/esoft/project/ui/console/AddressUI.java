package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.domain.Address;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddressUI{

    public Address start(){
        return requestAddress();
    }

    private Address requestAddress(){
        System.out.print("Address: \n");

        String street = requestStreetAddress();
        String cityName = requestCityNameAddress();
        String districtName = requestDistrictNameAddress();
        String stateAcronym = requestStateAcronym();
        String zipCode = requestZipCodeAddress();

        return new Address(street,cityName,districtName,stateAcronym,zipCode);
    }

    private String requestStreetAddress(){
        Scanner input = new Scanner(System.in);

        System.out.print("\tStreet Name: ");

        return input.nextLine();
    }

    private String requestCityNameAddress(){
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("\tCity Name: ");
                String cityName = input.nextLine();

                if (cityName.matches("^[^0-9]+$")) {
                    return cityName;
                } else {
                    throw new IllegalArgumentException("\nCity name format is invalid! The city name must have alphabetic characters.\n");
                }

            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private String requestDistrictNameAddress(){
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("\tDistrict Name (optional): ");
                String districtName = input.nextLine();

                if (districtName.isEmpty()) {
                    return null;
                }

                if (districtName.matches("^[^0-9]+$")) {
                    return districtName;
                } else {
                    throw new IllegalArgumentException("\nDistrict name format is invalid! The district name must have alphabetic characters.\n");
                }

            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private String requestStateAcronym(){
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("\tState Acronym: ");
                String stateAcronym = input.nextLine();

                if (stateAcronym.matches("^[^0-9]+$")){
                    return stateAcronym;
                } else {
                    throw new IllegalArgumentException("\nState acronym format is invalid! The state acronym must have two Capital alphabetic characters.\n");
                }

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String requestZipCodeAddress(){
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("\tZipcode: ");
                String zipCode = input.nextLine();
                if ((zipCode.length() == 5 || zipCode.matches("\\d{5}") )&& Integer.valueOf(zipCode) > 0) {
                    return zipCode;
                } else {
                    throw new IllegalArgumentException("\nZipcode of the address is out of range! The zipcode of the address must have 5 numeric digits.\n");
                }

            } catch (NumberFormatException e ){
                System.out.println("\nZipcode of the address is invalid! The zipcode of the address must have 5 numeric digits.\n");

            } catch (IllegalArgumentException e1){
                System.out.println(e1.getMessage());

            } catch (InputMismatchException e2){
                System.out.println("\nZipcode of the address is invalid! The zipcode of the address must have 5 numeric digits.\n");

            }
        }
    }
}
