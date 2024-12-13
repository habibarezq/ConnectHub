package Frontend;

import Backend.UserManagement.User;
import Backend.FriendManagment.FriendRequestManager;
import Backend.FriendManagment.FriendServiceManager;
import Backend.FriendManagment.FriendManagerFactory;
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
import Backend.FileManagers.RequestsFileManager;
import Backend.GroupManagement.Group;
import java.time.format.DateTimeFormatter;

public class NewsfeedPage extends javax.swing.JFrame {

    private DefaultListModel<String> friendsModel;
    private DefaultListModel<String> suggestedFriendsModel;
    private DefaultListModel<String> requestsModel;
    private ArrayList<Post> posts;
    private ArrayList<Story> stories;
    private ArrayList<User> users;
    private ArrayList<UserRequest> requests;
    private ArrayList<Group> groups;
    private ArrayList<User> friends;
    private String userId;
    private ConnectHubMain connectHub;
    
    public User user;

    public NewsfeedPage(User user, ConnectHubMain connectHub) {
        initComponents();
        setTitle("Newsfeed");
        setSize(1300, 700);
        setContentPane(mainPanel);
        this.connectHub = connectHub;
        this.dispose();

        this.user = user;
        this.userId = user.getUserID();

        FriendsFileManager.getInstance();
        RequestsFileManager.getInstance();

        this.posts = PostsFileManager.getInstance().getPosts();
        this.users = UserFileManager.getInstance().getUsers();
        this.friends = FriendManagerFactory.getFriendManager(userId).getFriends();
        this.stories = StoriesFileManager.getInstance().getStories();
        this.requests = RequestManager.getInstance(userId).getRequests();

        friendsModel = new DefaultListModel<>();
        suggestedFriendsModel = new DefaultListModel<>();
        requestsModel = new DefaultListModel<>();

        friendsList.setModel(friendsModel);
        friendsList.setVisibleRowCount(5);

        suggestedFriendsList.setModel(suggestedFriendsModel);

        requestsList.setModel(requestsModel);
        requestsList.setVisibleRowCount(5);
        requestsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        populateFriends();
        populateSuggestedFriends();
        populatePosts();
        populateStories();
        populateRequests();

        ContentManager.getInstance(userId).refreshPosts();

    }

   private void populateSuggestedFriends() {

        suggestedFriendsModel.removeAllElements();

        ArrayList<User> allUsers = UserFileManager.getInstance().getUsers();
        ArrayList<User> suggestedFriends;

        suggestedFriends = null;
        suggestedFriends = FriendServiceManager.getInstance(user).suggestFriends();

        for (User suggestedFriend : suggestedFriends) {
            System.out.println("Suggest: "+suggestedFriend.getUsername());
            suggestedFriendsModel.addElement(suggestedFriend.getUsername());
        }
    } 

    private void populateRequests() {
        requestsModel.removeAllElements();
        requests = null;
        requests = RequestManager.getInstance(userId).getRequests();

        if (requests != null) {
            for (UserRequest request : requests) {
                // Only include pending requests
                if (request.getRequestStat().equals("Pending") && !request.getSender().equals(user)) {
                    requestsModel.addElement(request.getSender().getUsername());
                }
            }
        }
    }

