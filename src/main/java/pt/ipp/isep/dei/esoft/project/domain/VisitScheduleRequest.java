package pt.ipp.isep.dei.esoft.project.domain;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class VisitScheduleRequest implements Serializable, Comparable<VisitScheduleRequest> {

    private static final long serialVersionUID = 281218097861995452L;

    private Property property;

    private Client client;

    List<ScheduleSlot> scheduleSlotList;


    public VisitScheduleRequest(Property property, Client client, List<ScheduleSlot> list) {
        this.property = property;
        this.client = client;
        this.scheduleSlotList= list;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ScheduleSlot> getScheduleSlotList() {
        return scheduleSlotList;
    }

    public void setScheduleSlotList(List<ScheduleSlot> scheduleSlotList) {
        this.scheduleSlotList = scheduleSlotList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitScheduleRequest that = (VisitScheduleRequest) o;
        return Objects.equals(property, that.property) && Objects.equals(client, that.client) && Objects.equals(scheduleSlotList, that.scheduleSlotList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(property, client, scheduleSlotList);
    }

    @Override
    public String toString() {
        return   property +""+ client +"\n"+ scheduleSlotList ;
    }

    public LocalDate getEarlierDate() {
        int i = 0;
        LocalDate earlierDate = null;
        if (scheduleSlotList.size() == 1){
            return scheduleSlotList.get(0).getDate();
        } else if (scheduleSlotList.size() >1) {
            for (ScheduleSlot slot:scheduleSlotList){
                if (i == 0){
                    earlierDate = slot.getDate();
                }else {
                    if (slot.getDate().isAfter(earlierDate)){
                        earlierDate = slot.getDate();
                    }
                }
                i++;
            }
            return earlierDate;
        }


        return earlierDate;
    }

    @Override
    public int compareTo(VisitScheduleRequest visitScheduleRequest) {
        return this.getEarlierDate().compareTo(visitScheduleRequest.getEarlierDate());
    }
}
