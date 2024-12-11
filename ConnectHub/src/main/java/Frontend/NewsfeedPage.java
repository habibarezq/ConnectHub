package Frontend;

import Backend.Post;
import Backend.Story;
import Backend.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Backend.*;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class NewsfeedPage extends javax.swing.JFrame {

    private DefaultListModel<String> friendsModel;
    private DefaultListModel<String> suggestedFriendsModel;
    private DefaultListModel<String> searchModel;
    private ArrayList<Post> posts;
    private ArrayList<Story> stories;
    private ArrayList<User> users;
    private ArrayList<Group> groups;
    private String userId;
    private ConnectHubMain connectHub;

    public NewsfeedPage(User user, ConnectHubMain connectHub) {
        initComponents();
        setTitle("Newsfeed");
        setSize(1300, 627);
        setContentPane(mainPanel);
        this.connectHub = connectHub;
        this.dispose();

        this.userId = user.getUserID();
        FriendsFileManager.getInstance(userId);
        this.posts = PostsFileManager.getInstance().getPosts();

        this.users = UserFileManager.getInstance().getUsers();
        //addUsers();

        this.stories = StoriesFileManager.getInstance().getStories();
        //fillstories();

        searchModel = new DefaultListModel<>();
        friendsModel = new DefaultListModel<>();
        suggestedFriendsModel = new DefaultListModel<>();

        searchList.setModel(searchModel);
        friendsList.setModel(friendsModel);
        suggestedFriendsList.setModel(suggestedFriendsModel);

        populateFriends();
        populateSuggestedFriends();
        populatePosts();
        populateStories();
        ActionEvent evt = null;

        requestsComboBoxActionPerformed(evt);
    }

    public void populateSuggestedFriends() {
        User user = UserFileManager.getInstance().findUserByID(userId);
        ArrayList<User> allUsers = UserFileManager.getInstance().getUsers();
        ArrayList<User> suggestedFriends = user.suggestFriends(allUsers);
        for (User suggestedFriend : suggestedFriends) {
            suggestedFriendsModel.addElement(suggestedFriend.getUsername());
        }
    }

    public void populateFriends() {
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
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        for (Post post : posts) {
            //creating a panel for each post
            JPanel everyPostPanel = new JPanel();
            everyPostPanel.setLayout(new BorderLayout());
            everyPostPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            everyPostPanel.setPreferredSize(new Dimension(300, 80));

            //adding username
            User u = UserFileManager.getInstance().findUserByID(post.getAuthorId()); //returns user to get username
            JLabel UsernameLabel = new JLabel("Username: " + u.getUsername());
            UsernameLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            postPanel.add(UsernameLabel, BorderLayout.NORTH);

            // adding the time Stamp
            JLabel timestampLabel = new JLabel("Time: " + post.getUploadingTime());
            timestampLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Adding padding
            postPanel.add(timestampLabel, BorderLayout.SOUTH);

            //adding content
            //adding text
            JLabel contentLabel = new JLabel("Content: " + post.getContentTxt());
            contentLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Adding padding
            postPanel.add(contentLabel, BorderLayout.NORTH);

            // adding the image 
            File imageFile = new File(post.getcontentPath());
            if (imageFile.exists()) {
                ImageIcon imageIcon = resizeImagePosts(post.getcontentPath());
                JLabel imageLabel = new JLabel(imageIcon);
                postPanel.add(imageLabel, BorderLayout.WEST);
            } else {
                JLabel noImageLabel = new JLabel("No image.");
                postPanel.add(noImageLabel, BorderLayout.WEST);
            }

            postPanel.add(everyPostPanel);
            postPanel.add(Box.createRigidArea(new Dimension(0, 1))); // Adding spacing between stories
        }
        postsScrollPane.setViewportView(postPanel);
    }

    private ImageIcon resizeImagePosts(String contentPath) {
        ImageIcon imageIcon = new ImageIcon(contentPath);
        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(postsScrollPane.getWidth() - 10, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
        return resizedImageIcon;
    }

    private ImageIcon resizeImageStories(String contentPath) {
        ImageIcon imageIcon = new ImageIcon(contentPath);
        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(storiesScrollPane.getWidth() - 200, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
        return resizedImageIcon;
    }

    private void populateStories() {
        JPanel storyPanel = new JPanel();
        storyPanel.setLayout(new BoxLayout(storyPanel, BoxLayout.X_AXIS));
        storyPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

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
        jScrollPane1 = new javax.swing.JScrollPane();
        friendsList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        suggestedFriendsList = new javax.swing.JList<>();
        profileButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        addStoryButton = new javax.swing.JButton();
        addPostButton = new javax.swing.JButton();
        postsScrollPane = new javax.swing.JScrollPane();
        storiesScrollPane = new javax.swing.JScrollPane();
        newRefreshButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        requestsComboBox = new javax.swing.JComboBox<>();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        searchList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Friends:");

        jLabel2.setText("Suggested Friends:");

        friendsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(friendsList);

        suggestedFriendsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(suggestedFriendsList);

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

        newRefreshButton.setText("Refresh");
        newRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRefreshButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Requests:");

        requestsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestsComboBoxActionPerformed(evt);
            }
        });

        searchField.setText("Search");

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        searchList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(searchList);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(addStoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(addPostButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(profileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(newRefreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(requestsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(searchButton)
                                        .addGap(0, 33, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(storiesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                    .addComponent(postsScrollPane))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(22, 22, 22))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(storiesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(postsScrollPane)
                        .addContainerGap())
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(113, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(profileButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(logoutButton)
                        .addGap(60, 60, 60)
                        .addComponent(newRefreshButton)
                        .addGap(18, 18, 18)
                        .addComponent(addStoryButton)
                        .addGap(18, 18, 18)
                        .addComponent(addPostButton)
                        .addGap(40, 40, 40)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(requestsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void profileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileButtonActionPerformed
        //goes to profile frame 
        new Profile(UserFileManager.getInstance().findUserByID(userId)).setVisible(true);
    }//GEN-LAST:event_profileButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        this.dispose();
        new ConnectHubMain().setVisible(true);

    }//GEN-LAST:event_logoutButtonActionPerformed

    private void addStoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStoryButtonActionPerformed
        // add new story to newsfeed and arraylist of stories
        String choice = JOptionPane.showInputDialog(null, "Choose Text or Image:");
        if (choice.isEmpty())
            JOptionPane.showMessageDialog(null, "Empty Field.", "Error", JOptionPane.ERROR_MESSAGE);
        else if (!choice.equalsIgnoreCase("text") && !choice.equalsIgnoreCase("image"))
            JOptionPane.showMessageDialog(null, "Invalid Answer.", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            if (choice.equalsIgnoreCase("image")) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int returnValue = fileChooser.showOpenDialog(this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    stories.add(new Story(userId, "text", selectedFile.getAbsolutePath(), LocalDateTime.now())); //fix content id
                } else {
                    String text = JOptionPane.showInputDialog(null, "Enter Text:");

                    stories.add(new Story(userId, text, null, LocalDateTime.now()));
                }
                refresh();
            }
        }
    }//GEN-LAST:event_addStoryButtonActionPerformed

    private void addPostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPostButtonActionPerformed
        // add new post to newsfeed and arraylist of posts
        String choice = JOptionPane.showInputDialog(null, "Choose Text or Image:");
        if (choice.isEmpty())
            JOptionPane.showMessageDialog(null, "Empty Field.", "Error", JOptionPane.ERROR_MESSAGE);
        else if (!choice.equalsIgnoreCase("text") && !choice.equalsIgnoreCase("image"))
            JOptionPane.showMessageDialog(null, "Invalid Answer.", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            if (choice.equalsIgnoreCase("image")) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int returnValue = fileChooser.showOpenDialog(this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    posts.add(new Post(userId, "text", selectedFile.getAbsolutePath(), LocalDateTime.now())); //fix content id
                } else {
                    String text = JOptionPane.showInputDialog(null, "Enter Text:");
                    posts.add(new Post(userId, text, null, LocalDateTime.now()));
                    //!!!!!!!!!!SAVE TO FILE
                }
                refresh();
            }
        }
    }//GEN-LAST:event_addPostButtonActionPerformed

    private void newRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newRefreshButtonActionPerformed
        //refreshing feed
        refresh();
    }//GEN-LAST:event_newRefreshButtonActionPerformed

    private void requestsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestsComboBoxActionPerformed
        User u = UserFileManager.getInstance().findUserByID(userId);
        HashMap<User, String> friendRequests = u.getFriendRequests();

        //to make sure it removes any old requests and re-writes the actually-existing ones
        requestsComboBox.removeAll();

        //adding the elements to the comboBox username(status)
        for (Map.Entry<User, String> entry : friendRequests.entrySet()) {
            User user = entry.getKey();  // The User object (key)
            String request = entry.getValue();  // The request message (value)
            requestsComboBox.addItem(user.getUsername() + " (" + request + ") ");
        }
        requestsComboBox.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                User u = UserFileManager.getInstance().findUserByID(userId);
                HashMap<User, String> friendRequests = u.getFriendRequests();

                String selectedItem = (String) requestsComboBox.getSelectedItem();
                if (selectedItem != null && !selectedItem.isEmpty()) {
                    // Parse the selected item to extract the username
                    String selectedUsername = selectedItem.split(" ")[0]; // Extract username before '('

                    // Create a dialog to show more information
                    Object[] requestAnswer = {"Accept", "Decline"};
                    int answer = JOptionPane.showOptionDialog(null, "Do you want to accept or decline?", "Friend Request", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, requestAnswer, requestAnswer[0]);
                    //no default option and no custom icon 

                    if (answer == 0) {
                        User acceptedFriend = UserFileManager.getInstance().findUserByUsername(selectedUsername);
                        String acceptedFriendId = acceptedFriend.getUserID();
                        friendRequests.put(acceptedFriend, acceptedFriendId);
                        requestsComboBox.removeItem(selectedItem);
                        refresh();
                    } else if (answer == 1) {
                        User declinedFriend = UserFileManager.getInstance().findUserByUsername(selectedUsername);
                        String declinedFriendId = declinedFriend.getUserID();
                        friendRequests.remove(declinedFriend);
                        requestsComboBox.removeItem(selectedItem);
                        refresh();
                    } else {
                        JOptionPane.showMessageDialog(null, "No action taken.");
                    }
                }
            }
        });

    }//GEN-LAST:event_requestsComboBoxActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String searchResult = searchField.getText();
        if (searchResult.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty Field.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            //searching in lists of users and groups
            for (User user : users) {
                if (user.getUsername().toLowerCase().contains(searchResult)) {
                    searchModel.addElement(user.getUsername());
                }
            }
            for (Group group : groups) {
                if (group.getGroupName().toLowerCase().contains(searchResult)) {
                    searchModel.addElement(group.getName());
                }
            }
        }
        if(groups == null && users == null) searchModel.addElement("No search Results.");

        searchList.addListSelectionListener(new ListSelectionListener() {
        @Override
        public void  valueChanged(ListSelectionEvent e) 
        {
            if(!e.getValueIsAdjusting())
            {
                String selectedString = searchList.getSelectedValue();
                User u = searchSelectedItemUser(selectedString);
                Group g = searchSelectedItemGroup(selectedString);
                if(u!= null) new Profile(u);
                else if(g!=null) new groupPage(g);
            }
        }
        });
    }//GEN-LAST:event_searchButtonActionPerformed

    public User searchSelectedItemUser(String selectedString)
    {
      for(User user : users)
          if(user.getUsername().equalsIgnoreCase(selectedString)) return user;
      return null;
    }
    
    public Group searchSelectedItemGroup(String selectedString)
    {
        for(Group group : groups)
            if(group.getGroupName().equalsIgnoreCase(selectedString)) return group;
        return null;
    }
    public void refresh() {
        populateStories();
        populatePosts();
        this.friendsModel.clear();
        this.suggestedFriendsModel.clear();
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton newRefreshButton;
    private javax.swing.JScrollPane postsScrollPane;
    private javax.swing.JButton profileButton;
    private javax.swing.JComboBox<String> requestsComboBox;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JList<String> searchList;
    private javax.swing.JScrollPane storiesScrollPane;
    private javax.swing.JList<String> suggestedFriendsList;
    // End of variables declaration//GEN-END:variables
}
