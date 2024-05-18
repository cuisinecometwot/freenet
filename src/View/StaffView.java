import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder; // For spacing

public class StaffView {

    JFrame f;

    StaffView() {
        //f = new JFrame();
        JFrame f = new JFrame("FreeNet Manager");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel();
        // sample data, should be retrieved from DB
        String[][] data = {
                {"01", "Offline", ""},
                {"02", "Online", "Lucky Duck"},
                {"03", "Offline", ""},
                {"04", "Online", "Yuduke"}
        };
        Font contentFont = new Font("Arial", Font.PLAIN, 20);
        // Column Names, constant
        String[] columnNames = {"#PC", "Status", "User"};
        
        JTable computers = new JTable(data, columnNames);
        computers.getTableHeader().setFont(contentFont);
        
        computers.setRowHeight(30);
        computers.setFont(contentFont);
        
        // Make the table uneditable
        computers.setCellSelectionEnabled(false); // Disable cell selection (optional)
        computers.setRowSelectionAllowed(true);   // Allow row selection (optional)
        computers.setDefaultEditor(Object.class, null); // Make table cannot be edited

        // Enable table sorting
        computers.setAutoCreateRowSorter(true);
        // Put table in ScrollPane,
        // so we can scroll when there are too much records
        JScrollPane sp = new JScrollPane(computers);
        p1.add(sp);

        p1.setPreferredSize(new Dimension(200, 500));

        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(200, 500));

        JPanel p3 = new JPanel();
        p3.setPreferredSize(new Dimension(200, 500));

        JPanel p4 = new JPanel();
        p4.setPreferredSize(new Dimension(200, 500));

        JPanel p5 = new JPanel();
        p4.setPreferredSize(new Dimension(200, 500));

        JTabbedPane tp = new JTabbedPane();
        tp.setTabPlacement(JTabbedPane.LEFT);  // Change to JTabbedPane.RIGHT for placement on the right
        tp.add("Computers", p1);
        tp.add("Customers", p2);
        tp.add("Staffs", p3);
        tp.add("Schedule", p4);
        tp.add("Revenue", p5);

        f.add(tp);

        // Set a larger font for the tab labels
        Font font = new Font("Serif", Font.BOLD, 36); // Adjust font name, style, and size as needed
        tp.setFont(font);
        tp.setBackground(Color.ORANGE);
        tp.setPreferredSize(new Dimension(500, 100));

        ImageIcon imageIcon = new ImageIcon("/home/huuduc2109/Pictures/logo.png");
        JLabel imageLabel = new JLabel("Staff (You)", imageIcon, SwingConstants.LEFT);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //f.add(imageLabel); // Uncomment to add image label (optional)

        f.setSize(750, 500);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new StaffView();
    }
}