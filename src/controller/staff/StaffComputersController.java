package controller.staff;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Computer;
import model.Model;

public class StaffComputersController implements Initializable {
    @FXML
    public VBox computerListVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Loop through computerList and create AnchorPanes dynamically
        try {
            for (Computer computer : Model.getInstance().getComputerList()) {
                AnchorPane computerItem = createComputerItem(computer);
                computerListVBox.getChildren().add(computerItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private AnchorPane createComputerItem(Computer computer) {
        AnchorPane computerItem = new AnchorPane();
        computerItem.prefHeight(50.0);
        computerItem.prefWidth(650.0);
        computerItem.getStyleClass().add("anchor-pane");

        // Create Labels for ID, Status, and Username
        Label computerIdLabel = new Label(String.valueOf(computer.getHostID()));
        computerIdLabel.setLayoutX(25);
        computerIdLabel.setLayoutX(16);
        computerIdLabel.getStyleClass().add("computer-id");

        Label computerStatusLabel = new Label(computer.getStatus());
        String statusStyle = computer.getStatus().equals("Online") ? "computer-status-on" : "computer-status-off"; // Set style based on status
        computerStatusLabel.setLayoutX(216);
        computerStatusLabel.setLayoutY(16);
        computerStatusLabel.getStyleClass().add(statusStyle);

        Label computerUsernameLabel = new Label(computer.getUsername());
        computerUsernameLabel.setLayoutX(400);
        computerUsernameLabel.setLayoutY(16);
        computerUsernameLabel.getStyleClass().add("computer-id");

        AnchorPane.setLeftAnchor(computerIdLabel, 25.0);
        AnchorPane.setTopAnchor(computerIdLabel, 16.0);

        AnchorPane.setLeftAnchor(computerStatusLabel, 200.0);
        AnchorPane.setTopAnchor(computerStatusLabel, 16.0);

        AnchorPane.setLeftAnchor(computerUsernameLabel, 350.0);
        AnchorPane.setTopAnchor(computerUsernameLabel, 16.0);

        // Add Labels to the AnchorPane
        computerItem.getChildren().addAll(computerIdLabel, computerStatusLabel, computerUsernameLabel);

        return computerItem;
    }
}
