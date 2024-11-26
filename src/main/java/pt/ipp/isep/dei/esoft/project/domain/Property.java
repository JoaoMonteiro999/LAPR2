package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * stores information about a property
 *
 */
public class Property implements Serializable {

    private static final long serialVersionUID = 3995731081334736780L;

    private int area;
    private int cityCenter;
    private int price;
    private Address address;
    private PropertyType propertyType;
    private BusinessType businessType;
    private List<Photograph> photos = new ArrayList<>();

    private Dwelling dwelling;
    private Client owner;


    public Property(){
    }

    public Property(int area, int cityCenter, int price){
        this.area = area;
        this.cityCenter = cityCenter;
        this.price = price;
    }

    public Property(int area, int cityCenter, int price, Address address, PropertyType propertyType, BusinessType businessType){
        this.area = area;
        this.cityCenter = cityCenter;
        this.price = price;
        this.address = address;
        this.propertyType = propertyType;
        this.businessType = businessType;
    }
    /**
     * creates a new Property
     */
    public Property(int area, int cityCenter, int price, Address address, PropertyType propertyType, BusinessType businessType, Dwelling dwelling) {
        this.area = area;
        this.cityCenter = cityCenter;
        this.price = price;
        this.address = address;
        this.propertyType = propertyType;
        this.businessType = businessType;
        this.dwelling = dwelling;
    }

    public Property(int area, int cityCenter, int price, Address address, PropertyType propertyType, BusinessType businessType, Dwelling dwelling,House house) {
        this.area = area;
        this.cityCenter = cityCenter;
        this.price = price;
        this.address = address;
        this.propertyType = propertyType;
        this.businessType = businessType;
        this.dwelling = dwelling;
        this.dwelling.setHouse(house);
    }

    public List<Photograph> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photograph> photos) {
        this.photos = photos;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getCityCenter() {
        return cityCenter;
    }

    public void setCityCenter(int cityCenter) {
        this.cityCenter = cityCenter;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public Dwelling getDwelling() {
        return dwelling;
    }

    public void setDwelling(Dwelling dwelling) {
        this.dwelling = dwelling;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public void setOwnerEmail(String ownerEmail){this.owner.setEmailAddress(ownerEmail);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return area == property.area
                && cityCenter == property.cityCenter
                && price == property.price
                && Objects.equals(address, property.address)
                && Objects.equals(propertyType, property.propertyType)
                && Objects.equals(businessType, property.businessType)
                && Objects.equals(dwelling, property.dwelling);
    }

    @Override
    public int hashCode() {
        return Objects.hash(area, cityCenter, price, address, propertyType, businessType, dwelling);
    }

    @Override
    public String toString() {
        String txt = "Property:\n" +
                     "Area= " + area + " feet, " +
                     "Distance City Center= " + cityCenter + " miles, " +
                     "Address= " + address + "\n" +
                     "Price= $" + price + "\n" +
                     "Type Of Property= " + propertyType + "\n" +
                     "Type Of Business= " + businessType + "\n";

        if (dwelling != null){
            txt += dwelling.toString();
        }

        return txt;
    }

    public Property clone(){
        return new Property(area,cityCenter,price, address, propertyType, businessType, dwelling);
    }
}
