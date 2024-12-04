package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserFileManager {

    private static final String FILE_PATH = "users.txt";

    public static ArrayList<User> readUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            String json = new String(Files.readAllBytes(Paths.get("users.txt")));
            JSONArray usersArray = new JSONArray(json); // Parse the JSON array

            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJSON = usersArray.getJSONObject(i);
                String id = userJSON.getString("userId");
                String email = userJSON.getString("email");
                String username = userJSON.getString("username");
                LocalDate dateOfBirth = LocalDate.parse(userJSON.getString("dob"));
                String password = userJSON.getString("password");

                User user = new User(id, email, username, dateOfBirth, password);
                users.add(user);
            }

        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        } catch (JSONException ex) {
            System.out.println("Error parsing JSON: " + ex.getMessage());
        }

        return users;
    }

    public static void saveUsers(ArrayList<User> users) {
        
        File f= new File(FILE_PATH);
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
            file.write(usersArray.toString(4));
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
