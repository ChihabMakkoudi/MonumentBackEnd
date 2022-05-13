package ma.ac.emi.MonumentBackEnd.MonumentControllerpackage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.ac.emi.MonumentBackEnd.APIControllerspackage.IEvaluationManager;
import ma.ac.emi.MonumentBackEnd.APIControllerspackage.IMonumentDeleter;
import ma.ac.emi.MonumentBackEnd.APIControllerspackage.IMonumentGetter;
import ma.ac.emi.MonumentBackEnd.APIControllerspackage.IMonumentManager;
import ma.ac.emi.MonumentBackEnd.Entities.Evaluation;
import ma.ac.emi.MonumentBackEnd.Entities.Monument;

@Component
public class ControlleurMonument implements IMonumentGetter, IEvaluationManager, IMonumentManager, IMonumentDeleter{

    @Autowired
    private IDBMonumentGetter dbMonumentGetter;
    @Autowired
    private IDBMonumentAdder dbMonumentAdder;
    @Autowired
    private IDBMonumentDeletter dbMonumentDeletter;

    @Autowired
    private IDBEvaluationDeletter dbEvaluationDeletter;
    @Autowired
    private IDBEvaluationAdder dbEvaluationAdder;
    @Autowired
    private IDBEvaluationGetter dbEvaluationGetter;

    @Override
    public List<Monument> getMonuments(List<String> motClets) {
        List<Monument> monuments = dbMonumentGetter.getMonuments();
        List<Monument> filtredMonuments = new ArrayList<>();
        for (Monument monument : monuments) {
            for (String motCle : motClets) {
                if (monument.getDescription().contains(motCle) || monument.getVille().getNom().toLowerCase().equals(motCle.toLowerCase())) {
                    filtredMonuments.add(getMonument(monument.getId()));
                    break;
                }
            }
        }
        return filtredMonuments;
    }

    // get the monument and get the evaluations
    @Override
    public Monument getMonument(String monumentId) {
        Monument monument = dbMonumentGetter.getMonument(monumentId);
        
        List<Evaluation> evaluations = dbEvaluationGetter.getEvaluations();
        for (Evaluation evaluation : evaluations) {
            if (evaluation.getId().startsWith(monumentId +"_"))
                monument.getEvaluations().add(evaluation);
        }
        
        return monument;
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

    @Override
    public void deleteMonument(String monumentId) {
        dbMonumentDeletter.deleteMonument(monumentId);
    } 
            
}
