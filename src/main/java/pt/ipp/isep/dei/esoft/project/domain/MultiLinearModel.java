package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

import java.text.DecimalFormat;

public class MultiLinearModel implements RegressionModel{

    private final DecimalFormat df = new DecimalFormat("#.###");

    private final int PROPERTY_AREA = 0;
    private final int DISTANCE_CENTRE = 1;
    private final int NUM_BEDROOMS = 2;
    private final int NUM_BATHROOMS = 3;
    private final int NUM_PARKING_SPC = 4;
    private final int PROPERTY_PRICE = 5;


    private double[][] regressionParameters;
    private int n;
    private double[] B;
    private int k;
    private double SE;
    private double ST;
    private double SR;
    private double squaredR;
    private double squaredRAdjusted;
    private double[] standardErrorsParameters;
    private double significanceValue;

    private OLSMultipleLinearRegression regression;


    /**
     * Empty constructor
     */
    public MultiLinearModel() {
    }

    /**
     * Default constructor
     * @param regression
     * @param regressionParameters
     * @param significanceValue
     */
    public MultiLinearModel(OLSMultipleLinearRegression regression, double[][] regressionParameters, double significanceValue){
        this.regression = regression;
        this.n = regressionParameters[0].length;
        this.regressionParameters = regressionParameters;
        this.B = regression.estimateRegressionParameters();
        this.k = B.length;
        this.SE = regression.calculateResidualSumOfSquares();
        this.ST = regression.calculateTotalSumOfSquares();
        this.SR = this.ST - this.SE;
        this.squaredR = regression.calculateRSquared();
        this.squaredRAdjusted = regression.calculateAdjustedRSquared();
        this.standardErrorsParameters = regression.estimateRegressionParametersStandardErrors();
        this.significanceValue = significanceValue;
    }

    /**
     * Get beta list
     * @return
     */
    public double[] getB() {
        return B;
    }

    /**
     * This method generate the anova table
     * @return string with all the anova information
     */
    @Override
    public String generateAnovaTable(){

        double MQR = SR / this.k;
        double MQE = this.SE / (this.n - (this.k + 1));
        double f = MQR/MQE;

        FDistribution fd = new FDistribution(this.k, this.n - (this.k + 1));
        Double fSnedecor = fd.inverseCumulativeProbability(significanceValue);



        String message = ("\n|||============== ANOVA ===============|||"
                + "\n MQR:" + df.format(MQR)
                + "\n MQE:" + df.format(MQE)
                + "\n F0 :" + df.format(f)
                + "\n F de Snedecor : " + df.format(fSnedecor)
                + "\n========================"
                + "\nH0 : b = b0"
                + "\nH1 : b != b0"
                + "\n=======================");

        if(f>fSnedecor){
            message += "\nF0 > F de Snedecor\nH0 is rejected -> regression model is significant";
        }else{
            message += "\nF0 < F de Snedecor\nH0 is accepted -> regression model is not significant";
        }

        message += "\n ========================================\n\n";

        return message;
    }

    /**
     * This method calculates the confidence intervals
     * @return string with confidence intervals info
     */
    public String calculateConfidenceIntervals(){

        TDistribution tDistribution = new TDistribution(this.n - (this.k + 1));
        double criticalValue = tDistribution.inverseCumulativeProbability(1 - (significanceValue / 2));

        // Calculate the confidence intervals
        double[] lowerBounds = new double[this.B.length];
        double[] upperBounds = new double[this.B.length];

        for (int i = 0; i < this.B.length; i++) {
            lowerBounds[i] = this.B[i] - criticalValue * this.standardErrorsParameters[i];
            upperBounds[i] = this.B[i] + criticalValue * this.standardErrorsParameters[i];
        }

        String message = "\n================ Confidence Intervals (" + significanceValue * 100 + "%) ==================\n\n";

        // Add confidence intervals to message
        for (int i = 0; i < this.B.length; i++) {
            message += "\nParameter " + (i) + " Confidence Interval (" + significanceValue * 100 + "%): ]" + df.format(lowerBounds[i]) + ", " + df.format(upperBounds[i]) + "["
                    + "\nParameter " + i + " =" + df.format(this.B[i]) + "\nStandard Error: " + df.format(this.standardErrorsParameters[i]) + "\n =======================================";
        }

        return message;
    }

    /**
     * This method calculates the hypothesis tests
     * @return string the the hypothesis tests results
     */
    public String calculateHypothesisTests(){
        TDistribution tDistribution = new TDistribution(this.n - (this.k + 1));
        double criticalValue = tDistribution.inverseCumulativeProbability(1 - (significanceValue / 2));
        String message = "\n\n ================ Hypothesis Tests (" + significanceValue * 100 + "%) ===================== \n Test : H0 : B = 0 \n        H1 : B != 0";

        double[] tValues = new double[this.B.length];

        for (int i = 0; i < this.B.length; i++) {

            tValues[i] = this.B[i] / this.standardErrorsParameters[i];
            message += "\n ===========================================\n Parameter " + i + ": "+ df.format(this.B[i]) + "\n observed t = " + df.format(tValues[i]) + "\n tc = " + df.format(criticalValue);

            if(tValues[i] <= criticalValue){
                message = message + "\n observed t <= tc, Accepts H0";
            }else {
                message = message + "\n observed t > tc, Rejects H0";
            }

        }
        return message;
    }

    /**
     * This method gets the forecast price list
     * @return string the forecast price info
     */
    @Override
    public String getForecastPriceList(){
        String message ="\n====================================\n\n\t\tForecast | Sale";

        for (int i = 0; i < regressionParameters[0].length; i++) {
            String forecast = String.valueOf(this.B[0] + regressionParameters[PROPERTY_AREA][i]*this.B[1] + regressionParameters[DISTANCE_CENTRE][i]*this.B[2] + regressionParameters[NUM_BEDROOMS][i]*this.B[3] + regressionParameters[NUM_BATHROOMS][i]*this.B[4] + regressionParameters[NUM_PARKING_SPC][i]*this.B[5]);
            message = message + "\n" + forecast + " | " + regressionParameters[PROPERTY_PRICE][i];
        }
        return message;
    }

    /**
     * This method creates an report with all the analysis from the previous methods
     * @return
     */
    public String createAnalysisReport(){
        return this.toString() + this.generateAnovaTable() + this.calculateConfidenceIntervals()  + this.calculateHypothesisTests() +  this.getForecastPriceList();
    }

    /**
     * To string method
     * @return the string with the instance attribute values
     */
    @Override
    public String toString() {
        return "\nSignificance Value= " + this.significanceValue +
                "\nn=" + df.format(this.n) +
                "\nSQT=" + df.format(ST) +
                "\nSQR=" + df.format(SR) +
                "\nSQE=" + df.format(SE) +
                "\nR Squared =" + df.format(squaredR) +
                "\nR Squared Adjusted =" + df.format(squaredRAdjusted) +
                "\n\nEquation : Y = " + "(" + df.format(this.B[0]) + ") + " + "(" + df.format(this.B[1]) + ")X\u2081 + (" + df.format(this.B[2]) + ")X\u2082 + (" + df.format(this.B[3]) + ")X\u2083 + (" + df.format(this.B[4]) + ")X\u2084 + (" + df.format(this.B[5]) + ")X\u2085" + "\n\n";

    }
}
