package Backend;

import java.util.ArrayList;

public class ContentManager {
    private static ContentManager instance;
    private ArrayList<Post> posts; // Changed to lazy-loadable
    private ArrayList<Story> stories; // Changed to lazy-loadable
    private final String userId;

    private boolean postsLoaded = false; // Tracks if posts are loaded
    private boolean storiesLoaded = false;

    // Private constructor to avoid instantiation
    private ContentManager(String userId) {
        this.userId = userId;
        this.posts = null; 
        this.stories = null; 
    }

    public static synchronized ContentManager getInstance(String userId) {
    if (instance != null && !instance.userId.equals(userId)) {
        // Clear the existing instance when the userId changes
        instance = null; 
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

    // Observer pattern
    public void refreshPosts() {
        this.posts = null; 
        this.postsLoaded = false; 
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
            posts.add(post);
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
            System.out.println("Story added: " + story.getContentTxt());
        } else {
            System.out.println("Cannot add story: Author ID mismatch.");
        }
    }
}
