package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ScheduleSlot implements Serializable {

    private static final long serialVersionUID = 7320975544827847703L;

    private LocalDate date;
    private int from;
    private int to;

    public ScheduleSlot(LocalDate date, int from, int to) {
        this.date=date;
        this.from=from;
        this.to=to;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleSlot that = (ScheduleSlot) o;
        return from == that.from && to == that.to && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, from, to);
    }

    @Override
    public String toString() {
        return "Date: "+ date +" from: "+ from +" to: "+ to ;
    }

    public boolean overlap(ScheduleSlot o){
        if (!this.date.equals(o.getDate()))
            return false;

        if (this.from >= o.from && this.from < o.to){
            return true;
        }

        if (o.from >= this.from && o.from < this.to){
            return true;
        }

        return false;
    }



}