package Backend;

import java.awt.Image;
import java.time.LocalDate;

public class Post extends Content{
    public Post(String contentId, String authorId, String contentText, Image ContentImage, LocalDate uploadingTime) {
        super(contentId, authorId, contentText, ContentImage, uploadingTime);
        // content id will be getUserName() + getNumPosts()
        // author id is the normal user id
        
    }

   
    
}
