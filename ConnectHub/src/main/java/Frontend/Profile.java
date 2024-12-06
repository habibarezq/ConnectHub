package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import Backend.*;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Profile extends javax.swing.JFrame {

    //for the friends list
   
    private DefaultListModel<String> friendsModel;
    private ArrayList<String> imagePaths;
   //private void startup(UserProfile profile);
    
    public Profile() {
        setTitle("My Profile");
        initComponents();
        setSize(1000, 593);
        setResizable(false);
        

        // method to retrieve the info of the profile of the logged in user
        //startup(profile);

        imagePaths =new ArrayList<>();
        
        imagePaths.add("C:/Users/habib/Documents/NetBeansProjects/ConnectHub/testScroll/images.jpg");
        imagePaths.add("C:/Users/habib/Documents/NetBeansProjects/ConnectHub/testScroll/download.jpeg");
        imagePaths.add("C:/Users/habib/Documents/NetBeansProjects/ConnectHub/testScroll/download(1).jpeg");
        
        JLabel jLabelTest=new JLabel("Here");
        //Initialize the post panel
        postPanel=new JPanel();
        postPanel.add(jLabelTest);
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        
        // Add the scroll pane to the frame
        JScrollPane scrollPane = new JScrollPane(postPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
        
//        // Create a button to load posts (images)
//        JButton loadImagesButton = new JButton("Load Posts");
//        loadImagesButton.addActionListener(e -> loadImagesFromArrayList());
//        add(loadImagesButton, BorderLayout.SOUTH);
    loadImagesFromArrayList();
        setVisible(true);
        //friendsModel = new DefaultListModel<>();
        //friendsList.setModel(friendsModel);

        //populateFriends();
    }

    public void populateFriends() {
        friendsModel.addElement("John Doe");
    
    }
    
    // This method will be used to load images from the ArrayList and display them in postPanel
    private void loadImagesFromArrayList() {
        // Clear previous posts before adding new ones
        postPanel.removeAll();

        // Loop through the ArrayList and display images
        for (String imagePath : imagePaths) {
            // Create a panel for each post
            JPanel singlePostPanel = new JPanel();
            singlePostPanel.setLayout(new BoxLayout(singlePostPanel, BoxLayout.Y_AXIS));
            singlePostPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Check if the image file exists
            File file = new File(imagePath);
            if (file.exists()) {
                // Load and resize the image
                ImageIcon originalIcon = new ImageIcon(imagePath);
                Image originalImage = originalIcon.getImage();
                int width = 300;  // desired width
                int height = 300;  // desired height
                Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImage);

                // Add the resized image to the post panel
                JLabel imageLabel = new JLabel(resizedIcon);
                singlePostPanel.add(imageLabel);
            } else {
                // Display an error if the image is not found
                JLabel errorLabel = new JLabel("Image not found: " + imagePath);
                errorLabel.setForeground(Color.RED);
                singlePostPanel.add(errorLabel);
            }

            // Add the single post panel to the main post panel
            postPanel.add(singlePostPanel);
            postPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between posts
        }

        // Refresh the UI
        postPanel.revalidate();
        postPanel.repaint();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        profileLabel = new javax.swing.JLabel();
        coverLabel = new javax.swing.JLabel();
        profileButton = new javax.swing.JButton();
        coverButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        bioLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        friendsList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 600));

        profileLabel.setBackground(new java.awt.Color(255, 255, 255));
        profileLabel.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        profileLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profileLabel.setText("Insert a Photo");
        profileLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        coverLabel.setBackground(new java.awt.Color(204, 255, 255));
        coverLabel.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        coverLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coverLabel.setText("Insert a Cover");
        coverLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        coverLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        profileButton.setText("change");
        profileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileButtonActionPerformed(evt);
            }
        });

        coverButton.setText("change");
        coverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coverButtonActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextArea1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextArea1FocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        bioLabel.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        bioLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bioLabel.setText("Insert  a Bio ");
        bioLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jButton1.setText("save Bio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Change Password");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        friendsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(friendsList);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel2.setText("Friends ");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("My Posts");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(157, 157, 157)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bioLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(profileLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                    .addComponent(profileButton))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(coverButton)
                                    .addComponent(coverLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(354, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coverLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profileButton)
                    .addComponent(coverButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bioLabel)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3))
                .addGap(34, 34, 34)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        profileLabel.getAccessibleContext().setAccessibleParent(profileLabel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void profileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileButtonActionPerformed
        // TODO add your handling code here:
        changeImage(profileLabel);
    }//GEN-LAST:event_profileButtonActionPerformed

    private void coverButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coverButtonActionPerformed
        // TODO add your handling code here:
        changeImage(coverLabel);
    }//GEN-LAST:event_coverButtonActionPerformed

    private void jTextArea1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea1FocusGained
        // TODO add your handling code here:
        if (jTextArea1.getText().isEmpty()) {
            jTextArea1.setText("Insert text");
            jTextArea1.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_jTextArea1FocusGained

    private void jTextArea1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea1FocusLost
        // TODO add your handling code here:
        if (jTextArea1.getText().equals("Insert text")) {
            jTextArea1.setText("");
            jTextArea1.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jTextArea1FocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String bio = saveBio(); //TO BE SENT TO THE BACKEND TO SAVE IT 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new password().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private String saveBio() {
        String bio = jTextArea1.getText();
        if (!bio.equals("")) {
            jTextArea1.setText(bio);
            return bio;
        }
        return null;
    }

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
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Profile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bioLabel;
    private javax.swing.JButton coverButton;
    private javax.swing.JLabel coverLabel;
    private javax.swing.JList<String> friendsList;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton profileButton;
    private javax.swing.JLabel profileLabel;
    // End of variables declaration//GEN-END:variables
     private javax.swing.JPanel  postPanel;
}
