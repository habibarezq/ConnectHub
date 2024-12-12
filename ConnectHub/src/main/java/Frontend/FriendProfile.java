package Frontend;

import Backend.ContentManager;
import Backend.FileManagers.ProfileFileManager;
import Backend.Post;
import Backend.UploadPosts;
import Backend.User;
import Backend.UserProfile;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class FriendProfile extends javax.swing.JFrame {

    private ArrayList<Post> posts;
    private NewsfeedPage newsfeed;
    private String bio;
    private User user;
    private String profilePic;
    private String coverPic;
    
    public FriendProfile(User user, NewsfeedPage newsfeed) {
        initComponents();
        setTitle("Profile");
        setSize(1300, 593);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        this.user = user;
        this.newsfeed = newsfeed;
        this.posts = ContentManager.getInstance(user.getUserID()).getPosts();
   
        populatePosts();
        
        UserProfile profile = ProfileFileManager.getInstance().getUserProfileById(user.getUserID());
        bioLabel.setText(profile.getBio()); 
        
        startup(profile);
    }

    private void populatePosts()
    {
        new UploadPosts(postsScrollPane , ContentManager.getInstance(user.getUserID()).getPosts());
    }
    
    private void startup(UserProfile profile)
    {
        File coverFile = new File(profile.getCoverPic());
        File profileFile = new File(profile.getProfilePic());
         if (coverFile.exists()) {
            try {
                Image image1 = ImageIO.read(coverFile);

                if (image1 != null) {
                    // Scale image to fit within the label
                    Image scaledImage = image1.getScaledInstance(coverImageLabel.getWidth(), coverImageLabel.getHeight(), Image.SCALE_SMOOTH);
                    coverImageLabel.setIcon(new ImageIcon(scaledImage));

                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (profileFile.exists()) {
            try {
                Image image2 = ImageIO.read(profileFile);
                if (image2 != null) {
                    // Scale image to fit within the label
                    Image scaledImage = image2.getScaledInstance(coverImageLabel.getWidth(), coverImageLabel.getHeight(), Image.SCALE_SMOOTH);
                    profileImageLabel.setIcon(new ImageIcon(scaledImage));
                } else {
                    JOptionPane.showMessageDialog(this, "The selected file is not a valid image.", "Invalid Image", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        friendProfilePanel = new javax.swing.JPanel();
        profileImageLabel = new javax.swing.JLabel();
        friendCoverPanel = new javax.swing.JPanel();
        coverImageLabel = new javax.swing.JLabel();
        postsScrollPane = new javax.swing.JScrollPane();
        bioLabel = new javax.swing.JLabel();
        backtofeedButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout friendProfilePanelLayout = new javax.swing.GroupLayout(friendProfilePanel);
        friendProfilePanel.setLayout(friendProfilePanelLayout);
        friendProfilePanelLayout.setHorizontalGroup(
            friendProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(profileImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
        );
        friendProfilePanelLayout.setVerticalGroup(
            friendProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(friendProfilePanelLayout.createSequentialGroup()
                .addComponent(profileImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout friendCoverPanelLayout = new javax.swing.GroupLayout(friendCoverPanel);
        friendCoverPanel.setLayout(friendCoverPanelLayout);
        friendCoverPanelLayout.setHorizontalGroup(
            friendCoverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(coverImageLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
        );
        friendCoverPanelLayout.setVerticalGroup(
            friendCoverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(coverImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        backtofeedButton.setText("Back to feed");
        backtofeedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backtofeedButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(107, Short.MAX_VALUE)
                .addComponent(postsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(bioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(friendProfilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(friendCoverPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backtofeedButton)
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(friendProfilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(friendCoverPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(backtofeedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(postsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backtofeedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backtofeedButtonActionPerformed
        //back to feed
        newsfeed.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backtofeedButtonActionPerformed

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
            java.util.logging.Logger.getLogger(FriendProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FriendProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FriendProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FriendProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backtofeedButton;
    private javax.swing.JLabel bioLabel;
    private javax.swing.JLabel coverImageLabel;
    private javax.swing.JPanel friendCoverPanel;
    private javax.swing.JPanel friendProfilePanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane postsScrollPane;
    private javax.swing.JLabel profileImageLabel;
    // End of variables declaration//GEN-END:variables
}
