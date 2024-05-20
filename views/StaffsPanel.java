package views;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class StaffsPanel extends JPanel {

    public StaffsPanel() {
        // Column Names
        String[] columnNames = {"#ID", "Name", "Rank", "Wage"};

        // Sample data (replace with actual data retrieval)
        String[][] data = {
                {"1", "Little Duck", "Director", "secret"}, // a.k.a Admin
                {"2", "You (Yes, it's you!)", "Cashier", "5000000"},
                {"3", "Ying Bu", "Cashier", "secret"},
                {"4", "Huang Zhong", "Technician", "secret"},
                {"5", "Zhao Yun", "Teacher", "secret"}
        };

        // Create JTable
        JTable staffs = new JTable(data, columnNames);
        staffs.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        staffs.getColumnModel().getColumn(0).setMinWidth(50);
        staffs.getColumnModel().getColumn(1).setMinWidth(200);
        staffs.getColumnModel().getColumn(2).setMinWidth(150);
        staffs.getColumnModel().getColumn(3).setMinWidth(150);
        staffs.setRowHeight(30);

        // Set font for table content and header
        Font contentFont = new Font("Arial", Font.PLAIN, 20);
        staffs.getTableHeader().setFont(contentFont);
        staffs.setFont(contentFont);

        // Make the table uneditable
        staffs.setCellSelectionEnabled(false);
        staffs.setRowSelectionAllowed(true);
        staffs.setDefaultEditor(Object.class, null);

        // Enable table sorting
        staffs.setAutoCreateRowSorter(true);

        // Put table in ScrollPane
        JScrollPane sp = new JScrollPane(staffs);
        sp.setPreferredSize(new Dimension(600, 450));
        add(sp);

        setPreferredSize(new Dimension(300, 500)); // Set preferred size
    }
}