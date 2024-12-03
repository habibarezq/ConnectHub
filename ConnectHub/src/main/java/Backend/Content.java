package Backend;

import java.awt.Image;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Content {
    protected String contentId;
    protected String authorId;
    protected String contentText;
    protected Image ContentImage;
    protected LocalDate uploadingTime;
    //protected User user;
    protected ArrayList<Post> posts;  //posts of user
    protected ArrayList<Story> stories; //stories of user

    public Content(String contentId, String authorId, String contentText, Image ContentImage, LocalDate uploadingTime) {
        this.contentId = contentId;
        this.authorId = authorId;
        this.contentText = contentText;
        this.ContentImage = ContentImage;
        this.uploadingTime = uploadingTime;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<Story> getStories() {
        return stories;
    }

    public void setStories(ArrayList<Story> stories) {
        this.stories = stories;
    }

    
    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public Image getContentImage() {
        return ContentImage;
    }

    public void setContentImage(Image ContentImage) {
        this.ContentImage = ContentImage;
    }

    public LocalDate getUploadingTime() {
        return uploadingTime;
    }

    public void setUploadingTime(LocalDate uploadingTime) {
        this.uploadingTime = uploadingTime;
    }
    
    public void addPost(Post p)
    {
        posts.add(p);
    }
    
    public void addStory(Story s)
    {
        stories.add(s);
        //manage time
    }
    
}
