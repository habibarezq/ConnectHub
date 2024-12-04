package Interfaces;

import java.time.LocalDateTime;

public interface ContentCreation {

    public String getContentId();

    public void setContentId(String contentId);

    public String getAuthorId();

    public void setAuthorId(String authorId);

    public String getContent();

    public void setContent(String content);

    public LocalDateTime getUploadingTime();

    public void setUploadingTime(LocalDateTime uploadingTime);
}
