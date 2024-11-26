package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.*;

public class OrderRepository {

    /**
     * List of order
     */
    public List<Order> orders = new ArrayList<>();

    /**
     * Adds an order to the orders list
     * @param order
     * @return
     */
    public boolean addOrder(Order order) {
        boolean success = false;

        if (validateOrder(order)) {
            success = orders.add(order.clone());
        }
        return success;
    }

    /**
     * This method checks if the order is already in the orders list
     * @param order
     * @return
     */
    private boolean validateOrder(Order order) {
        if (order == null) {
            return false;
        }
        return !orders.contains(order);
    }

    /**
     * This method is responsible for placing the order
     * @param property
     * @param orderAmount
     * @param clientEmail
     * @return order
     */
    public Optional<Order> placeOrder(Property property, Integer orderAmount, String clientEmail) {

        Optional<Order> optionalValue = Optional.empty();
        Order order = null;

        if (!checkClientMadeOrderSameProperty(clientEmail, property)) {
            order = new Order(property, orderAmount, clientEmail);
        }

        if (addOrder(order)) {
            optionalValue = Optional.of(order);
        }
        return optionalValue;
    }

    /**
     * This method checks if the client already made the order for the same property
     * @param clientEmail
     * @param property
     * @return true/false
     */
    private boolean checkClientMadeOrderSameProperty(String clientEmail, Property property) {
        for (Order order : orders) {
            if (order.getClientEmail().equals(clientEmail) && order.getProperty().equals(property)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks if the order amount if valid
     * @param property
     * @param orderAmount
     * @return true/false
     */
    public boolean checkOrderAmountIsValid(Property property, Integer orderAmount) {
        for (Order order : orders) {
            if (order.getProperty().equals(property) && order.getOrderAmount().equals(orderAmount)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of orders.
     *
     * @return The list of orders
     * .
     */
    public List<Order> getOrders() {
        return List.copyOf(orders);
    }

    /**
     * This method gets the regression parameters from the property that the order contains
     * @param chosenRegressionParameter
     * @return regression parameters
     */
    public Integer[][] getRegressionParameters(String chosenRegressionParameter) {

        List<List<Integer>> regressionParametersLists = new ArrayList<>();
        switch (chosenRegressionParameter) {
            case "":
                regressionParametersLists.add(getPropertyAreaList());
                regressionParametersLists.add(getDistanceCentreList());
                regressionParametersLists.add(getNumBedroomsList());
                regressionParametersLists.add(getNumBathroomsList());
                regressionParametersLists.add(getNumParkingSpacesList());
                break;
            case "Property Area (m2)":
                regressionParametersLists.add(getPropertyAreaList());
                break;
            case "Distance from Centre":
                regressionParametersLists.add(getDistanceCentreList());
                break;
            case "Number of Bedrooms":
                regressionParametersLists.add(getNumBedroomsList());
                break;
            case "Number of Bathrooms":
                regressionParametersLists.add(getNumBathroomsList());
                break;
            case "Number of Parking Spaces":
                regressionParametersLists.add(getNumParkingSpacesList());
                break;
        }
        regressionParametersLists.add(getPriceList());

        //Tranform the lists into a matrix
        Integer[][] regressionParamenters = new Integer[regressionParametersLists.size()][];

        for (int i = 0; i < regressionParametersLists.size(); i++) {
            List<Integer> parameterListRow = regressionParametersLists.get(i);
            Integer[] currentArray = new Integer[parameterListRow.size()];

            for (int j = 0; j < parameterListRow.size(); j++) {
                currentArray[j] = parameterListRow.get(j);
            }
            regressionParamenters[i] = currentArray;
        }
        return regressionParamenters;

    }

    /**
     * Get the price list
     * @return list of the prices
     */
    private List<Integer> getPriceList() {
        List<Integer> priceList = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {

            if ((orders.get(i).getProperty().getPropertyType().getPropertyType() == 0) || (orders.get(i).getProperty().getPropertyType().getPropertyType() == 1)) {
                priceList.add(orders.get(i).getOrderAmount());
            }
        }
        return priceList;
    }

    /**
     * Get the list containing the property area
     * @return list of the property area
     */
    private List<Integer> getPropertyAreaList() {
        List<Integer> areaList = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            if ((orders.get(i).getProperty().getPropertyType().getPropertyType() == 0) || (orders.get(i).getProperty().getPropertyType().getPropertyType() == 1)) {
                areaList.add(orders.get(i).getProperty().getArea());
            }
        }
        return areaList;
    }

    /**
     * Get the list of the distance from centre
     * @return list of the distance from centre
     */
    private List<Integer> getDistanceCentreList() {
        List<Integer> distanceList = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            if ((orders.get(i).getProperty().getPropertyType().getPropertyType() == 0) || (orders.get(i).getProperty().getPropertyType().getPropertyType() == 1)) {
                distanceList.add(orders.get(i).getProperty().getCityCenter());
            }

        }
        return distanceList;
    }

    /**
     * Get the list of the number of bedrooms
     * @return list of the number of bedrooms
     */
    private List<Integer> getNumBedroomsList() {
        List<Integer> numBedroomsList = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            if ((orders.get(i).getProperty().getPropertyType().getPropertyType() == 0) || (orders.get(i).getProperty().getPropertyType().getPropertyType() == 1)) {
                numBedroomsList.add(orders.get(i).getProperty().getDwelling().getNumBedrooms());
            }

        }
        return numBedroomsList;
    }

    /**
     * Get the list of the number of bathrooms
     * @return list of the number of bathrooms
     */
    private List<Integer> getNumBathroomsList() {
        List<Integer> numBathroomsList = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            if ((orders.get(i).getProperty().getPropertyType().getPropertyType() == 0) || (orders.get(i).getProperty().getPropertyType().getPropertyType() == 1)) {
                numBathroomsList.add(orders.get(i).getProperty().getDwelling().getNumBathrooms());
            }
        }
        return numBathroomsList;
    }

    /**
     * Get the list of the number of parking spaces
     * @return list of the number of parking spaces
     */
    private List<Integer> getNumParkingSpacesList() {
        List<Integer> numParkingSpcList = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            if ((orders.get(i).getProperty().getPropertyType().getPropertyType() == 0) || (orders.get(i).getProperty().getPropertyType().getPropertyType() == 1)) {
                numParkingSpcList.add(orders.get(i).getProperty().getDwelling().getNumParkingSpaces());
            }
        }
        return numParkingSpcList;
    }

    /**
     * This method returns the list of orders sorted by date
     * @return list of orders
     */
    public List<Order> getDealsMadeSortedByDate() {
        List<Order> dealsMade = getDealsMade();

        Collections.sort(dealsMade, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o2.getDateAccepted().compareTo(o1.getDateAccepted());
            }
        });

