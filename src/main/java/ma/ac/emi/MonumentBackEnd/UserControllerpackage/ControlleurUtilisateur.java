package ma.ac.emi.MonumentBackEnd.UserControllerpackage;

import ma.ac.emi.MonumentBackEnd.APIControllerspackage.*;
import ma.ac.emi.MonumentBackEnd.Entities.Utilisateur;

public class ControlleurUtilisateur implements IUserChecker,IAdminChecker,IConnectionController,IUserEditor,IUserDeletter {

    IDBUserAdder userAdder;
    IDBUserGetter userGetter;
    IDBUserDeletter userDeletter;

    @Override
    public void deleteUtilisateur(String idUtilisateur) {
        userDeletter.deleteUtilisateur(idUtilisateur);
    }
    @Override
    public void editUtilisateur(Utilisateur utilisateur) {
        if (checkUser(utilisateur.getId())) {
            userDeletter.deleteUtilisateur(utilisateur.getId());
            userAdder.addUtilisateur(utilisateur);
        }
    }
    @Override
    public Utilisateur connection(String mail, String passeword) {
        return userGetter.getUtilisateur(mail, passeword);
    }
    @Override
    public void creatUtilisateur(Utilisateur utilisateur) {
        if (!checkUser(utilisateur.getId())) {
            userAdder.addUtilisateur(utilisateur);
        }
        else{
            //TODO implement error handling
        }
    }
    @Override
    public boolean checkAdmin(String token) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean checkUser(String token) {
        Utilisateur utilisateur = userGetter.getUtilisateur(token);
        if (utilisateur==null) return false;
        return true;
    }

    
}
