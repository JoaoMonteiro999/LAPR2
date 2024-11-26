package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListPropertiesController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterClientController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ListPropertiesUI implements Runnable{

    private final ListPropertiesController controller = new ListPropertiesController();

    private List<Property> filteredPropertyList;

    public void run(){

        List<Property> propertyList = controller.getPropertiesList();
        filteredPropertyList = propertyList;

        System.out.println("\nList of Properties:\n");
        for(Property property: propertyList){
            System.out.println(property);
        }

        //get filter list and show
        List<String> filters = controller.getFilterCriteriaList();

        String filter = (String)Utils.showAndSelectOne(filters, "Select Filter");

        //if user selected a valid filter
        if (filter!=null) {
            switch (filter) {
                case "Business Type":
                    List<String> businessTypeList = List.of("Sale","Rent");
                    Utils.showList(businessTypeList, "Select type of business:");
                    String businessType = (String) Utils.selectsObject(businessTypeList);
                    filteredPropertyList = controller.getPropertiesByTypeOfBusiness(new BusinessType(businessType));
                    break;

                case "Property Type":
                    List<String> propertyTypeList = List.of("Apartment","House","Land");
                    int propertyType = Utils.showAndSelectIndex(propertyTypeList,"Select type of property:");
                    filteredPropertyList = controller.getPropertiesByTypeOfProperty(new PropertyType(propertyType));
                    break;

                case "Price":
                    int minPrice = Utils.readIntegerFromConsole("Enter minimum Price:");
                    int maxPrice = Utils.readIntegerFromConsole("Enter maximum Price:");
                    filteredPropertyList = controller.getPropertiesByPrice(minPrice, maxPrice);
                    break;

                case "Location":
                    String location = Utils.readLineFromConsole("Enter Location:");
                    filteredPropertyList = controller.getPropertiesByLocation(location);
                    break;

                case "Number of Bedrooms":
                    int numBedrooms = Utils.readIntegerFromConsole("Enter Number of Bedrooms:");
                    filteredPropertyList = controller.getPropertiesByNumberOfBedrooms(numBedrooms);
                    break;

                case "Number of Bathrooms":
                    int numBathrooms = Utils.readIntegerFromConsole("Enter Number of Bathrooms:");
                    filteredPropertyList = controller.getPropertiesByNumberOfBathrooms(numBathrooms);
                    break;

                case "Number of Parking Spaces":
                    int numParkingSpc = Utils.readIntegerFromConsole("Enter Number of Parking Spaces:");
                    filteredPropertyList = controller.getPropertiesByNumberOfParkingSpaces(numParkingSpc);
                    break;
            }

            System.out.println("\nList of Properties by filter");
            for (Property property : filteredPropertyList) {
                System.out.println(property);
            }
            System.out.println();
        }
    }

    public List<Property> getFilteredPropertiesList(){
        return filteredPropertyList;
    }

}
