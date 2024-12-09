package Backend;

import Interfaces.ContentCreation;
import java.awt.Image;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Content implements ContentCreation{
    protected String contentId;
    protected String authorId;
    protected String contentTxt;
    protected String contentPath;
    protected LocalDateTime uploadingTime;
    //protected User user;

    public Content(String authorId, String contentTxt, String contentPath, LocalDateTime uploadingTime) {
        this.contentId=UUID.randomUUID().toString();
        this.authorId = authorId;
        this.contentTxt = contentTxt;
        this.contentPath = contentPath;
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

    public String getContentTxt() {
        return contentTxt;
    }

    public void setContentTxt(String contentTxt) {
        this.contentTxt = contentTxt;
    }

    public String getcontentPath() {
        return contentPath;
    }

    public void setcontentPath(String contentPng) {
        this.contentPath = contentPng;
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
