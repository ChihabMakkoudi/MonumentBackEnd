package ma.ac.emi.MonumentBackEnd.Entities;

public class Utilisateur {
    String id;
    String nom;
    String prenom;
    String mail;
    String passeword;

    public Utilisateur(String id, String nom, String prenom, String mail, String passeword) {
        this.setId(id);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setMail(mail);
        this.setPasseword(passeword);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPasseword() {
        return this.passeword;
    }

    public void setPasseword(String passeword) {
        this.passeword = passeword;
    }
}
