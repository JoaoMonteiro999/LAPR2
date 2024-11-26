package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Divide agencies algorithm
 */
public class DivideAgenciesAlgorithm {

    private static final int DIFFERENCE = 0;
    private static final int MIN_DIFFERENCE = 1;
    private static final int MIN_DIFFERENCE_INDEX = 2;

    /**
     * @param numPropertiesInAgenciesList the list of the number of properties of each agency
     * @return return a class which contains the necessary params to print in the UI
     */
    public static SublistsAndDifference divideSetOfAgencies(int[] numPropertiesInAgenciesList) {

        int n = numPropertiesInAgenciesList.length;
        int numPartitions = calculatePowerNum(n) - 1;

        //0 - contains the difference ; 1 - contains the minimum difference ; 2 - contains the index of the minimum difference
        int[] differenceArray = new int[3];

        //Calculate the first difference
        String firstDifferenceBinary = convertIntToBinary(1, n);
        differenceArray[MIN_DIFFERENCE] = calculateDifferenceOfTheSum(numPropertiesInAgenciesList, firstDifferenceBinary, n, new int[n], new int[n]);

        for (int i = 2; i <= numPartitions; i++) {

            int[] oneList = new int[n];

            int[] zeroList = new int[n];

            String binaryString = convertIntToBinary(i, n);

            differenceArray[DIFFERENCE] = calculateDifferenceOfTheSum(numPropertiesInAgenciesList, binaryString, n, oneList, zeroList);
            verifyIfDifferenceIsMin(differenceArray, i);

        }

        String binaryString = convertIntToBinary(differenceArray[2], n);

        int[] oneList = new int[n];

        int[] zeroList = new int[n];

        int diff = calculateDifferenceOfTheSum(numPropertiesInAgenciesList, binaryString, n, zeroList, oneList);

        return new SublistsAndDifference(numPropertiesInAgenciesList, oneList, zeroList, diff);

    }

    /**
     * This method checks if the give difference is the minimum yet
     * @param differenceArray
     * @param i
     */
    private static void verifyIfDifferenceIsMin(int[] differenceArray, int i){
        //0 is the minTemp and the 1 is the min

        if (differenceArray[DIFFERENCE] < differenceArray[MIN_DIFFERENCE]){
            differenceArray[MIN_DIFFERENCE] = differenceArray[DIFFERENCE];
            differenceArray[MIN_DIFFERENCE_INDEX] = i;
        }

    }

    /**
     * @param n length of the array
     * @return the power of 2
     */
    private static int calculatePowerNum(int n) {

        int powerOfTwo = 1;

        for (int i = 0; i < n - 1; i++) {
            powerOfTwo *= 2;
        }
         return powerOfTwo;

    }


    /**
     * @param i   an int
     * @param n the length of the array
     * @return return a binary string number
     */
    private static String convertIntToBinary(int i, int n) {
        char[] binaryChars = new char[n];
        int index = n - 1;

        //Here's how the & operator works:
        //
        //If both corresponding bits in the operands are 1, the result will have a 1 in that position.
        //If any of the corresponding bits in the operands are 0, the result will have a 0 in that position.

        while (i != 0 && index >= 0) {

            if ((i & 1) == 1) {
                binaryChars[index] = '1';
            } else {
                binaryChars[index] = '0';
            }
            i >>= 1;
            index--;
        }

        while (index >= 0) {
            binaryChars[index] = '0';
            index--;
        }

        return new String(binaryChars);
    }

    /**
     * @param numPropertiesInAgenciesList       the list of stores
     * @param binaryString                      a binary string
     * @param zeroList                          the list with the 0 binaries numbers
     * @param oneList                           the list with the 1 binaries numbers
     * @param n                                 the length of the list
     */
    private static void createSublists(int[] numPropertiesInAgenciesList, String binaryString, int[] zeroList, int[] oneList, int n) {

        for (int i = 0; i < n; i++) {

            if (binaryString.charAt(n - i - 1) == '0') {

                zeroList[i] = numPropertiesInAgenciesList[i];
                oneList[i] = 0;

            } else {

                oneList[i] = numPropertiesInAgenciesList[i];
                zeroList[i] = 0;
            }
        }

    }

    /**
     * @param numPropertiesInAgenciesList       the list of stores
     * @param binaryString                      a binary string
     * @param n                                 the length of the list
     * @param zeroList                          the list with the 0 binaries numbers
     * @param oneList                           the list with the 1 binaries numbers
     * @return return the difference between the sums of each list
     */
    private static int calculateDifferenceOfTheSum(int[] numPropertiesInAgenciesList, String binaryString, int n, int[] zeroList, int[] oneList) {


        createSublists(numPropertiesInAgenciesList, binaryString, zeroList, oneList, n);

        return calculateAbsoluteValue(sublistSum(oneList) - sublistSum(zeroList));
    }

    /**
     * @param x an int
     * @return int with abs value
     */
    private static int calculateAbsoluteValue(int x) {
        return x > 0 ? x : -x;
    }

    /**
     * @param sublist the subset 0 or 1
     * @return the sum of the list
     */
    private static int sublistSum(int[] sublist) {
        int sum = 0;

        for (int num : sublist) {
            sum += num;
        }

        return sum;
    }

}