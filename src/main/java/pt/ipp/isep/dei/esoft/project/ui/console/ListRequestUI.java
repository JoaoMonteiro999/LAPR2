package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.ListRequestController;
import pt.ipp.isep.dei.esoft.project.domain.Request;

import java.util.List;

public class ListRequestUI implements Runnable {


    private final ListRequestController controller = new ListRequestController();

    public ListRequestUI(){

    }

    public void run(){

        //List requests
        controller.ListRequests();


        //options to choose one or cancel
        controller.ChooseOne();



    }







    public List<Request> getRequestList(){
        return controller.getRequestList();
    }




}
