package Backend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class NotificationManager {

    ArrayList<Notification> notifications;
    ArrayList<Notification> unreadNotifications;

    public Notification createNotification(String userID, String type) {
        String message;
        switch (type) {
            case "request":
                message = "Someone sent you a friend request!";
                break;
            case "groupAdd":
                message = "You were added to a group!";
                break;
            case "groupNewPost":
                message = "A new post was added to a group you are in!";
                break;
            case "groupStatusUpdate":
                message = "Your status in a group has been changed!";
                break;
            case "newPosts":
                message = "You have new posts!";
                break;
            default:
                message = null;
        }
        Notification notif = new Notification(userID, type, message);
        notif.setNotificationID(UUID.randomUUID().toString());
        notif.setTimestamp(LocalDate.now().toString());
        notif.setStatus(false);
        notifications.add(notif);
        return notif;
    }

    public ArrayList<Notification> getAllNotifications() {
        return notifications;
    }

    public void setAllNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<Notification> fetchNewNotifications() {
        unreadNotifications = null;
        for (Notification n : notifications) {
            if (!n.getStatus()) {
                unreadNotifications.add(n);
            }
        }
        return unreadNotifications;
    }
    
    public ArrayList<Notification> updateStatusAfterReading(){
        ArrayList<Notification> x = fetchNewNotifications();
        for (Notification n : x) {
            if (!n.getStatus()) {
                n.setStatus(true);
                unreadNotifications.remove(n);
                notifications.add(n);
            }
        }
        return notifications;
    }

}
