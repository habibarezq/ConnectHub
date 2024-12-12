package Backend;

public class Notification {
    private String notificationID;
    private String userID;
    private String timestamp;
    private String type;
    private String message;
    private boolean status;

    public Notification(String notificationID, String userID, String timestamp, String type, String message, boolean status) {
        this.notificationID = notificationID;
        this.userID = userID;
        this.timestamp = timestamp;
        this.type = type;
        this.message = message;
        this.status = status;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
