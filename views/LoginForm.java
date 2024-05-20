package views;
import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    public LoginForm() {
        super("FreeNet Manager - Staff Login");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        loginPanel.setBackground(Color.WHITE);

        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");

        JTextField usernameField = new JTextField(15);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        usernameField.setForeground(Color.BLACK);

        JTextField passwordField = new JTextField(15);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        passwordField.setForeground(Color.BLACK);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.ORANGE);
        loginButton.setForeground(Color.WHITE);

        // Add action listener to login button here

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel("")); // Spacer for layout
        loginPanel.add(loginButton);

        add(loginPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}