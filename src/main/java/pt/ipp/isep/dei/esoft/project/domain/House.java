package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

public class House implements Serializable {

    private static final long serialVersionUID = 8051932973716139437L;

    private boolean basement; //1-false 2-true
    private boolean inhabitableLoft; //1-false 2-true
    private int sunExposure; //1-North 2-East 3-South 4-West

    /**
     * Empty constructor
     */
    public House(){
    }

    /**
     * Constructor
     * @param basement
     * @param inhabitableLoft
     */
    public House(boolean basement, boolean inhabitableLoft){
        this.basement = basement;
        this.inhabitableLoft = inhabitableLoft;
    }

    /**
     * Constructor
     * @param basement
     * @param inhabitableLoft
     * @param sunExposure
     */
    public House(boolean basement, boolean inhabitableLoft, int sunExposure){
        this.basement = basement;
        this.inhabitableLoft = inhabitableLoft;
        this.sunExposure = sunExposure;
    }

    /**
     * Get basement
     * @return basement
     */
    public boolean getBasement() {
        return basement;
    }

    /**
     * Change basement
     * @param basement
     */
    public void setBasement(boolean basement) {
        this.basement = basement;
    }

    /**
     * Get inhabitable loft
     * @return true/false
     */
    public boolean getInhabitableLoft() {
        return inhabitableLoft;
    }

    /**
     * Change inhabitable loft value
     * @param inhabitableLoft
     */
    public void setInhabitableLoft(boolean inhabitableLoft) {
        this.inhabitableLoft = inhabitableLoft;
    }

    /**
     * Get sun exposure
     * @return
     */
    public int getSunExposure() {
        return sunExposure;
    }

    /**
     * Change sun exposure value
     * @param sunExposure
     */
    public void setSunExposure(int sunExposure) {
        this.sunExposure = sunExposure;
    }

    /**
     * To string method
     * @return the string with the instance attribute values
     */
    @Override
    public String toString() {

        String sunExposureString;
        String basementS;
        String inhabitableLoftS;
        switch (sunExposure){
            case 1:
                sunExposureString = "North";
                break;

            case 2:
                sunExposureString = "East";
                break;

            case 3:
                sunExposureString = "South";
                break;

            default:
                sunExposureString = "West";
                break;
        }

        if (basement){
            basementS = "Has";
        }else {basementS = "Hasn't";}
        if (inhabitableLoft){
            inhabitableLoftS= "Has";
        }else {inhabitableLoftS = "Hasn't";}

        return  basementS+ " basement, " +
                inhabitableLoftS + " inhabitable Loft\n" +
                "Sun Exposure= " + sunExposureString + " side \n";
    }
}
