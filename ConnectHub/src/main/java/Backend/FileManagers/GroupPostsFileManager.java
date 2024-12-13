package Backend.FileManagers;

import Backend.GroupManagement.GroupPost;
import Backend.Post;
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

public class GroupPostsFileManager implements FileManager<GroupPost> {

    private static GroupPostsFileManager instance;
    private ArrayList<GroupPost> posts;
    private final String FILE_PATH = FilePaths.GROUP_POSTS_FILE_PATH;

    // private constructor to avoid instantiation
    private GroupPostsFileManager() {
        this.posts = new ArrayList<>();
        readFromFile();
    }

    public static synchronized GroupPostsFileManager getInstance() {
        if (instance == null) {
            instance = new GroupPostsFileManager();
        }
        return instance;
    }

    public ArrayList<GroupPost> getPosts() {
        return posts;
    }

    @Override
    public void readFromFile() {
        if (!posts.isEmpty()) {
            return; // To avoid reloading
        }
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            // If the file does not exist or is empty, initialize it with an empty array
            System.out.println("File not found or empty. Initializing with an empty posts array.");
            posts = new ArrayList<>();
            saveToFile(posts);
            return;
        }

        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray postsArray = new JSONArray(json); // Parse the JSON array

            this.posts.clear(); // Clear the existing list before loading new data
            for (int i = 0; i < postsArray.length(); i++) {
                JSONObject postJSON = postsArray.getJSONObject(i);
                String userId = postJSON.getString("userId");
                String groupId = postJSON.getString("groupId");
                String TextContent = postJSON.getString("TextContent");
                String imagePath = postJSON.getString("imagePath");
                LocalDateTime time = LocalDateTime.parse(postJSON.getString("time"));
                posts.add(new GroupPost(groupId ,userId, TextContent, imagePath, time));
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        } catch (JSONException ex) {
            System.out.println("Error parsing JSON: " + ex.getMessage());
        }
    }

    @Override
    public void saveToFile(ArrayList<GroupPost> data) {
        File f = new File(FILE_PATH);
        try {
            f.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        JSONArray postsArray = new JSONArray();
        for (GroupPost post : data) {
            JSONObject postJSON = new JSONObject();
            postJSON.put("userId", post.getAuthorId());
            postJSON.put("groupId", post.getContentId());
            postJSON.put("TextContent", post.getContentTxt());
            postJSON.put("imagePath", post.getcontentPath()); 
            postJSON.put("time", post.getUploadingTime());
            postsArray.put(postJSON);
        }

        try {
            FileWriter file = new FileWriter(FILE_PATH);
            file.write(postsArray.toString(4));
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
