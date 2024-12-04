package Backend;

import java.time.LocalDateTime;

public class Story extends Content{
      
      public Story(User u, String content) {
        super(u.getUserID()+u.getNumberOfStories(), u.getUserID(), content, LocalDateTime.now());
        // content id will be getUserId() + getNumPosts()
        // author id is the normal user id getUserId()
        
    }
}
