package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.AnalyseDealsUI;
import pt.ipp.isep.dei.esoft.project.ui.console.DisplayListEmployeesUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ImportLegacyDataUI;
import pt.ipp.isep.dei.esoft.project.ui.console.RegisterEmployeeUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ManagerUI implements Runnable{

    public ManagerUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("Analyse deals", new AnalyseDealsUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "----------------------------------------------\n" +
                                                              "                 Manager Menu                 \n" +
                                                              "----------------------------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);

    }
}
