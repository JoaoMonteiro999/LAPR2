package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Arrays;

public class SublistsAndDifference {
    private final int[] inputArray;
    private final int[] oneList;
    private final int[] zeroList;
    private final int difference;

    public SublistsAndDifference(int[] inputArray, int[] oneList, int[] zeroList, int diff) {
        this.inputArray = Arrays.copyOf(inputArray, inputArray.length);
        this.oneList = Arrays.copyOf(oneList, oneList.length);
        this.zeroList = Arrays.copyOf(zeroList, zeroList.length);
        this.difference = diff;
    }

    public int[] getInputArray(){return Arrays.copyOf(inputArray, inputArray.length);}

    public int[] getOneList() {
        return Arrays.copyOf(oneList, oneList.length);
    }

    public int[] getZeroList() {
        return Arrays.copyOf(zeroList, zeroList.length);
    }

    public int getDifference() {
        return difference;
    }

    @Override
    public String toString() {
        return "Input List: " + Arrays.toString(inputArray) + "\n" +
                "Sublist 1: " + Arrays.toString(oneList) + "\n" +
                "Sublist 2: " + Arrays.toString(zeroList) + "\n" +
                "Difference: " + difference + "\n";
    }
}