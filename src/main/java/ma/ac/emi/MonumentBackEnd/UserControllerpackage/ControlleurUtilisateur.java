package ma.ac.emi.MonumentBackEnd.UserControllerpackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.ac.emi.MonumentBackEnd.APIControllerspackage.*;
import ma.ac.emi.MonumentBackEnd.Entities.Utilisateur;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class ControlleurUtilisateur implements IUserChecker,IAdminChecker,IConnectionController,IUserEditor,IUserDeletter {
    @Autowired
    IDBUserAdder userAdder;
    @Autowired
    IDBUserGetter userGetter;
    @Autowired
    IDBUserDeletter userDeletter;

    @Override
    public void deleteUtilisateur(String idUtilisateur) {
        userDeletter.deleteUtilisateur(idUtilisateur);
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
