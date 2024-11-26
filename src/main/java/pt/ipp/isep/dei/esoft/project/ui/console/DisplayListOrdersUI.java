package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.repository.OrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class DisplayListOrdersUI implements Runnable{

    /**
     * Displays a list of all orders.
     */
    public void run(){
        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println("     Orders Registered in the System:     ");
        System.out.println("---------------------------------------------");

        Repositories repositories = Repositories.getInstance();

        //Get Order Repository
        OrderRepository orderRepository = repositories.getOrderRepository();

        for (Order order : orderRepository.getOrders()) {
            if (orderRepository.getOrders() != null) {
                System.out.print("Property Price: $" + order.getProperty().getPrice() + "\nOrder Amount: $" + order.getOrderAmount() + "\nClient Email: " + order.getClientEmail() + "\n");
            }
            System.out.println("---------------------------------------------");
        }
        System.out.println("---------------------------------------------");
        System.out.println();
    }
}
