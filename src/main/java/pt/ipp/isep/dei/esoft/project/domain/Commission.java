package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

public class Commission implements Serializable {

    private static final long serialVersionUID = 5014728663384696248L;

    private boolean typeOfCommission; // false=fixed amount , true=percentage
    private int commissionValue;


    public Commission(boolean typeOfCommission){
        this.typeOfCommission=typeOfCommission;
    }
    public Commission(boolean typeOfCommission,int commissionValue){
        this.typeOfCommission=typeOfCommission;
        this.commissionValue=commissionValue;

    }

    public boolean isTypeOfComission() {
        return typeOfCommission;
    }

    public void setTypeOfComission(boolean typeOfComission) {
        this.typeOfCommission = typeOfComission;
    }

    public double getCommissionValue() {
        return commissionValue;
    }

    public void setCommissionValue(int commissionValue) {
        this.commissionValue = commissionValue;
    }
}
