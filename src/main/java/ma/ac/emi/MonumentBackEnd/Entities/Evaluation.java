package ma.ac.emi.MonumentBackEnd.Entities;

public class Evaluation {
    private String id;
    private double note;
    private String commentaire;
    private IEditeur editeur;

    public Evaluation(String id, double note, String commentaire, IEditeur editeur) {
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

    public void setEditeur(IEditeur editeur) {
        this.editeur = editeur;
    }
   
}
