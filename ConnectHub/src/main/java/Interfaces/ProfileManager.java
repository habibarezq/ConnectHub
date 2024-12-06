package Interfaces;

import java.awt.*;

public interface ProfileManager {
    // an Interface that has the methods of the Profile Management depending on the user ID

    public void changeProfilePic(Image profile);

    public void changeCoverPic( Image cover);

    public void updateBio(String bio);

    public boolean updatePassword(String userId, String password);

}
