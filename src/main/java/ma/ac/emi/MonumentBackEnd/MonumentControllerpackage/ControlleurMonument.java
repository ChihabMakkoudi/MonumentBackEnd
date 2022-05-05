package ma.ac.emi.MonumentBackEnd.MonumentControllerpackage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ac.emi.MonumentBackEnd.APIControllerspackage.IEvaluationManager;
import ma.ac.emi.MonumentBackEnd.APIControllerspackage.IMonumentGetter;
import ma.ac.emi.MonumentBackEnd.APIControllerspackage.IMonumentManager;
import ma.ac.emi.MonumentBackEnd.Entities.Evaluation;
import ma.ac.emi.MonumentBackEnd.Entities.Monument;

public class ControlleurMonument implements IMonumentGetter, IEvaluationManager, IMonumentManager{

    @Autowired
    private IDBMonumentGetter dbMonumentGetter;
    @Autowired
    private IDBMonumentAdder dbMonumentAdder;

    @Autowired
    private IDBEvaluationDeletter dbEvaluationDeletter;
    @Autowired
    private IDBEvaluationAdder dbEvaluationAdder;

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
        dbEvaluationAdder.addEvaluation(evaluation);
        
    }

    @Override
    public void deleteEvaluation(String evaluationId) {
        dbEvaluationDeletter.deleteEvaluation(evaluationId);
    }

    @Override
    public void addMonument(Monument monument) {
        dbMonumentAdder.addMonument(monument);
    }

    @Override
    public void editMonument(String monumentId, Monument monument) {
        monument.setId(monumentId);
        addMonument(monument);
    }
    
}
