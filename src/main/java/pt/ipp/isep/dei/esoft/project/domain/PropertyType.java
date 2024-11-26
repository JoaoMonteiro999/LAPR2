package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

public class PropertyType implements Serializable {

    private static final long serialVersionUID = 4789222863503029010L;

    private int propertyType; // 0=apartment,1=house,2=land

    public PropertyType(int propertyType){
        this.propertyType = propertyType;
    }

    public int getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(int propertyType) {
        this.propertyType = propertyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyType that = (PropertyType) o;
        return propertyType == (that.propertyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyType);
    }

    @Override
    public String toString() {
        switch(propertyType) {
            case 0:
                return "Apartment";

            case 1:
                return "House";

            case 2:
                return "Land";

            default:
                return "No type";
        }

    }

    public PropertyType clone(){
        return new PropertyType(propertyType);
    }
}
