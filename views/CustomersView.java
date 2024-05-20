package views;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomersView {
    JFrame f;
    JButton logoutButton;
    // TODO: session time counter, balance
    CustomersView(String username) {
        f = new JFrame("[" + username + "] FreeNet Client");
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        f.setBackground(Color.WHITE);
        Font font = new Font("Serif", Font.BOLD, 20);

        JPanel avatarPanel = new JPanel();
        avatarPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add some padding
        
        JLabel avatarImg = new JLabel(new ImageIcon("src/img/avt.png"));
        avatarImg.setPreferredSize(new Dimension(50, 50));
        
        JLabel avatarLabel = new JLabel("Welcome, " + username + "!");
        avatarLabel.setFont(font);
        avatarPanel.add(avatarImg);
        avatarPanel.add(avatarLabel);
        avatarPanel.setBackground(new java.awt.Color(255,153,51));

        JPanel menuPanel = new JPanel();
        //menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS)); // Arrange buttons vertically
        menuPanel.setLayout(new BorderLayout());
        
        JButton option1Button = new JButton("My Info");
        option1Button.setFont(font);
        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement action for option 1 button click
            	System.out.println("Controller works well!");
            }
        });

        JButton option2Button = new JButton("Place Order");
        option2Button.setFont(font);
        option2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement action for option 2 button click
            }
        });

        menuPanel.add(option1Button, BorderLayout.NORTH);
        menuPanel.add(option2Button, BorderLayout.SOUTH);
        
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logout functionality (e.g., close window, return to login)
            }
        });

        f.setLayout(new BorderLayout());
        f.add(avatarPanel, BorderLayout.NORTH);
        f.add(menuPanel, BorderLayout.CENTER);
        f.add(logoutButton, BorderLayout.SOUTH);

        f.setLocation(866, 0);
        f.setSize(400, 210);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new CustomersView("ichibannnnnnnn");
    }
}