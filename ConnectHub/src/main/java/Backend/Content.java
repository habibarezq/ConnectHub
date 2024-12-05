package Backend;

import Interfaces.ContentCreation;
import java.awt.Image;
import java.time.LocalDateTime;

public abstract class Content implements ContentCreation{
    protected String contentId;
    protected String authorId;
    protected String contentTxt;
    protected Image contentPng;
    protected LocalDateTime uploadingTime;
    //protected User user;

    public Content(String contentId, String authorId, String contentTxt, Image contentPng) {
        this.contentId = contentId;
        this.authorId = authorId;
        this.contentTxt = contentTxt;
        this.contentPng = contentPng;
        this.uploadingTime = LocalDateTime.now();
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

    public Image getContentPng() {
        return contentPng;
    }

    public void setContentPng(Image contentPng) {
        this.contentPng = contentPng;
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
