package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SaleAnnouncementRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class PublishSaleAnnouncementControllerTest {
    private PublishSaleAnnouncementController controller;








        @BeforeEach
        public void setup() {
            controller = new PublishSaleAnnouncementController();
            // You can initialize any required dependencies or mock objects here
        }













    @Test
    public void testCreateSaleAnnouncement2() {
        // Set up test data
        PropertyType propertyType = new PropertyType(1);
        BusinessType businessType = new BusinessType("Rent");
        Address address = new Address("123 Main St", "City", "district", "PP", "12345");
        Property property = new Property(100, 10, 200000, address, propertyType, businessType);
        Commission commission = new Commission(true, 10);

        // Call the method under test
        controller.createSaleAnnouncement(property, commission);

        // Assert that the sale announcement was created and added to the repository
        SaleAnnouncementRepository saleAnnouncementRepository = Repositories.getInstance().getSaleAnnouncementRepository();
        List<SaleAnnouncement> saleAnnouncements = saleAnnouncementRepository.getAnnouncementsListSorted();
        assertEquals(1, saleAnnouncements.size());
        assertEquals(property, saleAnnouncements.get(0).getProperty());
        assertEquals(commission, saleAnnouncements.get(0).getCommission());
    }







    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void sendSms() {
    }

    @Test
    void todayDate() {
    }

    @Test
    void requestData() {
    }

    @Test
    void createSaleAnnouncement() {
    }



    @Test
    void sendSmss() {
    }
}