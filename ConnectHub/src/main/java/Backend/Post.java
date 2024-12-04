package Backend;

import java.time.LocalDate;

public class Post extends Content{
    public Post(String contentId, String authorId, String content, LocalDate uploadingTime) {
        super(contentId, authorId, content, uploadingTime);
        // content id will be getUserName() + getNumPosts()
        // author id is the normal user id
        
    }

   
    
}
