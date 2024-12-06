package Backend;

import Interfaces.FilePaths;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class FriendsFileManager {

    private static FriendsFileManager instance;
    private ArrayList<FriendsManager> friendsManagers;
    private ArrayList<User> users;
    private ArrayList<User> friends;
    private ArrayList<User> blocked;
    private String FILE_PATH = FilePaths.FRIENDS_FILE_PATH;

    //private constructor to avoid instantiation (singleton)
    private FriendsFileManager() throws IOException {
        this.friendsManagers = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.blocked = new ArrayList<>();
        readFromFile();
    }

    public static FriendsFileManager getInstance() throws IOException {
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
       
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray userFriendsBlockedArray = new JSONArray(json); // Parse the JSON array
            
            this.users.clear();//clear the existing list before loading new data
            for (int i = 0; i < userFriendsBlockedArray.length(); i++) {
                JSONObject userFriendBlockedJSON = userFriendsBlockedArray.getJSONObject(i);
                String id = userFriendBlockedJSON.getString("userId");
              //  friends = userFriendBlockedJSON.;

                
                
            }

    }
    
public void saveToFile(ArrayList<User> data) throws IOException
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
         JSONObject userfriendBlockedJSON = new JSONObject();
         userfriendBlockedJSON.put("userId", user.getUserID());
         userfriendBlockedJSON.put("friends", user.getFriendsManager().getFriends());
         userfriendBlockedJSON.put("blocked", user.getFriendsManager().getBlocked());
         userFriendsBlockedArray.put(userfriendBlockedJSON);
     }
     try{
      FileWriter file = new FileWriter(FILE_PATH);
            file.write(userFriendsBlockedArray.toString(4)); //Why 4?
            file.flush();
            file.close();
            System.out.println("Data Saved Successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found:  " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        } 
}
}
