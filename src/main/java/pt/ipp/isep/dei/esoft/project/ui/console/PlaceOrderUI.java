package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListPropertiesController;
import pt.ipp.isep.dei.esoft.project.application.controller.PlaceOrderController;
import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.*;

public class PlaceOrderUI implements Runnable{

    private final PlaceOrderController controller = new PlaceOrderController();

    private Property property;
    private Integer orderAmount;

    private PlaceOrderController getController(){return controller;}

    public void run() {
        System.out.println("Place Order");

        //Show properties with filters
        List<Property> filteredAndSortedProperties = displayProperties();

        //Selects property and keep showing properties if cancelled
        int selectedOption = Utils.showAndSelectIndex(filteredAndSortedProperties,"List of Properties by Filter and Sort");

        if (selectedOption != -1) {
            //Fill property
            property = filteredAndSortedProperties.get(selectedOption);

            //Message to be showed if the order amount is the same
            String message = "";

            //Request order amount and submit data
            if (property != null) {
                orderAmount = requestOrderAmount();

                if (checkOrderAmountIsValid(property, orderAmount)) {
                    message = "The order amount submitted has already been posted for this property. Please contact the agent that is responsible for this property.";
                }
                submitData(message);

            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void submitData(String message){
        Optional<Order> order = Optional.empty();

        try {
            order = controller.placeOrder(property, orderAmount);

        } catch (IllegalArgumentException e) {
            System.out.println("Order data is invalid!");

        } finally {

            if (order.isPresent()) {

                //Show message if order amount is equal to another client order (if not, show operation success)
                if (!message.isEmpty()){
                    System.out.println(message);
                } else {
                    System.out.println("Order placed successfully!");
                }

            } else {
                System.out.println("The order was already submitted by you. If you want to place an order to the same property, you need to wait until the previous one is declined.");
            }
        }
    }

    private Integer requestOrderAmount(){
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Order Amount: ");
                Integer orderAmount = input.nextInt();

                if (orderAmount <= property.getPrice() && orderAmount >= 0) {
                    return orderAmount;
                } else {
                    throw new IllegalArgumentException();
                }

            } catch (IllegalArgumentException e1){
                System.out.println("\nThe entered order amount is invalid! The order amount must be equal or lower than the original price.\n");

            } catch (InputMismatchException e2) {
                System.out.println("\nOrder Amount is invalid! The order amount must be equal or lower.\n");
                input.next(); //clear the invalid input from the scanner
            }
        }
    }

    private boolean checkOrderAmountIsValid(Property property, Integer orderAmount){
            return controller.checkOrderAmountIsValid(property,orderAmount);
    }

    private List<Property> displayProperties() {
        return controller.displayProperties();
    }

}



