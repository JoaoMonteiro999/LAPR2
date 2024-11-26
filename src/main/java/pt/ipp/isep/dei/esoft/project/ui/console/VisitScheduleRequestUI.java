package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterClientController;
import pt.ipp.isep.dei.esoft.project.application.controller.VisitScheduleRequestController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.ScheduleSlot;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ScheduledFuture;

public class VisitScheduleRequestUI implements Runnable{

    private VisitScheduleRequestController controller = new VisitScheduleRequestController();

    private Property property;

    private List<ScheduleSlot> scheduleSlots = new ArrayList<>();


    private LocalDate date;
    private int from;
    private int to;

    public void run(){
        System.out.println("Visit Schedule Request");

        requestData();

        if (property==null)
            return;

        submitData();
    }

    private void submitData(){
        //send data to controller
        controller.submitVisitScheduleRequest(property, scheduleSlots);

        System.out.println("The Message was created with success");
    }

    private void requestData(){
        List<Property> properties = controller.getListOfPropertiesSorted();

        property = (Property) Utils.showAndSelectOne(properties, "Select a Property");

        if (property==null)
            return;

        while(true) {
            ScheduleSlot slot = requestScheduleSlot();
            for (ScheduleSlot s : scheduleSlots) {
                if (s.overlap(slot)) {
                    System.out.println("Invalid slot, overlaps another");
                    slot=null;
                    break;
                }
            }
            if (slot!=null)
                scheduleSlots.add(slot);

            if (!Utils.confirm("Insert another Slot (n/y) ?"))
                break;
        }
    }

    private ScheduleSlot requestScheduleSlot() {

        date = null;
        while(date==null) {
            String line = Utils.readLineFromConsole("Type Date (format: yyyy-mm-dd):");
            try {
                date = LocalDate.parse(line, DateTimeFormatter.ISO_DATE);
            } catch (DateTimeParseException ex) {
                System.out.println("Invalid Date!");
                date = null;
            }
        }

        from = -1;
        while(from <0 || from >23) {
            from = Utils.readIntegerFromConsole("Type from Slot");
        }

        to = -1;
        while(to <= from || to >24) {
            to = Utils.readIntegerFromConsole("Type to Slot");
        }

        return new ScheduleSlot(date, from, to);
    }


}
