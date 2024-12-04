package Backend;

import java.awt.Image;
import java.time.LocalDate;

public class Story extends Content{

      public Story(String contentId, String authorId, String content, LocalDate uploadingTime) {
        super(contentId, authorId, content, uploadingTime);
        // content id will be getUserName() + getNumPosts()
        // author id is the normal user id
        
    }
}
