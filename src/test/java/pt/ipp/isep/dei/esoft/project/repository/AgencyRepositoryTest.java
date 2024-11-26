package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Agency;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgencyRepositoryTest {

    @Test void getAgencyBySelectionEmptyList() {
        AgencyRepository agencyRepository = new AgencyRepository();
        Integer chosenAgencyId = 123;
        assertThrows(IllegalArgumentException.class,
                () -> agencyRepository.getAgencyBySelection(chosenAgencyId));
    }

    @Test void ensureNewAgencySuccessfullyAdded(){
        AgencyRepository agencyRepository = new AgencyRepository();
        Integer chosenAgencyId = 123;
        Agency agency = new Agency(chosenAgencyId,"Agency1","a@b.com", "(999) 999-9999");
        agencyRepository.add(agency);
    }

    @Test void ensureGetAgencyForExistingAgency() {
        AgencyRepository agencyRepository = new AgencyRepository();
        Integer chosenAgencyId = 123;
        Agency agency1 = new Agency(chosenAgencyId,"Agency1","a@b.com", "(999) 999-9999");
        agencyRepository.add(agency1);
        Agency agency2 = agencyRepository.getAgencyBySelection(chosenAgencyId);
        assertEquals(agency1, agency2);
    }

    @Test void ensureGetAgencyFailsForNonExistingAgency() {
        AgencyRepository agencyRepository = new AgencyRepository();
        Integer chosenAgencyId1 = 123;
        Agency agency = new Agency(chosenAgencyId1,"Agency1","a@b.com", "(999) 999-9999");
        agencyRepository.add(agency);
        Integer chosenAgencyId2 = 987;
        assertThrows(IllegalArgumentException.class,
                () -> agencyRepository.getAgencyBySelection(chosenAgencyId2));
    }

    @Test void ensureGetAgenciesReturnsAnImmutableList() {
        AgencyRepository agencyRepository = new AgencyRepository();
        Integer chosenAgencyId1 = 123;
        Agency agency = new Agency(chosenAgencyId1,"Agency1","a@b.com", "(999) 999-9999");
        agencyRepository.add(agency);

        assertThrows(UnsupportedOperationException.class,
                () -> agencyRepository.getAgencies().add(new Agency(987,"Agency1","a@b.com", "(999) 999-9999")));
    }

    @Test
    void ensureGetAgenciesReturnsTheCorrectList() {
        //Arrange
        AgencyRepository agencyRepository = new AgencyRepository();
        Integer chosenAgencyId1 = 123;
        Agency agency = new Agency(chosenAgencyId1,"Agency1","a@b.com", "(999) 999-9999");
        agencyRepository.add(agency);
        int expectedSize = 1;

        //Act
        int size = agencyRepository.getAgencies().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(agency, agencyRepository.getAgencies().get(size - 1));
    }

    @Test
    void ensureAddingDuplicateAgencyFails() {
        //Arrange
        AgencyRepository agencyRepository = new AgencyRepository();
        Agency agency = new Agency(123,"Agency1","a@b.com", "(999) 999-9999");
        //Add the first agency
        agencyRepository.add(agency);

        //Act
        Optional<Agency> duplicateAgency = agencyRepository.add(agency);

        //Assert
        assertTrue(duplicateAgency.isEmpty());
    }

    @Test
    void ensureAddingDifferentAgenciesWorks() {
        //Arrange
        AgencyRepository agencyRepository = new AgencyRepository();
        Agency agency1 = new Agency(123,"Agency1","a@b.com", "(999) 999-9999");
        Agency agency2 = new Agency(987,"Agency1","a@b.com", "(999) 999-9999");
        //Add the first agency
        agencyRepository.add(agency1);

        //Act
        Optional<Agency> result = agencyRepository.add(agency2);

        //Assert
        assertEquals(agency2, result.get());
    }
}