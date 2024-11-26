package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderRepositoryTest {

    @Test
    void ensureThatPlaceOrderWorks(){
        OrderRepository orderRepository = new OrderRepository();

        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        Order expected = new Order(property, 1000, "client@this.app");

        Optional<Order> order = orderRepository.placeOrder(property,1000,"client@this.app");

        assertNotNull(order);
        assertTrue(order.isPresent());
        assertEquals(expected,order.get());
    }

    @Test
    void ensureNewOrderSuccessfullyAdded(){
        OrderRepository orderRepository = new OrderRepository();

        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        Order order = new Order(property, 1000, "client@this.app");

        boolean returnOrder = orderRepository.addOrder(order);

        assertTrue(returnOrder);
    }

    @Test void ensureGetOrdersReturnsAnImmutableList() {
        OrderRepository orderRepository = new OrderRepository();

        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        Property property2 = new Property(300, 300, 3000, new Address("Street", "London", "London", "ING", "30000"),new PropertyType(1), new BusinessType("Buy"), new Dwelling());

        Order order = new Order(property, 1000, "client@this.app");

        orderRepository.addOrder(order);

        assertThrows(UnsupportedOperationException.class,
                () -> orderRepository.getOrders().add(new Order(property2,3000,"client@this.app")));
    }

    @Test
    void ensureGetOrdersReturnsTheCorrectList() {
        //Arrange
        OrderRepository orderRepository = new OrderRepository();

        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        Order order = new Order(property, 1000, "client@this.app");

        orderRepository.addOrder(order);
        int expectedSize = 1;

        //Act
        int size = orderRepository.getOrders().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(order, orderRepository.getOrders().get(size - 1));
    }

    @Test
    void ensureAddingDuplicateOrderFails() {
        OrderRepository orderRepository = new OrderRepository();

        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        Order order = new Order(property, 1000, "client@this.app");

        orderRepository.addOrder(order);

        boolean result = orderRepository.addOrder(order);

        assertFalse(result);
    }

    @Test
    void ensureAddingDifferentOrdersWorks() {
        //Arrange
        OrderRepository orderRepository = new OrderRepository();

        Property property1 = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        Property property2 = new Property(300, 300, 3000, new Address("Street", "London", "London", "ING", "30000"),new PropertyType(1), new BusinessType("Buy"), new Dwelling());

        Order order1 = new Order(property1, 1000, "client@this.app");
        Order order2 = new Order(property2, 3000, "client2@this.app");

        //Add the first client
        orderRepository.addOrder(order1);

        //Act
        boolean result = orderRepository.addOrder(order2);

        //Assert
        assertTrue(result);
    }

    @Test
    void ensureCheckOrderAmountIsValidWorks(){
        OrderRepository orderRepository = new OrderRepository();

        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        Order order1 = new Order(property, 1000, "client@this.app");

        orderRepository.addOrder(order1);

        boolean result = orderRepository.checkOrderAmountIsValid(property,1000);

        assertTrue(result);
    }

    @Test
    void ensureCheckOrderAmountIsValidFails(){
        OrderRepository orderRepository = new OrderRepository();

        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        Order order1 = new Order(property, 1000, "client@this.app");

        orderRepository.addOrder(order1);

        boolean result = orderRepository.checkOrderAmountIsValid(property,1500);

        assertFalse(result);
    }


    private void arrangeOrders(OrderRepository orderRepository){
        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        Order order = new Order(property, 1000, "client@this.app");
        orderRepository.addOrder(order);

        Property property2 = new Property(200, 100, 1000, new Address("Rua", "Lisboa", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        Order order2 = new Order(property2, 1000, "client@this.app");
        orderRepository.addOrder(order2);

        Property property3 = new Property(300, 100, 1000, new Address("Rua", "Coimbra", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        Order order3 = new Order(property3, 1000, "client@this.app");
        orderRepository.addOrder(order3);

        orderRepository.getOrders().get(0).setOrderAccepted(true);
        orderRepository.getOrders().get(0).setDateAccepted(LocalDate.of(2023,6,1));

        orderRepository.getOrders().get(1).setOrderAccepted(true);
        orderRepository.getOrders().get(1).setDateAccepted(LocalDate.of(2023,6,2));


    }
    @Test
    void ensureDealsSortedByDateAreCorrect(){
        //Arrange
        OrderRepository orderRepository = new OrderRepository();
        arrangeOrders(orderRepository);

        //Act
        List<Order> dealsMade = orderRepository.getDealsMadeSortedByDate();

        //Assert
        assertEquals(2, dealsMade.size());
        assertEquals(LocalDate.of(2023,6,2), dealsMade.get(0).getDateAccepted());
    }

    @Test
    void ensureDealsSortedAscendingUsingBubblesortAreCorrect(){
        //Arrange
        OrderRepository orderRepository = new OrderRepository();
        arrangeOrders(orderRepository);

        //Act
        List<Order> dealsMade = orderRepository.getDealsMadeSortedByPropertyAreaBubbleSort(true);

        //Assert
        assertEquals(2, dealsMade.size());
        assertEquals(100, dealsMade.get(0).getProperty().getArea());
        assertEquals(200, dealsMade.get(1).getProperty().getArea());
    }


    @Test
    void ensureDealsSortedDescendingUsingBubblesortAreCorrect(){
        //Arrange
        OrderRepository orderRepository = new OrderRepository();
        arrangeOrders(orderRepository);

        //Act
        List<Order> dealsMade = orderRepository.getDealsMadeSortedByPropertyAreaBubbleSort(false);

        //Assert
        assertEquals(2, dealsMade.size());
        assertEquals(200, dealsMade.get(0).getProperty().getArea());
        assertEquals(100, dealsMade.get(1).getProperty().getArea());
    }

    @Test
    void ensureDealsSortedAscendingUsingSelectionsortAreCorrect(){
        //Arrange
        OrderRepository orderRepository = new OrderRepository();
        arrangeOrders(orderRepository);

        //Act
        List<Order> dealsMade = orderRepository.getDealsMadeSortedByPropertyAreaSelectionSort(true);

        //Assert
        assertEquals(2, dealsMade.size());
        assertEquals(100, dealsMade.get(0).getProperty().getArea());
        assertEquals(200, dealsMade.get(1).getProperty().getArea());
    }


    @Test
    void ensureDealsSortedDescendingUsingSelectionsortAreCorrect(){
        //Arrange
        OrderRepository orderRepository = new OrderRepository();
        arrangeOrders(orderRepository);

        //Act
        List<Order> dealsMade = orderRepository.getDealsMadeSortedByPropertyAreaSelectionSort(false);

        //Assert
        assertEquals(2, dealsMade.size());
        assertEquals(200, dealsMade.get(0).getProperty().getArea());
        assertEquals(100, dealsMade.get(1).getProperty().getArea());
    }
}
