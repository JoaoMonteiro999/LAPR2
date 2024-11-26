package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsController;
import pt.ipp.isep.dei.esoft.project.domain.Order;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StoreNetworkManagerUS17Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboAlgorithm"
    private ComboBox<String> comboAlgorithm; // Value injected by FXMLLoader

    @FXML // fx:id="comboSortOrder"
    private ComboBox<String> comboSortOrder; // Value injected by FXMLLoader

    @FXML // fx:id="btnRefresh"
    private Button btnRefresh; // Value injected by FXMLLoader
    @FXML // fx:id="labelMessage"
    private Label labelMessage; // Value injected by FXMLLoader

    @FXML // fx:id="listDeals"
    private ListView<String> listDeals; // Value injected by FXMLLoader



    private final MainGUI mainGUI = new MainGUI();

    private ListDealsController listDealsController;


    @FXML
    void showList(javafx.event.ActionEvent event) throws IOException {
        List<Order> deals;

        if (comboAlgorithm.getSelectionModel().isEmpty()){
            labelMessage.setText("Please select a sorting Algorithm First!");
            return;
        }

        if (comboSortOrder.getSelectionModel().isEmpty()){
            labelMessage.setText("Please select a sort Order First!");
            return;
        }

        String algorithm = comboAlgorithm.getSelectionModel().getSelectedItem();
        String order = comboSortOrder.getSelectionModel().getSelectedItem();
        boolean  ascending = order.equals("Ascending");

        switch(algorithm){
            case "Bubble Sort":
                deals = listDealsController.getDealsMadeSortedByPropertyAreaBubbleSort(ascending);
                break;
            case "Selection Sort":
                deals = listDealsController.getDealsMadeSortedByPropertyAreaSelectionSort(ascending);
                break;

            default:
                labelMessage.setText("The algorithm is not supported");
                return;
        }

        labelMessage.setText("Deals are sorted " + order + " using " + algorithm);
        showList(deals);



    }

    private void showInitialList(){
        List<Order> deals = listDealsController.getDealsMadeSortedByDate();
        showList(deals);
    }

    private void showList(List<Order> deals){
        listDeals.getItems().clear();

        for(Order deal: deals){
            listDeals.getItems().add(deal.toString());
        }
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
        helpAlert.setContentText("In this section the Deals Made are shown ordered by Date. Then you can choose an Algorithm and SOrt order to sort them by Property Area");
        helpAlert.showAndWait();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        listDealsController = new ListDealsController();

        comboAlgorithm.getItems().addAll("Bubble Sort","Selection Sort");
        comboSortOrder.getItems().addAll("Ascending","Descending");

        showInitialList();
    }
}
