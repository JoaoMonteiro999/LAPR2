package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.DivideAgenciesController;
import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.SublistsAndDifference;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class DivideAgenciesUI implements Runnable {

    private final double nanoNumber = 1_000_000_000.0;

    private final DivideAgenciesController controller = new DivideAgenciesController();

    private List<Agency> agencyList;
    private Integer numAgenciesToDivide;


    /**
     * run the UI
     */
    public void run() {
        System.out.println("Divide Agencies:");

        requestData();

        divideSetOfAgencies();
    }

    private void requestData(){

        agencyList = getController().getAgencies();
        numAgenciesToDivide = typeNumAgenciesToDivide();
    }

    private Integer typeNumAgenciesToDivide(){
        Integer numAgencies = agencyList.size();
        Scanner input = new Scanner(System.in);

        while (true) {

            try {
                System.out.println("Enter the number of stores to divide (2 - " + numAgencies + "): ");
                Integer numAgenciesToDivide = input.nextInt();

                if (numAgenciesToDivide <= 1 || numAgenciesToDivide > numAgencies) {
                    throw new IllegalArgumentException("Invalid number! Please enter the number of stores to divide (1 - " + numAgencies + "): ");
                }
                return numAgenciesToDivide;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    private void divideSetOfAgencies(){
        SublistsAndDifference sublistsAndDifference = null;

        int[][] partitionSublist = controller.getPartitionSublist(agencyList,numAgenciesToDivide);

        long startTime = System.nanoTime();

        sublistsAndDifference = controller.divideSetOfAgencies(partitionSublist);

        long endTime = System.nanoTime();


        if (sublistsAndDifference != null){
            System.out.println("Input List: " + Arrays.toString(sublistsAndDifference.getInputArray()));
            printSubListWithStoreIDAndNumberOfProperties("1",sublistsAndDifference.getOneList(),partitionSublist);
            printSubListWithStoreIDAndNumberOfProperties("2",sublistsAndDifference.getZeroList(),partitionSublist);
            System.out.println("Difference: " + sublistsAndDifference.getDifference());
            printExecutionTime(startTime,endTime);
        } else {
            System.out.println("Error while creating sublists!");
        }
    }

    /**
     * @param sublistName name of the list , zero or one
     * @param list          list one or zero
     */
    private void printSubListWithStoreIDAndNumberOfProperties(String sublistName, int[] list, int[][] partitionSublist) {
        System.out.print("Sublist " + sublistName + ": ");

        for (int i = 0; i < list.length; i++) {
            if (list[i] != 0) {
                System.out.print("(" + partitionSublist[i][0] + " , " + partitionSublist[i][1] + "), ");
            } else {
                System.out.print(list[i] + ", ");
            }
        }
        System.out.println();
    }

    private void printExecutionTime(double startTime, double endTime){
        System.out.print("\nExecution Time: " + (endTime-startTime)/nanoNumber + " seconds\n");
    }

    private DivideAgenciesController getController(){
        return controller;
    }
}