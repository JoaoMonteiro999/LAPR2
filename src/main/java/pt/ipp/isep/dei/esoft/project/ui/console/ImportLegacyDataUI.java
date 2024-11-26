package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ImportLegacyDataController;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class ImportLegacyDataUI implements Runnable{

    private final ImportLegacyDataController controller = new ImportLegacyDataController();
    private File csvFile;

    public void run(){
        String folderPath = System.getProperty("user.dir");
        controller.fillCSVFileList(folderPath);
        csvFile = displayAndSelectCSVFile();

        if (csvFile != null) {
            submitData();
        }
    }

    private void submitData() {
        controller.importCSVFile(csvFile);
    }

    private File displayAndSelectCSVFile() {
        List<File> csvList = controller.getCSVFiles();
        int listSize = csvList.size();

        File csvFile = null;

        int answer = -1;

        while (true) {
            try {
                answer = displayCSVFilesOptions(csvList);

                if (answer == -1){
                    break;

                } else if (answer < -1 || answer > listSize) {
                    System.out.println("Invalid option. Please try again.\n");
                    continue;
                }

                csvFile = csvList.get(answer);
                return csvFile;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.\n");
            }
        }
        return csvFile;
    }

    /**
     * Method to display the csv list as a menu with number options to select
     * @param csvList
     */
    private int displayCSVFilesOptions(List<File> csvList) {

        List<String> csvListString = new ArrayList<>();

        for (File csvFile:csvList) {
            csvListString.add(csvFile.getName());
        }
        return Utils.showAndSelectIndex(csvListString,"Select a file to import:");
    }
}
