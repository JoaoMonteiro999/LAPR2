package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PropertiesRepository {

    public List<Property> properties = new ArrayList<>();

    /**
     * This method adds the property to the list of properties
     * @param property
     * @return property
     */
    public Optional<Property> add(Property property) {
        Optional<Property> newProperty = Optional.empty();
        boolean operationSuccess = false;

        if (validateProperty(property)) {
            newProperty = Optional.of(property.clone());
            operationSuccess = properties.add(newProperty.get());
        }

        if (!operationSuccess) {
            newProperty = Optional.empty();
        }

        return newProperty;
    }

    /**
     * Checks if the property already exists in the list of properties
     * @param property
     * @return true/false
     */
    private boolean validateProperty(Property property) {
        boolean isValid = !properties.contains(property);

        return isValid;
    }

    /**
     * Get the list of properties
     * @return list of properties
     */
    public List<Property> getPropertiesList(){
        return properties;
    }

    /**
     * Get list of properties by the business type of the property
     * @param businessType
     * @return list of properties with that business type
     */
    public List<Property> getPropertiesByTypeOfBusiness(BusinessType businessType){
        List<Property> filtered = new ArrayList<>();

        for(Property p: properties){
            if (p.getBusinessType().equals(businessType)){
                filtered.add(p);
            }
        }

        return filtered;
    }

    /**
     * Get list of properties by the type of property
     * @param propertyType
     * @return list of properties with that property type
     */
    public List<Property> getPropertiesByTypeOfProperty(PropertyType propertyType){
        List<Property> filtered = new ArrayList<>();

        for(Property p: properties){
            if (p.getPropertyType().equals(propertyType)){
                filtered.add(p);
            }
        }

        return filtered;
    }

    /**
     * Get list of properties by the number of bedrooms
     * @param numBedrooms
     * @return list of properties with that number of bedrooms
     */
    public List<Property> getPropertiesByNumberOfBedrooms(int numBedrooms){
        List<Property> filtered = new ArrayList<>();

        for(Property p: properties){
            if (p.getDwelling().getNumBedrooms() == numBedrooms){
                filtered.add(p);
            }
        }

        return filtered;
    }

    /**
     * Get list of properties by the number of bathrooms
     * @param numBathrooms
     * @return list of properties with that number of bathrooms
     */
    public List<Property> getPropertiesByNumberOfBathrooms(int numBathrooms){
        List<Property> filtered = new ArrayList<>();

        for(Property p: properties){
            if (p.getDwelling().getNumBathrooms() == numBathrooms){
                filtered.add(p);
            }
        }

        return filtered;
    }

    /**
     * Get list of properties by the number of parking spaces
     * @param numParkingSpc
     * @return list of properties with that number of parking spaces
     */
    public List<Property> getPropertiesByNumberOfParkingSpaces(int numParkingSpc){
        List<Property> filtered = new ArrayList<>();

        for(Property p: properties){
            if (p.getDwelling().getNumParkingSpaces() == numParkingSpc){
                filtered.add(p);
            }
        }

        return filtered;
    }

    /**
     * returns a list of properties with the price between min and max
     * @param minPrice
     * @param maxPrice
     * @return list of filtered properties
     */
    public List<Property> getPropertiesByPrice(int minPrice, int maxPrice){
        List<Property> filtered = new ArrayList<>();

        for(Property p: properties){
            if (p.getPrice()>=minPrice && p.getPrice()<=maxPrice){
                filtered.add(p);
            }
        }

        return filtered;
    }

    /**
     * Get list of properties by the location of the property
     * @param location
     * @return list of properties with that location
     */
    public List<Property> getPropertiesByLocation(String location){
        List<Property> filtered = new ArrayList<>();

        for(Property p: properties){
            if (p.getAddress().getCityName().equals(location)){
                filtered.add(p);
            }
        }

        return filtered;
    }

}
