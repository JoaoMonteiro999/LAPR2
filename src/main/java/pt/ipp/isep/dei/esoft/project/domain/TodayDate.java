package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public interface TodayDate {


    default String todayDate(){
        LocalDate today = LocalDate.now(); // obtains today date
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // defines the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd"); // defines the date format
        String todayDate = today.format(formatter);
        return todayDate;
    }
}
