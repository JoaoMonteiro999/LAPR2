package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.OrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ListDealsControllerTest {

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
        ListDealsController controller = new ListDealsController(orderRepository);


        //Act
        List<Order> dealsMade = controller.getDealsMadeSortedByDate();

        //Assert
        assertEquals(2, dealsMade.size());
        assertEquals(LocalDate.of(2023,6,2), dealsMade.get(0).getDateAccepted());
    }

    @Test
    void ensureDealsSortedAscendingUsingBubblesortAreCorrect(){
        //Arrange
        OrderRepository orderRepository = new OrderRepository();
        arrangeOrders(orderRepository);
        ListDealsController controller = new ListDealsController(orderRepository);

        //Act
        List<Order> dealsMade = controller.getDealsMadeSortedByPropertyAreaBubbleSort(true);

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
        ListDealsController controller = new ListDealsController(orderRepository);

        //Act
        List<Order> dealsMade = controller.getDealsMadeSortedByPropertyAreaBubbleSort(false);

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
        ListDealsController controller = new ListDealsController(orderRepository);

        //Act
        List<Order> dealsMade = controller.getDealsMadeSortedByPropertyAreaSelectionSort(true);

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
        ListDealsController controller = new ListDealsController(orderRepository);

        //Act
        List<Order> dealsMade = controller.getDealsMadeSortedByPropertyAreaSelectionSort(false);

        //Assert
        assertEquals(2, dealsMade.size());
        assertEquals(200, dealsMade.get(0).getProperty().getArea());
        assertEquals(100, dealsMade.get(1).getProperty().getArea());
    }
}
