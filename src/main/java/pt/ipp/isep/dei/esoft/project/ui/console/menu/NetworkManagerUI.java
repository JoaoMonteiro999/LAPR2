package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.DivideAgenciesUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class NetworkManagerUI implements Runnable{

    public NetworkManagerUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("Divide the set of all stores", new DivideAgenciesUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "----------------------------------------------\n" +
                                                              "             Network Manager Menu             \n" +
                                                              "----------------------------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);

    }
}
