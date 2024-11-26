package pt.ipp.isep.dei.esoft.project.domain;


import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.text.DecimalFormat;

public class SimpleLinearModel implements RegressionModel{

    private final DecimalFormat df = new DecimalFormat("#.###");

    private final int SELECTED_REGRESSION_PARAMETER = 0;
    private final int PROPERTY_PRICE = 1;


    private double[][] regressionParameters;
    private int n;
    private double Sxy;
    private double Sxx;
    private double Syy;
    private double SE;
    private double SR;
    private double meanX;
    private double meanY;
    private double slope;
    private double intercept;
    private double squaredR;

    private double significanceValue;

    private SimpleRegression regression;

    /**
     * Empty constructor
     */
    public SimpleLinearModel() {

    }

    /**
     * Default constructor
     * @param regressionParameters
     * @param n
     * @param Sxy
     * @param Sxx
     * @param Syy
     * @param SE
     * @param SR
     * @param meanX
     * @param meanY
     * @param slope
     * @param intercept
     * @param squaredR
     * @param significanceValue
     * @param regression
     */
    public SimpleLinearModel(double[][] regressionParameters, int n, double Sxy, double Sxx, double Syy, double SE, double SR, double meanX, double meanY, double slope, double intercept, double squaredR, double significanceValue, SimpleRegression regression) {
        this.regressionParameters = regressionParameters;
        this.n = n - 1;
        this.Sxy = Sxy;
        this.Sxx = Sxx;
        this.Syy = Syy;
        this.SE = SE;
        this.SR = SR;
        this.meanX = meanX;
        this.meanY = meanY;
        this.slope = slope;
        this.intercept = intercept;
        this.squaredR = squaredR;
        this.significanceValue = significanceValue;
        this.regression = regression;
    }

    /**
     * This method generate the anova table
     * @return string with all the anova information
     */
    @Override
    public String generateAnovaTable() {
        double SR = this.SR;
        double SE = this.SE;

        double MSR = SR;
        double MSE = SE / (this.n - 2);
        double f = MSR/MSE;

        FDistribution fd = new FDistribution(1, this.n - 2);
        Double fSnedecor = fd.inverseCumulativeProbability(significanceValue);

        String message = ("\n\n|||===== Significance Model With ANOVA =====|||"
                + "\n MQR:" + df.format(MSR)
                + "\n MQE:" + df.format(MSE)
                + "\n F0 :" + df.format(f)
                + "\n F de Snedecor : " + df.format(fSnedecor)
                + "\n =============="
                + "\n H0 : b = b0"
                + "\n H1 : b != b0"
                + "\n ==============");

        if(f>fSnedecor){
            message = message + "\nF0 > F de Snedecor\nH0 is rejected -> regression model is significant";
        }else{
            message = message + "\nF0 < F de Snedecor\nH0 is accepted -> regression model is not significant";
        }

        return message;
    }

    /**
     * This method calculates the confidence intervals
     * @return string with confidence intervals info
     */
    public String calculateCoefficientConfidenceIntervals(boolean isCoefficientSlope) {
        TDistribution tDistribution = new TDistribution(this.n - 2);
        double criticalValue = tDistribution.inverseCumulativeProbability(1 - significanceValue / 2);

        String coefficient;

        //Initialize variables
        double coefficientStandardError = 0;
        double marginOfError = 0;
        double confidenceIntervalMin = 0;
        double confidenceIntervalMax = 0;


        //Calculate the margin of error, min and the max of the confidence interval depending on the coefficient (slope or intercept)
        if (isCoefficientSlope) {
            coefficientStandardError = this.regression.getSlopeStdErr();
            marginOfError = criticalValue * coefficientStandardError;
            confidenceIntervalMin = this.regression.getSlope() - marginOfError;
            confidenceIntervalMax = this.regression.getSlope() + marginOfError;
            coefficient = "Slope";

        } else {
            coefficientStandardError = this.regression.getInterceptStdErr();
            marginOfError = criticalValue * coefficientStandardError;
            confidenceIntervalMin = this.regression.getIntercept() - marginOfError;
            confidenceIntervalMax = this.regression.getIntercept() + marginOfError;
            coefficient = "Intercept";
        }


        return "\n\n||======= " + coefficient + " Confidence Interval ========||"+
                "\n " + coefficient + " Standard Error: " + df.format(coefficientStandardError) +
                "\n " + coefficient + " Confidence Interval (" + (significanceValue * 100) + ") -> ] " + df.format(confidenceIntervalMin) + "; " + df.format(confidenceIntervalMax) + "[";
    }

