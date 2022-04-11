package ma.ac.emi.MonumentBackEnd.DAO;

import ma.ac.emi.MonumentBackEnd.Entities.Utilisateur;
import ma.ac.emi.MonumentBackEnd.UserControllerpackage.IDBUserAdder;
import ma.ac.emi.MonumentBackEnd.UserControllerpackage.IDBUserDeletter;
import ma.ac.emi.MonumentBackEnd.UserControllerpackage.IDBUserGetter;

public class UserDAO implements IDBUserAdder,IDBUserGetter,IDBUserDeletter{

    @Override
    public void deleteUtilisateur(String idUtilisateur) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Utilisateur getUtilisateur(String idUtilisateur) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Utilisateur getUtilisateur(String mail, String passeword) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addUtilisateur(Utilisateur utilisateur) {
        // TODO Auto-generated method stub
        
    }
    
}
