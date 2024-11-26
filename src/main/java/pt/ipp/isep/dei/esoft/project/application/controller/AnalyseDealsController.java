package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.DealAnalysis;
import pt.ipp.isep.dei.esoft.project.domain.MultiLinearModel;
import pt.ipp.isep.dei.esoft.project.domain.RegressionModel;
import pt.ipp.isep.dei.esoft.project.domain.SimpleLinearModel;
import pt.ipp.isep.dei.esoft.project.domain.dtos.RegressionModelDTO;
import pt.ipp.isep.dei.esoft.project.domain.mappers.RegressionModelMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;

/**
 * Controller for analysing the deals
 */
public class AnalyseDealsController {

    /**
     * Order repository
     */
    private OrderRepository orderRepository = null;

    /**
     * Default constructor
     */
    public AnalyseDealsController(){
        getOrderRepository();
    }

    /**
     * Default constructor
     * @param orderRepository
     */
    public AnalyseDealsController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    /**
     * Gets the order repository in the Repositories class
     * @return the order repository
     */
    private OrderRepository getOrderRepository(){
        if (orderRepository == null) {
            orderRepository = Repositories.getInstance().getOrderRepository();
        }
        return orderRepository;
    }

    /**
     * Get all the necessary statistics
     * @param chosenRegressionModel
     * @param chosenRegressionParameter
     * @param significanceValue
     * @return the regression model dto
     */
    public RegressionModelDTO getAnalysisStatistics(String chosenRegressionModel, String chosenRegressionParameter, double significanceValue){
        double[][] regressionParameters = getRegressionParameters(chosenRegressionParameter);

        RegressionModel regressionModel;
        String analysisReport;

        //If the user did not choose a parameter, it means that the regression is Multiple Linear
        if (!chosenRegressionModel.equalsIgnoreCase("Simple Linear Model (SLM)")){

            regressionModel = DealAnalysis.getRegressionModel(regressionParameters, significanceValue, false);
            analysisReport = ((MultiLinearModel) regressionModel).createAnalysisReport();

        } else {

            regressionModel = DealAnalysis.getRegressionModel(regressionParameters, significanceValue, true);
            analysisReport = ((SimpleLinearModel) regressionModel).createAnalysisReport();
        }

        return RegressionModelMapper.toDto(regressionModel, analysisReport);
    }

    /**
     * Get regression parameters
     * @param chosenRegressionParameter
     * @return the regression parameters
     */
    private double[][] getRegressionParameters(String chosenRegressionParameter){
        return DealAnalysis.getRegressionParameters(chosenRegressionParameter);
    }

}
