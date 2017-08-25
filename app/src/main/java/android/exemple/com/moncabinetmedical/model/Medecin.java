package android.exemple.com.moncabinetmedical.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 25/10/2015.
 */
public class Medecin {

    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;

    public Medecin(String email, String password) {

        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

/*public List<Dataset> dataset;
    public class Dataset{
        String information_login;
    }
*/

}
