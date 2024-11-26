package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BusinessTypeTest {
    //Tests for equals and hashcode
    @Test
    void testEqualsSameObject() {
        BusinessType businessType = new BusinessType("Rent");
        assertEquals(businessType, businessType);
    }

    @Test
    void testEqualsDifferentClass() {
        BusinessType businessType = new BusinessType("Rent");
        assertNotEquals("", businessType);
    }

    @Test
    void testEqualsNull() {
        BusinessType businessType = new BusinessType("Rent");
        assertNotEquals(null, businessType);
    }

    @Test
    void testEqualsDifferentObject() {
        BusinessType businessType = new BusinessType("Rent");
        BusinessType businessType1 = new BusinessType("Sale");
        assertNotEquals(businessType, businessType1);
    }

    @Test
    void testEqualsSameObjectDifferentBusinessType() {
        BusinessType businessType = new BusinessType("Rent");
        BusinessType businessType1 = new BusinessType("Sale");
        assertNotEquals(businessType, businessType1);
    }

    @Test
    void testEqualsSameObjectSameBusinessType() {
        BusinessType businessType = new BusinessType("Rent");
        BusinessType businessType1 = new BusinessType("Rent");
        assertEquals(businessType, businessType1);
    }

    @Test
    void testHashCodeSameObject() {
        BusinessType businessType = new BusinessType("Rent");
        assertEquals(businessType.hashCode(), businessType.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        BusinessType businessType = new BusinessType("Rent");
        BusinessType businessType1 = new BusinessType("Sale");
        assertNotEquals(businessType.hashCode(), businessType1.hashCode());
    }

    @Test
    void testHashCodeSameObjectDifferentBusinessType() {
        BusinessType businessType = new BusinessType("Rent");
        BusinessType businessType1 = new BusinessType("Sale");
        assertNotEquals(businessType.hashCode(), businessType1.hashCode());
    }

    @Test
    void testHashCodeSameObjectSameBusinessType() {
        BusinessType businessType = new BusinessType("Rent");
        BusinessType businessType1 = new BusinessType("Rent");
        assertEquals(businessType.hashCode(), businessType1.hashCode());
    }

    @Test
    void testEqualsForDifferentObjectType() {
        BusinessType businessType = new BusinessType("Rent");
        assertNotEquals(businessType, new Object());
    }

    @Test
    void ensureGetBusinessTypeWorks() {
        BusinessType businessType = new BusinessType("Rent");

        assertEquals("Rent", businessType.getBusinessType());
    }


    @Test
    void ensureCloneWorks() {
        BusinessType businessType = new BusinessType("Rent");
        BusinessType clone = businessType.clone();
        assertEquals(businessType, clone);
    }


}