package Backend;


import Backend.FileManagers.*;
import Backend.GroupManagement.Group;
import Backend.GroupManagement.MembershipManager;
import java.util.ArrayList;

public class ContentManager {

    private final String userId;
    private static ContentManager instance;
    private ArrayList<Post> posts; // Changed to lazy-loadable
    private ArrayList<Story> stories; // Changed to lazy-loadable
    private ArrayList<Group> groups;
    
    private boolean postsLoaded = false; // Tracks if posts are loaded
    private boolean storiesLoaded = false;
    private boolean groupsLoaded=false;
    

    // Private constructor to avoid instantiation
    private ContentManager(String userId) {
        this.userId = userId;
        this.posts = null;
        this.stories = null;

    }

    public static synchronized ContentManager getInstance(String userId) {
        if (instance != null && !instance.userId.equals(userId)) {
            // Clear the existing instance when the userId changes
            instance = null;  //SHOULD CALL CONSTRUCTOR HERE TO MAKE A NEW INSTANCE FOR NEW ID 
        }
        if (instance == null) {
            instance = new ContentManager(userId);
        }
        return instance;
    }

    

    public ArrayList<Post> getPosts() {
        if (!postsLoaded) {
            loadUserPosts();
        }
        return new ArrayList<>(posts); // return a copy to protect internal data
    }

    public ArrayList<Story> getStories() {
        if (!storiesLoaded) {
            loadUserStories();
        }
        return new ArrayList<>(stories);
    }

    public ArrayList<Group> getGroups() {
        if (!groupsLoaded) {
            loadUserStories();
        }
        return new ArrayList<>(groups);
    }
    
    private void loadUserPosts() {
        if (!postsLoaded) { // Ensure loading happens only once
            this.posts = new ArrayList<>();
            ArrayList<Post> allPosts = PostsFileManager.getInstance().getPosts();
            for (Post post : allPosts) {
                if (post.getAuthorId().equals(userId)) {
                    this.posts.add(post);
                }
            }
            postsLoaded = true;
        }
    }

    private void loadUserStories() {
        if (!storiesLoaded) {
            this.stories = new ArrayList<>();
            ArrayList<Story> allStories = StoriesFileManager.getInstance().getStories();
            for (Story story : allStories) {
                if (story.getAuthorId().equals(userId)) {
                    this.stories.add(story);
                }
            }
            storiesLoaded = true;
        }
    }
    
    private void loadUserGroups() {
        if (!groupsLoaded) {
            this.groups = new ArrayList<>();
            ArrayList<Group> allGroups=GroupsFileManager.getInstance().getGroups();
            for (Group group : allGroups) {
                if (group.getCreatorId().equals(userId) || group.isAdmin(userId) || group.isMember(userId)) {
                    this.groups.add(group);
                }
            }
            storiesLoaded = true;
        }
    }

    // Observer pattern
    public void refreshPosts() {
        this.posts = null;
        this.postsLoaded = false;
        //SHOULD RELOAD THE POSTS AND STORIES
        System.out.println("Posts refreshed.");
    }

    public void refreshStories() {
        this.stories = null;
        this.storiesLoaded = false;
        System.out.println("Stories refreshed.");
    }

    public void refreshAllContent() {
        refreshPosts();
        refreshStories();
        System.out.println("All content refreshed.");
    }

    // Add a post to the user's posts
    public void addPost(Post post) {
        if (post.getAuthorId().equals(userId)) {
            if (posts == null) {
                posts = new ArrayList<>();
            }
            posts.add(post); //Adds new Post to ArrayList of User's Posts
            PostsFileManager.getInstance().getPosts().add(post); //Adds new Post to ArrayList of AllPosts
            PostsFileManager.getInstance().saveToFile(PostsFileManager.getInstance().getPosts()); //Save to File

            System.out.println("Post added: " + post.getContentTxt());
        } else {
            System.out.println("Cannot add post: Author ID mismatch.");
        }
    }

    // Add a story to the user's stories
    public void addStory(Story story) {
        if (story.getAuthorId().equals(userId)) {
            if (stories == null) {
                stories = new ArrayList<>();
            }
            stories.add(story);
            StoriesFileManager.getInstance().getStories().add(story);
            StoriesFileManager.getInstance().saveToFile(StoriesFileManager.getInstance().getStories());

            System.out.println("Story added: " + story.getContentTxt());
        } else {
            System.out.println("Cannot add story: Author ID mismatch.");
        }
    }
}
