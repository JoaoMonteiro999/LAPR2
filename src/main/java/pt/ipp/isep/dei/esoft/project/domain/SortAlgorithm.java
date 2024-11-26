package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public interface SortAlgorithm {

    enum SortingOrder {
        ASC,
        DSC
    }

    <T extends Comparable<T>> void sort(List<T> list, SortingOrder order);
}
