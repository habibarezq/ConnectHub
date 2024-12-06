//
//package com.mycompany.connecthub;
//
//import Backend.*;
//import java.awt.Image;
//import java.awt.Toolkit;
//import java.time.LocalDateTime;
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//public class ConnectHub {
//
//    public static void main(String[] args) {
//        // Test UserFileManager first
//        System.out.println("Testing UserFileManager...");
//
//        // Get Users from file
//        UserFileManager userManager = UserFileManager.getInstance();
//        ArrayList<User> users = userManager.getUsers();
//
//        // Print all users
//        for (User user : users) {
//            System.out.println("User ID: " + user.getUserID());
//            System.out.println("Email: " + user.getEmail());
//            System.out.println("Username: " + user.getUsername());
//            System.out.println("Date of Birth: " + user.getDateOfBirth());
//            System.out.println("----");
//        }
//
//        // Adding a new user
//        System.out.println("Adding a new user...");
//        User newUser = new User("user5", "user5@example.com", "userusername", LocalDate.of(1995, 5, 15), "password123");
//        users.add(newUser);
//        userManager.saveToFile(users); // Save users back to file
//
//        // Find user by ID
//        System.out.println("\nTesting findUserByID...");
//        User foundUser = userManager.findUserByID("user4");
//        if (foundUser != null) {
//            System.out.println("User found: " + foundUser.getUsername());
//        } else {
//            System.out.println("User not found.");
//        }
//
//        // Now proceed with testing PostsFileManager and StoriesFileManager
//
//        // Test PostsFileManager
//        System.out.println("Testing PostsFileManager...");
//
//        // Get Posts from file
//        PostsFileManager postsManager = PostsFileManager.getInstance();
//        ArrayList<Post> posts = postsManager.getPosts();
//
//        // Print all posts
//        for (Post post : posts) {
//            System.out.println("Post ID: " + post.getContentId());
//            System.out.println("Author ID: " + post.getAuthorId());
//            System.out.println("Content: " + post.getContentTxt());
//            System.out.println("Time: " + post.getUploadingTime());
//            System.out.println("----");
//        }
//
//        // Adding a new post
//        System.out.println("Adding a new post...");
//        Image postImage = Toolkit.getDefaultToolkit().createImage(new byte[] {});
//        Post newPost = new Post("content4", "user4", "This is a new post", postImage, LocalDateTime.now());
//        posts.add(newPost);
//        Post newPost = new Post("content4", "user4", "This is a new post", postImage, LocalDateTime.now());
//        // posts.add(newPost);
//
//        postsManager.saveToFile(posts); // Save posts back to file
//
//        // Test StoriesFileManager
//        System.out.println("\nTesting StoriesFileManager...");
//
//        // Get Stories from file
//        StoriesFileManager storiesManager = StoriesFileManager.getInstance();
//        ArrayList<Story> stories = storiesManager.getStories();
//
//        // Print all stories
//        for (Story story : stories) {
//            System.out.println("Story ID: " + story.getContentId());
//            System.out.println("Author ID: " + story.getAuthorId());
//            System.out.println("Content: " + story.getContentTxt());
//            System.out.println("Time: " + story.getUploadingTime());
//            System.out.println("----");
//        }
//
//        // Adding a new story
//        System.out.println("Adding a new story...");
//        Image storyImage = Toolkit.getDefaultToolkit().createImage(new byte[] {});
//        Story newStory = new Story("story4", "user4", "This is a new story", storyImage, LocalDateTime.now());
//        stories.add(newStory);
//        storiesManager.saveToFile(stories); // Save stories back to file
//
//        // Test ContentManager
//        System.out.println("\nTesting ContentManager...");
//
//        // Initialize ContentManager with a user ID (e.g., "user4")
//        ContentManager contentManager = ContentManager.getInstance("user4");
//
//        // Test adding posts to ContentManager
//        System.out.println("Adding posts to ContentManager...");
//        contentManager.addPost(newPost);
//        contentManager.addPost(new Post("content5", "user5", "Another post!", postImage, LocalDateTime.now()));
//
//        // Test adding stories to ContentManager
//        System.out.println("Adding stories to ContentManager...");
//        contentManager.addStory(newStory);
//        contentManager.addStory(new Story("story5", "user5", "Another story!", storyImage, LocalDateTime.now()));
//
//        // Displaying posts
//        System.out.println("\nPosts in ContentManager:");
//        ArrayList<Post> contentManagerPosts = contentManager.getPosts();
//        for (Post post : contentManagerPosts) {
//            System.out.println("Post ID: " + post.getContentId());
//            System.out.println("Author ID: " + post.getAuthorId());
//            System.out.println("Content: " + post.getContentTxt());
//            System.out.println("Time: " + post.getUploadingTime());
//            System.out.println("----");
//        }
//
//        // Displaying stories
//        System.out.println("\nStories in ContentManager:");
//        ArrayList<Story> contentManagerStories = contentManager.getStories();
//        for (Story story : contentManagerStories) {
//            System.out.println("Story ID: " + story.getContentId());
//            System.out.println("Author ID: " + story.getAuthorId());
//            System.out.println("Content: " + story.getContentTxt());
//            System.out.println("Time: " + story.getUploadingTime());
//            System.out.println("----");
//        }
//
//        // Testing refresh functionality
//        contentManager.refreshAllContent();
//        System.out.println("Content refreshed successfully.");
//
//        System.out.println("Testing completed!");
//    }
//}
