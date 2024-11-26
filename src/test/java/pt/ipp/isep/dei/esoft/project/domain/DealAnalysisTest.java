package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealAnalysisTest {

    // Test case for getRegressionParameters method when chosenRegressionParameter is empty
    @Test
    public void testGetRegressionParameters_NoChosenParameter() {
        String chosenRegressionParameter = "";
        double[][] result = DealAnalysis.getRegressionParameters(chosenRegressionParameter);

        Assertions.assertAll(
                () -> Assertions.assertEquals(6, result.length),
                () -> Assertions.assertNotNull(result[0]),
                () -> Assertions.assertNotNull(result[1]),
                () -> Assertions.assertNotNull(result[2]),
                () -> Assertions.assertNotNull(result[3]),
                () -> Assertions.assertNotNull(result[4]),
                () -> Assertions.assertNotNull(result[5])
        );
    }

    // Test case for getRegressionParameters method when chosenRegressionParameter is not empty
    @Test
    public void testGetRegressionParameters_WithChosenParameter() {
        String chosenRegressionParameter = "Number of Bedrooms";
        double[][] result = DealAnalysis.getRegressionParameters(chosenRegressionParameter);

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, result.length),
                () -> Assertions.assertNotNull(result[0]),
                () -> Assertions.assertNotNull(result[1])
        );
    }

    // Test case for getRegressionModel method when isRegressionSLM is true
    @Test
    public void testGetRegressionModel_SimpleLinearModel() {
        double[][] regressionParameters = {{1.0, 2.0, 3.0}, {2.0, 4.0, 6.0}};
        double significanceValue = 0.05;
        boolean isRegressionSLM = true;

        RegressionModel result = DealAnalysis.getRegressionModel(regressionParameters, significanceValue, isRegressionSLM);

        Assertions.assertTrue(result instanceof SimpleLinearModel);
    }

}
