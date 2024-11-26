package pt.ipp.isep.dei.esoft.project.ui.console;

import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import pt.ipp.isep.dei.esoft.project.application.controller.AnalyseDealsController;
import pt.ipp.isep.dei.esoft.project.domain.RegressionModel;
import pt.ipp.isep.dei.esoft.project.domain.dtos.RegressionModelDTO;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AnalyseDealsUI implements Runnable{

    private final AnalyseDealsController controller = new AnalyseDealsController();

    private String chosenRegressionModel;

    private String chosenRegressionParameter = "";

    private double significanceValue;

    private AnalyseDealsController getController(){
        return controller;
    }

    @Override
    public void run(){
        System.out.println("Analyse Deals:");

        requestData();

        if (!chosenRegressionModel.isEmpty()) {
            getAnalysisStatistics();
        }
    }

    private void getAnalysisStatistics(){

        try {

            if (chosenRegressionModel.equalsIgnoreCase("Simple Linear Model (SLM)")) {

                List<String> regressionParameters = List.of("Property Area (m2)", "Distance from Centre" , "Number of Bedrooms", "Number of Bathrooms", "Number of Parking Spaces");

                for (int i = 0; i < 5; i++){
                    RegressionModelDTO regressionModelDTO = controller.getAnalysisStatistics(chosenRegressionModel, regressionParameters.get(i), significanceValue);
                    System.out.println("|||=========================== " + chosenRegressionModel + " =====================================");
                    System.out.println("\t\t\t||==================== " + regressionParameters.get(i) + " ===================||\t\t");
                    System.out.println(regressionModelDTO.getAnalysisReport());

                }


            } else {
                RegressionModelDTO regressionModelDTO = controller.getAnalysisStatistics(chosenRegressionModel, chosenRegressionParameter, significanceValue);
                System.out.println(regressionModelDTO.getAnalysisReport());
            }

        } catch (NoDataException e){
            System.out.println("No data!");

        } catch (NotStrictlyPositiveException e){
            System.out.println("No valid data!");
        }

    }

    private void requestData() {
        chosenRegressionModel = displayAndSelectRegressionModel();

        if (!chosenRegressionModel.isEmpty()){

            significanceValue = requestSignificanceValue();
        }
    }

    private double requestSignificanceValue(){
        while (true) {
            try {
                double significanceValue = Utils.readDoubleFromConsole("Enter the significance level (0.00 - 100.00): ");

                if (significanceValue >= 0 && significanceValue <= 100) {
                    return (significanceValue / 100);
                } else {
                    throw new IllegalArgumentException("\nThe significance level is invalid! It must be between 0.00 - 100.00.\n");
                }

            } catch (IllegalArgumentException e1){
                System.out.println(e1.getMessage());

            } catch (InputMismatchException e){
                System.out.println("\nThe significance level is invalid! It must be between 0.00 - 100.00.\n");
            }
        }
    }

    private String displayAndSelectRegressionModel(){
        List<String> availableRegressionModels = List.of("Simple Linear Model (SLM)", "Multi Linear Model (MLM)");

        int answer = -1;

        while (true){

            try {
                answer = Utils.showAndSelectIndex(availableRegressionModels, "Select the regression model to use:");

                if (answer == -1){
                    break;
                }

                if (answer < -1 || answer > availableRegressionModels.size()){
                    System.out.println("Invalid option. Please try again.\n");
                    continue;
                }

                return availableRegressionModels.get(answer);

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.\n");
            }
        }
        return "";
    }

}
