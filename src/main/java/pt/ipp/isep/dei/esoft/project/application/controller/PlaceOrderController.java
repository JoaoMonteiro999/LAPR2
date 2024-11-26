package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.RoleRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.ListPropertiesUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller for placing an order
 */
public class PlaceOrderController {

    /**
     * Order repository
     */
    private OrderRepository orderRepository = null;

    /**
     * Authentication repository
     */
    private AuthenticationRepository authenticationRepository = null;

    /**
     * Default constructor
     */
    public PlaceOrderController(){
        getOrderRepository();
        getAuthenticationRepository();
    }

    /**
     * Constructor
     * @param orderRepository
     * @param authenticationRepository
     */
    public PlaceOrderController(OrderRepository orderRepository, AuthenticationRepository authenticationRepository){
        this.orderRepository = orderRepository;
        this.authenticationRepository = authenticationRepository;
    }

    /**
     * Responsible for placing an order
     * @param property
     * @param orderAmount
     * @return the order that was placed
     */
    public Optional<Order> placeOrder(Property property, Integer orderAmount){

        Optional<Order> newOrder = Optional.empty();

        try {
            String clientEmail = getClientEmailFromSession();

            newOrder = orderRepository.placeOrder(property,orderAmount,clientEmail);

        } catch (IllegalArgumentException e) {
            System.out.println("Order data is invalid");
        } catch (Exception e){
            e.printStackTrace();
        }
        return newOrder;
    }

    /**
     * Get order repository in the Repositories class
     * @return the order repository
     */
    private OrderRepository getOrderRepository() {
        if (orderRepository == null) {
            Repositories repositories = Repositories.getInstance();
            orderRepository = repositories.getOrderRepository();
        }
        return orderRepository;
    }

    /**
     * Get authentication repository in the Repositories class
     * @return the authentication repository
     */
    private AuthenticationRepository getAuthenticationRepository(){
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Get the list of orders in the order repository
     * @return the list of orders
     */
    public List<Order> getOrders(){
        OrderRepository orderRepository = getOrderRepository();
        return orderRepository.getOrders();
    }

    /**
     * Calls the ui responsible for displaying the properties
     * @return a list of the sorted properties
     */
    public List<Property> displayProperties(){
        ListPropertiesUI listPropertiesUI = new ListPropertiesUI();
        listPropertiesUI.run();
        return listPropertiesUI.getFilteredPropertiesList();

    }

    /**
     * Get the client email from the current user in session
     * @return the email address
     */
    private String getClientEmailFromSession(){
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return email.getEmail();
    }

    /**
     * Calls the method to check if the order amount is valid
     * @param property
     * @param orderAmount
     * @return true of false
     */
    public boolean checkOrderAmountIsValid(Property property, Integer orderAmount){
        return orderRepository.checkOrderAmountIsValid(property,orderAmount);
    }
}
