package Interfaces;

import java.awt.*;

public interface ProfileManager {
    // an Interface that has the methods of the Profile Management depending on the user ID

    public boolean changeProfilePic(Image profile);

    public boolean changeCoverPic( Image cover);

    public boolean updateBio(String bio);

    public boolean updatePassword(String userId, String password);

}
