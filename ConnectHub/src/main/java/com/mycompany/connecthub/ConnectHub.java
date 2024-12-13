package com.mycompany.connecthub;

import Backend.*;
import Backend.FileManagers.*;
import Backend.GroupManagement.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class ConnectHub {

    public static void main(String[] args) {
         // Clear the file for a fresh start
        clearFile();

        // Initialize GroupContentManager for group1
        String groupId1 = "group1";
        GroupContentManager groupContentManager1 = GroupContentManager.getInstance(groupId1);

        // Add new posts to group1
        GroupPost post1 = new GroupPost(groupId1, "user1", "This is a test post 1 for group1", "path/to/image1.jpg", LocalDateTime.now());
        GroupPost post2 = new GroupPost(groupId1, "user2", "This is a test post 2 for group1", "path/to/image2.jpg", LocalDateTime.now());

        System.out.println("Adding posts to group1...");
        groupContentManager1.addPost(post1);
        groupContentManager1.addPost(post2);

        // Retrieve and print the posts for group1
        System.out.println("Retrieving posts for group1...");
        ArrayList<GroupPost> postsGroup1 = groupContentManager1.getPosts();
        printPosts(postsGroup1);

        // Initialize GroupContentManager for group2
        String groupId2 = "group2";
        GroupContentManager groupContentManager2 = GroupContentManager.getInstance(groupId2);

        // Add new posts to group2
        GroupPost post3 = new GroupPost(groupId2, "user3", "This is a test post 1 for group2", "path/to/image3.jpg", LocalDateTime.now());
        GroupPost post4 = new GroupPost(groupId2, "user4", "This is a test post 2 for group2", "path/to/image4.jpg", LocalDateTime.now());

        System.out.println("Adding posts to group2...");
        groupContentManager2.addPost(post3);
        groupContentManager2.addPost(post4);

        // Retrieve and print the posts for group2
        System.out.println("Retrieving posts for group2...");
        ArrayList<GroupPost> postsGroup2 = groupContentManager2.getPosts();
        printPosts(postsGroup2);

        // Reinitialize the manager to test persistence
        GroupContentManager newGroupContentManager1 = GroupContentManager.getInstance(groupId1);
        System.out.println("Retrieving posts for group1 after reinitializing...");
        ArrayList<GroupPost> newPostsGroup1 = newGroupContentManager1.getPosts();
        printPosts(newPostsGroup1);

        GroupContentManager newGroupContentManager2 = GroupContentManager.getInstance(groupId2);
        System.out.println("Retrieving posts for group2 after reinitializing...");
        ArrayList<GroupPost> newPostsGroup2 = newGroupContentManager2.getPosts();
        printPosts(newPostsGroup2);
    }

    private static void printPosts(ArrayList<GroupPost> posts) {
        System.out.println("Current posts:");
        for (GroupPost post : posts) {
            System.out.println("GroupId: " + post.getGroupId() + 
                               ", UserId: " + post.getAuthorId() + 
                               ", TextContent: " + post.getContentTxt() + 
                               ", ImagePath: " + post.getcontentPath() + 
                               ", Time: " + post.getUploadingTime());
        }
    }

    private static void clearFile() {
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get("group_posts.json"), new byte[0]);
            System.out.println("Cleared the posts file for a fresh start.");
        } catch (IOException e) {
            System.out.println("Error clearing the file: " + e.getMessage());
        }
    }
}
