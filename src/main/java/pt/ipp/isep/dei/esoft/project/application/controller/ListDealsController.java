package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.repository.OrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

/**
 * Controller for getting a properties list
 *
 * allows for several filters
 */
public class ListDealsController {

    /**
     * Order repository
     */
    private OrderRepository repository;

    public ListDealsController(){
        this.repository = Repositories.getInstance().getOrderRepository();
    }

    public ListDealsController(OrderRepository repository){
        this.repository = repository;
    }

    /**
     * obtains list of Deals Made from the repository
     * @return list of deals made
     */
    public List<Order> getDealsMadeSortedByDate(){
        return repository.getDealsMadeSortedByDate();
    }

    public List<Order> getDealsMadeSortedByPropertyAreaBubbleSort(boolean ascending){
        return repository.getDealsMadeSortedByPropertyAreaBubbleSort(ascending);
    }

    public List<Order> getDealsMadeSortedByPropertyAreaSelectionSort(boolean ascending){
        return repository.getDealsMadeSortedByPropertyAreaSelectionSort(ascending);
    }


}
