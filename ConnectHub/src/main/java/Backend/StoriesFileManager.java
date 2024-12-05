package Backend;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StoriesFileManager {

    private static final String FILE_PATH = "stories.txt";

    public static ArrayList<Story> readStories() {
        ArrayList<Story> stories = new ArrayList<>();

        try {
            String json = new String(Files.readAllBytes(Paths.get("stories.txt")));
            JSONArray storiesArray = new JSONArray(json); // Parse the JSON array

            for (int i = 0; i < storiesArray.length(); i++) {
                JSONObject storyJSON = storiesArray.getJSONObject(i);
                String authorId = storyJSON.getString("userId");
                String contentId = storyJSON.getString("contentId");
                String TextContent = storyJSON.getString("TextContent");
                String ImageContentBase64 = storyJSON.getString("ImageContent");
                byte[] imageBytes = Base64.getDecoder().decode(ImageContentBase64);//decoding the base64 string into a byte array
                Image image = Toolkit.getDefaultToolkit().createImage(imageBytes); 
                LocalDateTime time = LocalDateTime.parse(storyJSON.getString("time"));
                stories.add(new Story(contentId, authorId, TextContent, image, time));
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        } catch (JSONException ex) {
            System.out.println("Error parsing JSON: " + ex.getMessage());
        }
        return stories;
    }

    public static void savePosts(ArrayList<Story> stories) {
        File f = new File(FILE_PATH);
        try {
            f.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error:  " + ex.getMessage());
        }
        JSONArray storiesArray = new JSONArray();
        for (Story story : stories) {
            JSONObject storyJSON = new JSONObject();
            storyJSON.put("userId", story.getAuthorId());
            storyJSON.put("contentId", story.getContentId());
            storyJSON.put("TextContent", story.getContentTxt());
            storyJSON.put("ImageContent", story.getContentPng()); //image is added as a Base64 string
            storyJSON.put("time", story.getUploadingTime()); //base64 is a binary-to-text encoding scheme to encode binary data and ensure transmitting data safely
            storiesArray.put(storyJSON);
        }
        try {
            FileWriter file = new FileWriter(FILE_PATH);
            file.write(storiesArray.toString(4));
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
