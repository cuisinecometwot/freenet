package views;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class SchedulePanel extends JPanel {

    public SchedulePanel() {
        // Column Names
        String[] columnNames = {"Date", "Shift", "Name"};
        // Date: 01.01.2024
        // Shift: 0800 - 1245
        // Shift may have more than 1 staff

        // Sample data (replace with actual data retrieval)
        String[][] data = {
                {"01.06.2024", "0800 - 1200", "Little Duck"},
                {"01.06.2024", "1200 - 1600", "You, Huang Zhong"},
                {"01.06.2024", "1600 - 2000", "You"},
                {"01.06.2024", "2000 - 0000", "Ying Bu"},
                
                {"02.06.2024", "0800 - 1200", "Little Duck"},
                {"02.06.2024", "1200 - 1600", "You, Huang Zhong"},
                {"02.06.2024", "1600 - 2000", "You"},
                {"02.06.2024", "2000 - 0000", "Ying Bu"},
                
                {"03.06.2024", "0800 - 1200", "You, Huang Zhong"},
                {"03.06.2024", "1400 - 1800", "You"},
                
                {"04.06.2024", "0800 - 1200", "You, Huang Zhong"},
                {"04.06.2024", "1400 - 1800", "You"},
                
                {"05.06.2024", "0800 - 1200", "Little Duck"},
                {"05.06.2024", "1200 - 1600", "You, Huang Zhong"},
                {"05.06.2024", "1600 - 2000", "You"},
                {"05.06.2024", "2000 - 0000", "Ying Bu"},
                
                {"05.06.2024", "0800 - 1200", "Little Duck"},
                {"05.06.2024", "1200 - 1600", "You, Huang Zhong"},
                {"05.06.2024", "1600 - 2000", "You"},
                {"05.06.2024", "2000 - 0000", "Ying Bu"},
        };

        // Create JTable
        JTable schedule = new JTable(data, columnNames);
        schedule.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        schedule.getColumnModel().getColumn(0).setMinWidth(150);
        schedule.getColumnModel().getColumn(1).setMinWidth(150);
        schedule.getColumnModel().getColumn(2).setMinWidth(275);
        schedule.setRowHeight(30);

        // Set font for table content and header
        Font contentFont = new Font("Arial", Font.PLAIN, 20);
        schedule.getTableHeader().setFont(contentFont);
        schedule.setFont(contentFont);

        // Make the table uneditable
        schedule.setCellSelectionEnabled(false);
        schedule.setRowSelectionAllowed(true);
        schedule.setDefaultEditor(Object.class, null);

        // Enable table sorting
        schedule.setAutoCreateRowSorter(true);

        // Put table in ScrollPane
        JScrollPane sp = new JScrollPane(schedule);
        sp.setPreferredSize(new Dimension(600, 450));
        add(sp);

        setPreferredSize(new Dimension(300, 500)); // Set preferred size
    }
}