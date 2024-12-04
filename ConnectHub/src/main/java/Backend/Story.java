package Backend;

import java.time.LocalDate;

public class Story extends Content{
      
      public Story(User u, String content) {
        super(u.getUserID()+u.getNumberOfStories(), u.getUserID(), content, LocalDate.now());
        // content id will be getUserName() + getNumPosts()
        // author id is the normal user id
        
    }
}
