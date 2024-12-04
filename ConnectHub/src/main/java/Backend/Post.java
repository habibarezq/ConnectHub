package Backend;

import java.time.LocalDateTime;

public class Post extends Content{
    public Post(User u, String content) {
        super(u.getUserID()+u.getNumberOfPosts(), u.getUserID(), content, LocalDateTime.now());
        // content id will be getUserName() + getNumPosts()
        // author id is the normal user id getUserId()
        
    }

   
    
}
