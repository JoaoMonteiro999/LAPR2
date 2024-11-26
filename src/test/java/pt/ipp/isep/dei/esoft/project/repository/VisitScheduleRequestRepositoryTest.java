package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VisitScheduleRequestRepositoryTest {

    @Test
    void getVisitScheduleRequestsListTestEmpty() {
        VisitScheduleRequestRepository repository = new VisitScheduleRequestRepository();
        assertTrue(repository.getVisitScheduleRequestsList().isEmpty());
    }


    @Test
    void getVisitScheduleRequestsListTestNotEmpty() {
        VisitScheduleRequestRepository repository = new VisitScheduleRequestRepository();

        Property p = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        Client c = new Client("Client","111222222","222333444","(555) 555-5555", "client@this.app", new Address("Rua", "Porto", "Porto", "PT", "10000"),"CCC1234");

        ScheduleSlot slot1 = new ScheduleSlot(LocalDate.now(), 10,11);
        ScheduleSlot slot2 = new ScheduleSlot(LocalDate.now(), 15,19);
        List<ScheduleSlot> slots = new ArrayList<>();
        slots.add(slot1);
        slots.add(slot2);

        VisitScheduleRequest v = new VisitScheduleRequest(p, c, slots);
        repository.addVisitScheduleRequest(v);


        Property p2 = new Property(1000, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        Client c2 = new Client("Client","111222222","222333444","(555) 555-5555", "client@this.app", new Address("Rua", "Porto", "Porto", "PT", "10000"),"BBB1234");

        slot1 = new ScheduleSlot(LocalDate.now(), 10,11);
        List<ScheduleSlot> slots2 = new ArrayList<>();
        slots.add(slot2);

        v = new VisitScheduleRequest(p, c, slots);
        repository.addVisitScheduleRequest(v);

        assertEquals(2, repository.getVisitScheduleRequestsList().size());
    }


    @Test
    void addVisitScheduleRequestTest() {
        VisitScheduleRequestRepository repository = new VisitScheduleRequestRepository();

        Property p = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"),new PropertyType(0), new BusinessType("Rent"), new Dwelling());
        Client c = new Client("Client","111222222","222333444","(555) 555-5555", "client@this.app", new Address("Rua", "Porto", "Porto", "PT", "10000"),"AAA1234");

        ScheduleSlot slot1 = new ScheduleSlot(LocalDate.now(), 10,11);
        ScheduleSlot slot2 = new ScheduleSlot(LocalDate.now(), 15,19);
        List<ScheduleSlot> slots = new ArrayList<>();
        slots.add(slot1);
        slots.add(slot2);

        VisitScheduleRequest v = new VisitScheduleRequest(p, c, slots);

        repository.addVisitScheduleRequest(v);

        assertEquals(1, repository.getVisitScheduleRequestsList().size());

    }
}