package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.MainGUI;
import javafx.scene.input.KeyEvent;
import java.io.IOException;

public class MainController {

    @FXML
    private MenuItem closeApplicationItem;

    @FXML
    private TextField emailTxt;
    @FXML
    private PasswordField passwordTxt;
    @FXML // fx:id="rootPane"
    private AnchorPane rootPane; // Value injected by FXMLLoader

    @FXML
    private Button loginButton;

    @FXML
    private Label wrongLogin;


    @FXML
    void userLogin(ActionEvent event) throws IOException {
        doLogin();
    }

    private void doLogin() throws IOException {
        AuthenticationController authenticationController = new AuthenticationController();
        if (authenticationController.doLogin(emailTxt.getText(), passwordTxt.getText())){
            wrongLogin.setText("Success!");
            changeSceneAccordingToUser();

        } else {
            wrongLogin.setText("Wrong email or password!");
        }
    }

    private void changeSceneAccordingToUser() throws IOException {
        MainGUI mainGUI = new MainGUI();

        if (Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserRoles().size() > 1) {
            mainGUI.changeScene("mainScene2.fxml");

        } else {

            if (Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().isLoggedInWithRole(AuthenticationController.ROLE_ADMIN)) {
                mainGUI.changeScene("adminScene.fxml");

            } else if (Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().isLoggedInWithRole(AuthenticationController.ROLE_STORE_MANAGER)) {
                mainGUI.changeScene("analyseDeals.fxml");

            } else if (Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().isLoggedInWithRole(AuthenticationController.ROLE_STORE_NETWORK_MANAGER)) {
                mainGUI.changeScene("storeNetworkManagerChooseScene.fxml");

            } else if (Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().isLoggedInWithRole(AuthenticationController.ROLE_AGENT)) {
                mainGUI.changeScene("agentScene.fxml");
            }
        }
    }
    @FXML
    void initialize() {
        Platform.runLater(() -> emailTxt.requestFocus());
        emailTxt.setOnKeyPressed(this::handleEmailKeyPressed);
        passwordTxt.setOnKeyPressed(this::handlePasswordKeyPressed);
    }


    @FXML
    private void handleEmailKeyPressed(javafx.scene.input.KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            passwordTxt.requestFocus();
        }
    }

    private void handlePasswordKeyPressed(javafx.scene.input.KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                doLogin();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void closeApplication(ActionEvent event) {

    }

}
