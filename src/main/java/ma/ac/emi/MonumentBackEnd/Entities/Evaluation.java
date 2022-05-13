package ma.ac.emi.MonumentBackEnd.Entities;

import org.springframework.beans.factory.annotation.Autowired;

public class Evaluation {
    private String id;
    private double note;
    private String commentaire;
    private Editeur editeur;

    public Evaluation(String id, double note, String commentaire, Editeur editeur) {
        setId(id);
        setNote(note);
        setCommentaire(commentaire);
        setEditeur(editeur);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public IEditeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }
   
}
