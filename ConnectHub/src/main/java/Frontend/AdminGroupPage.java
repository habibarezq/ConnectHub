package Frontend;

import Backend.GroupManagement.Group;
import Backend.GroupManagement.GroupManager;
import Backend.GroupManagement.GroupServiceManager;
import Backend.GroupManagement.GroupUser;
import Backend.GroupManagement.MembershipManager;
import Backend.Post;
import Backend.UploadPosts;
import Backend.UserManagement.User;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class AdminGroupPage extends javax.swing.JFrame {

    private DefaultListModel<String> membersModel;
    private ArrayList<User> members;
    private ArrayList<Post> posts; //HOW WILL I TAKE IT
    private NewsfeedPage newsfeed;
    private User admin;
    private String groupId;

    public AdminGroupPage(NewsfeedPage newsfeed,String groupId) {
        initComponents();
        setTitle("Group");
        setContentPane(jPanel1);

        //GET GROUP BY GROUPID SO I CAN GET DESCRIPTION AND NAME
        
        this.newsfeed = newsfeed;
        this.groupId = groupId;
        
        //the user using the app
        this.admin = newsfeed.user; 
        
        membersModel = new DefaultListModel<>();
        requestsList.setModel(membersModel);

         //GET POSTS
        descriptionLabel.setText(null); //DESCRIPTION
        groupNameLabel.setText(null); //GROUP NAME
        
        populatePosts();
        populateMembers();
        startup();
    }

        private void populatePosts() {
        new UploadPosts(postsScrollPane, posts);
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
        //HOPEFULLY WORKING BUT NEEDS GROUP BACKEND
//        File groupPicFile = new File(//IMAGE); 
//        if(groupPicFile.exists())
//        {
//            try {
//                Image image = ImageIO.read(groupPicFile);
//                if (image != null) {
//                    // Scale image to fit within the label
//                    Image scaledImage = image.getScaledInstance(groupPicLabel.getWidth(), groupPicLabel.getHeight(), Image.SCALE_SMOOTH);
//                    groupPicLabel.setIcon(new ImageIcon(scaledImage));
//                } else {
//                    JOptionPane.showMessageDialog(this, "The selected file is not a valid image.", "Invalid Image", JOptionPane.ERROR_MESSAGE);
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
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
        newsfeedButton = new javax.swing.JButton();

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

        newsfeedButton.setText("Newsfeed");
        newsfeedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newsfeedButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(groupNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(editPostButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deletePostButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addPostButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(newsfeedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(postsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(postsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bioLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bioLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(groupNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(editPostButton)
                        .addGap(43, 43, 43)
                        .addComponent(addPostButton)
                        .addGap(37, 37, 37)
                        .addComponent(deletePostButton)
                        .addGap(45, 45, 45)
                        .addComponent(newsfeedButton)))
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
                .addContainerGap(253, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(138, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void requestsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestsListMouseClicked
         int index = requestsList.locationToIndex(evt.getPoint());
        if (index != -1) { // Ensure an item was clicked
            // Get the value at the clicked index
            String selectedValue = requestsList.getModel().getElementAt(index);
            System.out.println("Selected value: " + selectedValue);
            handleRequestSelection(selectedValue);}
    }//GEN-LAST:event_requestsListMouseClicked

    private void membersListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_membersListMouseClicked
        int index = requestsList.locationToIndex(evt.getPoint());
        if (index != -1) { // Ensure an item was clicked
            // Get the value at the clicked index
            String selectedValue = requestsList.getModel().getElementAt(index);
            System.out.println("Selected value: " + selectedValue);
            handleMemberSelection(selectedValue);}
    }//GEN-LAST:event_membersListMouseClicked

    private void editPostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPostButtonActionPerformed
        //EDIT
    }//GEN-LAST:event_editPostButtonActionPerformed

    private void addPostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPostButtonActionPerformed
        new postCreation(this.admin.getUserID(), newsfeed).setVisible(true);
        this.dispose();
        //ADD
    }//GEN-LAST:event_addPostButtonActionPerformed

    private void deletePostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePostButtonActionPerformed
        //DELETE
    }//GEN-LAST:event_deletePostButtonActionPerformed

    private void newsfeedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newsfeedButtonActionPerformed
        newsfeed.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_newsfeedButtonActionPerformed

    private void handleRequestSelection(String username)
    {
        GroupUser user = MembershipManager.getInstance(groupId).getGroupUserByUsername(username);
        
         if(user ==  null)
       {
            JOptionPane.showMessageDialog(null, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
       }
       else
       {
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
        
   if(choice == 0) 
        {
         //ADD USER TO MEMBERS
        }
        else if(choice == 1) return;
       }
    }
    
    private void handleMemberSelection(String username)
    {
         GroupUser user = MembershipManager.getInstance(groupId).getGroupUserByUsername(username);
        
         if(user ==  null)
       {
            JOptionPane.showMessageDialog(null, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
       }
       else
       {
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
        
        if(choice == 0) 
        {
         //REMOVE USER FROM MEMBERS
        }
        else if(choice == 1) return;
       }
    }
    public static void main(String args[]) {
       /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPostButton;
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
    private javax.swing.JButton newsfeedButton;
    private javax.swing.JScrollPane postsScrollPane;
    private javax.swing.JList<String> requestsList;
    // End of variables declaration//GEN-END:variables
}
