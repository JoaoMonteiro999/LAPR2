package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.authorization.AuthenticationUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class MainMenuUI implements Runnable {

    public MainMenuUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register", new RegisterClientUI()));
        options.add(new MenuItem("Do Login", new AuthenticationUI()));
        options.add(new MenuItem("Know the Development Team", new DevTeamUI()));
        options.add(new MenuItem("Display List Properties", new ListPropertiesUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "----------------------------------------------\n" +
                                                              "                   Main Menu                  \n" +
                                                              "----------------------------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }

        } while (option != -1);

        ExitAppUI exitAppUI = new ExitAppUI();
        exitAppUI.run();
    }
}
