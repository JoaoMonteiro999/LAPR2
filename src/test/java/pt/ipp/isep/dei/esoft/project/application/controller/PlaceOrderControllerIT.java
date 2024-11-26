package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.PropertiesRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PlaceOrderControllerIT {

    @Test
    void ensurePlaceOrderWorks() {
        //Arrange
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        OrderRepository orderRepository = new OrderRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        //Fill Order Repository
        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        orderRepository.addOrder(new Order(property,1000,"client@this.app"));

        //Add authentication for user client@this.app
        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Client", "client@this.app", "client",
                AuthenticationController.ROLE_CLIENT);

        authenticationRepository.doLogin("client@this.app", "client");

        PlaceOrderController controller = new PlaceOrderController(orderRepository, authenticationRepository);

        //Act
        Property property2 = new Property(300, 300, 3000, new Address("Street", "London", "London", "ING", "30000"),new PropertyType(1), new BusinessType("Buy"), new Dwelling());
        Optional<Order> newOrder = controller.placeOrder(property2,3000);
    }

    @Test
    void ensureGetCategoriesWork() {
        //Arrange
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        OrderRepository orderRepository = new OrderRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        //Fill Order Repository
        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        Order order = new Order(property,1000,"client@this.app");
        orderRepository.addOrder(order);

        Property property2 = new Property(300, 300, 3000, new Address("Street", "London", "London", "ING", "30000"),new PropertyType(1), new BusinessType("Buy"), new Dwelling());
        Order order2 = new Order(property2, 3000, "client@this.app");
        orderRepository.addOrder(order2);

        List<Order> expected = new ArrayList<>();
        expected.add(order);
        expected.add(order2);

        //Add authentication for user client@this.app
        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Client", "client@this.app", "client",
                AuthenticationController.ROLE_CLIENT);

        PlaceOrderController controller = new PlaceOrderController(orderRepository, authenticationRepository);

        //Act
        List<Order> orders = controller.getOrders();

        assertArrayEquals(expected.toArray(), orders.toArray());
    }

    @Test
    void ensureCheckOrderAmountIsValidWorks(){
        //Arrange
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        OrderRepository orderRepository = new OrderRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        //Fill Order Repository
        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        Order order = new Order(property,1000,"client@this.app");
        orderRepository.addOrder(order);

        //Add authentication for user client@this.app
        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Client", "client@this.app", "client",
                AuthenticationController.ROLE_CLIENT);

        authenticationRepository.doLogin("client@this.app", "client");

        PlaceOrderController controller = new PlaceOrderController(orderRepository,authenticationRepository);

        //Act
        boolean expected = true;

        boolean result = controller.checkOrderAmountIsValid(property,1000);

        assertEquals(expected,result);
    }

    @Test
    void ensureCheckOrderAmountIsValidFails(){
        //Arrange
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        OrderRepository orderRepository = new OrderRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        //Fill Order Repository
        Property property = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        Order order = new Order(property,1000,"client@this.app");
        orderRepository.addOrder(order);

        //Add authentication for user client@this.app
        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Client", "client@this.app", "client",
                AuthenticationController.ROLE_CLIENT);

        authenticationRepository.doLogin("client@this.app", "client");

        PlaceOrderController controller = new PlaceOrderController(orderRepository,authenticationRepository);

        //Act
        boolean expected = false;

        boolean result = controller.checkOrderAmountIsValid(property,900);

        assertEquals(expected,result);
    }

}
