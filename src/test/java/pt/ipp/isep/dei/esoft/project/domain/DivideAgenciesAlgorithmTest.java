package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.DivideAgenciesAlgorithm;
import pt.ipp.isep.dei.esoft.project.domain.SublistsAndDifference;

public class DivideAgenciesAlgorithmTest {


    @Test
    public void ensureDivideSetOfAgenciesWorks() {
        int[] numPropertiesInAgenciesList = {2, 4, 6};
        SublistsAndDifference result = DivideAgenciesAlgorithm.divideSetOfAgencies(numPropertiesInAgenciesList);

        Assertions.assertAll(
                () -> Assertions.assertEquals(0, result.getDifference()),
                () -> Assertions.assertArrayEquals(new int[]{2, 4, 0}, result.getOneList()),
                () -> Assertions.assertArrayEquals(new int[]{0, 0, 6}, result.getZeroList())
        );
    }

    @Test
    public void testDivideSetOfAgenciesDoesNotWork() {
        int[] numPropertiesInAgenciesList = null;

        Assertions.assertThrows(NullPointerException.class, () -> {
            DivideAgenciesAlgorithm.divideSetOfAgencies(numPropertiesInAgenciesList);
        });
    }





}
