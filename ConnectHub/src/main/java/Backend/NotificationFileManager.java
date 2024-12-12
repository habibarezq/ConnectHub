package Backend;

import Interfaces.FileManager;
import Interfaces.FilePaths;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationFileManager implements FileManager {

    private static NotificationFileManager instance;
    private ArrayList<Notification> notifications;
    private String FILE_PATH = FilePaths.NOTIFICATION_FILE_PATH;

    // private constructor to avoid instantiation
    private NotificationFileManager() {
        this.notifications = new ArrayList<>();
        readFromFile();
    }

    public static synchronized NotificationFileManager getInstance() {
        if (instance == null) {
            instance = new NotificationFileManager();
        }
        return instance;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    @Override
    public void readFromFile() {
        if (!notifications.isEmpty()) {
            return; // To avoid reloading
        }
        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray notificationsArray = new JSONArray(json); // Parse the JSON array

            this.notifications.clear(); // Clear the existing list before loading new data
            for (int i = 0; i < notificationsArray.length(); i++) {
                JSONObject notificationJSON = notificationsArray.getJSONObject(i);
                String notificationID = notificationJSON.getString("notificationId");
                String userID = notificationJSON.getString("userId");
                String timestamp = notificationJSON.getString("timestamp");
                String type = notificationJSON.getString("type");
                String message = notificationJSON.getString("message");
                String stat = notificationJSON.getString("status");
                boolean status;
                notifications.add(new Notification(notificationID,userID,timestamp,type,message,status));
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        } catch (JSONException ex) {
            System.out.println("Error parsing JSON: " + ex.getMessage());
        }
    }

    @Override
    public void saveToFile(ArrayList<Notification> data) {
        File f = new File(FILE_PATH);
        try {
            f.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        JSONArray notificationsArray = new JSONArray();
        for (Notification notification : data) {
            JSONObject notificationJSON = new JSONObject();
            notificationJSON.put("userId", notification.getAuthorId());
            notificationJSON.put("contentId", notification.getContentId());
            notificationJSON.put("TextContent", notification.getContentTxt());
            notificationJSON.put("imagePath", notification.getcontentPath()); // Convert image to Base64
            notificationJSON.put("time", notification.getUploadingTime());
            notificationsArray.put(notificationJSON);
        }

        try {
            FileWriter file = new FileWriter(FILE_PATH);
            file.write(notificationsArray.toString(4));
            file.flush();
            file.close();
            System.out.println("Data Saved Successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error saving posts: " + e.getMessage());
        }
    }

}
