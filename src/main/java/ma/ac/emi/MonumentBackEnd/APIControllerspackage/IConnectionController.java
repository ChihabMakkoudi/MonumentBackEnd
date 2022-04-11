package ma.ac.emi.MonumentBackEnd.APIControllerspackage;

import ma.ac.emi.MonumentBackEnd.Entities.Utilisateur;

public interface IConnectionController {
    public Utilisateur connection(String mail,String passeword);
    public void creatUtilisateur(Utilisateur utilisateur);
}
