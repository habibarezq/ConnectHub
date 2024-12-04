package Backend;

public interface UserInterface {
    public void block(User other);
    public void removeFriend(User other);
    public void sendRequest(User other);
    //public void logout();
}
