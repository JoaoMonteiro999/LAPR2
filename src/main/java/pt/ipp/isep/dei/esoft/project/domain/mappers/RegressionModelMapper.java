package pt.ipp.isep.dei.esoft.project.domain.mappers;

import pt.ipp.isep.dei.esoft.project.domain.MultiLinearModel;
import pt.ipp.isep.dei.esoft.project.domain.RegressionModel;
import pt.ipp.isep.dei.esoft.project.domain.SimpleLinearModel;
import pt.ipp.isep.dei.esoft.project.domain.dtos.RegressionModelDTO;

public class RegressionModelMapper {

    public static RegressionModelDTO toDto(RegressionModel regressionModel, String analysisReport){
        return new RegressionModelDTO(regressionModel, analysisReport);
    }
}
