package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.domain.SaleAnnouncement;

import java.util.*;

public class SaleAnnouncementRepository {

    /**
     * List of announcements
     */
    private final List<SaleAnnouncement> announcements = new ArrayList<>();

    /**
     * This method adds an announcement to the list of announcements
     * @param saleAnnouncement
     * @return announcement
     */
    public Optional<SaleAnnouncement> add(SaleAnnouncement saleAnnouncement) {
        Optional<SaleAnnouncement> newSaleAnnouncement = Optional.empty();
        boolean operationSuccess = false;

        if (validateAnnouncement(saleAnnouncement)) {
            newSaleAnnouncement = Optional.of(saleAnnouncement.clone());
            operationSuccess = announcements.add(newSaleAnnouncement.get());
        }

        if (!operationSuccess) {
            newSaleAnnouncement = Optional.empty();
        }

        return newSaleAnnouncement;
    }

    /**
     * This method checks if the announcement is already in the list of announcements
     * @param saleAnnouncement
     * @return true/false
     */
    private boolean validateAnnouncement(SaleAnnouncement saleAnnouncement) {
        boolean isValid = !announcements.contains(saleAnnouncement);

        return isValid;
    }

    /**
     * This method returns the list of announcements sorted by date
     * @return list of sorted announcements
     */
    public List<SaleAnnouncement> getAnnouncementsListSorted() {
        List<SaleAnnouncement> sorted = new ArrayList<>(announcements);

        //sorts the sale announcements by date, mos recent first
        //the dates are in the format yyyy-mm-dd
        Collections.sort(sorted, new Comparator<SaleAnnouncement>(){
            @Override
            public int compare(SaleAnnouncement o1, SaleAnnouncement o2) {
                if (o2.getDateAnnouncement().compareTo(o1.getDateAnnouncement()) > 0)
                    return -1;
                else if (o1.getDateAnnouncement().compareTo(o2.getDateAnnouncement()) < 0)
                    return 1;
                else
                    return 0;
            }
        });
        return sorted;
    }

    /**
     * Get list of announcements
     * @return list of announcements
     */
    public List<SaleAnnouncement> getAnnouncements(){
        return announcements;
    }
}
