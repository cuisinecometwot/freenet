package views;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class ComputersPanel extends JPanel {

    public ComputersPanel() {
        // Column Names
        String[] columnNames = {"#PC", "Status", "Name", "Session Time", "Action"};

        // Sample data (replace with actual data retrieval)
        String[][] data = {
                {"01", "Idle", "", "", "View"},
                {"02", "Busy", "Lucky Duck", "0:38:55", "View"},
                {"03", "Idle", "", "", "View"},
                {"04", "Busy", "Yuduke", "168:00:00", "View"}
        };

        // Create JTable
        JTable computers = new JTable(data, columnNames);
        computers.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        computers.getColumnModel().getColumn(0).setMinWidth(50);
        computers.getColumnModel().getColumn(1).setWidth(50);
        computers.getColumnModel().getColumn(2).setMinWidth(200);
        computers.getColumnModel().getColumn(3).setMinWidth(150);
        computers.setRowHeight(30);

        // Set font for table content and header
        Font contentFont = new Font("Arial", Font.PLAIN, 20);
        computers.getTableHeader().setFont(contentFont);
        computers.setFont(contentFont);

        // Add button renderer and editor for "Action" column (optional)
        computers.getColumn("Action").setCellRenderer(new ButtonRenderer());
        computers.getColumn("Action").setCellEditor(
                new ButtonEditor(new JCheckBox()));

        // Make the table uneditable
        computers.setCellSelectionEnabled(false);
        computers.setRowSelectionAllowed(true);
        computers.setDefaultEditor(Object.class, null);

        // Enable table sorting
        computers.setAutoCreateRowSorter(true);

        // Put table in ScrollPane
        JScrollPane sp = new JScrollPane(computers);
        sp.setPreferredSize(new Dimension(600, 450));
        add(sp);

        setPreferredSize(new Dimension(300, 500)); // Set preferred size
    }
}