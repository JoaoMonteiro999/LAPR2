package pt.ipp.isep.dei.esoft.project.domain;

public interface RegressionModel {

    /**
     * Method to generate the anova table
     * @return string with all the anova info
     */
    String generateAnovaTable();

    /**
     * This method gets the forecast price list
     * @return string with the forecast price info
     */
    String getForecastPriceList();
}
