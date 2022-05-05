package ma.ac.emi.MonumentBackEnd.MonumentControllerpackage;

import java.util.List;

import ma.ac.emi.MonumentBackEnd.Entities.Evaluation;

public interface IDBEvaluationGetter {
    public Evaluation getEvaluation(String idEvaluation);
    public List<Evaluation> getEvaluations();
}
