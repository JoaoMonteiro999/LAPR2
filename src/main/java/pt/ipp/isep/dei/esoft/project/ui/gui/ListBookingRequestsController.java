/**
 * Sample Skeleton for 'listBookingRequests.fxml' Controller Class
 */

package pt.ipp.isep.dei.esoft.project.ui.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Region;
import javafx.scene.text.TextFlow;
import org.w3c.dom.Text;
import pt.ipp.isep.dei.esoft.project.domain.VisitScheduleRequest;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;


public class ListBookingRequestsController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnBack"
    private MenuItem btnBack; // Value injected by FXMLLoader

    @FXML // fx:id="btnAbout"
    private MenuItem btnAbout; // Value injected by FXMLLoader

    @FXML
    private ListView<VisitScheduleRequest> myListView;


    private Repositories repositories;

    List<VisitScheduleRequest> bookingList;

    private final MainGUI mainGUI = new MainGUI();

    private  LocalDate beginningDate;
    private  LocalDate endingDate;

    public void setDates(LocalDate beginningDate, LocalDate endingDate) {
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
    }





    @FXML
    void goBack(javafx.event.ActionEvent event) throws IOException {
        mainGUI.changeScene("agentScene.fxml");
    }

    @FXML
    void showAboutMsg(javafx.event.ActionEvent event) {
        Alert helpAlert = new Alert(Alert.AlertType.INFORMATION);
        helpAlert.setTitle("About");
        helpAlert.setHeaderText(null);
        helpAlert.setContentText("In this section, you can see the booking requests listing in the date range you have chosen before.\nCheck the troubleshooting section of the user manual if issues appear.");



        helpAlert.showAndWait();
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'listBookingRequests.fxml'.";
        assert myListView != null : "fx:id=\"myListView\" was not injected: check your FXML file 'listBookingRequests.fxml'.";
        assert btnAbout != null : "fx:id=\"btnAbout\" was not injected: check your FXML file 'listBookingRequests.fxml'.";





    }

    public void getBookingListSorted(){

        String sortMethod = getSortMethod();
        bookingList = Repositories.getInstance().getVisitScheduleRequestRepository().sortVisitScheduleRequestsList(beginningDate,endingDate,sortMethod);

        myListView.getItems().addAll(bookingList);



    }

    public String getSortMethod(){

        String filePath = "sortingAlgorithm.txt";
        String method = "";

        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.nextLine(); //ignores first phrase
            if (scanner.hasNext()) {
                method = scanner.next();
                System.out.println("Word found: " + method);
            } else {
                System.out.println("The file is empty.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file could not be found: " + e.getMessage());
        }
        return method;
    }



}
