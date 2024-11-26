package pt.ipp.isep.dei.esoft.project.domain;


import java.io.Serializable;
import java.util.Objects;

/**
 * BusinessType of the property
 */
public class BusinessType implements Serializable {

    private static final long serialVersionUID = 2005701403314831572L;

    private String businessType; // Sale/Rent

    /**
     * creates a BusinessType
     * @param businessType the type of business
     */
    public BusinessType(String businessType){
        this.businessType = businessType;
    }

    /**
     * Get business type of the property
     * @return business type
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * Change the business type
     * @param businessType
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    /**
     * Equals method
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessType that = (BusinessType) o;
        return Objects.equals(businessType, that.businessType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessType);
    }

    /**
     * To string method
     * @return the string with the instance attribute values
     */
    @Override
    public String toString() {
        return businessType;
    }

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public BusinessType clone(){
        return new BusinessType(businessType);
    }
}
