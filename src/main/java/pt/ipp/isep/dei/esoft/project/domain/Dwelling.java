package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

public class Dwelling implements Serializable {

    private static final long serialVersionUID = 1871293789204008071L;

    private int numBedrooms;
    private int numBathrooms; // optional
    private int numParkingSpaces;
    private Equipment equipment;
    private House house;


    public Dwelling(int numBedrooms, int numParkingSpaces){
        this.numBedrooms=numBedrooms;
        this.numParkingSpaces=numParkingSpaces;
    }

    public Dwelling(int numBedrooms,int numBathrooms, int numParkingSpaces){
        this.numBedrooms=numBedrooms;
        this.numBathrooms=numBathrooms;
        this.numParkingSpaces=numParkingSpaces;
    }
    public Dwelling(int numBedrooms, int numParkingSpaces,Equipment equipment){
        this.numBedrooms=numBedrooms;
        this.numParkingSpaces=numParkingSpaces;
        this.equipment=equipment;
    }
    public Dwelling(int numBedrooms,int numBathrooms, int numParkingSpaces,Equipment equipment){
        this.numBedrooms=numBedrooms;
        this.numBathrooms=numBathrooms;
        this.numParkingSpaces=numParkingSpaces;
        this.equipment=equipment;
    }

    public Dwelling(int numBedrooms, int numParkingSpaces,House house){
        this.numBedrooms=numBedrooms;
        this.numParkingSpaces=numParkingSpaces;
        this.house = house;
    }

    public Dwelling(int numBedrooms,int numBathrooms, int numParkingSpaces,House house){
        this.numBedrooms=numBedrooms;
        this.numBathrooms=numBathrooms;
        this.numParkingSpaces=numParkingSpaces;
        this.house = house;

    }
    public Dwelling(int numBedrooms, int numParkingSpaces,Equipment equipment,House house){
        this.numBedrooms=numBedrooms;
        this.numParkingSpaces=numParkingSpaces;
        this.equipment=equipment;
        this.house = house;

    }
    public Dwelling(int numBedrooms,int numBathrooms, int numParkingSpaces,Equipment equipment,House house){
        this.numBedrooms=numBedrooms;
        this.numBathrooms=numBathrooms;
        this.numParkingSpaces=numParkingSpaces;
        this.equipment=equipment;
        this.house = house;
    }


    public Dwelling(){
    }

    public int getNumBedrooms() {
        return numBedrooms;
    }

    public void setNumBedrooms(int numBedrooms) {
        this.numBedrooms = numBedrooms;
    }

    public int getNumBathrooms() {
        return numBathrooms;
    }

    public void setNumBathrooms(int numBathrooms) {
        this.numBathrooms = numBathrooms;
    }

    public int getNumParkingSpaces() {
        return numParkingSpaces;
    }

    public void setNumParkingSpaces(int numParkingSpaces) {
        this.numParkingSpaces = numParkingSpaces;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dwelling dwelling = (Dwelling) o;
        return numBedrooms == dwelling.numBedrooms && numBathrooms == dwelling.numBathrooms && numParkingSpaces == dwelling.numParkingSpaces;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numBedrooms, numBathrooms, numParkingSpaces);
    }

    @Override
    public String toString() {
        String txt = "Bedrooms= " + numBedrooms + ", " +
                     "Bathrooms= " + numBathrooms + ", " +
                     "Parking Spaces= " + numParkingSpaces + "\n";

        if (equipment != null){
            txt += equipment;
        }

        if (house != null){
            txt += house.toString();
        }

        return txt;
    }
}
