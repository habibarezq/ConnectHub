package Frontend;

//import Backend.Post;
//import Backend.Story;
import java.awt.BorderLayout;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Newsfeed extends javax.swing.JFrame {

   // private ArrayList<Post> posts;
    //private ArrayList<Story> stories;
    //remove arraylist from abstract class in content creation
    //change the content type to String and its going to be determined by either .jpg if image or text

    public Newsfeed() {
        initComponents();
        setTitle("Newsfeed");
        setContentPane(jPanel1);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        this.posts = new ArrayList<>();
  //      this.stories = new ArrayList<>();
        //jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.Y_AXIS));
//         posts.add(new Post("p1", "user1", "This is my first post!", LocalDateTime.now()));
//        posts.add(new Post("p2", "user2", "Having a great day!", LocalDateTime.now().minusHours(1)));
//        stories.add(new Story("s1", "user1", "path/to/image1.jpg", LocalDateTime.now()));
//        stories.add(new Story("s2", "user2", "path/to/image2.jpg", LocalDateTime.now().minusHours(2)));
        setLayout(new BorderLayout());
        //postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        //add(scrollPane, BorderLayout.CENTER);
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        postsScroll = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        jScrollPane1.setViewportView(jScrollPane2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 239, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );

        postsScroll.setViewportView(jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addComponent(postsScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(postsScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(252, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Newsfeed().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane postsScroll;
    // End of variables declaration//GEN-END:variables
}
