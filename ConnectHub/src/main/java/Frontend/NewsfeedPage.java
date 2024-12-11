package Frontend;

import Backend.FileManagers.StoriesFileManager;
import Backend.FileManagers.PostsFileManager;
import Backend.FileManagers.FriendsFileManager;
import Backend.FileManagers.UserFileManager;
import java.awt.*;
import java.io.File;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import Backend.*;
import java.time.format.DateTimeFormatter;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class NewsfeedPage extends javax.swing.JFrame {

    private DefaultListModel<String> friendsModel;
    private DefaultListModel<String> suggestedFriendsModel;
    private ArrayList<Post> posts;
    private ArrayList<Story> stories;
    private ArrayList<User> users;
    private String userId;
    private ConnectHubMain connectHub;
    private User user;

    public NewsfeedPage(User user, ConnectHubMain connectHub) {
        initComponents();
        setTitle("Newsfeed");
        setSize(1300, 700);
        setContentPane(mainPanel);
        this.connectHub = connectHub;
        this.dispose();
        this.user = user;

        this.userId = user.getUserID();
        FriendsFileManager.getInstance(userId);
        this.posts = PostsFileManager.getInstance().getPosts();

        this.users = UserFileManager.getInstance().getUsers();
        //addUsers();

        this.stories = StoriesFileManager.getInstance().getStories();
        //fillstories();

        friendsModel = new DefaultListModel<>();
        suggestedFriendsModel = new DefaultListModel<>();

        friendsList.setModel(friendsModel);
        suggestedFriendsList.setModel(suggestedFriendsModel);
        friendsList.setVisibleRowCount(5);

        populateFriends();
        populateSuggestedFriends();
        populatePosts();
        populateStories();

        ContentManager.getInstance(userId).refreshPosts();

    }

    private void populateSuggestedFriends() {

        suggestedFriendsModel.removeAllElements();
        ArrayList<User> allUsers = UserFileManager.getInstance().getUsers();
        ArrayList<User> suggestedFriends = user.suggestFriends(allUsers);
        for (User suggestedFriend : suggestedFriends) {
            suggestedFriendsModel.addElement(suggestedFriend.getUsername());
        }
    }

    private void populateFriends() {
        friendsModel.removeAllElements();
        ArrayList<User> friends = UserFileManager.getInstance().findUserByID(userId).getFriends();
        if (friends == null) {
            friendsModel.addElement("No Friends");
        } else {
            for (User friend : friends) {
                String status;
                if (friend.getStatus()) {
                    status = "online";
                } else {
                    status = "offline";
                }
                String friendInfo = friend.getUsername() + " (" + status + ") ";
                friendsModel.addElement(friendInfo);
            }
        }

    }

    private void populatePosts() {
        // uploading all posts in the posts ArrayList
        new UploadPosts(postsScrollPane, posts);
    }

    private ImageIcon resizeImageStories(String imagePath) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    private void populateStories() {
        JPanel storyPanel = new JPanel();
        storyPanel.setLayout(new BoxLayout(storyPanel, BoxLayout.X_AXIS));
        storyPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        if (stories == null) {
           return;
        }
        for (Story story : stories) {
            if (checkExpiryStory(story)) {
                //creating a panel for each story
                JPanel singleStoryPanel = new JPanel();
                singleStoryPanel.setLayout(new BorderLayout());
                singleStoryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                singleStoryPanel.setPreferredSize(new Dimension(150, 200));

                //adding username
                User u = UserFileManager.getInstance().findUserByID(story.getAuthorId()); //returns user to get username
                JLabel UsernameLabel = new JLabel("Username: " + u.getUsername());
                UsernameLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                storyPanel.add(UsernameLabel, BorderLayout.NORTH);

                // adding the time Stamp
                JLabel timestampLabel = new JLabel("Time: " + story.getUploadingTime());
                timestampLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Adding padding
                storyPanel.add(timestampLabel, BorderLayout.NORTH);

                //adding content
                // adding the image 
                File imageFile = new File(story.getcontentPath());
                if (imageFile.exists()) {
                    ImageIcon imageIcon = resizeImageStories(story.getcontentPath());

                    JLabel imageLabel = new JLabel(imageIcon);
                    storyPanel.add(imageLabel, BorderLayout.WEST);
                } else {
                    JLabel noImageLabel = new JLabel("No image.");
                    storyPanel.add(noImageLabel, BorderLayout.WEST);
                }
                storyPanel.add(singleStoryPanel);
                storyPanel.add(Box.createRigidArea(new Dimension(0, 1))); // Adding spacing between stories
            }
        }
        storiesScrollPane.setViewportView(storyPanel);
    }

    private boolean checkExpiryStory(Story story) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = story.getUploadingTime();

        LocalDateTime expiryTime = time.plusDays(1);
        if (LocalDateTime.now().isAfter(expiryTime)) {
            //this.stories.remove(story);
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        postsScrollPane = new javax.swing.JScrollPane();
        storiesScrollPane = new javax.swing.JScrollPane();
        jPanelFriends = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        friendsList = new javax.swing.JList<>();
        jPanelSuggestedFriends = new javax.swing.JPanel();
        jScrollPaneSugFriends = new javax.swing.JScrollPane();
        suggestedFriendsList = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        jPanelRequests = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        requestsList = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        profileButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        newRefreshButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        addStoryButton = new javax.swing.JButton();
        addPostButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setPreferredSize(new java.awt.Dimension(1168, 1000));

        jLabel1.setText("Friends:");

        jLabel2.setText("Suggested Friends:");

        friendsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        friendsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                friendsListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(friendsList);

        javax.swing.GroupLayout jPanelFriendsLayout = new javax.swing.GroupLayout(jPanelFriends);
        jPanelFriends.setLayout(jPanelFriendsLayout);
        jPanelFriendsLayout.setHorizontalGroup(
            jPanelFriendsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFriendsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelFriendsLayout.setVerticalGroup(
            jPanelFriendsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFriendsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        suggestedFriendsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        suggestedFriendsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                suggestedFriendsListValueChanged(evt);
            }
        });
        jScrollPaneSugFriends.setViewportView(suggestedFriendsList);

        javax.swing.GroupLayout jPanelSuggestedFriendsLayout = new javax.swing.GroupLayout(jPanelSuggestedFriends);
        jPanelSuggestedFriends.setLayout(jPanelSuggestedFriendsLayout);
        jPanelSuggestedFriendsLayout.setHorizontalGroup(
            jPanelSuggestedFriendsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuggestedFriendsLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jScrollPaneSugFriends, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelSuggestedFriendsLayout.setVerticalGroup(
            jPanelSuggestedFriendsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuggestedFriendsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneSugFriends, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Requests:");

        requestsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(requestsList);

        javax.swing.GroupLayout jPanelRequestsLayout = new javax.swing.GroupLayout(jPanelRequests);
        jPanelRequests.setLayout(jPanelRequestsLayout);
        jPanelRequestsLayout.setHorizontalGroup(
            jPanelRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRequestsLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelRequestsLayout.setVerticalGroup(
            jPanelRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
        );

        profileButton.setText("My Profile");
        profileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileButtonActionPerformed(evt);
            }
        });

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        newRefreshButton.setText("Refresh");
        newRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(profileButton, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(logoutButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newRefreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profileButton)
                .addGap(18, 18, 18)
                .addComponent(logoutButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newRefreshButton)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        addStoryButton.setText("Add Story");
        addStoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStoryButtonActionPerformed(evt);
            }
        });

        addPostButton.setText("Add Post");
        addPostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPostButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addPostButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addStoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(addStoryButton)
                .addGap(30, 30, 30)
                .addComponent(addPostButton)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(storiesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
                    .addComponent(postsScrollPane))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jPanelSuggestedFriends, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanelRequests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(138, 138, 138))
                        .addComponent(jPanelFriends, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelFriends, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(storiesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelSuggestedFriends, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelRequests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(postsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1156, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void profileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileButtonActionPerformed
        //goes to profile frame 
        new Profile(UserFileManager.getInstance().findUserByID(userId), this).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_profileButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        this.dispose();
        new ConnectHubMain().setVisible(true);

    }//GEN-LAST:event_logoutButtonActionPerformed

    private void addStoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStoryButtonActionPerformed
        // add new story to newsfeed and arraylist of stories

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File file) {
                String filename = file.getName().toLowerCase();
                return file.isDirectory() || filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png") || filename.endsWith(".gif");
            }

            @Override
            public String getDescription() {
                return "Image Files (*.jpg, *.jpeg, *.png, *.gif)";
            }
        });

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            Story story = new Story(userId, "text", selectedFile.getAbsolutePath(), LocalDateTime.now());
            ContentManager.getInstance(userId).addStory(story);

            refresh();
        }

    }//GEN-LAST:event_addStoryButtonActionPerformed

    private void addPostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPostButtonActionPerformed

        new postCreation(this.userId, this).setVisible(true);
        this.dispose();

