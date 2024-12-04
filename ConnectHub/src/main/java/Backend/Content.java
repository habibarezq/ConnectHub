package Backend;

import Interfaces.ContentCreation;
import java.time.LocalDateTime;

public abstract class Content implements ContentCreation{
    protected String contentId;
    protected String authorId;
    protected String content;
    protected LocalDateTime uploadingTime;
    //protected User user;

    public Content(String contentId, String authorId, String content, LocalDateTime uploadingTime) {
        this.contentId = contentId;
        this.authorId = authorId;
        this.content = content;
        this.uploadingTime = uploadingTime;
    }

    @Override
    public String getContentId() {
        return contentId;
    }

    @Override
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    @Override
    public String getAuthorId() {
        return authorId;
    }

    @Override
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public LocalDateTime getUploadingTime() {
        return uploadingTime;
    }

    @Override
    public void setUploadingTime(LocalDateTime uploadingTime) {
        this.uploadingTime = uploadingTime;
    }

}
