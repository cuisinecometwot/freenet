package views;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import javax.swing.border.EmptyBorder; // For spacing

public class StaffView {
    JFrame f;
    // TODO: MyInfo
    StaffView(String username) {
        JFrame f = new JFrame("["+username+"] FreeNet Manager"); // there is Admin Version (I guest)
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tp = new JTabbedPane();
        
        
        tp.setTabPlacement(JTabbedPane.LEFT);  // Change to JTabbedPane.RIGHT for placement on the right
        tp.addTab("Computers", new ImageIcon("src/img/computers.png") , new ComputersPanel());
        tp.addTab("Customers", new ImageIcon("src/img/customers.png"),  new CustomersPanel());
        tp.addTab("Staffs", new ImageIcon("src/img/staffs.png"),  new StaffsPanel());
        tp.addTab("Orders", new ImageIcon("src/img/orders.png"),  new OrdersPanel());
        tp.addTab("Schedule", new ImageIcon("src/img/schedule.png"),  new SchedulePanel());
        tp.addTab("My Info", new ImageIcon("src/img/my-info.png"),  new MyInfoPane());
        
        
        f.add(tp);

        tp.setFont(new Font("Serif", Font.BOLD, 36));
        tp.setBackground(new java.awt.Color(255,153,51)); // orenji
        tp.setPreferredSize(new Dimension(500, 100));
        
        f.setSize(900, 500);
        f.setVisible(true);
    }
    public static void main(String[] args) {
        new StaffView("huuduc2109");
    }
}