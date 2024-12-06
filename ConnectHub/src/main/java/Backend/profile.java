<<<<<<< HEAD
<<<<<<< HEAD
//package Backend;
//
//import Interfaces.profileManager;
//import java.awt.Image;
//
//public class Profile implements profileManager {
//
//    UserManager manager = new UserManager();
//
//    private Image profilePic;
//    private Image coverPic;
//    private String bio;
//
//    public Profile(String userId) {
//        User user = manager.findUser(userId);
//        this.profilePic = null;
//        this.coverPic = null;
//        this.bio = null;
//    }
//    
//    //method to update the profile photo
//    @Override
//    public boolean changeProfilePic(String userId, Image profile) {
//        
//        
//        return true;
//    }
//
//    //method to update the cover photo
//    @Override
//    public boolean changeCoverPic(String userId, Image cover) {
//       
//        return true;
//    }
//
//    // method to enable the user to update the current bio
//    @Override
//    public boolean updateBio(String userId, String bio) {
//       
//        //update bio
//        return true;
//    }
//
//    // method to enable the user to update the current password
//    @Override
//    public boolean updatePassword(String userId, String password) {
//        User user = manager.findUser(userId);
//        String hashedPass = Password.hashPassword(password);
//
//        //confirm updating passwords only if it does not match the old one
//        if (Password.verifyPassword(user.getPassword(), hashedPass)) {
//            return false;
//        }
//        user.setPassword(hashedPass);
//        return true;
//    }
//
//}
=======

=======
>>>>>>> f09f43002994318fd183ce53e0575cc14295515b
package Backend;

import Interfaces.*;
import java.awt.Image;

public class Profile implements ProfileManager {

    UserFileManager userManager = UserFileManager.getInstance();

    private String userId;
    private Image profilePic;
    private Image coverPic;
    private String bio;

    public Profile(String userId, Image profilePic, Image coverPic, String bio) {
        this.userId = userId;
        this.profilePic = profilePic;
        this.coverPic = coverPic;
        this.bio = bio;
    }
    
    
    //method to update the profile photo
    @Override
    public void changeProfilePic(Image profile) {
        this.profilePic = profile;

        //SaveToFile
    }

    //method to update the cover photo
    @Override
    public void changeCoverPic(Image cover) {
        this.coverPic = cover;

        //SaveToFile
    }

    // method to enable the user to update the current bio
    @Override
    public void updateBio(String bio) {
        this.bio = bio;

        //SaveToFile
    }

    // method to enable the user to update the current password
    @Override
    public boolean updatePassword(String userId, String password) {

        User user = userManager.findUserByID(userId);
        String hashedPass = Password.hashPassword(password);

        //confirm updating passwords only if it does not match the old one
        if (Password.verifyPassword(user.getPassword(), hashedPass)) {
            return false;
        }
        user.setPassword(hashedPass);

        // updating the users.txt file
        userManager.saveToFile(UserFileManager.getInstance().getUsers(), FilePaths.USERS_FILE_PATH);

        return true;
    }

}
>>>>>>> fd4622ed107b161071b05ff5c7b994e33a3ac1c2
