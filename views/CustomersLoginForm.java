package views;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

public class CustomersLoginForm {  
	JFrame f;
	JButton submit;
	JTextField username;
	JPasswordField password;
	
	CustomersLoginForm(){  
		f = new JFrame("FreeNet Client Login");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		f.getContentPane().setBackground(new java.awt.Color(255, 102, 0));
		Font font = new Font("Serif", Font.PLAIN, 26);   
		JLabel usernameLabel = new JLabel("Username");
		JLabel passwordLabel = new JLabel("Password");
        
        JPanel panel0 = new JPanel();
        panel0.setBounds(450, 167, 465, 410);
        panel0.setBackground(new java.awt.Color(255, 153, 102));
        
        JPanel panel = new JPanel();
        panel.setBounds(455,172,455,400);  
        panel.setBackground(Color.WHITE);  
        //panel.setBorder(border);
        
        JButton b1=new JButton("Button 1");     
        b1.setBounds(50,100,80,30);    
        b1.setBackground(Color.yellow);   
        
        JButton b2=new JButton("Button 2");   
        b2.setBounds(100,100,80,30);    
        b2.setBackground(Color.green);  
        
        username = new JTextField(16);
		username.setFont(font);
		usernameLabel.setFont(font);
		
		//username.setBounds(10, 200, 300, 200);
		//usernameLabel.setBounds(50, 210, 400, 50);
		
		password = new JPasswordField(16);
		password.setFont(font);
		passwordLabel.setFont(font);
		
        
        f.add(panel);
        f.add(panel0);
        
        panel.add(usernameLabel); panel.add(username);
        panel.add(passwordLabel); panel.add(password);
        
        submit = new JButton("Login");
		submit.setFont(font);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usernameInput = username.getText();
				char[] passwordInput = password.getPassword();
				if (validateLogin(usernameInput, passwordInput)) {
					JOptionPane.showMessageDialog(f, "Login Successful!");
				} else
					JOptionPane.showMessageDialog(f, "Invalid username or password!");
				// Clear password after attempt
				Arrays.fill(passwordInput, '0');
			}
		});
		JLabel blank = new JLabel();
		panel.add(submit);
		
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setLayout(null);    
        f.setVisible(true);  
        
    }  
	private static boolean validateLogin(String usernameInput, char[] passwordInput) {
		// return true if admin/password
		// TODO: retrieve from database
		return usernameInput.equals("admin") 
				&& Arrays.equals(passwordInput, "password".toCharArray());
	}
    public static void main(String args[]) {  
        new CustomersLoginForm();  
    }  
}  
