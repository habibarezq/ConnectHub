package Backend;

import java.awt.Image;
import java.time.LocalDateTime;

public class Post extends Content{
    public Post(String authorId, String contentTxt, String contentPath, LocalDateTime uploadingTime) {
        super(authorId, contentTxt, contentPath, uploadingTime);
        // content id will be getUserName() + getNumPosts()
        // author id is the normal user id getUserId()
        
    }
   
    
}
