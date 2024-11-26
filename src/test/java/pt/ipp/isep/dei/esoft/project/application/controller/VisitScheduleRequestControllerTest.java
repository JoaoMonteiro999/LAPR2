package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VisitScheduleRequestControllerTest {

    AuthenticationRepository authenticationRepository;
    PropertiesRepository propertiesRepository ;
    SaleAnnouncementRepository saleAnnouncementRepository;
    VisitScheduleRequestRepository visitScheduleRequestRepository;
    ClientRepository clientRepository;

    Property property1;
    Property property2;
    Client client;

    @BeforeEach
    void setup(){
        authenticationRepository = new AuthenticationRepository();
        propertiesRepository = new PropertiesRepository();
        saleAnnouncementRepository = new SaleAnnouncementRepository();
        visitScheduleRequestRepository = new VisitScheduleRequestRepository();
        clientRepository = new ClientRepository();

        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Client", "client@this.app", "client", AuthenticationController.ROLE_CLIENT);
        authenticationRepository.doLogin("client@this.app", "BBB1234");

        client = new Client("Client","111222222","222333444","(555) 555-5555", "client@this.app", new Address("Rua", "Porto", "Porto", "PT", "10000"),"AAA1234");
        clientRepository.addClient(client);

        property1 = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        property2 = new Property(300, 300, 3000, new Address("Street", "London", "London", "ING", "30000"),new PropertyType(1), new BusinessType("Buy"), new Dwelling());

        propertiesRepository.add(property1);
        propertiesRepository.add(property2);

        SaleAnnouncement saleAnnouncement1 = new SaleAnnouncement(property1, new Commission(true,100), "2023-05-25");
        SaleAnnouncement saleAnnouncement2 = new SaleAnnouncement(property2, new Commission(true,100), "2023-05-28");
        saleAnnouncementRepository.add(saleAnnouncement1);
        saleAnnouncementRepository.add(saleAnnouncement2);
    }

    @Test
    void getListOfPropertiesSortedTestListSorted() {


        VisitScheduleRequestController controller = new VisitScheduleRequestController(propertiesRepository, saleAnnouncementRepository, visitScheduleRequestRepository,
                                                            authenticationRepository,clientRepository);

        List<Property> list = controller.getListOfPropertiesSorted();
        assertEquals(2, list.size());

        assertEquals(property2, list.get(0));

    }

    @Test
    void submitVisitScheduleRequest() {

        VisitScheduleRequestController controller = new VisitScheduleRequestController(propertiesRepository, saleAnnouncementRepository, visitScheduleRequestRepository,
                authenticationRepository,clientRepository);

        ScheduleSlot slot1 = new ScheduleSlot(LocalDate.now(), 10,11);
        ScheduleSlot slot2 = new ScheduleSlot(LocalDate.now(), 15,19);
        List<ScheduleSlot> slots = new ArrayList<>();
        slots.add(slot1);
        slots.add(slot2);

        controller.submitVisitScheduleRequest(property1, slots);

        assertEquals(1, visitScheduleRequestRepository.getVisitScheduleRequestsList().size());
    }
}