// add new post to newsfeed and arraylist of posts
//        String choice = JOptionPane.showInputDialog(null, "Choose Text or Image:");
//        if (choice.isEmpty())
//            JOptionPane.showMessageDialog(null, "Empty Field.", "Error", JOptionPane.ERROR_MESSAGE);
//        else if (!choice.equalsIgnoreCase("text") && !choice.equalsIgnoreCase("image"))
//            JOptionPane.showMessageDialog(null, "Invalid Answer.", "Error", JOptionPane.ERROR_MESSAGE);
//        else {
//            if (choice.equalsIgnoreCase("image")) {
//                JFileChooser fileChooser = new JFileChooser();
//                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//                int returnValue = fileChooser.showOpenDialog(this);
//                if (returnValue == JFileChooser.APPROVE_OPTION) {
//                    File selectedFile = fileChooser.getSelectedFile();
//                    posts.add(new Post(userId, "text", selectedFile.getAbsolutePath(), LocalDateTime.now())); //fix content id
//                } else {
//                    String text = JOptionPane.showInputDialog(null, "Enter Text:");
//                    posts.add(new Post(userId, text, null, LocalDateTime.now()));
//                    //!!!!!!!!!!SAVE TO FILE
//                }
//                refresh();
//            }

    }//GEN-LAST:event_addPostButtonActionPerformed

    private void newRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newRefreshButtonActionPerformed
        //refreshing feed
        refresh();
    }//GEN-LAST:event_newRefreshButtonActionPerformed

    private void requestsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestsComboBoxActionPerformed
    }

    private void handleFriendRequest(String senderUsername, User loggedInUser) {
        User sender = UserFileManager.getInstance().findUserByUsername(senderUsername);
        if (sender == null) {
            JOptionPane.showMessageDialog(null, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] options = {"Accept", "Decline"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Do you want to accept or decline the friend request from " + sender.getUsername() + "?",
                "Friend Request",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        ArrayList<Request> requests = loggedInUser.getFriendRequests();

        if (choice == 0) { // Accept
            loggedInUser.acceptRequest(sender);
            JOptionPane.showMessageDialog(null, "Friend request accepted.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else if (choice == 1) { // Decline
            loggedInUser.declineRequest(sender);
            JOptionPane.showMessageDialog(null, "Friend request declined.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        refresh();
    }
//GEN-LAST:event_requestsComboBoxActionPerformed
    private void sendFriendRequest(String recipientUsername, User loggedInUser) {
        //loggedInUser is the sender
        User recipient = UserFileManager.getInstance().findUserByUsername(recipientUsername);
        if (recipient == null) {
            JOptionPane.showMessageDialog(null, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] options = {"Send Request", "Cancel"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "<html>Do you want to send a Friend Request to <b>" + recipient.getUsername() + "</b>?</html>",
                "Friend Request",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        ArrayList<Request> requests = loggedInUser.getFriendRequests();

        if (choice == 0) { // Send
            loggedInUser.sendRequest(recipient);
            JOptionPane.showMessageDialog(null, "Friend request sent.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else if (choice == 1) { // Cancel

            JOptionPane.showMessageDialog(null, "Friend request not Sent.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        refresh();
    }
    private void friendsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_friendsListValueChanged
        String SelectedFruit = (String) friendsList.getSelectedValue();
        //System.out.println("Selected String "+SelectedFruit);
    }//GEN-LAST:event_friendsListValueChanged

    private void suggestedFriendsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_suggestedFriendsListValueChanged
        String selectedUsername = (String) suggestedFriendsList.getSelectedValue();
        sendFriendRequest(selectedUsername, user);

    }//GEN-LAST:event_suggestedFriendsListValueChanged
    public void refresh() {
        populateStories();
        populatePosts();
        populateFriends();
        populateSuggestedFriends();

    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewsfeedPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewsfeedPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewsfeedPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewsfeedPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            //new NewsfeedPage().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPostButton;
    private javax.swing.JButton addStoryButton;
    private javax.swing.JList<String> friendsList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelFriends;
    private javax.swing.JPanel jPanelRequests;
    private javax.swing.JPanel jPanelSuggestedFriends;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPaneSugFriends;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton newRefreshButton;
    private javax.swing.JScrollPane postsScrollPane;
    private javax.swing.JButton profileButton;
    private javax.swing.JList<String> requestsList;
    private javax.swing.JScrollPane storiesScrollPane;
    private javax.swing.JList<String> suggestedFriendsList;
    // End of variables declaration//GEN-END:variables
}
