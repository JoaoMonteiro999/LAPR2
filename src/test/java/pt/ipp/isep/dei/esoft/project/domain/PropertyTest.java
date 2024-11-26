package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest{
    //Tests for equals and hashcode
    @Test
    void testEqualsSameObject() {
        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        assertEquals(property, property);
    }

    @Test
    void testEqualsDifferentClass() {
        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        assertNotEquals("", property);
    }

    @Test
    void testEqualsNull() {
        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        assertNotEquals(null, property);
    }

    @Test
    void testEqualsDifferentObject() {
        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        Property property1 = new Property(200, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        assertNotEquals(property, property1);
    }


    @Test
    void testEqualsSameObjectDifferentArea() {
        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        Property property1 = new Property(200, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        assertNotEquals(property, property1);
    }

    @Test
    void testEqualsSameObjectDifferentCityCenter() {
        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        Property property1 = new Property(100, 200, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        assertNotEquals(property, property1);
    }

    @Test
    void testEqualsSameObjectSameProperties() {
        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        Property property1 = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        assertEquals(property, property1);
    }

    @Test
    void testHashCodeSameObject() {
        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        assertEquals(property.hashCode(), property.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Property property  = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        Property property1 = new Property(1000, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        assertNotEquals(property.hashCode(), property1.hashCode());
    }

    @Test
    void testHashCodeSameObjectSameProperties() {
        Property property  = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        Property property1 = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        assertEquals(property.hashCode(), property1.hashCode());
    }

    @Test
    void testHashCodeSameObjectDifferentArea() {
        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        Property property1 = new Property(200, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        assertNotEquals(property.hashCode(), property1.hashCode());
    }

    @Test
    void testHashCodeSameObjectDifferentCityCenter() {
        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        Property property1 = new Property(100, 200, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        assertNotEquals(property.hashCode(), property1.hashCode());
    }

    @Test
    void testEqualsForDifferentObjectType() {
        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        assertNotEquals(property, new Object());
    }

    @Test
    void ensureCloneWorks() {
        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        Property clone = property.clone();
        assertEquals(property, clone);
    }
}