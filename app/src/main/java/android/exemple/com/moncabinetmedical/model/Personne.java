package android.exemple.com.moncabinetmedical.model;

import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
/**
 * Created by lenovo on 12/11/2015.
 */
public class Personne {
    private ProfileDrawerItem profile;
    private int background;

    public Personne(){}

    public ProfileDrawerItem getProfile(){return profile;}

    public void setProfile(ProfileDrawerItem profile) {
        this.profile = profile;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}
