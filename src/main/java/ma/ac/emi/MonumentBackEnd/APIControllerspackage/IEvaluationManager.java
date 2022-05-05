package ma.ac.emi.MonumentBackEnd.APIControllerspackage;

import ma.ac.emi.MonumentBackEnd.Entities.Evaluation;

public interface IEvaluationManager {

    public void addEvaluation(Evaluation evaluation, String monumentId);

    public void deleteEvaluation(String evaluationId);
    
}
