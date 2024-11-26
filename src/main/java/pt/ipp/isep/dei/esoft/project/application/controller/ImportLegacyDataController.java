package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.CSVFileRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.File;
import java.util.List;

/**
 * Controller for importing csv files
 */
public class ImportLegacyDataController {

    /**
     * CSV File Repository
     */
    private CSVFileRepository csvFileRepository = null;

    /**
     * Default Constructor
     */
    public ImportLegacyDataController(){
        getCSVFileRepository();
    }

    /**
     * Constructor
     * @param csvFileRepository
     */
    public ImportLegacyDataController(CSVFileRepository csvFileRepository){
        this.csvFileRepository = csvFileRepository;
    }

    /**
     * Get the csv file repository in the Repositories class
     * @return the csv file repository
     */
    private CSVFileRepository getCSVFileRepository(){
        if (csvFileRepository == null) {
            Repositories repositories = Repositories.getInstance();
            csvFileRepository = repositories.getCSVFileRepository();
        }
        return csvFileRepository;
    }

    /**
     * Get all the csv files in the repository
     * @return
     */
    public List<File> getCSVFiles(){
        CSVFileRepository csvFileRepository = getCSVFileRepository();
        return csvFileRepository.getCSVFiles();
    }

    /**
     * Fills the csv file list in the csv repository
     * @param folderPath
     */
    public void fillCSVFileList(String folderPath){
        CSVFileRepository csvFileRepository = getCSVFileRepository();
        csvFileRepository.setFolderPath(folderPath);
        csvFileRepository.fillCSVFileList();
    }

    /**
     * Calls method to import a csv file
     * @param csvFile
     */
    public void importCSVFile(File csvFile){
        CSVFileRepository csvFileRepository = getCSVFileRepository();
        csvFileRepository.importCSVFile(csvFile);
    }
}
