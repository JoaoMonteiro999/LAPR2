package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.domain.DivideAgenciesAlgorithm;
import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.domain.SublistsAndDifference;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreNetworkManagerUS19Controller {

    @FXML
    private TextArea outputTextArea;
    @FXML
    private Button runButton;

    @FXML
    private MenuItem returnToMainMenu;
    @FXML
    private MenuItem quit;
    @FXML
    private Button bruteForce;


    private static final String BUBBLE_SORT = "Bubble Sort";
    private static final String SELECTION_SORT = "Insertion Sort";
    private static final String ASCENDING = "Ascending";
    private static final String DESCENDING = "Descending";

    private int numDigits;
    private int[] subArray;
    private int[] stores;

    private final double nanoNumber = 1_000_000_000.0;

    List<Order> orderList;


    @FXML
    public void calculate(ActionEvent event) {
        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyRepository();
        int numAgencies = agencyRepository.getAgencies().size();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Number of Digits");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the number of digits to test (2-" + numAgencies + "):");

        dialog.showAndWait().ifPresent(input -> {
            try {
                numDigits = Integer.parseInt(input);
                if (numDigits < 2 || numDigits > numAgencies) {
                    throw new IllegalArgumentException();
                }

                int[][] partitionSublist = agencyRepository.getPartitionSublist(agencyRepository.getAgencies(), numDigits);
                stores = agencyRepository.getNumberPropertiesPerAgency(partitionSublist);

                createSubArray(stores);
                long startTime = System.nanoTime();

                SublistsAndDifference result = DivideAgenciesAlgorithm.divideSetOfAgencies(subArray);

                int[] oneList = result.getOneList();
                int[] zeroList = result.getZeroList();
                int diff = result.getDifference();

                StringBuilder outputText = new StringBuilder();
                outputText.append("Input list: ").append(Arrays.toString(stores)).append("\n\n");
                outputText.append("Subset OneList: [");
                printList(oneList, outputText);

                outputText.append("Subset ZeroList: [");
                printList(zeroList, outputText);

                outputText.append("Difference: ").append(diff).append("\n\n");

                outputTextArea.clear();
                outputTextArea.setText(outputText.toString());

                long endTime = System.nanoTime();

                double executionTime = (endTime - startTime) / nanoNumber;

                String timeText = "Time: " + executionTime + " seconds\n";
                outputTextArea.appendText(timeText);

                outputTextArea.setVisible(true);

            } catch (IllegalArgumentException ignored) {
            }
        });
    }

    private void printList(int[] oneList, StringBuilder outputText) {
        for (int i = 0; i < oneList.length; i++) {
            if (oneList[i] != 0) {
                outputText.append("(").append(i + 1).append(", ").append(oneList[i]).append(")");
            } else {
                outputText.append("(").append(oneList[i]).append(",").append(0).append(")");
            }
            if (i < oneList.length - 1) {
                outputText.append(",");
            }
        }
        outputText.append("]\n\n");
    }

    /**
     * @param stores the list of stores
     */
    @FXML
    public void createSubArray(int[] stores) {
        subArray = new int[numDigits];

        for (int i = 0; i <= numDigits - 1; i++) {
            subArray[i] = stores[i];
        }
    }

    @FXML
    public void clearTextArea() {
        outputTextArea.clear();
    }

    @FXML
    public void hideTextArea() {
        outputTextArea.setVisible(false);
    }

    @FXML
    public void quit() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void returnToMainMenu() throws IOException {
        MainGUI mainGUI = new MainGUI();
        mainGUI.changeScene("mainScene1.fxml");
    }


    /**
     * Requests the list of accepted orders from the controller.
     */
    @FXML
    public void requestData() {
        orderList = Repositories.getInstance().getOrderRepository().getOrders();
    }



    @FXML
    public void initialize() {
        assert returnToMainMenu != null : "fx:id=\"returnToMainMenu\" was not injected: check your FXML file 'NetworkManagerMenu.fxml'.";
        assert quit != null : "fx:id=\"quit\" was not injected: check your FXML file 'NetworkManagerMenu.fxml'.";
        assert bruteForce != null : "fx:id=\"bruteForce\" was not injected: check your FXML file 'NetworkManagerMenu.fxml'.";
    }


    @FXML
    public void start() {
        bruteForce.setOnAction(this::calculate);
    }
}