package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.DisplayListEmployeesUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ImportLegacyDataUI;
import pt.ipp.isep.dei.esoft.project.ui.console.RegisterEmployeeUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AdminUI implements Runnable{

    public AdminUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("Register Employee", new RegisterEmployeeUI()));
        options.add(new MenuItem("Display List of Employees", new DisplayListEmployeesUI()));
        options.add(new MenuItem("Import Data from Legacy System", new ImportLegacyDataUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "----------------------------------------------\n" +
                                                              "                  Admin Menu                  \n" +
                                                              "----------------------------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);

    }
}
