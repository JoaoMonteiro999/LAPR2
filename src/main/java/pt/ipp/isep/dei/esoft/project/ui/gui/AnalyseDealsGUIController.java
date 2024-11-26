package pt.ipp.isep.dei.esoft.project.ui.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AnalyseDealsGUIController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> linearModelCombo;

    @FXML
    private TextField significanceField;

    @FXML
    private Button confirmButton;

    private final MainGUI mainGUI = new MainGUI();


    ObservableList<String> linearModel = FXCollections.observableArrayList("Simple","Multi");
    ObservableList<String> parameters = FXCollections.observableArrayList("Area","Distance from the center","Number of bedrooms","Number of bathrooms","Number of parking spaces");

    @FXML
    void chosenModel(ActionEvent event) {
        validate();


    }

    @FXML
    void confirmSignificanceInput(ActionEvent event) {
        validate();
    }



    void validate(){

        String input = significanceField.getText().trim();
        System.out.println(input);


        try {
            double value = Double.parseDouble(input);

            if (value >= 0 && value <= 100 && (linearModelCombo.getValue().equals("Multi"))) {
                confirmButton.setDisable(false);
            } else if(value >= 0 && value <= 100 && (linearModelCombo.getValue().equals("Simple")) ){
                confirmButton.setDisable(false);
            }else {
                confirmButton.setDisable(true);
            }
        } catch (NumberFormatException e) {
            confirmButton.setDisable(true);
        }
    }

    @FXML
    void showList(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listAnalyseDeals.fxml"));
        Parent root = loader.load();
        ListAnalyseDealsController listAnalyseDealsController = loader.getController();
        listAnalyseDealsController.setValues(linearModelCombo.getValue(), Double.valueOf(significanceField.getText()));
        //listAnalyseDealsController.getBookingListSorted();

        Scene currentScene = confirmButton.getScene();

        currentScene.setRoot(root);



    }





    @FXML
    void goBack(ActionEvent event) throws IOException {
        mainGUI.changeScene("mainScene1.fxml");
    }

    @FXML
    void showAboutMessage(ActionEvent event) {
        Alert helpAlert = new Alert(Alert.AlertType.INFORMATION);
        helpAlert.setTitle("About");
        helpAlert.setHeaderText(null);
        helpAlert.setContentText("In this section you must chose a linear model between simple and multi, and a significance level.\n Press enter after inserting a valid input, the confirm button won't be available if it isn't valid.");



        helpAlert.showAndWait();

    }

    @FXML
    void initialize() {
        assert linearModelCombo != null : "fx:id=\"linearModelCombo\" was not injected: check your FXML file 'analyseDeals.fxml'.";
        assert significanceField != null : "fx:id=\"significanceField\" was not injected: check your FXML file 'analyseDeals.fxml'.";
        assert confirmButton != null : "fx:id=\"confirmButton\" was not injected: check your FXML file 'analyseDeals.fxml'.";
        linearModelCombo.setItems(linearModel);
        linearModelCombo.setValue("");
        confirmButton.setDisable(true);

    }
}
