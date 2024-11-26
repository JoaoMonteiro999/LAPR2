package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTypeTest {
    //Tests for equals and hashcode
    @Test
    void testEqualsSameObject() {
        PropertyType propertyType = new PropertyType(100);
        assertEquals(propertyType, propertyType);
    }

    @Test
    void testEqualsDifferentClass() {
        PropertyType propertyType = new PropertyType(100);
        assertNotEquals("", propertyType);
    }

    @Test
    void testEqualsNull() {
        PropertyType propertyType = new PropertyType(100);
        assertNotEquals(null, propertyType);
    }

    @Test
    void testEqualsDifferentObject() {
        PropertyType propertyType = new PropertyType(100);
        PropertyType propertyType1 = new PropertyType(101);
        assertNotEquals(propertyType, propertyType1);
    }

    @Test
    void testEqualsSameObjectDifferentPropertyType() {
        PropertyType propertyType = new PropertyType(100);
        PropertyType propertyType1 = new PropertyType(101);
        assertNotEquals(propertyType, propertyType1);
    }

    @Test
    void testEqualsSameObjectSamePropertyType() {
        PropertyType propertyType = new PropertyType(100);
        PropertyType propertyType1 = new PropertyType(100);
        assertEquals(propertyType, propertyType1);
    }

    @Test
    void testHashCodeSameObject() {
        PropertyType propertyType = new PropertyType(100);
        assertEquals(propertyType.hashCode(), propertyType.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        PropertyType propertyType = new PropertyType(100);
        PropertyType propertyType1 = new PropertyType(101);
        assertNotEquals(propertyType.hashCode(), propertyType1.hashCode());
    }

    @Test
    void testHashCodeSameObjectDifferentPropertyType() {
        PropertyType propertyType = new PropertyType(100);
        PropertyType propertyType1 = new PropertyType(101);
        assertNotEquals(propertyType.hashCode(), propertyType1.hashCode());
    }

    @Test
    void testHashCodeSameObjectSamePropertyType() {
        PropertyType propertyType = new PropertyType(100);
        PropertyType propertyType1 = new PropertyType(100);
        assertEquals(propertyType.hashCode(), propertyType1.hashCode());
    }

    @Test
    void testEqualsForDifferentObjectType() {
        PropertyType propertyType = new PropertyType(100);
        assertNotEquals(propertyType, new Object());
    }

    @Test
    void ensureGetPropertyTypeWorks() {
        PropertyType propertyType = new PropertyType(100);

        assertEquals(100, propertyType.getPropertyType());
    }


    @Test
    void ensureCloneWorks() {
        PropertyType propertyType = new PropertyType(100);
        PropertyType clone = propertyType.clone();
        assertEquals(propertyType, clone);
    }


}