    private void populateFriends() {
        friendsModel.removeAllElements();

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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        myGroupsList = new javax.swing.JList<>();
        bioLabel = new javax.swing.JLabel();
        bioLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        suggestedGroupsList = new javax.swing.JList<>();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setPreferredSize(new java.awt.Dimension(1168, 1000));

        jLabel1.setText("Friends:");

        jLabel2.setText("Suggested Friends:");

        friendsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        friendsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                friendsListMouseClicked(evt);
            }
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
        suggestedFriendsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suggestedFriendsListMouseClicked(evt);
            }
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

        requestsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                requestsListMouseClicked(evt);
            }
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
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addStoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(addPostButton, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(addStoryButton)
                .addGap(18, 18, 18)
                .addComponent(addPostButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        myGroupsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myGroupsListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(myGroupsList);

        bioLabel.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        bioLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bioLabel.setText("My Groups");
        bioLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bioLabel1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        bioLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bioLabel1.setText("Suggested Groups");
        bioLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        suggestedGroupsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suggestedGroupsListMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(suggestedGroupsList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(bioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bioLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bioLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bioLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4)
                .addContainerGap())

        );

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(storiesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
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
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
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
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(postsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton))
                        .addGap(10, 10, 10)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
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
        user.logout();
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
            JOptionPane.showMessageDialog(null, "User not found! HELLOOOOOO", "Error", JOptionPane.ERROR_MESSAGE);
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

        if (choice == 0) { // Accept
            FriendRequestManager.getInstance(userId).acceptRequest(sender);
            requestsModel.removeElement(senderUsername);
            JOptionPane.showMessageDialog(null, "Friend request accepted.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else if (choice == 1) { // Decline
            FriendRequestManager.getInstance(userId).declineRequest(sender);
            JOptionPane.showMessageDialog(null, "Friend request declined.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        refresh();
    }
//GEN-LAST:event_requestsComboBoxActionPerformed
    private void sendFriendRequest(String recipientUsername, User loggedInUser) {

        //loggedInUser is the sender
        User recipient = UserFileManager.getInstance().findUserByUsername(recipientUsername);
        System.out.println("Username Front: " + recipient.getUsername());
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

        if (choice == 0) { // Send
            FriendRequestManager.getInstance(userId).sendRequest(recipient);
            suggestedFriendsModel.removeElement(recipientUsername);
            JOptionPane.showMessageDialog(null, "Friend request sent.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else if (choice == 1) { // Cancel

            JOptionPane.showMessageDialog(null, "Friend request not Sent.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        refresh();
    }

    private void handleFriendAction(String selectedValue, User loggedInUser) {
        String recipientUsername = selectedValue.split(" \\(")[0];
        User recipient = UserFileManager.getInstance().findUserByUsername(recipientUsername);

        if (recipient == null) {
            JOptionPane.showMessageDialog(null, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] options = {"View Profile", "Remove Friend", "Block Friend"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "<html>What do you want to do with <b>" + recipient.getUsername() + "</b>?</html>",
                "Friend Options",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 0) {
            //JOptionPane.showMessageDialog(null, "Viewing " + recipient.getUsername() + "'s profile.", "Profile", JOptionPane.INFORMATION_MESSAGE);

        } else if (choice == 1) {
            FriendServiceManager.getInstance(user).removeFriend(recipientUsername);
            friendsModel.removeElement(selectedValue);
            JOptionPane.showMessageDialog(null, "You have removed " + recipient.getUsername() + " from your friend list.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else if (choice == 2) {
            FriendServiceManager.getInstance(user).blockFriend(recipientUsername);
            JOptionPane.showMessageDialog(null, "You have blocked " + recipient.getUsername() + ".", "Blocked", JOptionPane.INFORMATION_MESSAGE);
        }

        refresh();
    }

    private void friendsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_friendsListValueChanged
        String SelectedFruit = (String) friendsList.getSelectedValue();
        //System.out.println("Selected String "+SelectedFruit);
    }//GEN-LAST:event_friendsListValueChanged

    private void suggestedFriendsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_suggestedFriendsListValueChanged

    }//GEN-LAST:event_suggestedFriendsListValueChanged

    private void suggestedFriendsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suggestedFriendsListMouseClicked
        int index = suggestedFriendsList.locationToIndex(evt.getPoint());
        if (index != -1) { // Ensure an item was clicked
            // Get the value at the clicked index
            String selectedValue = suggestedFriendsList.getModel().getElementAt(index);
            System.out.println("Selected value: " + selectedValue);
            sendFriendRequest(selectedValue, user);

        }
    }//GEN-LAST:event_suggestedFriendsListMouseClicked

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String searchResult = searchField.getText();
        
        //test 
        ArrayList<User> friends = UserFileManager.getInstance().findUserByID(userId).getFriends();
        for(User friend : friends)
            System.out.println("friends : "+friend.getUsername());
            
            
        boolean userFound = false, groupFound = false;
        if (searchResult.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty Field.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            //searching in lists of users and groups
            for (User user : users) {
                if (user.getUsername().equalsIgnoreCase(searchResult)) {
                   userFound = true;
                   new SearchActionsUser(this, false, user).setVisible(true);
                }
            }
            if(!userFound)
            {
            for (Group group : groups) {
                if (group.getName().equalsIgnoreCase(searchResult)) {
                   groupFound = true;
                   new SearchActionsGroup(this, false, group).setVisible(true);
                }
            }
            }
            if(!userFound && !groupFound) JOptionPane.showMessageDialog(null, "No search results.", "Error", JOptionPane.ERROR_MESSAGE);
       
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void requestsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestsListMouseClicked
        int index = requestsList.locationToIndex(evt.getPoint());
        if (index != -1) { // Ensure an item was clicked
            // Get the value at the clicked index
            String selectedValue = requestsList.getModel().getElementAt(index);
            System.out.println("Selected value: " + selectedValue);
            handleFriendRequest(selectedValue, user);

        }
    }//GEN-LAST:event_requestsListMouseClicked

    private void friendsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_friendsListMouseClicked
        int index = friendsList.locationToIndex(evt.getPoint());
        if (index != -1) { // Ensure an item was clicked
            // Get the value at the clicked index
            String selectedValue = friendsList.getModel().getElementAt(index);
            System.out.println("Selected value: " + selectedValue);
            
            handleFriendAction(selectedValue, user);
            
        }
    }//GEN-LAST:event_friendsListMouseClicked

    private void myGroupsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myGroupsListMouseClicked
        int index = myGroupsList.locationToIndex(evt.getPoint());
        if (index != -1) { // Ensure an item was clicked
            // Get the value at the clicked index
            String selectedValue = myGroupsList.getModel().getElementAt(index);
            System.out.println("Selected value: " + selectedValue);
            //WHICH WINDOW???
            //NO OPTIONS BECAUSE IM ALREADY A MEMBER/ADMIN/ CREATOR OF GROUP SO 
            //I CAN EDIT IN THE GROUP ACCORDING TO THE BUTTONS IN THE WINDOW THAT APPEARS
        }
    }//GEN-LAST:event_myGroupsListMouseClicked

    private void suggestedGroupsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suggestedGroupsListMouseClicked
         int index = myGroupsList.locationToIndex(evt.getPoint());
        if (index != -1) { // Ensure an item was clicked
            // Get the value at the clicked index
            String selectedValue = myGroupsList.getModel().getElementAt(index);
            System.out.println("Selected value: " + selectedValue);
            for(Group group : groups)
            {
              if(group.getName().equalsIgnoreCase(selectedValue))
                  new SearchActionsGroup(this, false, group).setVisible(true);
            }
            
    }//GEN-LAST:event_suggestedGroupsListMouseClicked
}
    
    public User searchSelectedItemUser(String selectedString) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(selectedString)) {
                return user;
            }
        }
        return null;
    }

//    public Group searchSelectedItemGroup(String selectedString)
//    {
//        for(Group group : groups)
//            if(group.getName().equalsIgnoreCase(selectedString)) return group;
//        return null;
//    }
    public void refresh() {

        populateStories();
        populatePosts();
        populateFriends();
        populateSuggestedFriends();
        populateRequests();
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
    private javax.swing.JLabel bioLabel;
    private javax.swing.JLabel bioLabel1;
    private javax.swing.JList<String> friendsList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelFriends;
    private javax.swing.JPanel jPanelRequests;
    private javax.swing.JPanel jPanelSuggestedFriends;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPaneSugFriends;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JList<String> myGroupsList;
    private javax.swing.JButton newRefreshButton;
    private javax.swing.JScrollPane postsScrollPane;
    private javax.swing.JButton profileButton;
    private javax.swing.JList<String> requestsList;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JScrollPane storiesScrollPane;
    private javax.swing.JList<String> suggestedFriendsList;
    private javax.swing.JList<String> suggestedGroupsList;
    // End of variables declaration//GEN-END:variables
}

