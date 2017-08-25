package android.exemple.com.moncabinetmedical.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo on 02/11/2015.
 */
public class Contact implements Parcelable {
    public static final String PATH = "";

    private long id;
    private String nom;
    private String prenom;
    private String tel;
    private int photo;
    private String description;
    private int category;
    private String urlPhoto;

    public Contact() {}

    public Contact(String n, String pren, String t, int phot) {
        nom = n;
        prenom = pren;
        tel = t;
        photo = phot;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    // PARCELABLE
    public Contact(Parcel parcel){
        setId(parcel.readLong());
        setNom(parcel.readString());
        setPrenom(parcel.readString());
        setDescription(parcel.readString());
        setCategory(parcel.readInt());
        setTel(parcel.readString());
        setPhoto(parcel.readInt());
        setUrlPhoto(parcel.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong( getId() );
        dest.writeString( getNom() );
        dest.writeString( getPrenom() );
        dest.writeString( getDescription() );
        dest.writeInt( getCategory() );
        dest.writeString( getTel() );
        dest.writeInt( getPhoto() );
        dest.writeString( getUrlPhoto() );
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>(){
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }
        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
