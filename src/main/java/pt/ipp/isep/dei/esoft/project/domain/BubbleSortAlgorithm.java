package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public class BubbleSortAlgorithm implements SortAlgorithm{

    /**
     * Method to check if the chosen sorting order is correct
     * @param elem1
     * @param elem2
     * @param targetOrder
     * @return true/false
     * @param <T>
     */
    private <T extends Comparable<T>> boolean isOrderCorrect(T elem1, T elem2, SortingOrder targetOrder){
        if(targetOrder == SortingOrder.DSC)
        {
            return elem1.compareTo(elem2) >= 0; // elem1 >= elem2
        }
        else if(targetOrder == SortingOrder.ASC)
        {
            return elem1.compareTo(elem2) <= 0; // elem1 <= elem2
        }else{
            throw new IllegalArgumentException("Sorting order not valid");
        }
    }

    /**
     * Method to sort a generic list with the chosen order
     * @param list
     * @param order
     * @param <T>
     */
    public <T extends Comparable<T>> void sort(List<T> list, SortingOrder order){

        int n = list.size();

        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - i - 1; j++)
            {
                if (!isOrderCorrect(list.get(j), list.get(j + 1), order))
                {
                    // Swap element in pos j and j + 1
                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}
