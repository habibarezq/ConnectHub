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
