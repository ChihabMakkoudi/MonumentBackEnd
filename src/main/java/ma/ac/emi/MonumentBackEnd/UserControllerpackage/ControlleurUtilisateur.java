package ma.ac.emi.MonumentBackEnd.UserControllerpackage;

import ma.ac.emi.MonumentBackEnd.APIControllerspackage.*;
import ma.ac.emi.MonumentBackEnd.Entities.Utilisateur;

public class ControlleurUtilisateur implements IUserChecker,IAdminChecker,IConnectionController,IUserEditor,IUserDeletter {

    IDBUserAdder userAdder;
    IDBUserGetter userGetter;
    IDBUserDeletter userDeletter;

    @Override
    public void deleteUtilisateur(String idUtilisateur) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void editUtilisateur(Utilisateur utilisateur) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public Utilisateur connection(String mail, String passeword) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void creatUtilisateur(Utilisateur utilisateur) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public boolean checkAdmin(String token) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean checkUser(String token) {
        return false;
    }

    
}
