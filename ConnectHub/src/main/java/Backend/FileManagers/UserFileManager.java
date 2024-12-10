package Backend.FileManagers;

import Backend.User;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.*;

//importing the FilePaths and FileManager interfaces
import Interfaces.*;

public class UserFileManager implements FileManager<User> {

    private static UserFileManager instance;
    private ArrayList<User> users;
    private String FILE_PATH = FilePaths.USERS_FILE_PATH;

    //private constructor to avoid instantiation
    private UserFileManager() {
        this.users = new ArrayList<>();
        readFromFile();
    }

    public static synchronized UserFileManager getInstance() {
        if (instance == null) {
            instance = new UserFileManager();
        }
        return instance;
    }

    public ArrayList<User> getUsers() {

        if (users.isEmpty()) {
            readFromFile();
        }
        return users;
    }

    @Override
    public void readFromFile() {
        if (!users.isEmpty()) {
            return; //to avoid reloading
        }
        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray usersArray = new JSONArray(json); // Parse the JSON array

            this.users.clear(); // Clear the existing list before loading new data
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJSON = usersArray.getJSONObject(i);
                String id = userJSON.getString("userId");
                String email = userJSON.getString("email");
                String username = userJSON.getString("username");
                LocalDate dateOfBirth = LocalDate.parse(userJSON.getString("dob"));
                String password = userJSON.getString("password");
                boolean isOnline = userJSON.getBoolean("isOnline");

                User user = new User(id, email, username, dateOfBirth, password);
                this.users.add(user); // Add to the instance variable, not local
            }

        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        } catch (JSONException ex) {
            System.out.println("Error parsing JSON: " + ex.getMessage());
        }
    }

    @Override
    public void saveToFile(ArrayList<User> data) {
        File f = new File(FILE_PATH);
        try {
            f.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error:  " + ex.getMessage());
        }
        JSONArray usersArray = new JSONArray();

        for (User user : users) {
            JSONObject userJSON = new JSONObject();
            
            userJSON.put("userId", user.getUserID());
            userJSON.put("email", user.getEmail());
            userJSON.put("username", user.getUsername());
            userJSON.put("dob", user.getDateOfBirth().toString());
            userJSON.put("password", user.getPassword());
            userJSON.put("isOnline", user.getStatus());

            usersArray.put(userJSON);
        }

        try {
            FileWriter file = new FileWriter(FILE_PATH);
            file.write(usersArray.toString(4)); //Why 4?
            file.flush();
            file.close();
            System.out.println("Data Saved Successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found:  " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }

    }

    // Method to find user by ID
    public User findUserByID(String userID) {
        for (User u : users) {
            if (userID.equals(u.getUserID())) {
                return u;
            }
        }
        return null;
    }


    public User findUserByUsername(String username) {
        for (User u : users) {
            if (username.equals(u.getUsername())) {
                return u;
            }
        }

        return null;
    }

    public void refreshUserStatus() {
        for (User user : getUsers()) {
            boolean isOnline = user.getStatus();
            // Update UI accordingly
        }
    }
}
