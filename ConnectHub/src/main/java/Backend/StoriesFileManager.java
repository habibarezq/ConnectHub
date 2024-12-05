package Backend;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.*;

//importing the FilePaths and FileManager interfaces
import Interfaces.*;

public class StoriesFileManager implements FileManager<Story> {

    private static StoriesFileManager instance;
    private ArrayList<Story> stories;

    // private constructor to avoid instantiation
    private StoriesFileManager() {
        this.stories = new ArrayList<>();
        readFromFile(FilePaths.STORIES_FILE_PATH);
    }

    public static StoriesFileManager getInstance() {
        if (instance == null) {
            instance = new StoriesFileManager();
        }
        return instance;
    }

    public ArrayList<Story> getStories() {

        if (stories.isEmpty()) {
            readFromFile(FilePaths.USERS_FILE_PATH);
        }
        return stories;
    }

    @Override
    public void readFromFile(String FILE_PATH) {
        if (!stories.isEmpty())
            return; // to avoid reloading
        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray storiesArray = new JSONArray(json); // Parse the JSON array

            this.stories.clear(); // Clear the existing list before loading new data
            for (int i = 0; i < storiesArray.length(); i++) {
                JSONObject storyJSON = storiesArray.getJSONObject(i);
                String authorId = storyJSON.getString("userId");
                String contentId = storyJSON.getString("contentId");
                String content = storyJSON.getString("content");
                LocalDateTime time = LocalDateTime.parse(storyJSON.getString("time"));
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        } catch (JSONException ex) {
            System.out.println("Error parsing JSON: " + ex.getMessage());
        }
    }

    @Override
    public void saveToFile(ArrayList<Story> stories,String FILE_PATH) {
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
            storyJSON.put("ImageContent", story.getContentPng());
            storyJSON.put("time", story.getUploadingTime());
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

    // Method to find Story by authorID
    public Story findUserByID(String authorId) {
        for (Story s : stories) {
            if (authorId.equals(s.getAuthorId())) {
                return s;
            }
        }
        return null;
    }
}