        return dealsMade;
    }

    /**
     * Get the list of deal checking if the order is a deal (if the order is accepted)
     * @return list of accepted orders
     */
    private List<Order> getDealsMade() {
        List<Order> dealsMade = new ArrayList<>();

        for (Order order : orders) {
            if (order.isOrderAccepted()) {
                dealsMade.add(order);
            }
        }
        return dealsMade;
    }

    /**
     * Get the deals sorted by the property area using bubble sort
     * @param ascending
     * @return list of sorted orders
     */
    public List<Order> getDealsMadeSortedByPropertyAreaBubbleSort(boolean ascending) {
        List<Order> dealsMade = getDealsMade();

        new BubbleSortAlgorithm().sort(dealsMade, ascending ? SortAlgorithm.SortingOrder.ASC : SortAlgorithm.SortingOrder.DSC);

        return dealsMade;
    }

    /**
     * Get the deals sorted by the property area using the selection sort
     * @param ascending
     * @return list of sorted orders
     */
    public List<Order> getDealsMadeSortedByPropertyAreaSelectionSort(boolean ascending) {
        List<Order> dealsMade = getDealsMade();

        new SelectionSortAlgorithm().sort(dealsMade, ascending ? SortAlgorithm.SortingOrder.ASC : SortAlgorithm.SortingOrder.DSC);

        return dealsMade;
    }

}