package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.PropertiesRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * The Create Task Controller Integration Tests.
 * <p>
 * The class CreateTaskController does not perform anything by itself and relies on other classes to work. Therefore,
 * all these tests are integration tests and not unit tests. That is why this class is named IT from Integration Tests.
 */
class ListPropertiesControllerIT {

    @Test
    void ensureGetPropertiesListWork() {
        //Arrange
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        PropertiesRepository propertiesRepository = new PropertiesRepository();

        ArrayList<Property> expected = new ArrayList<>();
        Property p1 = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        Property p2 = new Property(200, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        Property p3 = new Property(300, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        expected.add(p1);
        expected.add(p2);
        expected.add(p3);
        propertiesRepository.add(p1);
        propertiesRepository.add(p2);
        propertiesRepository.add(p3);

        ListPropertiesController controller = new ListPropertiesController(propertiesRepository);

        //Act
        List<Property> properties = controller.getPropertiesList();


        assertArrayEquals(expected.toArray(), properties.toArray());
    }


    @Test
    void ensureCreateTaskWorksWithSingleton() {
        //Arrange
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        PropertiesRepository propertiesRepository = new PropertiesRepository();

        ArrayList<Property> expected = new ArrayList<>();
        Property p1 = new Property(100, 100, 1000, new Address("Rua", "Porto", "Porto", "PT", "10000"), new PropertyType(10), new BusinessType("Rent"), new Dwelling());
        expected.add(p1);
        propertiesRepository.add(p1);

        ListPropertiesController controller = new ListPropertiesController(propertiesRepository);

        //Act
        List<Property> properties = controller.getPropertiesList();


        assertArrayEquals(expected.toArray(), properties.toArray());        //Assert
    }

}