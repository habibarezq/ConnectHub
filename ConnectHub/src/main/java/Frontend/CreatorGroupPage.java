package Frontend;

import Backend.FileManagers.UserFileManager;
import Backend.Post;
import Backend.UploadPosts;
import Backend.User;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CreatorGroupPage extends javax.swing.JFrame {

    private DefaultListModel<String> adminsModel;
    private DefaultListModel<String> membersModel;
    private ArrayList<User> admins;
    private ArrayList<User> members;
    private ArrayList<Post> posts;
    private NewsfeedPage newsfeed;
    private User creator;

    public CreatorGroupPage(NewsfeedPage newsfeed) {
        initComponents();
        setTitle("Group");
        setContentPane(jPanel3);

        this.newsfeed = newsfeed;
        this.creator = newsfeed.user;

        //adding admins for test
        admins.add(new User("jfnjdn", "admin1@gmail.com", "admin1", LocalDate.now(), "vjnjejl,nf"));
        admins.add(new User("jfnerpojdn", "admin2@gmail.com", "admin2", LocalDate.now(), "vjnjejrl,f,nf"));
        admins.add(new User("jfnjrdn", "admin3@gmail.com", "admin3", LocalDate.now(), "vjnjejl;nf"));
        admins.add(new User("jfnjrl,dn", "admin4@gmail.com", "admin4", LocalDate.now(), "vjnjejrl,nf"));

        //adding members for test
        members.add(new User("jjdn", "admin1@gmail.com", "member1", LocalDate.now(), "vjnjejl,nf"));
        members.add(new User("pojdn", "admin2@gmail.com", "member2", LocalDate.now(), "vjnjejrl,f,nf"));
        members.add(new User("jrdn", "admin3@gmail.com", "member3", LocalDate.now(), "vjnjejl;nf"));
        members.add(new User("jfndn", "admin4@gmail.com", "member4", LocalDate.now(), "vjnjejrl,nf"));

        adminsModel = new DefaultListModel<>();
        adminsList.setModel(adminsModel);

        membersModel = new DefaultListModel<>();
        membersList.setModel(membersModel);

        //GET POSTS
        descriptionLabel.setText(null); //DESCRIPTION
        groupNameLabel.setText(null); //GROUP NAME

        populatePosts();
        populateAdmins();
        populateMembers();
        startup();

        //to allow selecting a string from the jList
        adminsList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                String selectedAdmin = adminsList.getSelectedValue();

                if (selectedAdmin != null) {
                    System.out.println("Selected admin: " + selectedAdmin);
                }
            }
        });

        membersList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                String selectedAdmin = membersList.getSelectedValue();

                if (membersList != null) {
                    System.out.println("Selected member: " + selectedAdmin);
                }
            }
        });

    }

    private void populatePosts() {
        new UploadPosts(postsScrollPane, posts);
    }

    private void populateAdmins() {
        //to make sure the list is empty
        adminsModel.removeAllElements();

        if (admins == null) {
            adminsModel.addElement("No admins");
        } else {
            for (User admin : admins) {
                adminsModel.addElement(admin.getUsername());
            }
        }
    }

    private void populateMembers() {
        //to make sure the list is empty
        membersModel.removeAllElements();

        if (members == null) {
            adminsModel.addElement("No members");
        } else {
            for (User member : members) {
                adminsModel.addElement(member.getUsername());
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

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        groupPicLabel = new javax.swing.JLabel();
        groupNameLabel = new javax.swing.JLabel();
        postsScrollPane = new javax.swing.JScrollPane();
        editPostButton = new javax.swing.JButton();
        deleteGroupButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        membersList = new javax.swing.JList<>();
        bioLabel = new javax.swing.JLabel();
        bioLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        adminsList = new javax.swing.JList<>();
        promoteButton = new javax.swing.JButton();
        demoteButton = new javax.swing.JButton();
        descriptionLabel = new javax.swing.JLabel();
        changeDescriptionButton = new javax.swing.JButton();
        changeGroupPhotoButton = new javax.swing.JButton();
        removeMemberButton = new javax.swing.JButton();
        addPostButton = new javax.swing.JButton();
        deletePostButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        groupPicLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(groupPicLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(groupPicLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );

        groupNameLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        jScrollPane1.setViewportView(membersList);

        bioLabel.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        bioLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bioLabel.setText("Members");
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

        promoteButton.setText("Promote");
        promoteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                promoteButtonActionPerformed(evt);
            }
        });

        demoteButton.setText("Demote");
        demoteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demoteButtonActionPerformed(evt);
            }
        });

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

        removeMemberButton.setText("Remove");
        removeMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMemberButtonActionPerformed(evt);
            }
        });

        addPostButton.setText("Add Post");

        deletePostButton.setText("Delete Post");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(editPostButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(addPostButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(deletePostButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(deleteGroupButton, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(changeGroupPhotoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(postsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(changeDescriptionButton))
                                .addGap(0, 8, Short.MAX_VALUE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(removeMemberButton)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bioLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addComponent(demoteButton))))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addComponent(promoteButton)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(groupNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(groupNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(deletePostButton)
                                .addGap(18, 18, 18)
                                .addComponent(deleteGroupButton))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(bioLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(demoteButton)
                                .addGap(43, 43, 43)
                                .addComponent(bioLabel)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(promoteButton)
                            .addComponent(removeMemberButton))
                        .addGap(0, 33, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(changeDescriptionButton)
                        .addGap(18, 18, 18)
                        .addComponent(postsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changeGroupPhotoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeGroupPhotoButtonActionPerformed
        String path = changeImage(groupPicLabel);
        //CHANGE IMAGE IN BACKEND
    }//GEN-LAST:event_changeGroupPhotoButtonActionPerformed

    private void changeDescriptionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeDescriptionButtonActionPerformed
        // taking description from creator
        String descriptionInput = JOptionPane.showInputDialog("Enter new description:");
        if (descriptionInput.isEmpty())
            JOptionPane.showMessageDialog(this, "Empty field.", "Invalid Image", JOptionPane.ERROR_MESSAGE);
        else {
            descriptionLabel.setText(descriptionInput);
        }
    }//GEN-LAST:event_changeDescriptionButtonActionPerformed

    private void editPostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPostButtonActionPerformed
        //removes posts from arraylist and from scrollpane

    }//GEN-LAST:event_editPostButtonActionPerformed

    private void demoteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoteButtonActionPerformed
        String selectedAdmin = adminsList.getSelectedValue();
        if (selectedAdmin == null) {
            JOptionPane.showMessageDialog(this, "Choose an admin to be demoted.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            demoteAdmin(selectedAdmin);
        }

    }//GEN-LAST:event_demoteButtonActionPerformed

    private void promoteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_promoteButtonActionPerformed
        String selectedMember = membersList.getSelectedValue();
        if (selectedMember == null)
            JOptionPane.showMessageDialog(this, "Choose a member to be promoted.", "Error", JOptionPane.ERROR_MESSAGE);
        else
            promoteMember(selectedMember);
    }//GEN-LAST:event_promoteButtonActionPerformed

    private void deleteGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteGroupButtonActionPerformed
        //
    }//GEN-LAST:event_deleteGroupButtonActionPerformed

    private void removeMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMemberButtonActionPerformed
        String selectedMember = membersList.getSelectedValue();
        if (selectedMember == null)
            JOptionPane.showMessageDialog(this, "Choose a member to be removed.", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            User member = UserFileManager.getInstance().findUserByUsername(selectedMember);
            if (member != null) {
                members.remove(member);
            }
        }
    }//GEN-LAST:event_removeMemberButtonActionPerformed

    private void adminsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminsListMouseClicked
        //
    }//GEN-LAST:event_adminsListMouseClicked

    private void demoteAdmin(String adminUsername) {
        User selectedAdmin = UserFileManager.getInstance().findUserByUsername(adminUsername);
        if (selectedAdmin != null) {
            admins.remove(selectedAdmin);
            members.add(selectedAdmin);
        }

        //update the JLists
        populateAdmins();
        populateMembers();
    }

    private void promoteMember(String memberUsername) {
        User selectedMember = UserFileManager.getInstance().findUserByUsername(memberUsername);
        if (selectedMember != null) {
            members.remove(selectedMember);
            admins.add(selectedMember);
        }

        //update the JLists
        populateAdmins();
        populateMembers();
    }

    private String changeImage(javax.swing.JLabel imageLabel) {

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
                    Image scaledImage = image.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(scaledImage));

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
    private javax.swing.JLabel bioLabel;
    private javax.swing.JLabel bioLabel1;
    private javax.swing.JButton changeDescriptionButton;
    private javax.swing.JButton changeGroupPhotoButton;
    private javax.swing.JButton deleteGroupButton;
    private javax.swing.JButton deletePostButton;
    private javax.swing.JButton demoteButton;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JButton editPostButton;
    private javax.swing.JLabel groupNameLabel;
    private javax.swing.JLabel groupPicLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> membersList;
    private javax.swing.JScrollPane postsScrollPane;
    private javax.swing.JButton promoteButton;
    private javax.swing.JButton removeMemberButton;
    // End of variables declaration//GEN-END:variables
}
