package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Agency;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgencyRepository{

    /**
     * List of agencies
     */
    private final List<Agency> agencies = new ArrayList<>();

    /**
     * This method returns an existing Agency by its id.
     *
     * @param chosenAgencyId The id of the agency to be created.
     * @return The agency.
     * @throws IllegalArgumentException if the agency does not exist, which should never happen.
     */
    public Agency getAgencyBySelection(Integer chosenAgencyId){
        Agency agency = null;

        for (Agency searchAgency: agencies) {
            if (searchAgency.getAgenciesBySelection().equals(chosenAgencyId)){
                agency = searchAgency;
                break;
            }
        }
        if (agency == null) {
            throw new IllegalArgumentException(
                    "Agency [" + chosenAgencyId + "] does not exist.");
        }
        return agency;
    }

    /**
     * This method add an agency to the agencies list
     * @param agency
     * @return agency
     */
    public Optional<Agency> add(Agency agency) {

        Optional<Agency> newAgency = Optional.empty();
        boolean operationSuccess = false;

        if (validateAgency(agency)) {
            newAgency = Optional.of(agency.clone());
            operationSuccess = agencies.add(newAgency.get());

        } else if (!validateAgency(agency) && getAgencyIndex(agency) != -1){
            newAgency = Optional.of(agency.clone());
            Agency repeatedAgency = agencies.get(getAgencyIndex(agency));
            operationSuccess = repeatedAgency.getOrders().add(newAgency.get().getOrders().get(0));
        }

        if (!operationSuccess) {
            newAgency = Optional.empty();
        }

        return newAgency;
    }

    /**
     * Checks if the agency is already in the list of agencies
     * @param agency
     * @return true/false
     */
    private boolean validateAgency(Agency agency) {
        boolean isValid = !agencies.contains(agency);
        return isValid;
    }

    /**
     * Get the agency index in the list of agencies
     * @param agencyToVerify
     * @return
     */
    private int getAgencyIndex(Agency agencyToVerify){
        for (Agency agency : agencies) {
            if (validateOrders(agency,agencyToVerify)){
                return agencies.indexOf(agency);
            }
        }
        return -1;
    }

    /**
     * This method checks if the order is already in the list of orders of that agency
     * @param agency
     * @param agencyToVerify
     * @return true/false
     */
    private boolean validateOrders(Agency agency, Agency agencyToVerify){
        return agency.equals(agencyToVerify) && !agency.getOrders().equals(agencyToVerify.getOrders());
    }

    /**
     * This method returns a defensive (immutable) copy of the list of agencies.
     *
     * @return The list of agencies.
     */
    public List<Agency> getAgencies() {
        return List.copyOf(agencies);
    }

    public int[][] getPartitionSublist(List<Agency> agencyList, int numAgenciesToDivide){
        int[][] partitionSublist = new int[numAgenciesToDivide][2];

        for (int i = 0; i < numAgenciesToDivide; i++) {
            Agency agency = agencyList.get(i);
            partitionSublist[i] = new int[]{agency.getAgenciesBySelection(), getOrdersFromAgency(agency)};
        }
        return partitionSublist;
    }

    /**
     * This method gets the number of properties that each agency in the list have
     * @param partitionSublist
     * @return numberPropertiesPerAgency
     */
    public int[] getNumberPropertiesPerAgency(int[][] partitionSublist){
        int[] numberPropertiesPerAgency = new int[partitionSublist.length];

        for (int i = 0; i < numberPropertiesPerAgency.length; i++){
            numberPropertiesPerAgency[i] = partitionSublist[i][1];
        }
        return numberPropertiesPerAgency;
    }

    /**
     * Get the size of the list of orders from the agency
     * @param agency
     * @return size of the list of orders
     */
    private int getOrdersFromAgency(Agency agency){
        return agency.getOrders().size();
    }
}
