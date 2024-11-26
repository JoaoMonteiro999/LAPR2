package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * CSV File Repository class
 */
public class CSVFileRepository {

    private List<File> csvFiles = new ArrayList<>();
    private String folderPath;

    /**
     * Fills the csv file list
     */
    public void fillCSVFileList() {
        File folder = new File(folderPath);

        //Check if folder is valid
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid folder path: " + folderPath);
            return;
        }

        //Fills csv file list with the files that are in the folder
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (csvFiles.contains(file)){
                    continue;
                } else if (file.isFile() && file.getName().toLowerCase().endsWith(".csv")) {
                    csvFiles.add(file);
                }
            }
        }

        checkForNewCSVFiles();
    }

    /**
     * Checks for new csv files in the folder
     */
    private void checkForNewCSVFiles() {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".csv") && !csvFiles.contains(file)) {
                    csvFiles.add(file);
                    System.out.println("New CSV file added: " + file.getName());
                }
            }
        }
    }

    /**
     * Imports all the data from the csv file
     * @param csvFile
     */
    public void importCSVFile(File csvFile){

        try {
            List<List<String>> data = new ArrayList<>();
            Scanner scanner = new Scanner(csvFile);

            scanner.nextLine();

            while (scanner.hasNextLine()) {
                data.add(getDataFromLine(scanner.nextLine()));
            }

            createObjectsInFile(data);

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    /**
     * Creates instances of objects in the csv file
     * @param data
     */
    private void createObjectsInFile(List<List<String>> data){
        SaleAnnouncementRepository saleAnnouncementRepository = Repositories.getInstance().getSaleAnnouncementRepository();

        for (List<String> row : data) {

            //OWNER
            Client owner = new Client();
            owner.setName(row.get(1));
            owner.setPassportCardNum(row.get(2));
            owner.setTaxNumber(row.get(3));
            owner.setEmailAddress(row.get(4));
            owner.setPhoneNumber(row.get(5));
            Repositories.getInstance().getClientRepository().add(owner);


            //EQUIPMENT
            Equipment equipment = new Equipment();
            equipment.setCentralHeating(convertStringToBoolean(row.get(13)));
            equipment.setAirConditioned(convertStringToBoolean(row.get(14)));

            //HOUSE
            House house = new House();
            house.setBasement(convertStringToBoolean(row.get(15)));
            house.setInhabitableLoft(convertStringToBoolean(row.get(16)));
            house.setSunExposure(convertStringToIntSunExposure(row.get(17)));

            //DWELLING AND LAND
            Dwelling dwelling = new Dwelling();

            //If the property is a land, the number of rooms is 0.
            if (row.get(6).equalsIgnoreCase("land")) {
                dwelling.setNumBedrooms(0);
                dwelling.setNumBathrooms(0);
                dwelling.setNumParkingSpaces(0);

            } else {
                dwelling.setNumBedrooms(Integer.parseInt(row.get(10)));
                dwelling.setNumBathrooms(Integer.parseInt(row.get(11)));
                dwelling.setNumParkingSpaces(Integer.parseInt(row.get(12)));
            }

            dwelling.setHouse(house);
            dwelling.setEquipment(equipment);


            //PROPERTY
            Property property = new Property();

            //property type
            String propertyType = row.get(6);
            int propertyTypeValue = 0;
            switch (propertyType){
                case "house":
                    propertyTypeValue = 1;
                    break;

                case "land":
                    propertyTypeValue = 2;
                    break;
            }

            property.setPropertyType(new PropertyType(propertyTypeValue));
            property.setArea(Integer.parseInt(row.get(7)));
            property.setAddress(findAddressFromFile(row,true));
            property.setCityCenter(Integer.parseInt(row.get(9)));
            property.setPrice(Integer.parseInt(row.get(18)));
            property.setBusinessType(new BusinessType(row.get(24)));
            property.setDwelling(dwelling);
            property.setOwner(owner);
            Repositories.getInstance().getPropertiesRepository().add(property);

            //AGENCY
            Agency agency = new Agency();
            agency.setId(Integer.parseInt(row.get(25)));
            agency.setDesignation(row.get(26));
            agency.setEmailAddress(row.get(29));
            agency.setPhoneNumber(row.get(28));
            agency.setAddress(findAddressFromFile(row,false));

            //AGENT
            Employee agent = new Employee();
            agent.setName("Legacy Agent");
            agent.setPassportCardNum("000000000");
            agent.setTaxNumber("000000000");
            agent.setPhoneNumber("000-000-0000");
            agent.setEmailAddress("legacy@realstateUSA.com");
            agent.setRoles(List.of(new Role("Agent")));
            agent.setAgency(agency);
            agency.registerEmployee("Legacy Agent","000000000","000000000","000-000-0000","legacy@realstateUSA.com", new Address(),List.of(new Role("Agent")),agency);

            //ANNOUNCEMENT
            SaleAnnouncement saleAnnouncement = new SaleAnnouncement();
            saleAnnouncement.setCommission(new Commission(true, Integer.parseInt(row.get(20))));

            if (row.get(21).equalsIgnoreCase("NA")){
                saleAnnouncement.setContractDuration(0);
            } else {
                saleAnnouncement.setContractDuration(Integer.parseInt(row.get(21)));
            }

            saleAnnouncement.setDateAnnouncement(row.get(22));
            saleAnnouncement.setAgent(agent);
            saleAnnouncementRepository.add(saleAnnouncement);

            //ORDER
            Order order = new Order();
            order.setProperty(property);
            order.setOrderAccepted(true);
            order.setClientEmail(owner.getEmailAddress());
            order.setOrderAmount(Integer.parseInt(row.get(19)));

            //ADD ORDER AND AGENCY
            AgencyRepository agencyRepository = Repositories.getInstance().getAgencyRepository();

            agency.addOrder(order);
            agencyRepository.add(agency);
        }
    }

    /**
     * It returns the address depending on the address that it corresponds to
     * @param row
     * @param isAddressForProperty
     * @return address
     */
    private Address findAddressFromFile(List<String> row, boolean isAddressForProperty){
        Address address = new Address();
        String fullAddress = "";

        if (isAddressForProperty){
            fullAddress = row.get(8);
        } else {
            fullAddress = row.get(27);
        }

        String[] separatedAddress = fullAddress.split(",");
        address.setStreet(separatedAddress[0].trim());

        switch (separatedAddress.length){
            case 4:
                address.setCityName(separatedAddress[1].trim());
                address.setStateAcronym(separatedAddress[2].replaceAll("\\s+|\\p{Z}", ""));
                address.setZipCode(separatedAddress[3].trim());
                break;

            case 5:
                address.setCityName(separatedAddress[1].trim());
                address.setDistrictName(separatedAddress[2].trim());
                address.setStateAcronym(separatedAddress[3].replaceAll("\\s+|\\p{Z}", ""));
                address.setZipCode(separatedAddress[4].trim());
                break;

            case 7:
                address.setBuilding(separatedAddress[1].trim());
                address.setFloor(separatedAddress[2].trim());
                address.setApartment(separatedAddress[3].trim());
                address.setCityName(separatedAddress[4].trim());
                address.setStateAcronym(separatedAddress[5].replaceAll("\\s+|\\p{Z}", ""));
                address.setZipCode(separatedAddress[6].trim());
                break;

            case 8:
                address.setBuilding(separatedAddress[1].trim());
                address.setFloor(separatedAddress[2].trim());
                address.setApartment(separatedAddress[3].trim());
                address.setCityName(separatedAddress[4].trim());
                address.setDistrictName(separatedAddress[5]);
                address.setStateAcronym(separatedAddress[6].replaceAll("\\s+|\\p{Z}", ""));
                address.setZipCode(separatedAddress[7].trim());
                break;
        }

        return address;
    }

    /**
     * Gets the info from a line in the csv file
     * @param line
     * @return
     */
    private static List<String> getDataFromLine(String line) {
        List<String> dataRow = new ArrayList<>();

        try (Scanner row = new Scanner(line)) {
            row.useDelimiter(";");
            while (row.hasNext()) {
                dataRow.add(row.next());
            }
        }
        return dataRow;
    }

    /**
     * Get all the csv files
     * @return
     */
    public List<File> getCSVFiles() {
        return csvFiles;
    }

    /**
     * Change the folder path
     * @param folderPath
     */
    public void setFolderPath(String folderPath){
        this.folderPath = folderPath;
    }

    /**
     * Converts a string to a boolean
     * @param string
     * @return true/false
     */
    private boolean convertStringToBoolean(String string){
        return string.equals("Y");
    }

    /**
     * Converts a string to an int for the sun exposure
     * @param string
     * @return sun exposure value
     */
    private int convertStringToIntSunExposure(String string){
        switch (string){
            case "N":
                return 1;

            case "E":
                return 2;

            case "S":
                return 3;

            default:
                return 4;
        }
    }

}
