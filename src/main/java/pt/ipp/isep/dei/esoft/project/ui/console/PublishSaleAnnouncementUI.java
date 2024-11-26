package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishSaleAnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.ClientRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.Email;


import java.util.List;
import java.util.Scanner;

public class PublishSaleAnnouncementUI implements Runnable {

    private Property property;



    private final PublishSaleAnnouncementController controller = new PublishSaleAnnouncementController();

    public void run() {

        property = controller.requestData();

        controller.createSaleAnnouncement(property);

        System.out.println("\nThe add has been created!\n");

        controller.sendSmss();

    }
}



