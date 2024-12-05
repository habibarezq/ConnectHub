package com.mycompany.connecthub;

import Backend.User;
import Backend.UserFileManager;
import Backend.UserManager;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class ConnectHub {

    public static void main(String[] args) {
      ArrayList<User> users = new ArrayList<>();
      //TEST SAVE USERS' DATA in json FORMAT
      UserFileManager m=UserFileManager.getInstance();
      UserManager u=new UserManager(m.getUsers());
      User u1=u.signup("c@.com","janaEmad",LocalDate.of(2004, Month.MARCH, 3),"27120000");
      User u2=u.signup("d@.com","habibaelghazouly",LocalDate.of(2003, Month.AUGUST, 19),"12345678");
    
      
    u1.sendRequest((User) u2);
    u2.declineRequest((User) u1);
    System.out.println("Request :"+u2.getFriendRequests().get(u1));
    

     //TEST READ USERS' DATA from json FORMAT
//    users = UserFileManager.readUsers();
//    for (User user : users) {
//            System.out.println("User ID: " + user.getUserID());
//            System.out.println("Username: " + user.getUsername());
//            System.out.println("Email: " + user.getEmail());
//            System.out.println("Date of Birth: " + user.getDateOfBirth());
//            System.out.println("Password: " + user.getPassword());
//            System.out.println("---------------------------");
//        }

    }
}
