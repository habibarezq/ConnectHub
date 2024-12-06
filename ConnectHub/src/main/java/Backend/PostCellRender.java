package Backend;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class PostCellRender extends JPanel implements ListCellRenderer<Post>{

    @Override
    public Component getListCellRendererComponent(JList<? extends Post> list, Post value, int index, boolean isSelected, boolean cellHasFocus) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public class PostCellRenderer extends JPanel implements ListCellRenderer<Post> {

    private JLabel titleLabel;
    private JLabel imageLabel;

    public PostCellRenderer() {
        setLayout(new BorderLayout());
        titleLabel = new JLabel();
        imageLabel = new JLabel();

        add(titleLabel, BorderLayout.WEST);
        add(imageLabel, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Post> list, Post post, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        // Set the title
        titleLabel.setText("post1");

        // Set the image
        JFileChooser fileChooser = new JFileChooser();
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
//            try {
//                Image image = ImageIO.read(selectedFile);
//                if (image != null) {
//                    // Scale image to fit within the label
//                    Image scaledImage = image.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
//                    imageLabel.setIcon(new ImageIcon(scaledImage));
//                } else {
//                    JOptionPane.showMessageDialog(this, "The selected file is not a valid image.", "Invalid Image", JOptionPane.ERROR_MESSAGE);
//                }
//            } catch (IOException e) {
//                JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }  // Load the image dynamically
//        imageLabel.setIcon(icon);
//
//        // Handle selection colors
//        if (isSelected) {
//            setBackground(list.getSelectionBackground());
//            setForeground(list.getSelectionForeground());
//        } else {
//            setBackground(list.getBackground());
//            setForeground(list.getForeground());
//        }
//
//        setOpaque(true);  // Make sure the background is visible
//        return this;
//    }
//}

}
        return null;
    }
    }
}
