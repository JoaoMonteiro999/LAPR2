package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.MainGUI;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main2Controller implements Initializable {

    @FXML
    private ChoiceBox<String> rolesChoiceBox;

    @FXML
    private Button userLoginBtt;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (UserRoleDTO role : Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserRoles()) {
            rolesChoiceBox.getItems().addAll(role.getDescription());
        }
    }

    @FXML
    void doLogin() throws IOException {
        MainGUI mainGUI = new MainGUI();

        String role = rolesChoiceBox.getSelectionModel().getSelectedItem();

        if (role.equalsIgnoreCase(AuthenticationController.ROLE_ADMIN)) {
            mainGUI.changeScene("adminScene.fxml");

        } else if (role.equalsIgnoreCase(AuthenticationController.ROLE_STORE_MANAGER)) {
            mainGUI.changeScene("storeManagerScene.fxml");

        } else if (role.equalsIgnoreCase(AuthenticationController.ROLE_STORE_NETWORK_MANAGER)) {
            mainGUI.changeScene("storeNetworkManagerScene.fxml");

        } else if (role.equalsIgnoreCase(AuthenticationController.ROLE_AGENT)) {
            mainGUI.changeScene("agentScene.fxml");
        }
    }


}
