package android.exemple.com.moncabinetmedical.model;

/**
 * Created by lenovo on 30/10/2015.
 */
public class RdvItem {

        private String heure_deb;
        private String nom;
        private String prenom;
        private String commentaire;

    public String getHeure_deb() {
        return heure_deb;
    }

    public void setHeure_deb(String heure_deb) {
        this.heure_deb = heure_deb;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
