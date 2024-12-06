//package Backend;
//
//import java.util.ArrayList;
//
//public class FriendsManager {
//
//    private final String userId;
//    private static FriendsManager instance;
//    private ArrayList<User> friends; //List of User's Friends
//    private ArrayList<User> blocked; //List of User's blocked
//
//    private FriendsManager(String userId) {
//        this.userId = userId;
//        this.friends = null;
//        this.blocked = null;
//        getFriendsAndBlocked(this.userId);
//    }
//
//    public static synchronized FriendsManager getInstance(String userId) {
//        if (instance != null && !instance.userId.equals(userId)) {
//            // Clear the existing instance when the userId changes
//            instance = null;  //SHOULD CALL CONSTRUCTOR HERE TO MAKE A NEW INSTANCE FOR NEW ID 
//        }
//        if (instance == null) {
//            instance = new FriendsManager(userId);
//        }
//        return instance;
//    }
//
//    public void getFriendsAndBlocked(String userId)
//    {
//        
//        
//        
//    }
//    
//    public ArrayList<User> getBlocked() {
//        return blocked;
//    }
//
//    public void setBlocked(ArrayList<User> blocked) {
//        this.blocked = blocked;
//    }
//
//    public ArrayList<User> getFriends() {
//        return friends;
//    }
//
//    public void setFriends(ArrayList<User> friends) {
//        this.friends = friends;
//    }
//
//}
