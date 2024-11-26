package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.ClientRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SaleAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.AddressUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.AgentUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.*;

public class PublishSaleAnnouncementController implements SendSms{

    private Address address;
    private PropertyType propertyType; // between apartment; house; land
    private BusinessType businessType; // false=buy ; true=rent
    Property property;
    private Dwelling dwelling;
    private House house;
    private String ownerEmail;
    private Commission commission;
    private Client owner;
    private Repositories repositories ;
    private ClientRepository clientRepository;
    private Integer contractDuration;
    private List<Photograph> photos = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();


    private SaleAnnouncementRepository repository= new SaleAnnouncementRepository();
    private AuthenticationRepository authenticationRepository = null;
    private AgentUI agentUI = new AgentUI();



    public PublishSaleAnnouncementController(){

    }
    public PublishSaleAnnouncementController(SaleAnnouncementRepository saleAnnouncementRepository){


    }


    public Property requestData(){

        System.out.println("\n----------------------------------------------\n" +
        "       Publish Sale Announcement Menu        \n" +
        "----------------------------------------------");


        //Request the Address
        AddressUI addressUI = new AddressUI();
        address = addressUI.start();

        //Request Property type
        propertyType = requestPropertyType();

        //Request Business Type
        businessType = requestBusinessType();
        if (businessType.getBusinessType().equals("Rent")){
            contractDuration = requestInt("contract duration (in months)");
        }

        //Request Property characteristics
        property = requestProperty();

        if ((propertyType.getPropertyType()+1) == 1 || (propertyType.getPropertyType()+1) == 2){
            //Request Dwelling characteristics
            dwelling = requestDwelling();

            property.setDwelling(dwelling);
            if ((propertyType.getPropertyType()+1) == 2){
                //Request House characteristics
                house = requestHouse();
                dwelling.setHouse(house);
            }
        }

        //Request Photos
        photos = requestPhotographs();


        //Request commission
        commission=requestCommission();

        //Request the Email Address
        ownerEmail = requestEmailAddress();



        property.setAddress(address);
        property.setPropertyType(propertyType);
        property.setBusinessType(businessType);
        property.setPhotos(photos);
        property.setOwner(owner);


        return property;
    }

    PropertyType requestPropertyType(){
        List<String> propertyTypeList = List.of("Apartment","House","Land");

        int propertyType = Utils.showAndSelectIndex(propertyTypeList,"\nSelect type of property:");

        if (propertyType >= 0 && propertyType <= 2) {
            return new PropertyType(propertyType);

        } else if (propertyType == -1) {
            agentUI.run();
            return null;
        } else {
            throw new IllegalArgumentException("Invalid type!");
        }
    }




    BusinessType requestBusinessType(){
        List<String> propertyTypeList = List.of("Sale","Rent");

        int businesstype = Utils.showAndSelectIndex(propertyTypeList,"\nSelect type of property:");


        if (businesstype == 0) {
            return new BusinessType("Sale");

        } else if (businesstype == 1) {
            return  new BusinessType("Rent");
        }else if (businesstype == -1) {
            agentUI.run();
            return null;

        } else {
            throw new IllegalArgumentException("Invalid type!");
        }
    }

    private Property requestProperty(){
        int area = requestInt("area in square meters");
        int cityCenter = requestInt("distance from city center");
        int price = requestInt("property price");
        return new Property(area,cityCenter,price);
    }

