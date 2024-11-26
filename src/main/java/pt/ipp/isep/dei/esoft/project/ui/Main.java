package pt.ipp.isep.dei.esoft.project.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.serialization.LoadSerializationFiles;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.MainGUI;

import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        boolean runWithSerialization = getSerializationOptionFromUser();

        boolean isJavaFX = getUserChoiceForApplication();

        if (runWithSerialization) {
            try {
                LoadSerializationFiles loadSerializationFiles = new LoadSerializationFiles();
                loadSerializationFiles.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (isJavaFX) {
            try {
                launchJavaFX();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                runMainConsole();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean getSerializationOptionFromUser() {
        Scanner scanner = new Scanner(System.in);
        boolean validChoice = false;
        boolean runWithSerialization = false;

        while (!validChoice) {
            System.out.println("Choose an option:");
            System.out.println("1. Run with serialization");
            System.out.println("2. Don't run with serialization");
            int choice = scanner.nextInt();

            if (choice == 1) {
                runWithSerialization = true;
                validChoice = true;
            } else if (choice == 2) {
                validChoice = true;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        return runWithSerialization;
    }

    private boolean getUserChoiceForApplication() {
        Scanner scanner = new Scanner(System.in);
        boolean validChoice = false;
        boolean isJavaFX = false;

        while (!validChoice) {
            System.out.println("Choose an option:");
            System.out.println("1. Run JavaFX");
            System.out.println("2. Run Main Console");
            int choice = scanner.nextInt();

            if (choice == 1) {
                validChoice = true;
                isJavaFX = true;
            } else if (choice == 2) {
                validChoice = true;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        return isJavaFX;
    }

    private static void launchJavaFX() throws IOException {
        MainGUI mainGUI = new MainGUI();
        mainGUI.start(new Stage());
    }

    private static void runMainConsole() {
        MainMenuUI menu = new MainMenuUI();
        menu.run();
    }
}