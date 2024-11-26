package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.PropertiesRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for getting a properties list
 *
 * allows for several filters
 */
public class ListPropertiesController {

    //private Repositories repositories;

    private PropertiesRepository repository;

    public ListPropertiesController(){
        //this.repositories = Repositories.getInstance();
        this.repository = Repositories.getInstance().getPropertiesRepository();
    }

    public ListPropertiesController(PropertiesRepository repository){
        this.repository = repository;
    }

    /**
     * returns a complete list of all the properties
     * @return a complete list of all the properties
     */
    public List<Property> getPropertiesList(){
        return repository.getPropertiesList();
    }

    /**
     * returns a list of properties by type of business
     * @param businessType the type of business to filter
     * @return list of properties
     */
    public List<Property> getPropertiesByTypeOfBusiness(BusinessType businessType){
        return repository.getPropertiesByTypeOfBusiness(businessType);
    }

    /**
     * returns a list of properties by type of property
     * @param propertyType the type of property to filter
     * @return list of properties
     */
    public List<Property> getPropertiesByTypeOfProperty(PropertyType propertyType){
        return repository.getPropertiesByTypeOfProperty(propertyType);
    }

    public List<Property> getPropertiesByNumberOfBedrooms(int numBedrooms){
        return repository.getPropertiesByNumberOfBedrooms(numBedrooms);
    }

    public List<Property> getPropertiesByNumberOfBathrooms(int numBathrooms){
        return repository.getPropertiesByNumberOfBathrooms(numBathrooms);
    }

    public List<Property> getPropertiesByNumberOfParkingSpaces(int numParkingSpc){
        return repository.getPropertiesByNumberOfParkingSpaces(numParkingSpc);
    }

    public List<Property> getPropertiesByPrice(int minPrice, int maxPrice){
        return repository.getPropertiesByPrice(minPrice, maxPrice);
    }

    public List<Property> getPropertiesByLocation(String location){
        return repository.getPropertiesByLocation(location);
    }

    public List<String> getFilterCriteriaList(){
        List<String> filters = new ArrayList<>();

        filters.add("Business Type");
        filters.add("Property Type");
        filters.add("Price");
        filters.add("Location");
        filters.add("Number of Bedrooms");
        filters.add("Number of Bathrooms");
        filters.add("Number of Parking Spaces");

        return filters;

    }

}