    /**
     * This method calculates the hypothesis tests
     * @return string the the hypothesis tests results
     */
    public String calculateCoefficientHypothesisTests(boolean isCoefficientSlope) {
        double t;
        String coefficient;

        //Calculate t depending on the coefficient (slope or intercept)
        if (isCoefficientSlope){
            t = this.slope / this.regression.getSlopeStdErr();
            coefficient = "Slope";
        } else {
            t = this.intercept / this.regression.getInterceptStdErr();
            coefficient = "Intercept";
        }

        double s = Math.sqrt(this.SE / this.n - 2);

        TDistribution tDist = new TDistribution(this.n - 2);
        double tc = tDist.inverseCumulativeProbability(1 - significanceValue / 2);

        //Hypothesis tests results depending on the coefficient
        String message;
        if (isCoefficientSlope) {

            message = ("\n\n||======= " + coefficient + " Hypothesis Test ========||"
                    + "\ns : " + df.format(s)
                    + "\n==============="
                    + "\nH0 : b = b0"
                    + "\nH1 : b != b0"
                    + "\n===============")
                    + "\nt = " + df.format(t)
                    + "\ntc = " + df.format(tc);

        } else {

            message = ("\n\n||======= " + coefficient + " Hypothesis Test ========||"
                    + "\ns : " + df.format(s)
                    + "\n==============="
                    + "\nH0 : a = b0"
                    + "\nH1 : a != b0"
                    + "\n===============")
                    + "\nt = " + df.format(t)
                    + "\ntc = " + df.format(tc);
        }


        if (t <= tc) {
            message = message + ("\n t <= tc \n -> H0 accepted");
        } else {
            message = message + ("\n t > tc \n -> H0 rejected");
        }


        return message;
    }

    /**
     * This method gets the forecast price list
     * @return string the forecast price info
     */
    @Override
    public String getForecastPriceList() {
        String message ="\n\nForecast | Sale";

        for (int i = 0; i < regressionParameters[0].length; i++) {
            String forecast = String.valueOf(df.format(regression.predict(regressionParameters[SELECTED_REGRESSION_PARAMETER][i])));
            message += "\n" + forecast + " | " + df.format(regressionParameters[PROPERTY_PRICE][i]);
        }
        return message;
    }


    @Override
    public String toString() {
        return "\nn = " + this.n +
                "\nSxx= " + df.format(Sxx) +
                "\nSyy= " + df.format(Syy) +
                "\nSxy= " + df.format(Sxy) +
                "\nSE= " + df.format(SE) +
                "\nSR= " + df.format(SR) +
                "\nST= " +df.format(SR+SE) +
                "\navgX= " + df.format(meanX) +
                "\navgY= " + df.format(meanY) +
                "\nslope= " + df.format(slope) +
                "\nintercept= " + df.format(intercept) +
                "\nR^2 = " + df.format(squaredR) +
                "\nR =" + df.format(Math.sqrt(squaredR)) +
                "\n\nEquation -> y = " + df.format(this.intercept) + " + (" + df.format(this.slope) + ")X";
    }

    /**
     * This method creates an report with all the analysis from the previous methods
     * @return
     */
    public String createAnalysisReport(){
        return this.toString() + this.calculateCoefficientConfidenceIntervals(true) + this.calculateCoefficientConfidenceIntervals(false) + this.calculateCoefficientHypothesisTests(true) + this.calculateCoefficientHypothesisTests(false) + this.generateAnovaTable() + this.getForecastPriceList();
    }
}
