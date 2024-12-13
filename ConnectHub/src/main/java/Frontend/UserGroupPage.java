package Frontend;

import Backend.FileManagers.UserFileManager;
import Backend.GroupManagement.Group;
import Backend.GroupManagement.GroupPost;
import Backend.GroupManagement.GroupUser;
import Backend.GroupManagement.NormalAdmin;
import Backend.UserManagement.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class UserGroupPage extends javax.swing.JFrame {

    private NewsfeedPage newsfeed;
    private User user;
    private String groupId;
    private Group group;
    private ArrayList<GroupPost> posts;

    
    public UserGroupPage(NewsfeedPage newsfeed, String groupId) {
        initComponents();
        setTitle("User");
        setContentPane(jPanel1);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.newsfeed = newsfeed;
        this.groupId = groupId;
        this.group = group;

        //the user using the app
        this.user = newsfeed.user;
        populatePosts();

        startup();
    }

    private void startup() //MANAGEMENT OR IMAGE
    {
if(group!=null){
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        groupPicLabel = new javax.swing.JLabel();
        groupNameLabel = new javax.swing.JLabel();
        postsScrollPane = new javax.swing.JScrollPane();
        descriptionLabel = new javax.swing.JLabel();
        leaveGroupButton = new javax.swing.JButton();
        addPostButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        groupPicLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(groupPicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(groupPicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        groupNameLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        descriptionLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        leaveGroupButton.setText("Leave Group");
        leaveGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveGroupButtonActionPerformed(evt);
            }
        });

        addPostButton.setText("Add Post");
        addPostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPostButtonActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(153, 204, 255));
        jButton1.setText("Back To Feed");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(groupNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(leaveGroupButton)
                                    .addComponent(addPostButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(53, 53, 53)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(postsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(groupNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113)
                        .addComponent(addPostButton)
                        .addGap(18, 18, 18)
                        .addComponent(leaveGroupButton)
                        .addGap(94, 94, 94)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(postsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(223, 223, 223))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addPostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPostButtonActionPerformed
        new groupPostCreation(this.user.getUserID(), this.groupId, newsfeed).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_addPostButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        newsfeed.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void leaveGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveGroupButtonActionPerformed
        // TODO add your handling code here:
        GroupUser u = new GroupUser(user.getUserID());
        u.deleteGroup(groupId);
    }//GEN-LAST:event_leaveGroupButtonActionPerformed

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
            java.util.logging.Logger.getLogger(UserGroupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserGroupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserGroupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserGroupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPostButton;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JLabel groupNameLabel;
    private javax.swing.JLabel groupPicLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton leaveGroupButton;
    private javax.swing.JScrollPane postsScrollPane;
    // End of variables declaration//GEN-END:variables
}
