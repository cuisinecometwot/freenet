package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class CustomersLoginForm {
	JFrame f;
	JButton submit;
	JTextField username;
	JPasswordField password;

	CustomersLoginForm() {
		f = new JFrame("FreeNet Client Login");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//f.setLayout(new FlowLayout());
		f.setLayout(new GridLayout());
		
		Font font = new Font("Serif", Font.PLAIN, 16);

		username = new JTextField(16);
		username.setFont(font);
		f.add(username);

		password = new JPasswordField(16);
		password.setFont(font);
		f.add(password);

		submit = new JButton("Login");
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
	    f.add(submit);
	
	    f.setLocation(466, 300);
	    f.setSize(400, 210);
	    f.setVisible(true);
	}

	private static boolean validateLogin(String usernameInput, char[] passwordInput) {
		// return true if admin/password
		// TODO: retrieve from database
		return usernameInput.equals("admin") 
				&& Arrays.equals(passwordInput, "password".toCharArray());
	}

	public static void main(String[] args) {
		new CustomersLoginForm();
	}
}