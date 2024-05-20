package views;
import javax.swing.*;
import java.awt.*;
public class MyInfoPane extends JPanel {

	  public MyInfoPane() {
	    JLabel avatarLabel = new JLabel(new ImageIcon("/home/")); // Replace with your image path
	    avatarLabel.setPreferredSize(new Dimension(200, 200));  // Adjust size as needed

	    JLabel usernameLabel = new JLabel("Username: huuduc2109");
	    JLabel nameLabel = new JLabel("Name: Little Duck");
	    JLabel emailLabel = new JLabel("Email: email@example.com");
	    JLabel phoneLabel = new JLabel("Phone: +84");
	    JLabel wageLabel = new JLabel("Wage: 0");  // Adjust currency symbol

	    // Use GridLayout with 2 rows and 3 columns
	    JPanel infoGrid = new JPanel(new GridLayout(6, 1, 10, 10));  // Adjust spacing as needed
	    infoGrid.add(avatarLabel);
	    infoGrid.add(usernameLabel);
	    infoGrid.add(nameLabel);
	    infoGrid.add(emailLabel);
	    infoGrid.add(phoneLabel);
	    infoGrid.add(wageLabel);

	    // Add the grid to the panel with some padding
	    add(infoGrid, BorderLayout.CENTER);
	    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	  }
	}

