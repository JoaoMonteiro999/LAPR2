package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ClientUI implements Runnable{

    public ClientUI(){
    }

    public void run(){
        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("Place Order", new PlaceOrderUI()));
        options.add(new MenuItem("Display List of Orders", new DisplayListOrdersUI()));
        options.add(new MenuItem("Request a Visit Schedule", new VisitScheduleRequestUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "----------------------------------------------\n" +
                                                              "                  Client Menu                  \n" +
                                                              "----------------------------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
