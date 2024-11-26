package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import pt.ipp.isep.dei.esoft.project.repository.OrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

/**
 * Deal analysis class
 */
public class DealAnalysis {

    /**
     * This method gets the regression parameters in the order repository
     * @param chosenRegressionParameter
     * @return the regression parameters
     */
    public static double[][] getRegressionParameters(String chosenRegressionParameter){
        int matrixSize = 6;

        //Sets matrix size to 2 if it is a simple linear regression (prices and the selected parameter)
        if(!chosenRegressionParameter.isEmpty()){
            matrixSize = 2;
        }

        OrderRepository orderRepository = Repositories.getInstance().getOrderRepository();

        Integer[][] regressionParametersList = orderRepository.getRegressionParameters(chosenRegressionParameter);
        double[][] regressionParametersMatrix = new double[matrixSize][];

        for (int i = 0; i < regressionParametersList.length; i++) {
            regressionParametersMatrix[i] = new double[regressionParametersList[i].length];

            for (int j = 0; j < regressionParametersList[i].length; j++) {
                double parameterValueTemp = regressionParametersList[i][j].doubleValue();
                regressionParametersMatrix[i][j] = parameterValueTemp;
            }
        }
        return regressionParametersMatrix;
    }

    /**
     * This method get the regression model that was chosen
     * @param regressionParameters
     * @param significanceValue
     * @param isRegressionSLM
     * @return the regression model
     */
    public static RegressionModel getRegressionModel(double[][] regressionParameters, double significanceValue, boolean isRegressionSLM){
        RegressionModel regressionModel;

        if (isRegressionSLM) {
            SimpleRegression regression = addDataToSimpleRegression(regressionParameters);
            regressionModel = createSimpleRegressionModel(regressionParameters, significanceValue, regression);

        } else {
            regressionModel = createMultipleRegressionModel(regressionParameters, significanceValue);
        }

        return regressionModel;
    }

    /**
     * This method adds the necessary data to the simple regression
     * @param regressionParameters
     * @return the simple regression
     */
    private static SimpleRegression addDataToSimpleRegression(double[][] regressionParameters){
        SimpleRegression regression = new SimpleRegression();

        for (int i = 0; i < regressionParameters[0].length; i++) {
            regression.addData(regressionParameters[0][i], regressionParameters[1][i]);
        }
        return regression;
    }

    /**
     * This method create an instance of the simple regression model
     * @param regressionParameters
     * @param significanceValue
     * @param regression
     * @return the simple regression model
     */
    private static RegressionModel createSimpleRegressionModel(double[][] regressionParameters, double significanceValue, SimpleRegression regression){

        Double intercept = regression.getIntercept();
        Double slope = regression.getSlope();
        Double squaredR = regression.getRSquare();

        // Get meanX and meanY
        DescriptiveStatistics xStats = new DescriptiveStatistics();
        for (double x : regressionParameters[0]) {
            xStats.addValue(x);
        }

        DescriptiveStatistics yStats = new DescriptiveStatistics();
        for (double y : regressionParameters[1]) {
            yStats.addValue(y);
        }

        double xAvg = xStats.getMean();
        double yAvg = yStats.getMean();


        // Gets Sxx, Syy and Sxy
        double Sxx = 0;
        for (double x : regressionParameters[0]) {
            Sxx += Math.pow(x-xAvg, 2);
        }

        double Syy = 0;
        for (double y : regressionParameters[1]) {
            Syy += Math.pow(y-yAvg, 2);
        }

        double Sxy = 0;
        for (int i = 0; i < regressionParameters[0].length; i++) {
            Sxy += (regressionParameters[0][i] - xAvg)*(regressionParameters[1][i] -yAvg);
        }

        double SE = regression.getSumSquaredErrors();
        double SR = regression.getRegressionSumSquares();

        return new SimpleLinearModel(regressionParameters, regressionParameters[1].length, Sxy, Sxx, Syy, SE, SR, xStats.getMean(), yStats.getMean(), slope, intercept, squaredR, significanceValue, regression);
    }

    /**
     * This method create an instance of the multiple regression model
     * @param regressionParameters
     * @param significanceValue
     * @return the multiple regression model
     */
    private static RegressionModel createMultipleRegressionModel(double[][] regressionParameters, double significanceValue){

        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();

        double[][] predictors = new double[regressionParameters[0].length][];

        for (int i = 0; i < regressionParameters[0].length; i++) {
            predictors[i] = new double[]{regressionParameters[0][i], regressionParameters[1][i], regressionParameters[2][i],regressionParameters[3][i],regressionParameters[4][i]};
        }

        regression.newSampleData(regressionParameters[5], predictors);

        MultiLinearModel multiLinear = new MultiLinearModel(regression, regressionParameters, significanceValue);

        return multiLinear;
    }
}
