package Frontend;

import Backend.Post;
import Backend.Story;
import Backend.User;
import java.util.ArrayList;
import javax.swing.DefaultListModel;


public class Newsfeed extends javax.swing.JFrame {

    private ArrayList<Post> posts;
    private ArrayList<Story> stories;
    private ArrayList<User> friends; //will be <Friend> later on
    private ArrayList<User> suggestedFriends;
    private DefaultListModel<String> friendsModel;
    private DefaultListModel<String> suggestedFriendsModel;
    private DefaultListModel<String> postsModel;
    //remove arraylist from abstract class in content creation
    //change the content type to String and its going to be determined by either .jpg if image or text

    public Newsfeed() {
        initComponents();
        setTitle("Newsfeed");
        //setContentPane(jPanel1);
        setResizable(false);
        
        this.posts = new ArrayList<>();
        this.stories = new ArrayList<>();
        friendsModel = new DefaultListModel<>();
        suggestedFriendsModel = new DefaultListModel<>();
        //postsModel = new DefaultListModel<>();
        
        //friendsJList.setModel(friendsModel);
       // suggestedFriendsJList.setModel(suggestedFriendsModel);
       // postsJList.setModel(postsModel);
        
        populateFriends();
        pack();
        populateSuggestedFriedns();
        //populatePosts();

    }

    public void populateFriends() //will be <Friend> later on
    {
        friendsModel.addElement("John Doe");
        friendsModel.addElement("Jane Smith");
        friendsModel.addElement("Bob Johnson");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("Jane Smith");
        friendsModel.addElement("Bob Johnson");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("Jane Smith");
        friendsModel.addElement("Bob Johnson");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("Jane Smith");
        friendsModel.addElement("Bob Johnson");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("Jane Smith");
        friendsModel.addElement("Bob Johnson");
    }
    public void populateSuggestedFriedns()
    {
        friendsModel.addElement("Bob Johnson");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("Jane Smith");
        friendsModel.addElement("Bob Johnson");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("Jane Smith");
        friendsModel.addElement("Bob Johnson");
        friendsModel.addElement("John Doe");
        friendsModel.addElement("Jane Smith");
        friendsModel.addElement("Bob Johnson");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel2 = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jScrollPane2);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/AADZ6536.JPEG"))); // NOI18N
        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 436, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Newsfeed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Newsfeed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Newsfeed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Newsfeed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
 try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(Newsfeed.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Newsfeed().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