    int requestInt(String header){
        Scanner input = new Scanner(System.in);
        int value = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Insert the "+header+":");
            String userInput = input.nextLine();

            try {
                value = Integer.parseInt(userInput);
                if (userInput.matches(".*\\s+.*")) {
                    throw new IllegalArgumentException("Please do not include extra spaces!");
                } else if (value < 0) {
                    throw  new IllegalArgumentException("Please write a positive number!");
                }
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number without commas!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return value;
    }
    private int requestIntOpt(String header){
        Scanner input = new Scanner(System.in);
        int value = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Insert the "+header+":");
            String userInput = input.nextLine();
            if (userInput.isEmpty()) {
                return -1;
            }

            try {
                value = Integer.parseInt(userInput);
                if (userInput.matches(".*\\s+.*")) {
                    throw new IllegalArgumentException("Invalid input. Please do not include extra spaces.");
                }else if (value < 0) {
                    throw  new IllegalArgumentException("Please write a positive number!");
                }
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return value;



    }


    private Dwelling requestDwelling(){
        int numBed = requestInt("number of bedrooms");
        int numBath = requestIntOpt("number of bathrooms (optional)");
        int numPark = requestInt("number of parking spaces");
        Equipment equipment = requestEquipment();
        if (numBath == -1){
            return new Dwelling(numBed,numPark,equipment);
        }
        return new Dwelling(numBed,numBath,numPark,equipment);
    }

    private Equipment requestEquipment(){
        List<String> equipmentList = List.of("Central Heating","Air conditioning","Both","None","I rather not answer");

        int equipment = Utils.showAndSelectIndex(equipmentList,"\nSelect Equipment information:");

        Equipment equipment1 = new Equipment();

        if (equipment == 0){
            equipment1.setCentralHeating(true);
            equipment1.setAirConditioned(false);
            return equipment1;
        } else if (equipment == 1) {
            equipment1.setCentralHeating(false);
            equipment1.setAirConditioned(true);
            return equipment1;
        } else if (equipment == 2 ) {
            equipment1.setAirConditioned(true);
            equipment1.setCentralHeating(true);
            return  equipment1;
        } else if (equipment == 3 ) {
            equipment1.setAirConditioned(false);
            equipment1.setCentralHeating(false);
            return  equipment1;
        } else if (equipment == 4 ) {
            return null;
        } else if (equipment == -1) {
            agentUI.run();
            return null;
        } else {
            throw new IllegalArgumentException("Invalid type!");
        }

    }







    private House requestHouse(){
        Scanner input = new Scanner(System.in);

        List<String> messageBasement = List.of("No basement", "Has basement");
        int basementValue = Utils.showAndSelectIndex(messageBasement, "Select one about the basement:");
        boolean basement = (basementValue == 1);

        List<String> messageInhabitableLoft = List.of("No inhabitable loft", "Has inhabitable loft");
        int inhabitableLoftValue = Utils.showAndSelectIndex(messageInhabitableLoft, "Select one about the inhabitable loft:");
        boolean inhabitableLoft = (inhabitableLoftValue == 1);

        List<String> messageSunExposure = List.of("North", "East", "South", "West","I rather not answer");
        int sunExposureValue = Utils.showAndSelectIndex(messageSunExposure, "Select one about the sun exposure:") +1;
        if (sunExposureValue == 5){
            return new House(basement,inhabitableLoft);
        } else return new House(basement,inhabitableLoft,sunExposureValue);
    }

    private List<Photograph> requestPhotographs(){
        Scanner input = new Scanner(System.in);
        List<Photograph> photos = new ArrayList<>();
        try {
            int numPhotos = requestInt("number of photos you want (between 1 and 30)");
            if (numPhotos>0 && numPhotos<31){
                System.out.println("insert URL of photos:");
                for (int i = 0; i < numPhotos; i++) {
                    System.out.print("\nlink "+(i+1)+": ");
                    String link = input.nextLine();
                    if (link.isEmpty()){
                        throw  new IllegalArgumentException("the link can't be null");
                    }else photos.add(new Photograph(link));
                }
            }else throw new IllegalArgumentException("invalid number of photos");
            return photos;

        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            requestPhotographs();
            return null;
        }
    }


    private Commission requestCommission(){
        Scanner input = new Scanner(System.in);

        List<String> optionList = List.of("Fixed","Percentage");

        int commissionType = Utils.showAndSelectIndex(optionList,"\nSelect commission type:");

        if (commissionType == 0){
            int value = requestInt("commission value (in USD)");
            return new Commission(false,value);
        } else if (commissionType == 1) {
            int percentage = requestInt("commission percentage");
            return new Commission(true,percentage);
        } else if (commissionType == -1) {
            agentUI.run();
            return null;
        } else {
            throw new IllegalArgumentException("Invalid type!");
        }

    }





    private String requestEmailAddress(){
        Scanner input = new Scanner(System.in);

        while (true) {

            try {
                System.out.print("Owner Email Address: ");
                String emailAddress = input.nextLine();

                if (emailAddress.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9._%-]+\\.[A-Za-z]{2,}$")) {
                    repositories = Repositories.getInstance();
                    clientRepository = repositories.getClientRepository();

                    if (clientRepository.clientExists(emailAddress) == null){
                        throw new Exception("\nThis client is not registered in the system!\n");
                    }else{
                        return  emailAddress;
                    }
                } else {
                    throw new IllegalArgumentException("\nEmail address is invalid! The email address must be in the following format: username@domainname.extension\n");
                }

            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


    public void createSaleAnnouncement(Property property){
        Repositories.getInstance().getPropertiesRepository().add(property);
        SaleAnnouncement saleAnnouncement = new SaleAnnouncement(property,commission,contractDuration);
        Repositories.getInstance().getSaleAnnouncementRepository().add(saleAnnouncement);
    }

    public void createSaleAnnouncement(Property property,Commission commission){
        Repositories.getInstance().getPropertiesRepository().add(property);
        SaleAnnouncement saleAnnouncement = new SaleAnnouncement(property,commission);
        Repositories.getInstance().getSaleAnnouncementRepository().add(saleAnnouncement);
    }




    public void sendSmss(){

        String agentEmail = getAgentEmailFromSession();

        employees = Repositories.getInstance().getEmployeeRepository().getEmployees();

        String agentName = null;
        String agentPhone = null;


        // Iterate through the list of Employees
        for (Employee employee : employees) {
            // Check if the Employee's email is equivalent to agentEmail
            if (employee.getEmailAddress().equals(agentEmail)) {
                agentName = employee.getName();
                agentPhone = employee.getPhoneNumber();

                break;
            }
        }





        sendSms(agentName ,agentPhone ,ownerEmail,address);
    }

    private String getAgentEmailFromSession(){
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return email.getEmail();
    }

    private AuthenticationRepository getAuthenticationRepository(){
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

}

