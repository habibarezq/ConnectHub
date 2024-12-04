package com.mycompany.connecthub;

import Backend.User;
import Backend.UserFileManager;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class ConnectHub {

    public static void main(String[] args) {
      ArrayList<User> users = new ArrayList<>();
      //TEST SAVE USERS' DATA in json FORMAT
    User u1=new User("T4","b@.com","habibarezq",LocalDate.of(2004, Month.JUNE, 30),"27120000");
    User u2=new User("T5","a@.com","shiraznasser",LocalDate.of(2003, Month.NOVEMBER, 23),"12345678");
    users.add(u1);
    users.add(u2);
    
    u1.sendRequest((User) u2);
    //u2.declineRequest((User) u1);
    System.out.println("Request :"+u2.getFriendRequests().get(u1));
//    UserFileManager.saveUsers(users);


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
