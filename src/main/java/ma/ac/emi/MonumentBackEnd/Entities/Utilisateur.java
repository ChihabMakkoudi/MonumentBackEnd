package ma.ac.emi.MonumentBackEnd.Entities;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Utilisateur implements IEditeur{
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

    @Override
    public String getNomComplet() {
        return getNom() + " " + getPrenom();
    }
    public String getToken() throws NoSuchAlgorithmException {
        return id+"_"+hash(mail+passeword);
    }
    // use SHA-256 as algorithm for hashing mail+password
    /* for frontEnd javascript, use this ==>
        const { createHash } = require('crypto');
        function hash(string) {
          return createHash('sha256').update(string).digest('hex');
        }
     */
    public String hash(String key) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(key.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
