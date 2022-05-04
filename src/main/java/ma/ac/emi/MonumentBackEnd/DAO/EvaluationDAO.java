package ma.ac.emi.MonumentBackEnd.DAO;

import java.io.File;

import ma.ac.emi.MonumentBackEnd.Entities.Evaluation;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBEvaluationAdder;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBEvaluationDeletter;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBEvaluationGetter;

public class EvaluationDAO implements IDBEvaluationGetter,IDBEvaluationAdder,IDBEvaluationDeletter{

    @Override
    public void deleteEvaluation(String idEvaluation) {
        File dir = new File("evaluations/");
        File[] files = dir.listFiles((dir1, name) -> name.equals(idEvaluation+".xml"));
        if (files.length>0)
            files[0].delete();
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
