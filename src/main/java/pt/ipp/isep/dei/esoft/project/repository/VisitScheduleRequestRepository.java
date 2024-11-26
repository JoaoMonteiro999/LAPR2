package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VisitScheduleRequestRepository{

    /**
     * List of visit schedule requests
     */
    private final List<VisitScheduleRequest> visitScheduleRequests = new ArrayList<>();

    /**
     * Get the list of visit schedule requests
     * @return list of visit schedule requests
     */
    public List<VisitScheduleRequest> getVisitScheduleRequestsList(){
        return new ArrayList<>(visitScheduleRequests);
    }

    public void addVisitScheduleRequest(VisitScheduleRequest visitScheduleRequest){
        visitScheduleRequests.add(visitScheduleRequest);
    }

    /**
     * This method return the list of visit schedule requests sorted by date
     * @param beginningDate
     * @param endingDate
     * @param sortMethod
     * @return list of sorted visit schedule requests
     */
    public List<VisitScheduleRequest> sortVisitScheduleRequestsList(LocalDate beginningDate, LocalDate endingDate, String sortMethod) {
        List<VisitScheduleRequest> newList = new ArrayList<>();
        listByDate(newList,beginningDate,endingDate);
        newList = sortByDates(newList,sortMethod);

        return newList;
    }

    /**
     * Method to sort the list visit schedule requests by date
     * @param visitScheduleRequests
     * @param sortMethod
     * @return
     */
    private List<VisitScheduleRequest> sortByDates(List<VisitScheduleRequest> visitScheduleRequests, String sortMethod) {

        List<VisitScheduleRequest> sortedList = new ArrayList<>(visitScheduleRequests);
        try {
            if (sortMethod.equals("bubble"))
            {
                SortAlgorithm sortAlgorithm = new BubbleSortAlgorithm();
                sortAlgorithm.sort(sortedList, SortAlgorithm.SortingOrder.ASC);
            }
            else if (sortMethod.equals("selection"))
            {
                SortAlgorithm sortAlgorithm = new SelectionSortAlgorithm();
                sortAlgorithm.sort(sortedList, SortAlgorithm.SortingOrder.ASC);
            }else {
                throw new RuntimeException("There is no Method specified in '"+sortMethod+"' , you must write 'tree' or 'bubble' on the second line of the file and restart the program");
            }

        }catch (RuntimeException e){
            e.getMessage();

        }

        return sortedList;
    }


    /**
     * This method sorts the list of visit schedule requests by a date range
     * @param list
     * @param beginningDate
     * @param endingDate
     */
    private void listByDate(List<VisitScheduleRequest> list,LocalDate beginningDate,LocalDate endingDate){
        int i =-1;
        for (VisitScheduleRequest request:visitScheduleRequests){
            i++;
            List<ScheduleSlot> slots = request.getScheduleSlotList();
            for (ScheduleSlot slot:slots){
                LocalDate targetDate = slot.getDate();
                if (isBetween(targetDate, beginningDate, endingDate)){
                    list.add(request);
                }


            }

        }


    }

    public static boolean isBetween(LocalDate targetDate, LocalDate startDate, LocalDate endDate) {
        return endDate != null && targetDate.isAfter(startDate) && targetDate.isBefore(endDate);
    }



}
