package ma.ac.emi.MonumentBackEnd.APIControllerspackage;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.ac.emi.MonumentBackEnd.Entities.Utilisateur;


@RestController
public class AdminController {

    @Autowired
    private IMonumentDeleter monumentDeleter;
    @Autowired
    private IEvaluationManager evaluationManager;
    @Autowired
    private IUserDeletter userDeletter;
    @Autowired
    private IUserEditor userEditor;
    @Autowired
    private IAdminChecker adminChecker;

    @PostMapping("/monument/delete")
    public void deleteMonument(@RequestHeader String token, @RequestParam String monumentId) throws Exception {
        if (!adminChecker.checkAdmin(token)) throw new Exception("not admin");
        monumentDeleter.deleteMonument(monumentId);
    }
    @PostMapping("/evaluation/delete")
    public void deleteEvaluation(@RequestHeader String token, @RequestParam String evaluationId) throws Exception {
        if (!adminChecker.checkAdmin(token)) throw new Exception("not admin");
        evaluationManager.deleteEvaluation(evaluationId);
    }
    @PostMapping("/user/delete")
    public void deleteUser(@RequestHeader String token, @RequestParam String userId) throws Exception {
        if (!adminChecker.checkAdmin(token)) throw new Exception("not admin");
        userDeletter.deleteUtilisateur(userId);
    }
    @PostMapping("/user/edit")
    public void editAccount(@RequestHeader String token, @RequestBody Utilisateur user) throws Exception {
        if (!adminChecker.checkAdmin(token)) throw new Exception("not admin");
        userEditor.editUtilisateur(user);
    }
}
