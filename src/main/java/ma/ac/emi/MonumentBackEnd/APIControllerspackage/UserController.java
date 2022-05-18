package ma.ac.emi.MonumentBackEnd.APIControllerspackage;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.ac.emi.MonumentBackEnd.Entities.Evaluation;
import ma.ac.emi.MonumentBackEnd.Entities.Monument;
import ma.ac.emi.MonumentBackEnd.Entities.Utilisateur;

@CrossOrigin
@RestController
public class UserController {
    
    @Autowired
    private IUserChecker userChecker;
    @Autowired
    private IMonumentManager monumentManager;
    @Autowired
    private IEvaluationManager evaluationManager;
    @Autowired
    private IUserEditor userEditor;

    @CrossOrigin
    @PostMapping("/monument/add")
    public String addMonument(/*@RequestHeader String token,*/ @RequestBody Monument monument) throws Exception {
        // if (!userChecker.checkUser(token)) throw new Exception("not user");


        // if something missing Exception
        if (failedMonument(monument)) return "fail";


        monument.setId(UUID.randomUUID().toString());
        monumentManager.addMonument(monument);
        return monument.getId();
    }

    @CrossOrigin
    @PostMapping("/monument/edit")
    public String editMonument(/*@RequestHeader String token,*/ @RequestBody Monument monument) throws Exception {
        // if (!userChecker.checkUser(token)) throw new Exception("not user");
        if (failedMonument(monument)) return "fail";
        monumentManager.editMonument(monument.getId(),monument);
        return monument.getId();
    }

    @PostMapping("/evaluation/add")
    public void addEvaluation(@RequestParam String monumentId, @RequestBody Evaluation evaluation) throws Exception {
        // if (!userChecker.checkUser(token)) throw new Exception("not user");
        // if (getUserIdFromToken(token) != evaluation.getEditeur().getId()) throw new Exception("not same user");
        evaluationManager.addEvaluation(evaluation, monumentId);
    }
    
    @PostMapping("/evaluation/userdelete")
    public void deleteEvaluation(/*@RequestHeader String token,*/ @RequestParam String evaluationId) throws Exception {
        // if (!userChecker.checkUser(token)) throw new Exception("not user");
        evaluationManager.deleteEvaluation(evaluationId);
    }

    @PostMapping("/account/edit")
    public void editAccount(/*@RequestHeader String token,*/ @RequestBody Utilisateur user) throws Exception {
        // if (!userChecker.checkUser(token)) throw new Exception("not user");
        // if (getUserIdFromToken(token) != user.getId()) throw new Exception("not same user");
        userEditor.editUtilisateur(user);

    }

    private String getUserIdFromToken(String token) {
        String[] arrOfStr = token.split("_", 2);
        return arrOfStr[0];
    }
    
    private Boolean failedMonument(Monument monument) {
        System.out.println(monument.getVille().getNom());
        System.out.println(monument.getCoordinate().getLatitude());
        
        Boolean failed =  monument.getNom().isBlank() || monument.getCoordinate().getLatitude()==0 
                || monument.getCoordinate().getLongitude() == 0 
                || monument.getLiensImage().get(0).isBlank();
        System.out.println(failed);

        return failed;
    }
}
