package pt.ipp.isep.dei.esoft.project.ui.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import pt.ipp.isep.dei.esoft.project.application.controller.AnalyseDealsController;
import pt.ipp.isep.dei.esoft.project.domain.dtos.RegressionModelDTO;

public class ListAnalyseDealsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea text;

    private final MainGUI mainGUI = new MainGUI();
    private final AnalyseDealsController controller = new AnalyseDealsController();
    private String chosenRegressionParameter = "";



    private String linearModel;
    private Double significance;
    private String parameter;
    private String list = "";


    @FXML
    void goBack(ActionEvent event) throws IOException {
        mainGUI.changeScene("analyseDeals.fxml");

    }
    @FXML
    void showHelpMessage(ActionEvent event){
        Alert helpAlert = new Alert(Alert.AlertType.INFORMATION);
        helpAlert.setTitle("Help");
        helpAlert.setHeaderText(null);
        helpAlert.setContentText("In this section, you can analyse the deals according to the information you gave before.\nCheck the troubleshooting section of the user manual if issues appear.");



        helpAlert.showAndWait();

    }



    @FXML
    void initialize() {
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'listAnalyseDeals.fxml'.";



    }

    public void setValues(String linearModel,Double significance) {
        this.linearModel=linearModel;
        this.significance=(significance/100);

        getString();

        this.text.setText(list);

    }

    private void getString() {
        try {

            if (linearModel.equals("Simple")){
                linearModel = "Simple Linear Model (SLM)";
            }



            if (linearModel.equalsIgnoreCase("Simple Linear Model (SLM)")) {

                List<String> regressionParameters = List.of("Property Area (m2)", "Distance from Centre" , "Number of Bedrooms", "Number of Bathrooms", "Number of Parking Spaces");

                for (int i = 0; i < 5; i++){

                    RegressionModelDTO regressionModelDTO = controller.getAnalysisStatistics(linearModel, regressionParameters.get(i), significance);
                    list = list +("\n|||=========================== " + linearModel + " =====================================");
                    list = list +("\n\t\t\t||==================== " + regressionParameters.get(i) + " ===================||\t\t");
                    list = list +(regressionModelDTO.getAnalysisReport());

                }


            } else {
                RegressionModelDTO regressionModelDTO = controller.getAnalysisStatistics(linearModel, chosenRegressionParameter, significance);
                list = list +(regressionModelDTO.getAnalysisReport());
            }

        } catch (NoDataException e){
            list = list +("No data!");

        } catch (NotStrictlyPositiveException e){
            list = list +("No valid data!");
        }

    }
}
