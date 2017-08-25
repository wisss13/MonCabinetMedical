package android.exemple.com.moncabinetmedical.model;

/**
 * Created by lenovo on 01/12/2015.
 */
public class Message {

    private String nom;
    private String prenom;
    private String msg;
    private String date;
    private int photo;

    public Message(String n, String m, String d, int p){
        nom = n;
        msg = m;
        date = d;
        photo = p;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
