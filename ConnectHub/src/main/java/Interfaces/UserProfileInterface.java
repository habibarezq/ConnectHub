package Interfaces;

import java.awt.*;

public interface UserProfileInterface {
    // an Interface that has the methods of the Profile Management depending on the user ID

    public void changeProfilePic(String profile);

    public void changeCoverPic( String cover);

    public void updateBio(String bio);

    public void updatePassword(String password);


}
