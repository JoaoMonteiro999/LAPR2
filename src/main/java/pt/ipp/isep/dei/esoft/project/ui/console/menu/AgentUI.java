package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.ListRequestUI;
import pt.ipp.isep.dei.esoft.project.ui.console.PublishSaleAnnouncementUI;
import pt.ipp.isep.dei.esoft.project.ui.console.RegisterEmployeeUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AgentUI implements Runnable{

    public AgentUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("Publish Sale Announcement",new PublishSaleAnnouncementUI()));
        options.add(new MenuItem("Requests list",new ListRequestUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "----------------------------------------------\n" +
                                                              "                  Agent Menu                  \n" +
                                                              "----------------------------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);

    }
}
