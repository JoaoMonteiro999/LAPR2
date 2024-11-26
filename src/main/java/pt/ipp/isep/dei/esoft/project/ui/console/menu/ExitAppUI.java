package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.serialization.SaveDataToFile;

public class ExitAppUI implements Runnable{

    public void run() {
        SaveDataToFile saveDataToFile = new SaveDataToFile();
        saveDataToFile.run();
        System.exit(0);
    }
}
