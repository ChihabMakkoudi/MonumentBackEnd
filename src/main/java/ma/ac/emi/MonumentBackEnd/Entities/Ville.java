package ma.ac.emi.MonumentBackEnd.Entities;

public class Ville {
    private String nom;
    private String description;

    public Ville() {
        nom = "default city";
        description = "";
    }
    public Ville(String nom, String description) {
        this.setNom(nom);
        this.setDescription(description);
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    
}
