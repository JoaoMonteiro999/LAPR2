package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import pt.ipp.isep.dei.esoft.project.application.controller.ImportLegacyDataController;
import pt.ipp.isep.dei.esoft.project.ui.gui.MainGUI;

import java.io.File;
import java.io.IOException;

public class AdminController {

    @FXML
    private MenuItem closeItem;

    @FXML
    private Button importDataButton;

    @FXML
    private MenuItem returnToMainItem;

    @FXML
    void importData(ActionEvent event) {
        File csvFile = chooseCSVFile();

        if (csvFile.exists()) {
            importDataFromLegacySystem(csvFile);
        }

    }

    private File chooseCSVFile(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose a csv file");

        fc.setInitialDirectory(new File(System.getProperty("user.dir")));

        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File file = fc.showOpenDialog(null);

        return file;

    }

    private void importDataFromLegacySystem(File csvFile){
        ImportLegacyDataController controller = new ImportLegacyDataController();
        controller.importCSVFile(csvFile);
    }

    @FXML
    void closeApp(ActionEvent event) {


    }


    @FXML
    void returnToMainMenu(ActionEvent event) throws IOException {
        MainGUI mainGUI = new MainGUI();
        mainGUI.changeScene("mainScene1.fxml");
    }

}
