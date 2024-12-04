package Backend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    public static String hashPassword(String password){
        return encoder.encode(password);
    }
    
    public static boolean verifyPassword(String password,String encodedPassword){
        return encoder.matches(password, encodedPassword);
    }
}
