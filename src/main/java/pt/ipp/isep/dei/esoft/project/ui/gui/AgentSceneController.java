package pt.ipp.isep.dei.esoft.project.ui.gui; /**
 * Sample Skeleton for 'agentScene.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AgentSceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="beginningDate"
    private DatePicker beginningDate; // Value injected by FXMLLoader

    @FXML // fx:id="endingDate"
    private DatePicker endingDate; // Value injected by FXMLLoader

    @FXML // fx:id="btnHelp"
    private MenuItem btnHelp; // Value injected by FXMLLoader

    @FXML // fx:id="btnConfirm"
    private Button btnConfirm; // Value injected by FXMLLoader
    private final MainGUI mainGUI = new MainGUI();
    private  ListBookingRequestsController listBookingRequestsController;


    @FXML
    void beginningDateEntered(javafx.event.ActionEvent actionEvent) {
        if (beginningDate.getValue() != null) {
            endingDate.setDisable(false); // Enable the button

            if (endingDate.getValue() != null && beginningDate.getValue().compareTo(endingDate.getValue()) > 0){
                endingDate.setValue(null);
            }

        } else {
            endingDate.setDisable(true); // Disable the button
        }


        endingDate.setDayCellFactory(getDayCellFactory(beginningDate.getValue()));



    }


    private Callback<DatePicker, DateCell> getDayCellFactory(LocalDate disableBeforeDate) {
        return new Callback<>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && item.isBefore(disableBeforeDate)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #dddddd;");
                        }
                    }
                };
            }
        };
    }


    @FXML
    void endingDateEntered(javafx.event.ActionEvent actionEvent) {
        if (beginningDate.getValue() != null && endingDate.getValue() != null) {
            btnConfirm.setDisable(false); // Enable the button
        } else {
            btnConfirm.setDisable(true); // Disable the button
        }

    }




    @FXML
    void showList(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listBookingRequests.fxml"));
        Parent root = loader.load();
        ListBookingRequestsController listBookingRequestsController = loader.getController();
        listBookingRequestsController.setDates(beginningDate.getValue(), endingDate.getValue());
        listBookingRequestsController.getBookingListSorted();

        Scene currentScene = btnConfirm.getScene();

        currentScene.setRoot(root);
    }



    @FXML
    public void goBack(javafx.event.ActionEvent actionEvent) throws IOException {
        mainGUI.changeScene("mainScene1.fxml");
    }


    @FXML
    public void showBoxWithHelp(javafx.event.ActionEvent actionEvent) {
        Alert helpAlert = new Alert(Alert.AlertType.INFORMATION);
        helpAlert.setTitle("Help");
        helpAlert.setHeaderText(null);
        helpAlert.setContentText("In this section you can choose a date range to see the booking requests listing.\nThe sorting algorithm used is written in 'sortingAlgorithm.txt' file, you can change it to the other sorting algorithm available if you wish.");
        helpAlert.showAndWait();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert beginningDate != null : "fx:id=\"beginningDate\" was not injected: check your FXML file 'agentScene.fxml'.";
        assert endingDate != null : "fx:id=\"endingDate\" was not injected: check your FXML file 'agentScene.fxml'.";
        assert btnHelp != null : "fx:id=\"btnHelp\" was not injected: check your FXML file 'agentScene.fxml'.";
        assert btnConfirm != null : "fx:id=\"btnConfirm\" was not injected: check your FXML file 'agentScene.fxml'.";
        btnConfirm.setDisable(true);// Disable the button initially
        endingDate.setDisable(true);// Disable the button initially
    }
}
