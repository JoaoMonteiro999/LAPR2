package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

public class Equipment implements Serializable {

    private static final long serialVersionUID = 6774017960898733546L;

    /**
     * Central heating
     */
    private boolean centralHeating;

    /**
     * Air conditioned
     */
    private boolean airConditioned;

    /**
     * Empty constructor
     */
    public Equipment(){
    }

    /**
     * Default constructor
     * @param centralHeating
     * @param airConditioned
     */
    public Equipment(boolean centralHeating, boolean airConditioned){
        this.centralHeating = centralHeating;
        this.airConditioned = airConditioned;
    }

    /**
     * Check if property has central heating
     * @return true/false
     */
    public boolean isCentralHeating() {
        return centralHeating;
    }

    /**
     * Change central heating value
     * @param centralHeating
     */
    public void setCentralHeating(boolean centralHeating) {
        this.centralHeating = centralHeating;
    }

    /**
     * Check if property is air conditioned
     * @return
     */
    public boolean isAirConditioned() {
        return airConditioned;
    }

    /**
     * Change air conditioned value
     * @param airConditioned
     */
    public void setAirConditioned(boolean airConditioned) {
        this.airConditioned = airConditioned;
    }

    /**
     * Equal method
     * @param o
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return centralHeating == equipment.centralHeating && airConditioned == equipment.airConditioned;
    }

    /**
     * To string method
     * @return the string with the instance attribute values
     */
    @Override
    public String toString() {

        String centralHeatingS;
        String airConditionedS;

        if (centralHeating){centralHeatingS ="Has";}
        else {centralHeatingS="Hasn't";}
        if (airConditioned){airConditionedS="Has";}
        else {airConditionedS="Hasn't";}


        return   centralHeatingS + " central heating, " +
                 airConditionedS + " air conditioned\n";
    }
}
