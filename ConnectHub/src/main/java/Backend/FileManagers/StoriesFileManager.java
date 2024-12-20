package Backend.FileManagers;

import Backend.Story;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import org.json.*;

//importing the FilePaths and FileManager interfaces
import Interfaces.*;

public class StoriesFileManager implements FileManager<Story> {

    private static StoriesFileManager instance;
    private String FILE_PATH = FilePaths.STORIES_FILE_PATH;
    private ArrayList<Story> stories;

    // private constructor to avoid instantiation
    private StoriesFileManager() {
        this.stories = new ArrayList<>();
        readFromFile();
    }

    public static synchronized StoriesFileManager getInstance() {
        if (instance == null) {
            instance = new StoriesFileManager();
        }
        return instance;
    }

    public ArrayList<Story> getStories() {
        return stories;
    }

    @Override
    public void readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            // If the file does not exist or is empty, initialize it with an empty stories array
            System.out.println("File not found or empty. Initializing with an empty stories array.");
            stories = new ArrayList<>(); // Initialize the list to an empty state
            saveToFile(stories); // Save an empty array to initialize the file
            return;
        }

        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray storiesArray = new JSONArray(json); // Parse the JSON array

            this.stories.clear(); // Clear the existing list before loading new data
            for (int i = 0; i < storiesArray.length(); i++) {
                JSONObject storyJSON = storiesArray.getJSONObject(i);
                String authorId = storyJSON.getString("userId");
                String contentId = storyJSON.getString("contentId");
                String TextContent = storyJSON.getString("TextContent");
                String imagePath = storyJSON.getString("imagePath");
                LocalDateTime time = LocalDateTime.parse(storyJSON.getString("time"));
                stories.add(new Story(authorId, TextContent, imagePath, time));
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        } catch (JSONException ex) {
            System.out.println("Error parsing JSON: " + ex.getMessage());
        }

    }

    @Override
    public void saveToFile(ArrayList<Story> stories) {
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
            storyJSON.put("imagePath", story.getcontentPath()); //image is added as a Base64 string
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
            System.out.println("Error saving Stories: " + e.getMessage());
        }
    }

}
