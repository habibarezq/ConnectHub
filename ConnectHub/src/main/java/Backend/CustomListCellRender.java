package Backend;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CustomListCellRender extends JLabel implements  ListCellRenderer<String> {
    
    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        // Set the text for the item
        setText(value);

        // Example: Use an ImageIcon for the image part
        ImageIcon icon = new ImageIcon("path/to/your/image.jpg");  // Change path dynamically if needed
        setIcon(icon);

        // Handle the selected state
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setOpaque(true); // Make sure the background is visible
        return this;
    }

}
