package Frontend;

import Backend.FileManagers.GroupPostsFileManager;
import Backend.FileManagers.GroupsFileManager;
import Backend.FileManagers.UserFileManager;
import Backend.GroupManagement.Group;
import Backend.GroupManagement.GroupContentManager;
import Backend.GroupManagement.GroupManager;
import Backend.GroupManagement.GroupPost;
import Backend.GroupManagement.GroupServiceManager;
import Backend.GroupManagement.GroupUser;
import Backend.GroupManagement.MembershipManager;
import Backend.GroupManagement.NormalAdmin;
import Backend.Post;
import Backend.UploadPosts;
import Backend.UserManagement.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AdminGroupPage extends javax.swing.JFrame {

    private DefaultListModel<String> membersModel;
    private ArrayList<User> members;
    private ArrayList<GroupPost> posts; //HOW WILL I TAKE IT
    private NewsfeedPage newsfeed;
    private User admin;
    private String groupId;
    private Group group;
    JPanel selectedPostPanel;
    GroupPost selectedPost;

    public AdminGroupPage(NewsfeedPage newsfeed, String groupId) {
        initComponents();
        setTitle("Admin pages");
        setContentPane(jPanel1);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //GET GROUP BY GROUPID SO I CAN GET DESCRIPTION AND NAME

        this.newsfeed = newsfeed;
        this.groupId = groupId;
        this.group = GroupsFileManager.getInstance().getGroupById(groupId);

        //the user using the app
        this.admin = newsfeed.user;

        membersModel = new DefaultListModel<>();
        requestsList.setModel(membersModel);

        //GET POSTS
        descriptionLabel.setText(group.getDescription()); //DESCRIPTION
        groupNameLabel.setText(group.getName()); //GROUP NAME

        populatePosts();
        populateMembers();
        startup();
    }

    private void populateMembers() {
        //to make sure the list is empty
        membersModel.removeAllElements();

        if (members == null) {
            membersModel.addElement("No members");
        } else {
            for (User member : members) {
                membersModel.addElement(member.getUsername());
            }
        }
    }

    private void startup() //MANAGEMENT OR IMAGE
    {

        File groupPicFile = new File(group.getPhotoPath());
        if (groupPicFile.exists()) {
            try {
                Image image = ImageIO.read(groupPicFile);
                if (image != null) {
                    // Scale image to fit within the label
                    Image scaledImage = image.getScaledInstance(groupPicLabel.getWidth(), groupPicLabel.getHeight(), Image.SCALE_SMOOTH);
                    groupPicLabel.setIcon(new ImageIcon(scaledImage));
                } else {
                    JOptionPane.showMessageDialog(this, "The selected file is not a valid image.", "Invalid Image", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String username = group.getName();
        groupNameLabel.setText(username);
        groupNameLabel.setFont(new Font("Serif", Font.BOLD, 18));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        groupPicLabel = new javax.swing.JLabel();
        groupNameLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        postsScrollPane = new javax.swing.JScrollPane();
        bioLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        requestsList = new javax.swing.JList<>();
        bioLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        membersList = new javax.swing.JList<>();
        editPostButton = new javax.swing.JButton();
        addPostButton = new javax.swing.JButton();
        deletePostButton = new javax.swing.JButton();
        backFeedButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        groupPicLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(groupPicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(groupPicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        groupNameLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        descriptionLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bioLabel.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        bioLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bioLabel.setText("Members");
        bioLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        requestsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                requestsListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(requestsList);

        bioLabel1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        bioLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bioLabel1.setText("Requests");
        bioLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        membersList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                membersListMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(membersList);

        editPostButton.setText("Edit Post");
        editPostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPostButtonActionPerformed(evt);
            }
        });

        addPostButton.setText("Add Post");
        addPostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPostButtonActionPerformed(evt);
            }
        });

        deletePostButton.setText("Delete Post");
        deletePostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePostButtonActionPerformed(evt);
            }
        });

        backFeedButton.setBackground(new java.awt.Color(153, 204, 255));
        backFeedButton.setText("Back To Feed");
        backFeedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backFeedButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(backFeedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deletePostButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addPostButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editPostButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addComponent(postsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(groupNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bioLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(695, 695, 695)
                    .addComponent(jScrollPane3)
                    .addGap(30, 30, 30)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(bioLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bioLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(postsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(groupNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(editPostButton)
                                .addGap(43, 43, 43)
                                .addComponent(addPostButton)
                                .addGap(37, 37, 37)
                                .addComponent(deletePostButton)
                                .addGap(104, 104, 104)
                                .addComponent(backFeedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(64, 64, 64)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(323, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void requestsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestsListMouseClicked
        int index = requestsList.locationToIndex(evt.getPoint());
        if (index != -1) { // Ensure an item was clicked
            // Get the value at the clicked index
            String selectedValue = requestsList.getModel().getElementAt(index);
            System.out.println("Selected value: " + selectedValue);
            handleRequestSelection(selectedValue);
        }
    }//GEN-LAST:event_requestsListMouseClicked

    private void membersListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_membersListMouseClicked
        int index = requestsList.locationToIndex(evt.getPoint());
        if (index != -1) { // Ensure an item was clicked
            // Get the value at the clicked index
            String selectedValue = requestsList.getModel().getElementAt(index);
            System.out.println("Selected value: " + selectedValue);
            handleMemberSelection(selectedValue);
        }
    }//GEN-LAST:event_membersListMouseClicked

    private void editPostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPostButtonActionPerformed
        editSelectedPost();

    }//GEN-LAST:event_editPostButtonActionPerformed

    private void addPostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPostButtonActionPerformed
        new groupPostCreation(this.admin.getUserID(), this.groupId, newsfeed).setVisible(true);
        this.dispose();  //ADD
    }//GEN-LAST:event_addPostButtonActionPerformed

    private void deletePostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePostButtonActionPerformed
        deleteSelectedPost();
    }//GEN-LAST:event_deletePostButtonActionPerformed

    private void backFeedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backFeedButtonActionPerformed
        // TODO add your handling code here:
        newsfeed.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backFeedButtonActionPerformed

    private void handleRequestSelection(String username) {
        GroupUser user = MembershipManager.getInstance(groupId).getGroupUserByUsername(username);

        if (user == null) {
            JOptionPane.showMessageDialog(null, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            Object[] options = {"Accept", "Decline"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "<html>What do you want to do with <b>" + username + "</b>?</html>",
                    "Admin Options",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == 0) {
                NormalAdmin a = new NormalAdmin(admin.getUserID());
                a.acceptMember(user.getGroupUserId());
            } else if (choice == 1) {
                return;
            }
        }
    }

    private void populatePosts() {
        uploadPostsFunction(postsScrollPane, posts);
    }

    public void uploadPostsFunction(JScrollPane postsScrollPane, ArrayList<GroupPost> posts) {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        if (posts == null) {
            return;
        }
        for (GroupPost post : posts) {
            System.out.println(posts);
            //creating a panel for each post
            JPanel everyPostPanel = new JPanel();
            everyPostPanel.setLayout(new BorderLayout());
            everyPostPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            everyPostPanel.setPreferredSize(new Dimension(300, 80));

            // Adding mouse listener for selection
            everyPostPanel.addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent e) {
                    selectPost(everyPostPanel, post);
                }
            });

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

    public ImageIcon resizeImagePosts(String contentPath) {
        ImageIcon imageIcon = new ImageIcon(contentPath);
        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(postsScrollPane.getWidth() - 10, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
        return resizedImageIcon;
    }

    private void selectPost(JPanel postPanel, GroupPost post) {

        if (selectedPostPanel != null) {
            selectedPostPanel.setBackground(Color.WHITE);
        }
        selectedPostPanel = postPanel;
        selectedPostPanel.setBackground(Color.LIGHT_GRAY);
        selectedPost = post;
        System.out.println("Selected post: " + selectedPost.getContentTxt());
    }

    public void editSelectedPost() {
        if (selectedPost != null) {
            JTextField contentField = new JTextField(selectedPost.getContentTxt());
            JTextField imagePathField = new JTextField(selectedPost.getcontentPath());
            JButton browseButton = new JButton("Browse...");

            browseButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("."));
                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        imagePathField.setText(selectedFile.getAbsolutePath());
                    }
                }
            });

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JLabel("Edit Content:"), BorderLayout.NORTH);
            panel.add(contentField, BorderLayout.CENTER);

            JPanel imagePanel = new JPanel(new BorderLayout());
            imagePanel.add(new JLabel("Edit Image Path:"), BorderLayout.NORTH);
            imagePanel.add(imagePathField, BorderLayout.CENTER);
            imagePanel.add(browseButton, BorderLayout.EAST);

            panel.add(imagePanel, BorderLayout.SOUTH);

            int result = JOptionPane.showConfirmDialog(null, panel, "Edit Post", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String newContent = contentField.getText();
                String newImagePath = imagePathField.getText();

                if (newContent != null && !newContent.trim().isEmpty()) {
                    selectedPost.setContentTxt(newContent);
                }
                if (newImagePath != null && !newImagePath.trim().isEmpty()) {
                    selectedPost.setcontentPath(newImagePath);
                }

                GroupPostsFileManager.getInstance().saveToFile(GroupPostsFileManager.getInstance().getPosts());
                JOptionPane.showMessageDialog(null, "Post updated successfully!");
                // Refresh the posts display
                uploadPostsFunction(postsScrollPane, GroupPostsFileManager.getInstance().getPosts());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No post selected to edit!");
        }
    }

    public void deleteSelectedPost() {
        if (selectedPost != null) {

            ArrayList<GroupPost> posts = GroupPostsFileManager.getInstance().getPosts();
            posts.remove(selectedPost);
            GroupPostsFileManager.getInstance().saveToFile(posts);
            JOptionPane.showMessageDialog(null, "Post deleted successfully!");
            // Refresh the posts display
            uploadPostsFunction(postsScrollPane, GroupPostsFileManager.getInstance().getPosts());
        } else {
            JOptionPane.showMessageDialog(null, "No post selected to delete!");
        }
    }

    private void handleMemberSelection(String username) {
        GroupUser user = MembershipManager.getInstance(groupId).getGroupUserByUsername(username);

        if (user == null) {
            JOptionPane.showMessageDialog(null, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            Object[] options = {"Remove", "Cancel"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "<html>What do you want to do with <b>" + username + "</b>?</html>",
                    "Admin Options",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == 0) {
                NormalAdmin a = new NormalAdmin(admin.getUserID());
                a.decline(user.getGroupUserId());
            } else if (choice == 1) {
                return;
            }
        }
    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPostButton;
    private javax.swing.JButton backFeedButton;
    private javax.swing.JLabel bioLabel;
    private javax.swing.JLabel bioLabel1;
    private javax.swing.JButton deletePostButton;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JButton editPostButton;
    private javax.swing.JLabel groupNameLabel;
    private javax.swing.JLabel groupPicLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> membersList;
    private javax.swing.JScrollPane postsScrollPane;
    private javax.swing.JList<String> requestsList;
    // End of variables declaration//GEN-END:variables
}
