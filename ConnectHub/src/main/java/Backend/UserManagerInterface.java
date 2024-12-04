package Backend;

import java.time.LocalDate;

public interface UserManagerInterface {

    public void signup(String email, String username, String password, LocalDate dateOfBirth);
    public void login(String email,String password);
}
