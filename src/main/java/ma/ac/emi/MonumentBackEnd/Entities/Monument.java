package ma.ac.emi.MonumentBackEnd.Entities;

import java.util.ArrayList;
import java.util.List;

public class Monument {
    private String id;
    private String nom;
    private String description;
    private Coordinate coordinate;
    private List<String> liensImage;
    private Ville ville;
    private List<Evaluation> evaluations;
    
    public Monument(String id, String nom, String description, Coordinate coordinate, List<String> liensImage, Ville ville) {
        this.setId(id);
        this.setNom(nom);
        this.setDescription(description);
        this.setCoordinate(coordinate);
        this.setLiensImage(liensImage);
        this.setVille(ville);
        this.evaluations=new ArrayList<Evaluation>();
    }
    
    public Monument(String id, String nom, String description, Coordinate coordinate, Ville ville) {
        this.setId(id);
        this.setNom(nom);
        this.setDescription(description);
        this.setCoordinate(coordinate);
        this.liensImage=new ArrayList<String>();
        this.setVille(ville);
        this.evaluations=new ArrayList<Evaluation>();
    }
    public double getRating() {
        double rating=0;
        for (Evaluation evaluation : evaluations) {
            rating += evaluation.getNote();
        }
        rating /= evaluations.size();
        return rating;
    }



    public Ville getVille() {
        return ville;
    }


    public void setVille(Ville ville) {
        this.ville = ville;
    }


    public List<String> getLiensImage() {
        return liensImage;
    }


    public void setLiensImage(List<String> liensImage) {
        this.liensImage = liensImage;
    }


    public Coordinate getCoordinate() {
        return coordinate;
    }


    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public List<Evaluation> getEvaluations() {
        return evaluations;
    }


    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


   
    
}
