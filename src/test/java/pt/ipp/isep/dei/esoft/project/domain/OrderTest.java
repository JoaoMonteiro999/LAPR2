package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrderTest {

    @Test
    void ensureTwoOrdersWithSameAttributeValuesEquals(){

        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());

        Order order1 = new Order(property,1000, "client@this.app");
        Order order2 = new Order(property,1000, "client@this.app");
        assertEquals(order1,order2);
    }

    @Test void ensureOrderWithDifferentAttributeValuesNotEquals(){

        Property property1 = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        Property property2 = new Property(300, 300, 3000, new Address("Street", "London", "London", "ING", "30000"),new PropertyType(1), new BusinessType("Buy"), new Dwelling());

        Order order1 = new Order(property1, 1000, "client@this.app");
        Order order2 = new Order(property2, 3000, "client2@this.app");

        assertNotEquals(order1,order2);
    }

    @Test void ensureOrderDoesNotEqualNull() {

        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());

        Order order = new Order(property,1000, "client@this.app");

        assertNotEquals(order, null);
    }

    @Test void ensureOrderDoesNotEqualOtherObject() {

        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());

        Order order = new Order(property,1000, "client@this.app");

        assertNotEquals(order, new Object());
    }

    @Test void ensureTheSameObjectIsEqual() {

        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());

        Order order = new Order(property,1000, "client@this.app");

        assertEquals(order, order);
    }

    @Test void ensureCloneWorks(){

        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());

        Order order = new Order(property,1000, "client@this.app");
        Order clone = order.clone();

        assertEquals(order,clone);
    }
}
