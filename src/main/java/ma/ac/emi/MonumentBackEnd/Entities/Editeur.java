package ma.ac.emi.MonumentBackEnd.Entities;

public class Editeur implements IEditeur{

    private String id;
    private String nomComplet;

    public Editeur(String id, String nomComplet) {
        this.id = id;
        this.nomComplet = nomComplet;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getNomComplet() {
        return nomComplet;
    }
    
}
