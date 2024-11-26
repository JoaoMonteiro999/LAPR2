package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;
import java.util.Objects;


public class MainGUI extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainGUI.class.getResource("mainScene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load((getClass().getResource(fxml)));
        stage.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();
        launch();
    }
}
