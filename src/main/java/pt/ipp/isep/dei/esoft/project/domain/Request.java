package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Request implements Comparable<Request>, Serializable {

    private static final long serialVersionUID = 8705138647872707219L;

    private Property property;
    private String date;


    public Request(Property property){
        this.property=property;
        this.date=todayDate();
    }
    public Request(Property property,String date){
        this.property=property;
        this.date=date;
    }


    @Override
    public int compareTo(Request other) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate thisDate = LocalDate.parse(this.date, formatter);
        LocalDate otherDate = LocalDate.parse(other.date, formatter);

        // Compare dates in reverse order (newest to oldest)
        return otherDate.compareTo(thisDate);
    }



    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Request clone(){
        return new Request(getProperty(),getDate());
    }




    private String todayDate(){
        LocalDate today = LocalDate.now(); // obtains today date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // defines the date format
        String todayDate = today.format(formatter);
        return todayDate;
    }

}
