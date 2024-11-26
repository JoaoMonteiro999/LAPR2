package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class StoreNetworkManagerController {

    @FXML
    private Button divideAgenciesBtt;

    @FXML
    private Button listDealsBtt;

    private final MainGUI mainGUI = new MainGUI();

    @FXML
    void divideAgenciesBttAction(ActionEvent event) throws IOException {
        mainGUI.changeScene("storeNetworkManagerScene2.fxml");
    }

    @FXML
    void listDealsBttAction(ActionEvent event) throws IOException {
        mainGUI.changeScene("storeNetworkManagerScene.fxml");
    }

}
