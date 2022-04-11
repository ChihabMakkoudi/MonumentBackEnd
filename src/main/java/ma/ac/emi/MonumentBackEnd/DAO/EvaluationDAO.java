package ma.ac.emi.MonumentBackEnd.DAO;

import ma.ac.emi.MonumentBackEnd.Entities.Evaluation;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBEvaluationAdder;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBEvaluationDeletter;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBEvaluationGetter;

public class EvaluationDAO implements IDBEvaluationGetter,IDBEvaluationAdder,IDBEvaluationDeletter{

    @Override
    public void deleteEvaluation(String idEvaluation) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addEvaluation(Evaluation evaluation) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Evaluation geEvaluation(String idEvaluation) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
