package pt.ipp.isep.dei.esoft.project.domain.dtos;

import pt.ipp.isep.dei.esoft.project.domain.MultiLinearModel;
import pt.ipp.isep.dei.esoft.project.domain.RegressionModel;
import pt.ipp.isep.dei.esoft.project.domain.SimpleLinearModel;

public class RegressionModelDTO {

    private RegressionModel regressionModel;

    private String analysisReport;

    public RegressionModelDTO(RegressionModel regressionModel, String analysisReport){
        this.regressionModel = regressionModel;
        this.analysisReport = analysisReport;
    }

    public RegressionModel getRegressionModel() { return regressionModel;}

    public String getAnalysisReport() {
        return analysisReport;
    }


}
