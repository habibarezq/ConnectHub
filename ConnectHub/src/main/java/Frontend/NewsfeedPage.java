package Frontend;

import Backend.CustomListCellRender;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class NewsfeedPage extends javax.swing.JFrame {

    private DefaultListModel<String> friendsModel;
    private DefaultListModel<String> suggestedFriendsModel;
    private DefaultListModel<String> postsModel;
    private DefaultListModel<String> storiesModel;
    private ArrayList<Post> posts;
    private ArrayList<Story> stories;

    public NewsfeedPage() {
        initComponents();
        setTitle("Newsfeed");
        setContentPane(mainPanel);

        this.posts = new ArrayList<>();
        friendsModel = new DefaultListModel<>();
        suggestedFriendsModel = new DefaultListModel<>();
        postsModel = new DefaultListModel<>();

        friendsList.setModel(friendsModel);
        suggestedFriendsList.setModel(suggestedFriendsModel);

//        StoriesList.setCellRenderer(new CustomListCellRender());
//        postsList.setCellRenderer(new CustomListCellRender());       
//        populateFriends(); //arraylist missing
//        populateSuggestedFriends(); //arraylist missing
        // populatePosts();
        // populateStories();
    }

    private ArrayList<Post> fillPosts()
    {
        this.posts.add(new Post("gsdhf", "ahmed123", "im ahmed", "C:/Users/hp/Downloads/Screenshot (73).png", LocalDateTime.now()));
        this.posts.add(new Post("ehjew", "jana123", "hi", "C:/Users/hp/Downloads/Screenshot (73).png", LocalDateTime.now()));
        this.posts.add(new Post("ehjew", "habiba123", "im ahmed", "C:/Users/hp/Downloads/Screenshot (73).png", LocalDateTime.now()));
        this.posts.add(new Post("gsdefjhf", "malak123", "hi", "C:/Users/hp/Downloads/Screenshot (73).png", LocalDateTime.now()));
        this.posts.add(new Post("kjefhejf", "malek123", "hi", "C:/Users/hp/Downloads/Screenshot (73).png", LocalDateTime.now()));
        this.posts.add(new Post("fjfhkjkfh", "hamza123", "hi", "C:/Users/hp/Downloads/Screenshot (73).png", LocalDateTime.now()));
        return posts;
    }
    private void displayPosts() {
        JPanel postPanel = new JPanel();
        //jScrollPane5 = new JScrollPane(postPanel);
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        for (Post post : posts) {
            JPanel singlePostPanel = new JPanel();
            singlePostPanel.setLayout(new BorderLayout());
            singlePostPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            singlePostPanel.setPreferredSize(new Dimension(150, 200));

            //adds the caption
            JLabel contentLabel = new JLabel("Content: " + post.getContentTxt());
            contentLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding
            postPanel.add(contentLabel, BorderLayout.NORTH);

            // adds the time Stamp
            JLabel timestampLabel = new JLabel("Time: " + post.getUploadingTime());
            timestampLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding
            postPanel.add(timestampLabel, BorderLayout.SOUTH);

            // adds the image 
            File imageFile = new File(post.getcontentPath());
            if (imageFile.exists()) {
                ImageIcon imageIcon = resizeImage(post.getcontentPath(), 100, 100);
                JLabel imageLabel = new JLabel(imageIcon);
                postPanel.add(imageLabel, BorderLayout.WEST);
            } else {
                JLabel noImageLabel = new JLabel("No image available.");
                postPanel.add(noImageLabel, BorderLayout.WEST);
            }
            postPanel.add(singlePostPanel);
            postPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add spacing between stories
        }
    }

    private ImageIcon resizeImage(String contentPath, int x, int y) {
        ImageIcon imageIcon = new ImageIcon(contentPath);
        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(jScrollPane1.getWidth() - 10, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
        return resizedImageIcon;
    }
//    private void displayStories() {
//
//    JPanel storyPanel = new JPanel();
//    storyPanel.setLayout(new BoxLayout(storyPanel, BoxLayout.X_AXIS)); 
//    storyPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
//
//    for (Story story :stories ) {
//        if (story.getAuthorId().equals(userID)) {
//            // Create a panel for each story
//            JPanel singleStoryPanel = new JPanel();
//            singleStoryPanel.setLayout(new BorderLayout());
//            singleStoryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
//            singleStoryPanel.setPreferredSize(new Dimension(150, 200));
//
//          
//            JLabel contentLabel = new JLabel("Content: " + story.getContentTxt());
//            contentLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
//            singleStoryPanel.add(contentLabel, BorderLayout.NORTH);
//
//            JLabel timestampLabel = new JLabel("Time: " + story.getUploadingTime());
//            timestampLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//            singleStoryPanel.add(timestampLabel, BorderLayout.SOUTH);
//
//            File imageFile = new File(story.getImagePath());
//            if (imageFile.exists()) {
//                ImageIcon imageIcon = resizeImage(story.getImagePath(), 120, 120); // Resize for a smaller image
//                JLabel imageLabel = new JLabel(imageIcon);
//                singleStoryPanel.add(imageLabel, BorderLayout.CENTER);
//            } else {
//                JLabel noImageLabel = new JLabel("No image available.");
//                noImageLabel.setHorizontalAlignment(JLabel.CENTER);
//                singleStoryPanel.add(noImageLabel, BorderLayout.CENTER);
//            }
//
//            storyPanel.add(singleStoryPanel);
//            storyPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add spacing between stories
//        }
//    }
//
//   jScrollPane1.setViewportView(storyPanel);
//}

    public void populateFriends(ArrayList<User> friends) {
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

    public void populateSuggestedFriends(ArrayList<User> suggestedFriends) {
        for (User suggestedFriend : suggestedFriends) {
            suggestedFriendsModel.addElement(suggestedFriend.getUsername());
        }
    }

    public void populatePosts() {

    }

    public void populateStories() {
        friendsModel.addElement("John Doe");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("John Doe");
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
        jScrollPane5 = new javax.swing.JScrollPane();
        jScrollPane6 = new javax.swing.JScrollPane();

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

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addStoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                        .addComponent(addPostButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(profileButton))
                .addGap(29, 29, 29)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                    .addComponent(jScrollPane6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addGap(33, 33, 33)
                .addComponent(profileButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logoutButton)
                .addGap(101, 101, 101)
                .addComponent(addStoryButton)
                .addGap(18, 18, 18)
                .addComponent(addPostButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5)
                        .addContainerGap())
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(113, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void profileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileButtonActionPerformed
        //goes to profile frame 
    }//GEN-LAST:event_profileButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed

    }//GEN-LAST:event_logoutButtonActionPerformed

    private void addStoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStoryButtonActionPerformed
        // add new story to newsfeed and arraylist of stories
    }//GEN-LAST:event_addStoryButtonActionPerformed

    private void addPostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPostButtonActionPerformed
        // add new post to newsfeed and arraylist of posts
    }//GEN-LAST:event_addPostButtonActionPerformed

    /**
     * @param args the command line arguments
     */
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
            new NewsfeedPage().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPostButton;
    private javax.swing.JButton addStoryButton;
    private javax.swing.JList<String> friendsList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton profileButton;
    private javax.swing.JList<String> suggestedFriendsList;
    // End of variables declaration//GEN-END:variables
}
