package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

import static pt.ipp.isep.dei.esoft.project.ui.Bootstrap.*;

/**
 * Address class
 */
public class Address implements Serializable {

    private static final long serialVersionUID = 3034689844124973087L;

    /**
     * the street name
     */
    private String street;

    /**
     * the building
     */
    private String building;

    /**
     * the floor
     */
    private String floor;

    /**
     * the apartment
     */
    private String apartment;

    /**
     * the city name
     */
    private String cityName;

    /**
     * the district name
     */
    private String districtName;

    /**
     * the state
     */
    private String stateAcronym;

    /**
     * the zip code
     */
    private String zipCode;


    /**
     * Constructor
     */
    public Address(){
    }

    /**
     * Constructor (for employees)
     * @param street
     * @param cityName
     * @param districtName
     * @param stateAcronym
     * @param zipCode
     */
    public Address(String street, String cityName, String districtName, String stateAcronym, String zipCode){
        setStreet(street);
        setCityName(cityName);
        setDistrictName(districtName);
        setStateAcronym(stateAcronym);
        setZipCode(zipCode);
    }


    /**
     * Constructor (for the agencies and properties)
     * @param street
     * @param cityName
     * @param stateAcronym
     * @param zipCode
     */
    public Address(String street, String cityName, String stateAcronym, String zipCode){
        setStreet(street);
        setCityName(cityName);
        setStateAcronym(stateAcronym);
        setZipCode(zipCode);
    }


    /**
     * Constructor (for the properties)
     * @param street
     * @param building
     * @param floor
     * @param apartment
     * @param cityName
     * @param districtName
     * @param stateAcronym
     * @param zipCode
     */
    public Address(String street, String building, String floor, String apartment, String cityName, String districtName, String stateAcronym, String zipCode){
        setStreet(street);
        setBuilding(building);
        setFloor(floor);
        setApartment(apartment);
        setCityName(cityName);
        setDistrictName(districtName);
        setBuilding(stateAcronym);
        setZipCode(zipCode);
    }


    /**
     * Constructor (for the properties without district)
     * @param street
     * @param building
     * @param floor
     * @param apartment
     * @param cityName
     * @param stateAcronym
     * @param zipCode
     */
    public Address(String street, String building, String floor, String apartment, String cityName, String stateAcronym, String zipCode){
        setStreet(street);
        setBuilding(building);
        setFloor(floor);
        setApartment(apartment);
        setCityName(cityName);
        setBuilding(stateAcronym);
        setZipCode(zipCode);
    }

    /**
     * Get the street value
     * @return the steet
     */
    public String getStreet() {
        return street;
    }

    /**
     * Change street value
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Get the building value
     * @return the building
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Change the building value
     * @param building
     */
    public void setBuilding(String building) {
        if (building != null && !building.isEmpty()){
            if (!building.matches("BLDG [a-zA-Z]")){
                throw new IllegalArgumentException("Building is invalid!");
            }
            this.building = building;

        } else {
            this.building = null;
        }
    }

    /**
     * Get the floor value
     * @return the floor
     */
    public String getFloor() {
        return floor;
    }

    /**
     * Change the floor value
     * @param floor
     */
    public void setFloor(String floor) {
        if (floor != null && !floor.isEmpty()){
            if (!floor.matches("FL [0-9]")){
                throw new IllegalArgumentException("Floor is invalid!");
            }
            this.floor = floor;

        } else {
            this.floor = null;
        }
    }

    /**
     * Get the apartment value
     * @return the apartment
     */
    public String getApartment() {
        return apartment;
    }

    /**
     * Change the apartment value
     * @param apartment
     */
    public void setApartment(String apartment) {
        if (apartment != null && !apartment.isEmpty()){
            if (!apartment.matches("APT [0-9]")){
                throw new IllegalArgumentException("Apartment is invalid!");
            }
            this.apartment = apartment;

        } else {
            this.apartment = null;
        }
    }

    /**
     * Get the city value
     * @return the city
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Change the city value
     * @param cityName
     */
    public void setCityName(String cityName) {
        if (!(cityName.matches("^[^0-9]+$"))) {
            throw new IllegalArgumentException("City name is invalid!");
        }
        this.cityName = cityName;
    }

    /**
     * Get the district value
     * @return the district
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * Change the district value
     * @param districtName
     */
    public void setDistrictName(String districtName) {
        if (districtName != null && !districtName.isEmpty()) {
            if (!(districtName.matches("^[^0-9]+$"))) {
                throw new IllegalArgumentException("District name is invalid!");
            }
            this.districtName = districtName;
        } else {
            this.districtName = null; // Set districtName to null if it's not provided
        }
    }

    /**
     * Get the state value
     * @return state
     */
    public String getStateAcronym() {
        return stateAcronym;
    }

    /**
     * Change the state value
     * @param stateAcronym
     */
    public void setStateAcronym(String stateAcronym) {
        if (!(stateAcronym.matches("^[^0-9]+$"))){
            throw new IllegalArgumentException("State Acronym is invalid!");
        }
        this.stateAcronym = stateAcronym;
    }

    /**
     * Get the zip code value
     * @return
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Change the zip code value
     * @param zipCode
     */
    public void setZipCode(String zipCode) {
        if ((zipCode.length() == 5 || zipCode.matches("\\d{5}") )&& Integer.parseInt(zipCode) > 0) {
            this.zipCode=zipCode;
        } else {
            throw new IllegalArgumentException("\nZipcode of the address is out of range! The zipcode of the address must have 5 numeric digits.\n");
        }
    }

    /**
     * Equals method for the address
     * @param o
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        Address that = (Address) o;
        return zipCode.equals(that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode);
    }

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Address clone() {
        return new Address(this.street, this.cityName, this.districtName, this.stateAcronym, this.zipCode);
    }

    /**
     * To string method
     * @return the string with the instance attribute values
     */
    @Override
    public String toString() {
        if (building == null && floor == null && apartment == null){

            if (districtName == null){
                return street + ", " + cityName + ", " + stateAcronym + ", " + zipCode;
            } else {
                return street + ", " + cityName + ", " + districtName + ", " + stateAcronym + ", " + zipCode;
            }

        } else {

            if (districtName == null){
                return street + ", " + building + ", " + floor + ", " + apartment + ", " + cityName + ", " + stateAcronym + ", " + zipCode;
            } else {
                return street + ", " + building + ", " + floor + ", " + apartment + ", " + cityName + ", " + districtName + ", " + stateAcronym + ", " + zipCode;
            }

        }
    }
}
