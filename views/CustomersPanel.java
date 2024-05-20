package views;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class CustomersPanel extends JPanel {

    public CustomersPanel() {
        // Column Names
        String[] columnNames = {"#ID", "username", "Name", "Balance", "Action"};

        // Sample data (replace with actual data retrieval)
        String[][] data = {
                {"1", "huuduc2109", "Duc Dep Trai 2k11", "68686868", "View"},
                {"2", "someone", "Someone", "0", "View"},
                {"3", "someone", "Someone", "0", "View"},
                {"4", "someone", "Someone", "0", "View"},
                {"5", "someone", "Someone", "0", "View"},
                {"6", "someone", "Someone", "0", "View"},
                {"7", "someone", "Someone", "0", "View"},
                {"8", "someone", "Someone", "0", "View"},
                {"9", "someone", "Someone", "0", "View"},
                {"10", "someone", "Someone", "0", "View"},
                {"11", "someone", "Someone", "0", "View"},
                {"12", "someone", "Someone", "0", "View"},
                {"13", "someone", "Someone", "0", "View"},
                {"14", "someone", "Someone", "0", "View"},
                {"15", "someone", "Someone", "0", "View"},
        };

        // Create JTable 
        JTable customers = new JTable(data, columnNames);
        customers.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        customers.getColumnModel().getColumn(0).setMinWidth(50);
        customers.getColumnModel().getColumn(1).setMinWidth(150);
        customers.getColumnModel().getColumn(2).setMinWidth(125);
        customers.getColumnModel().getColumn(3).setMinWidth(150);
        customers.setRowHeight(30);

        // Set font for table content and header
        Font contentFont = new Font("Arial", Font.PLAIN, 20);
        customers.getTableHeader().setFont(contentFont);
        customers.setFont(contentFont);

        // Add button renderer and editor for "Action" column (optional)
        customers.getColumn("Action").setCellRenderer(new ButtonRenderer());
        customers.getColumn("Action").setCellEditor(
                new ButtonEditor(new JCheckBox()));

        // Make the table uneditable
        customers.setCellSelectionEnabled(false);
        customers.setRowSelectionAllowed(true);
        customers.setDefaultEditor(Object.class, null);

        // Enable table sorting
        customers.setAutoCreateRowSorter(true);

        // Put table in ScrollPane
        JScrollPane sp = new JScrollPane(customers);
        sp.setPreferredSize(new Dimension(600, 450));
        add(sp);
        
        //setPreferredSize(new Dimension(300, 500)); // Set preferred size
    }
}