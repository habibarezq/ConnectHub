/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import javax.swing.ImageIcon;

/**
 *
 * @author Habiba Elghazouly
 */
public interface PostsActions {
    
    //method to be used to uplade posts from a specific arrayList in frontend
      public void uploadPostsFunction();
      
      //a method to resize the image content in a post
      public ImageIcon resizeImagePosts(String contentPath) ;
     
}
