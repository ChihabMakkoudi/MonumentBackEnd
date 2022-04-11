package ma.ac.emi.MonumentBackEnd.UserControllerpackage;

import ma.ac.emi.MonumentBackEnd.Entities.Utilisateur;

public interface IDBUserGetter {
    public Utilisateur getUtilisateur(String idUtilisateur);
    public Utilisateur getUtilisateur(String mail,String passeword);
}
