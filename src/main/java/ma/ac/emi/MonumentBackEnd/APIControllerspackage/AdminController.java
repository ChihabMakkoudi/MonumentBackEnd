package ma.ac.emi.MonumentBackEnd.APIControllerspackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBEvaluationDeletter;

@RestController
public class AdminController {

    @Autowired
    private IMonumentDeleter monumentDeleter;
    @Autowired
    private IDBEvaluationDeletter evaluationDeletter;

    @PostMapping("/monument/delete")
    public void deleteMonument(@RequestParam String monumentId) {
        monumentDeleter.deleteMonument(monumentId);
    }
    @PostMapping("/evaluation/delete")
    public void deleteEvaluation(@RequestParam String evaluationId) {
        evaluationDeletter.deleteEvaluation(evaluationId);
    }
}
