package Frontend;

import Backend.FileManagers.UserFileManager;
import Backend.Post;
import Backend.UploadPosts;
import Backend.*;
import Backend.FileManagers.GroupPostsFileManager;
import Backend.FileManagers.GroupsFileManager;
import Backend.FileManagers.*;
import Backend.GroupManagement.*;
import Backend.FriendManagment.*;
import Backend.GroupManagement.*;
import Backend.UserManagement.*;
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
import java.time.LocalDate;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CreatorGroupPage extends javax.swing.JFrame {

    private DefaultListModel<String> adminsModel;
    private DefaultListModel<String> membersModel;
    private DefaultListModel<String> requestsListModel;
    private ArrayList<NormalAdmin> admins;
    private ArrayList<GroupUser> members;
    private ArrayList<GroupRequest> requests;
    private ArrayList<GroupPost> posts;
    private NewsfeedPage newsfeed;
    private User creator;
    private String groupId;
    private Group group;
    JPanel selectedPostPanel;
    GroupPost selectedPost;

    public CreatorGroupPage(NewsfeedPage newsfeed, String groupId) {
        initComponents();
        setTitle("Creator page");
        setContentPane(jPanel3);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.newsfeed = newsfeed;
        this.creator = newsfeed.user;
        this.groupId=groupId;
        this.members=MembershipManager.getInstance(groupId).getGroupUsers();
        this.admins=MembershipManager.getInstance(groupId).getAdmins();
        this.group=GroupsFileManager.getInstance().getGroupById(groupId);
        this.posts=GroupContentManager.getInstance(groupId).getPosts();
        this.requests=GroupRequestManager.getInstance(groupId).getGroupRequests();
       
        System.out.println(posts);

        adminsModel = new DefaultListModel<>();
        adminsList.setModel(adminsModel);

        membersModel = new DefaultListModel<>();
        requestsListModel=new DefaultListModel<>();
        
        membersList.setModel(membersModel);
        requestsList.setModel(requestsListModel);
        //GET POSTS
        descriptionLabel.setText(group.getDescription()); //DESCRIPTION
        nameLabel.setText(group.getName()); //GROUP NAME

        populatePosts();
        populateAdmins();
        populateMembers();
        populateRequests();
        startup();
    }

    private void populatePosts() {
        uploadPostsFunction(postsScrollPane, posts);
    }


    private void populateRequests()
    {
        requestsListModel.removeAllElements();
        requests = null;
        requests=GroupRequestManager.getInstance(groupId).getGroupRequests();
        ArrayList<GroupRequest> test=GroupRequestsFileManager.getInstance().getRequests();
      
        if (requests != null) {
            for (GroupRequest request : requests) {
                // Only include pending requests
                if (request.getRequestStat().equals("Pending")) {
                    System.out.println("Name"+request.getUser().getUser(request.getUser().getGroupUserId()).getUsername());
                    requestsListModel.addElement(request.getUser().getUser(request.getUser().getGroupUserId()).getUsername());
                }
            }
        }
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

    private void populateAdmins() {
        //to make sure the list is empty
        adminsModel.removeAllElements();

        if (admins == null) {
            adminsModel.addElement("");
        } else {
            for (NormalAdmin admin : admins) {
                if(!admin.getGroupUserId().equals(creator.getUserID()))
                adminsModel.addElement(admin.getUser(admin.getGroupUserId()).getUsername());
            }
        }
    }

    private void populateMembers() {
        //to make sure the list is empty
        membersModel.removeAllElements();

        if (members == null) {
            membersModel.addElement("");
        } else {
            for (GroupUser member : members) {
               if(!member.getGroupUserId().equals(creator.getUserID()) && !admins.contains(member))
                membersModel.addElement(member.getUser(member.getGroupUserId()).getUsername());
            }
        }
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
            PostsFileManager.getInstance().getPosts().remove(selectedPost);
            PostsFileManager.getInstance().saveToFile(PostsFileManager.getInstance().getPosts());
            JOptionPane.showMessageDialog(null, "Post deleted successfully!");
            // Refresh the posts display
            uploadPostsFunction(postsScrollPane, GroupPostsFileManager.getInstance().getPosts());
        } else {
            JOptionPane.showMessageDialog(null, "No post selected to delete!");
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
        nameLabel.setText(username);
        nameLabel.setFont(new Font("Serif", Font.BOLD, 18));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        groupPicLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        postsScrollPane = new javax.swing.JScrollPane();
        editPostButton = new javax.swing.JButton();
        deleteGroupButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        membersList = new javax.swing.JList<>();
        bioLabel = new javax.swing.JLabel();
        bioLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        adminsList = new javax.swing.JList<>();
        descriptionLabel = new javax.swing.JLabel();
        changeDescriptionButton = new javax.swing.JButton();
        changeGroupPhotoButton = new javax.swing.JButton();
        addPostButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        backFeedButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        requestsList = new javax.swing.JList<>();
        bioLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        groupPicLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(groupPicLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(groupPicLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
        );

        nameLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        postsScrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                postsScrollPaneMouseClicked(evt);
            }
        });

        editPostButton.setText("Edit Post");
        editPostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPostButtonActionPerformed(evt);
            }
        });

        deleteGroupButton.setText("Delete Group");
        deleteGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteGroupButtonActionPerformed(evt);
            }
        });

        membersList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                membersListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(membersList);

        bioLabel.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        bioLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bioLabel.setText("Requests");
        bioLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bioLabel1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        bioLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bioLabel1.setText("Admins");
        bioLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        adminsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminsListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(adminsList);

        descriptionLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        changeDescriptionButton.setText("Change Description");
        changeDescriptionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeDescriptionButtonActionPerformed(evt);
            }
        });

        changeGroupPhotoButton.setText("Change Group Photo");
        changeGroupPhotoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeGroupPhotoButtonActionPerformed(evt);
            }
        });

        addPostButton.setText("Add Post");
        addPostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPostButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete Post");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        backFeedButton.setBackground(new java.awt.Color(153, 204, 255));
        backFeedButton.setText("Back To Feed");
        backFeedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backFeedButtonActionPerformed(evt);
            }
        });

        requestsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                requestsListMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(requestsList);

        bioLabel2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        bioLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bioLabel2.setText("Members");
        bioLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(changeGroupPhotoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(editPostButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addPostButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteGroupButton, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addComponent(backFeedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(56, 56, 56)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(postsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(changeDescriptionButton))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                                .addGap(15, 15, 15))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bioLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(702, Short.MAX_VALUE)
                    .addComponent(bioLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(85, 85, 85)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(changeDescriptionButton)
                        .addGap(18, 18, 18)
                        .addComponent(postsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(changeGroupPhotoButton)
                                .addGap(65, 65, 65)
                                .addComponent(editPostButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addPostButton)
                                .addGap(18, 18, 18)
                                .addComponent(deleteButton)
                                .addGap(18, 18, 18)
                                .addComponent(deleteGroupButton)
                                .addGap(18, 18, 18)
                                .addComponent(backFeedButton))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(bioLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bioLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(260, 260, 260)
                    .addComponent(bioLabel2)
                    .addContainerGap(358, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changeGroupPhotoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeGroupPhotoButtonActionPerformed
        String path = changeImage();
        GroupsFileManager.getInstance().getGroupById(groupId).setPhotoPath(path);
    }//GEN-LAST:event_changeGroupPhotoButtonActionPerformed

    private void changeDescriptionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeDescriptionButtonActionPerformed
        // taking description from creator
        String descriptionInput = JOptionPane.showInputDialog("Enter new description:");
        if (descriptionInput.isEmpty())
            JOptionPane.showMessageDialog(this, "Empty field.", "Invalid Image", JOptionPane.ERROR_MESSAGE);
        else {
            descriptionLabel.setText(descriptionInput);
            GroupsFileManager.getInstance().getGroupById(groupId).setDescription(descriptionInput);
        }
    }//GEN-LAST:event_changeDescriptionButtonActionPerformed

    private void editPostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPostButtonActionPerformed
        //removes posts from arraylist and from scrollpane
        editSelectedPost();
    }//GEN-LAST:event_editPostButtonActionPerformed

    private void deleteGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteGroupButtonActionPerformed
        PrimaryAdmin p = new PrimaryAdmin(creator.getUserID());
        p.deleteGroup(groupId);
        this.dispose();
    }//GEN-LAST:event_deleteGroupButtonActionPerformed

    private void adminsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminsListMouseClicked
        int index = adminsList.locationToIndex(evt.getPoint());
        if (index != -1) { // Ensure an item was clicked
            // Get the value at the clicked index
            String selectedValue = adminsList.getModel().getElementAt(index);
            System.out.println("Selected value: " + selectedValue);
            handleAdminSelection(selectedValue);
        }
    }//GEN-LAST:event_adminsListMouseClicked

    private void membersListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_membersListMouseClicked
        int index = membersList.locationToIndex(evt.getPoint());
        if (index != -1) { // Ensure an item was clicked
            // Get the value at the clicked index
            String selectedValue = membersList.getModel().getElementAt(index);
            System.out.println("Selected value: " + selectedValue);
            handleMemberSelection(selectedValue);
        }
    }//GEN-LAST:event_membersListMouseClicked

    private void backFeedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backFeedButtonActionPerformed
        newsfeed.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backFeedButtonActionPerformed

    private void addPostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPostButtonActionPerformed
        // TODO add your handling code here:
        new groupPostCreation(this.creator.getUserID(), this.groupId, newsfeed).setVisible(true);

        this.dispose();
    }//GEN-LAST:event_addPostButtonActionPerformed

    private void deletePostButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        PrimaryAdmin admin=new PrimaryAdmin(groupId);
        //GroupContentManager.getInstance(groupId).deletePost(post);
    }                                                

    private void requestsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestsListMouseClicked
        int index = requestsList.locationToIndex(evt.getPoint());
        if (index != -1) { // Ensure an item was clicked
            // Get the value at the clicked index
            String selectedValue = requestsList.getModel().getElementAt(index);
            System.out.println("Selected value: " + selectedValue);
            handleRequestSelection(selectedValue);
            
        }
    }//GEN-LAST:event_requestsListMouseClicked

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
                GroupManager.getInstance(user, group).acceptMember(user.getGroupUserId());
            } else if (choice == 1) {
                return;
            }
        }
    }

    private void postsScrollPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_postsScrollPaneMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_postsScrollPaneMouseClicked

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        deleteSelectedPost();
       
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void handleAdminSelection(String username) {
        GroupUser user = MembershipManager.getInstance(groupId).getGroupUserByUsername(username);

        if (user == null) {
            JOptionPane.showMessageDialog(null, "Admin not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            Object[] options = {"Demote", "Cancel"};
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
                PrimaryAdmin p = new PrimaryAdmin(creator.getUserID());
                p.demote(user.getGroupUserId());
            } else if (choice == 1) {
                return;
            }
        }
    }

    private void handleMemberSelection(String username) {
        GroupUser user = MembershipManager.getInstance(groupId).getGroupUserByUsername(username);

        if (user == null) {
            JOptionPane.showMessageDialog(null, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            Object[] options = {"Pomote", "Remove", "Cancel"};
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

            PrimaryAdmin p = new PrimaryAdmin(creator.getUserID());
            if (choice == 0) {

                p.promote(user.getGroupUserId());
            } else if (choice == 1) {
                p.removeMember(user.getGroupUserId());
            } else if (choice == 2) {
                return;
            }
        }
    }

    private String changeImage() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(".")); // Changing the directory to specific one

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

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                Image image = ImageIO.read(selectedFile);
                String imagePath = selectedFile.getPath();

                if (image != null) {
                    // Scale image to fit within the label
                    Image scaledImage = image.getScaledInstance(groupPicLabel.getWidth(), groupPicLabel.getHeight(), Image.SCALE_SMOOTH);
                    groupPicLabel.setIcon(new ImageIcon(scaledImage));

                    return imagePath; //TO BE SENT TO THE BACKEND TO SAVE IT 
                } else {
                    JOptionPane.showMessageDialog(this, "The selected file is not a valid image.", "Invalid Image", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(CreatorGroupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreatorGroupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreatorGroupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreatorGroupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> {
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPostButton;
    private javax.swing.JList<String> adminsList;
    private javax.swing.JButton backFeedButton;
    private javax.swing.JLabel bioLabel;
    private javax.swing.JLabel bioLabel1;
    private javax.swing.JLabel bioLabel2;
    private javax.swing.JButton changeDescriptionButton;
    private javax.swing.JButton changeGroupPhotoButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton deleteGroupButton;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JButton editPostButton;
    private javax.swing.JLabel groupPicLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> membersList;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JScrollPane postsScrollPane;
    private javax.swing.JList<String> requestsList;
    // End of variables declaration//GEN-END:variables
}
