package Backend;

import java.awt.Image;
import java.time.LocalDateTime;

public class Story extends Content{
      
      public Story(String contentId, String authorId, String contentTxt, String contentPath, LocalDateTime uploadingTime) {
        super(contentId, authorId, contentTxt, contentPath, uploadingTime);
        // content id will be getUserId() + getNumPosts()
        // author id is the normal user id getUserId()
        
    }
}
