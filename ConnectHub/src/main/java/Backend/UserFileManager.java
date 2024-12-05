package Backend;

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

    //private constructor to avoid instantiation
    private UserFileManager() {
        this.users = new ArrayList<>();
        readFromFile(FilePaths.USERS_FILE_PATH);
    }

    public static UserFileManager getInstance() {
        if (instance == null) {
            instance = new UserFileManager();
        }
        return instance;
    }

    public ArrayList<User> getUsers() {

        if (users.isEmpty()) {
            readFromFile(FilePaths.USERS_FILE_PATH);
        }
        return users;
    }

    @Override
    public void readFromFile(String FILE_PATH) {
        if(!users.isEmpty()) return; //to avoid reloading
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
    public void saveTOFile(ArrayList<User> data, String FILE_PATH) {
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
}
