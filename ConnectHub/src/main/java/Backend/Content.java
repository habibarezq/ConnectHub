package Backend;

import java.awt.Image;
import java.time.LocalDate;

public abstract class Content {
    protected String contentId;
    protected String authorId;
    protected String content;
    protected LocalDate uploadingTime;
    //protected User user;

    public Content(String contentId, String authorId, String content, LocalDate uploadingTime) {
        this.contentId = contentId;
        this.authorId = authorId;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContentImage(String content) {
        this.content = content;
    }

    public LocalDate getUploadingTime() {
        return uploadingTime;
    }

    public void setUploadingTime(LocalDate uploadingTime) {
        this.uploadingTime = uploadingTime;
    }

}
