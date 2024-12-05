package Backend;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.*;

//importing the FilePaths and FileManager interfaces
import Interfaces.*;

public class PostsFileManager implements FileManager<Post> {
    private static PostsFileManager instance;
    private ArrayList<Post> posts;

    // private constructor to avoid instantiation
    private PostsFileManager() {
        this.posts = new ArrayList<>();
        readFromFile(FilePaths.USERS_FILE_PATH);
    }

    public static PostsFileManager getInstance() {
        if (instance == null) {
            instance = new PostsFileManager();
        }
        return instance;
    }

    public ArrayList<Post> getPosts() {

        if (posts.isEmpty()) {
            readFromFile(FilePaths.POSTS_FILE_PATH);
        }
        return posts;
    }


    @Override
    public void readFromFile(String FILE_PATH) {
        if(!posts.isEmpty()) return; //to avoid reloading
        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray usersArray = new JSONArray(json); // Parse the JSON array

            this.posts.clear(); // Clear the existing list before loading new data
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject postJSON = postsArray.getJSONObject(i);
                String authorId = postJSON.getString("userId");
                String contentId = postJSON.getString("contentId");
                String TextContent = postJSON.getString("TextContent");
                String ImageContentBase64 = postJSON.getString("ImageContent");
                byte[] imageBytes = Base64.getDecoder().decode(ImageContentBase64);//decoding the base64 string into a byte array
                Image image = Toolkit.getDefaultToolkit().createImage(imageBytes); 
                LocalDateTime time = LocalDateTime.parse(postJSON.getString("time"));
                posts.add(new Post(contentId, authorId, TextContent, image, time));
                this.posts.add(posts); // Add to the instance variable, not local
            }

        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        } catch (JSONException ex) {
            System.out.println("Error parsing JSON: " + ex.getMessage());
        }
    }


    @Override
    public void saveToFile(ArrayList<Post> data, String FILE_PATH) {
        File f = new File(FILE_PATH);
        try {
            f.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error:  " + ex.getMessage());
        }
        JSONArray postsArray = new JSONArray();
        for(Post post : posts)
        {
            JSONObject postJSON = new JSONObject();
            postJSON.put("userId", post.getAuthorId());
            postJSON.put("contentId", post.getContentId());
            postJSON.put("TextContent", post.getContentTxt());
            postJSON.put("ImageContent", post.getContentPng());
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
            System.out.println("File Not Found:  " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error saving posts: " + e.getMessage());
        }
    }

}
