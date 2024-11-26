package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.DivideAgenciesAlgorithm;
import pt.ipp.isep.dei.esoft.project.domain.SublistsAndDifference;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

/**
 * Controller for dividing a set of agencies
 */
public class DivideAgenciesController {

    /**
     * Agency repository
     */
    private AgencyRepository agencyRepository = null;

    /**
     * Default constructor.
     */
    public DivideAgenciesController() {
        getAgencyRepository();
    }

    /**
     * Default constructor
     * @param agencyRepository
     */
    public DivideAgenciesController(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    /**
     * Get agency repository from Repository class
     * @return the agency repository
     */
    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    /**
     * Get the agencies in the agency repository
     * @return the list of agencies
     */
    public List<Agency> getAgencies(){
        return Repositories.getInstance().getAgencyRepository().getAgencies();
    }

    /**
     * Get the partition sublist
     * @param agencyList
     * @param numAgenciesToDivide
     * @return the sublist of partitions
     */
    public int[][] getPartitionSublist(List<Agency> agencyList, Integer numAgenciesToDivide){
        return agencyRepository.getPartitionSublist(agencyList,numAgenciesToDivide);
    }

    /**
     * It calls the method to divide the set of agencies
     * @return an array containing the minor difference index, oneList, zeroList, and difference
     */
    public SublistsAndDifference divideSetOfAgencies(int[][] partitionSublist) {
        int[] numPropertiesInAgenciesList = agencyRepository.getNumberPropertiesPerAgency(partitionSublist);
        return DivideAgenciesAlgorithm.divideSetOfAgencies(numPropertiesInAgenciesList);
    }
}