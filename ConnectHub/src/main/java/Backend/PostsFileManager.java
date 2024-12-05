package Backend;

import java.io.*;
import java.util.*;
import org.json.*;

public class PostsFileManager {
    
    private static final String FILE_PATH = "posts.txt";
    
    
    public static ArrayList<Post> readUsers()
    {
        return null;
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
