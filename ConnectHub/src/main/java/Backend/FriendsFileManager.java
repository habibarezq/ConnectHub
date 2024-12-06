package Backend;

import Interfaces.FilePaths;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class FriendsFileManager {

    private static FriendsFileManager instance;
    private ArrayList<User> users;
    private ArrayList<User> friends;
    private ArrayList<User> blocked;
    private String FILE_PATH = FilePaths.FRIENDS_FILE_PATH;

    //private constructor to avoid instantiation (singleton)
    private FriendsFileManager() {
        this.friends = new ArrayList<>();
        this.blocked = new ArrayList<>();
        readFromFile();
    }

    public static FriendsFileManager getInstance() {
        if (instance == null) {
            instance = new FriendsFileManager();
        }
        return instance;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public ArrayList<User> getBlocked() {
        return blocked;
    }

    public void readFromFile() throws IOException {
        if(!users.isEmpty()) return;
        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray usersArray = new JSONArray(json); // Parse the JSON array
            
            this.users.clear();//clear the existing list before loading new data
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJSON = usersArray.getJSONObject(i);
                String id = userJSON.getString("userId");
                String email = userJSON.getString("email");
                String username = userJSON.getString("username");
                LocalDate dateOfBirth = LocalDate.parse(userJSON.getString("dob"));
                String password = userJSON.getString("password");

                User user = new User(id, email, username, dateOfBirth, password);
                this.users.add(user); // Add to the instance variable, not local
            }

    }
//    File f = new File(FILE_PATH);
//        try {
//            f.createNewFile();
//        } catch (IOException ex) {
//            System.out.println("Error:  " + ex.getMessage());
//        }
//        JSONArray usersFriendsArray = new JSONArray();
    }
public void saveToFile(ArrayList<User> data)
{
    File f = new File(FILE_PATH);
     try {
           f.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error:  " + ex.getMessage());
        }
     JSONArray userFriendsBlockedArray = new JSONArray();
     
     for(User user : users)
     {
         JSONObject userfrindBlockedJSON = new JSONObject();
         userfrindBlockedJSON.put("userId", user.getUserID());
         userfrindBlockedJSON.put("friends", user.)
     }
}
}
