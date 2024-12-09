package Interfaces;

import java.awt.Image;
import java.time.LocalDateTime;

public interface ContentCreation {

    public String getContentId();

    public void setContentId(String contentId);

    public String getAuthorId();

    public void setAuthorId(String authorId);

    public String getContentTxt();

    public void setContentTxt(String contentTxt);
    
    public String getcontentPath();
    
    public void setcontentPath(String contentPath);

    public LocalDateTime getUploadingTime();

    public void setUploadingTime(LocalDateTime uploadingTime);
}
