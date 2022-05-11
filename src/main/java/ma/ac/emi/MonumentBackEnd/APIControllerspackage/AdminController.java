package ma.ac.emi.MonumentBackEnd.APIControllerspackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminController {

    @Autowired
    private IMonumentDeleter monumentDeleter;
    @Autowired
    private IEvaluationManager evaluationManager;
    @Autowired
    private IUserDeletter userDeletter;

    @PostMapping("/monument/delete")
    public void deleteMonument(@RequestParam String monumentId) {
        monumentDeleter.deleteMonument(monumentId);
    }
    @PostMapping("/evaluation/delete")
    public void deleteEvaluation(@RequestParam String evaluationId) {
        evaluationManager.deleteEvaluation(evaluationId);
    }
    @PostMapping("/user/delete")
    public void deleteUser(@RequestParam String userId) {
        userDeletter.deleteUtilisateur(userId);
    }
}
