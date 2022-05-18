package ma.ac.emi.MonumentBackEnd.UserControllerpackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.ac.emi.MonumentBackEnd.APIControllerspackage.*;
import ma.ac.emi.MonumentBackEnd.Entities.Editeur;
import ma.ac.emi.MonumentBackEnd.Entities.Evaluation;
import ma.ac.emi.MonumentBackEnd.Entities.Monument;
import ma.ac.emi.MonumentBackEnd.Entities.Utilisateur;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBEvaluationDeletter;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBEvaluationGetter;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ControlleurUtilisateur implements IUserChecker,IAdminChecker,IConnectionController,IUserEditor,IUserDeletter {
    @Autowired
    IDBUserAdder userAdder;
    @Autowired
    IDBUserGetter userGetter;
    @Autowired
    IDBUserDeletter userDeletter;
    @Autowired
    IEvaluationManager evaluationManager;
    @Autowired
    IMonumentGetter monumentGetter;
    @Autowired
    IDBEvaluationGetter dbEvaluationGetter;


    @Override
    public void deleteUtilisateur(String idUtilisateur) {
        userDeletter.deleteUtilisateur(idUtilisateur);

        List<Evaluation> evaluations = dbEvaluationGetter.getEvaluations();
        for (Evaluation evaluation : evaluations) {
            if (evaluation.getEditeur().getId().equals(idUtilisateur)) {

                evaluation.setEditeur(new Editeur(evaluation.getEditeur().getId(), "[Deleted]"));
                evaluationManager.addEvaluation(evaluation, evaluation.getId().split("_")[0]);
            }
        }
    }
    @Override
    public void editUtilisateur(Utilisateur utilisateur) {
        if (checkExistence(utilisateur.getId())) {
            userDeletter.deleteUtilisateur(utilisateur.getId());
            userAdder.addUtilisateur(utilisateur);
        }
    }
    @Override
    public Utilisateur connection(String mail, String passeword) {
        return userGetter.getUtilisateur(mail, passeword);
    }
    @Override
    public void creatUtilisateur(Utilisateur utilisateur) throws Exception {
        utilisateur.setId(utilisateur.getMail());
        if (checkExistence(utilisateur.getId())) {
            throw new Exception("User Exist");
        }
        userAdder.addUtilisateur(utilisateur);
    }
    public boolean checkExistence(String id) {
        Utilisateur utilisateur = userGetter.getUtilisateur(id);
        return utilisateur!=null;
    }

    @Override
    public boolean checkUser(String token) throws NoSuchAlgorithmException {
        String id=token.split("_",2)[0];
        if(checkExistence(id)){
            return token.equals(userGetter.getUtilisateur(id).getToken());
        }
        return false;
    }

    @Override
    public boolean checkAdmin(String token) throws NoSuchAlgorithmException {
        String id=token.split("_",2)[0];
        return id.equals("0") && checkUser(token);
    }
}
