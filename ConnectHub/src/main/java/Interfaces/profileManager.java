package Interfaces;

import java.awt.*;

public interface profileManager {
    // an Interface that has the methods of the Profile Management depending on the user ID

    public boolean changeProfilePic(String userId, Image profile);

    public boolean changeCoverPic(String userId, Image cover);

    public boolean updateBio(String userId, String bio);

    public boolean updatePassword(String userId, String password);

}
