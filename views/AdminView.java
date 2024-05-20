package views;
import javax.swing.*;
import java.awt.*;

public class AdminView {
    JFrame f;
    // TODO: Revenue, MyInfo
    AdminView() {
        JFrame f = new JFrame("[ADMIN] FreeNet Manager");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tp = new JTabbedPane();
        
        
        tp.setTabPlacement(JTabbedPane.LEFT);  // Change to JTabbedPane.RIGHT for placement on the right
        tp.addTab("Computers", new ImageIcon("src/img/computers.png") , new ComputersPanel());
        tp.addTab("Customers", new ImageIcon("src/img/customers.png"),  new CustomersPanel());
        tp.addTab("Staffs", new ImageIcon("src/img/staffs.png"),  new StaffsPanel());
        tp.addTab("Schedule", new ImageIcon("src/img/schedule.png"),  new SchedulePanel());
        tp.addTab("Revenue", new ImageIcon("src/img/revenue.png"),  new RevenuePanel());
        tp.addTab("My Info", new ImageIcon("src/img/my-info.png"),  new MyInfoPane());
        
        
        f.add(tp);

        tp.setFont(new Font("Serif", Font.BOLD, 36));
        tp.setBackground(new java.awt.Color(255,153,51));
        tp.setPreferredSize(new Dimension(500, 100));
        
        f.setSize(900, 500);
        f.setVisible(true);
    }
    public static void main(String[] args) {
        new AdminView();
    }
}