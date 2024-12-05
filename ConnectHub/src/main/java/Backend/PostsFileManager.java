package Backend;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import org.json.*;

public class PostsFileManager {
    
    private static final String FILE_PATH = "posts.txt";
    
    
    public static ArrayList<Post> readUsers()
    {
         ArrayList<Post> posts = new ArrayList<>();

        try {
            String json = new String(Files.readAllBytes(Paths.get("posts.txt")));
            JSONArray postsArray = new JSONArray(json); // Parse the JSON array

            for (int i = 0; i < postsArray.length(); i++) {
                JSONObject postJSON = postsArray.getJSONObject(i);
                String authorId = postJSON.getString("userId");
                String contentId = postJSON.getString("contentId");
                String TextContent = postJSON.getString("TextContent");
                String ImageContentBase64 = postJSON.getString("ImageContent");
                byte[] imageBytes = Base64.getDecoder().decode(ImageContentBase64);//decoding the base64 string into a byte array
                Image image = Toolkit.getDefaultToolkit().createImage(imageBytes); 
                LocalDateTime time = LocalDateTime.parse(postJSON.getString("time"));
                posts.add(new Post(contentId, authorId, TextContent, image, time));
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        } catch (JSONException ex) {
            System.out.println("Error parsing JSON: " + ex.getMessage());
        }
        return posts;
    }
    
    

    public static void savePosts(ArrayList<Post> posts) 
    {
        File f= new File(FILE_PATH);
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
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
    
    
}
