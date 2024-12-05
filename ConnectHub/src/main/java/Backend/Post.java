package Backend;

import java.awt.Image;
import java.time.LocalDateTime;

public class Post extends Content{
    public Post(String contentId, String authorId, String contentTxt, Image contentPng, LocalDateTime uploadingTime) {
        super(contentId, authorId, contentTxt, contentPng, uploadingTime);
        // content id will be getUserName() + getNumPosts()
        // author id is the normal user id getUserId()
        
    }


   
    
}
