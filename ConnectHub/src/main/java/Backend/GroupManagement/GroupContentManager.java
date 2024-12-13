package Backend.GroupManagement;
import Backend.FileManagers.GroupPostsFileManager;
import Backend.FileManagers.PostsFileManager;
import Backend.Post;
import java.util.ArrayList;
import javax.swing.SwingConstants;

public class GroupContentManager {

    private final String groupId;
    private static GroupContentManager instance;
    private ArrayList<GroupPost> posts; // Changed to lazy-loadable

    private boolean postsLoaded = false; // Tracks if posts are loaded

    // Private constructor to avoid instantiation
    private GroupContentManager(String userId) {
        this.groupId = userId;
        this.posts = null;
    }

    public static synchronized GroupContentManager getInstance(String groupId) {
        if (instance != null && !instance.groupId.equals(groupId)) {
            // Clear the existing instance when the userId changes
            instance = null;  //SHOULD CALL CONSTRUCTOR HERE TO MAKE A NEW INSTANCE FOR NEW ID 
        }
        if (instance == null) {
            instance = new GroupContentManager(groupId);
        }
        return instance;
    }

    public ArrayList<GroupPost> getPosts() {
            loadGroupPosts();
        return new ArrayList<>(posts); // return a copy to protect internal data
    }

    private void loadGroupPosts() {
        
            this.posts = new ArrayList<>();
            ArrayList<GroupPost> allPosts = GroupPostsFileManager.getInstance().getPosts();
           // System.out.println("POSTS FROM load" + allPosts);
            for (GroupPost post : allPosts) {
                if (post.getGroupId().equals(groupId)) {
                    this.posts.add(post);
                }
            }
                        System.out.println("POSTS FROM MANAGER" + posts);
    }

    // Add a post to the group's posts
    public void addPost(GroupPost post) {
        if (post.getGroupId().equals(groupId)){
            if (posts == null) {
                posts = new ArrayList<>();
            }
            posts.add(post); //Adds new Post to ArrayList of User's Posts
            
            GroupPostsFileManager.getInstance().getPosts().add(post); //Adds new Post to ArrayList of AllPosts
            for(GroupPost var:GroupPostsFileManager.getInstance().getPosts())
            {
                System.out.println("IDDD ADD POST:"+var.getGroupId());
            }
            GroupPostsFileManager.getInstance().saveToFile(GroupPostsFileManager.getInstance().getPosts()); //Save to File

            GroupPostsFileManager.getInstance().getPosts().add(post); //Adds new Post to ArrayList of AllPosts
            for(GroupPost var:GroupPostsFileManager.getInstance().getPosts())
            {
                System.out.println("IDDD ADD POST:"+var.getGroupId());
            }
            System.out.println("Post added: " + post.getContentTxt());
        } else {
            System.out.println("Cannot add post: Group ID mismatch.");
        }
    }

    // delete a post to the group's posts
    public void deletePost(GroupPost post) {
        if (post.getGroupId().equals(groupId)) {
            
            posts.remove(post); //removes new Post to ArrayList of User's Posts
            GroupPostsFileManager.getInstance().getPosts().remove(post); //Adds new Post to ArrayList of AllPosts
            GroupPostsFileManager.getInstance().saveToFile(GroupPostsFileManager.getInstance().getPosts()); //Save to File

            System.out.println("Post added: " + post.getContentTxt());
        } else {
            System.out.println("Cannot add post: Group ID mismatch.");
        }
    }
}
