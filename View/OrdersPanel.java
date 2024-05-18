import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class OrdersPanel extends JPanel {

    public OrdersPanel() {
        // Column Names
        String[] columnNames = {"#PC", "Name", "Items", "Total Cost", "Action"};
        // Items: return item quantity
        // Example: 2 cakes, 1 juice, 3 snacks -> 6
        // Action: View Order popup -> Accept/Decline Buttons

        // Sample data (replace with actual data retrieval)
        String[][] data = {
                {"01", "Lucky Duck", "6", "35000", "View"},
                {"03", "Yuduke", "1", "10000", "View"}
        };

        // Create JTable
        JTable orders = new JTable(data, columnNames);
        orders.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        orders.getColumnModel().getColumn(0).setMinWidth(50);
        orders.getColumnModel().getColumn(1).setMinWidth(200);
        orders.getColumnModel().getColumn(2).setMinWidth(50);
        orders.getColumnModel().getColumn(3).setMinWidth(150);
        orders.setRowHeight(30);

        // Set font for table content and header
        Font contentFont = new Font("Arial", Font.PLAIN, 20);
        orders.getTableHeader().setFont(contentFont);
        orders.setFont(contentFont);

        orders.getColumn("Action").setCellRenderer(new ButtonRenderer());
        orders.getColumn("Action").setCellEditor(
                new ButtonEditor(new JCheckBox()));
        
        // Make the table uneditable
        orders.setCellSelectionEnabled(false);
        orders.setRowSelectionAllowed(true);
        orders.setDefaultEditor(Object.class, null);

        // Enable table sorting
        orders.setAutoCreateRowSorter(true);

        // Put table in ScrollPane
        JScrollPane sp = new JScrollPane(orders);
        sp.setPreferredSize(new Dimension(600, 450));
        add(sp);

        setPreferredSize(new Dimension(300, 500)); // Set preferred size
    }
}