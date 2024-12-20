package Frontend;

import Backend.*;
import Backend.FriendManagment.*;
import Backend.GroupManagement.*;
import Backend.UserManagement.*;
import Backend.UserManagement.User;
import javax.swing.JOptionPane;


public class SearchActionsUser extends javax.swing.JDialog {

    protected NewsfeedPage newsfeed;
    protected User friend;
    protected User user;
    
    public SearchActionsUser(java.awt.Frame parent, boolean modal, User user) {
         super(parent, modal);
        initComponents();
        setContentPane(mainPanel);
        setTitle("Search for "+user.getUsername());
        //setSize(306, 302);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.newsfeed = (NewsfeedPage) parent;
        this.user=this.newsfeed.user;
        this.friend = user;
        //jLabel1.setText("User "+user.getUsername()+" found. Choose an Option:");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        viewProfileButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        blockButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        viewProfileButton.setText("View Profile");
        viewProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewProfileButtonActionPerformed(evt);
            }
        });

        removeButton.setText("Remove ");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        blockButton.setText("Block");
        blockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blockButtonActionPerformed(evt);
            }
        });

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewProfileButton)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(viewProfileButton)
                .addGap(62, 62, 62)
                .addComponent(addButton)
                .addGap(64, 64, 64)
                .addComponent(removeButton)
                .addGap(48, 48, 48)
                .addComponent(blockButton)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void viewProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewProfileButtonActionPerformed
        //go to profile frame
            new FriendProfile(friend, newsfeed).setVisible(true);  
            this.dispose();
    }//GEN-LAST:event_viewProfileButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        FriendManager userManager=FriendManagerFactory.getFriendManager(user.getUserID());
        
        if(userManager.getFriends().contains(friend))
        {
            FriendServiceManager.getInstance(user).removeFriend(friend.getUsername());
           JOptionPane.showMessageDialog(null, "User removed successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
        } 
        else JOptionPane.showMessageDialog(null, "This user is not added so it cannot be removed.", "Error", JOptionPane.ERROR_MESSAGE);
        this.dispose();
    }//GEN-LAST:event_removeButtonActionPerformed

    private void blockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockButtonActionPerformed
        //blocking user 
            FriendServiceManager.getInstance(user).blockFriend(friend.getUsername());
            JOptionPane.showMessageDialog(null, "User blocked successfully", "Message", JOptionPane.INFORMATION_MESSAGE);

        this.dispose();
    }//GEN-LAST:event_blockButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        FriendManager userManager=FriendManagerFactory.getFriendManager(user.getUserID());
        
        if(!userManager.getFriends().contains(friend))
        {
            FriendRequestManager.getInstance(user.getUserID()).sendRequest(friend);
            JOptionPane.showMessageDialog(null, "Request sent successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
        }
        else JOptionPane.showMessageDialog(null, "This user is already added.", "Error", JOptionPane.ERROR_MESSAGE);
        this.dispose();
    }//GEN-LAST:event_addButtonActionPerformed

    
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
            java.util.logging.Logger.getLogger(SearchActionsUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchActionsUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchActionsUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchActionsUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton blockButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton viewProfileButton;
    // End of variables declaration//GEN-END:variables
}
