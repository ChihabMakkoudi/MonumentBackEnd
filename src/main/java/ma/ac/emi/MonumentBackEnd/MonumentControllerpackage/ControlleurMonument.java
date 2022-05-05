package ma.ac.emi.MonumentBackEnd.MonumentControllerpackage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ac.emi.MonumentBackEnd.APIControllerspackage.IEvaluationManager;
import ma.ac.emi.MonumentBackEnd.APIControllerspackage.IMonumentGetter;
import ma.ac.emi.MonumentBackEnd.Entities.Evaluation;
import ma.ac.emi.MonumentBackEnd.Entities.Monument;

public class ControlleurMonument implements IMonumentGetter, IEvaluationManager{

    @Autowired
    private IDBMonumentGetter dbMonumentGetter;

    @Autowired
    private IDBEvaluationDeletter dbMonumentDeletter;
    @Autowired
    private IDBEvaluationAdder dbMonumentAdder;

    @Override
    public List<Monument> getMonuments(List<String> motClets) {
        List<Monument> monuments = dbMonumentGetter.getMonuments();
        List<Monument> filtredMonuments = new ArrayList<>();
        for (Monument monument : monuments) {
            for (String motCle : motClets) {
                if (monument.getDescription().contains(motCle)) {
                    filtredMonuments.add(monument);
                    break;
                }
            }
        }
        return filtredMonuments;
    }

    @Override
    public Monument getMonument(String monumentId) {
        return dbMonumentGetter.getMonument(monumentId);
    }

    // the evaluation id is monumentID_editorID
    @Override
    public void addEvaluation(Evaluation evaluation, String monumentId) {
        evaluation.setId(monumentId + "_" + evaluation.getEditeur().getId());
        dbMonumentAdder.addEvaluation(evaluation);
        
    }

    @Override
    public void deleteEvaluation(String evaluationId) {
        dbMonumentDeletter.deleteEvaluation(evaluationId);
    }
    
}
