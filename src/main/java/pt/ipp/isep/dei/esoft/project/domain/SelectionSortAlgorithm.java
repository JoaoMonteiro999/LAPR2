package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public class SelectionSortAlgorithm implements SortAlgorithm{


    private <T extends Comparable<T>> boolean isBetterCandidate(T possibleCandidate, T currentBest, SortingOrder targetOrder){
        if(targetOrder == SortingOrder.DSC)
        {
            return possibleCandidate.compareTo(currentBest) > 0; // possibleCandidate > currentBest
        }
        else if(targetOrder == SortingOrder.ASC)
        {
            return possibleCandidate.compareTo(currentBest) < 0; // possibleCandidate < currentBest
        }else{
            throw new IllegalArgumentException("Sorting order not valid");
        }
    }

    public <T extends Comparable<T>> void sort(List<T> list, SortingOrder order)
    {
        int n = list.size();

        for (int i = 0; i < n - 1; i++)
        {
            // Find the minIdx when order ascending and maxIdx when order descending
            int bestIdx = i;

            for (int j = i + 1; j < n; j++)
            {
                T possibleCandidate = list.get(j);
                T currentBest = list.get(bestIdx);

                if (isBetterCandidate(possibleCandidate, currentBest, order)){
                    bestIdx = j;
                }
            }

            // Swap elements in pos i and best_idx
            T temp = list.get(bestIdx);
            list.set(bestIdx, list.get(i));
            list.set(i, temp);
        }
    }


}
