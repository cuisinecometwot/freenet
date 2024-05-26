package Controller;

import Model.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class StaffStaffsController implements Initializable {
    @FXML
    public VBox staffListVBox;

    private final ObservableList<Staff> staffList = FXCollections.observableArrayList(); // ObservableList for ListView items

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create sample Staff objects
        // TODO : query database
        Staff staff1 = new Staff("trunganhnee", "Trung Anh", "Admin");
        Staff staff2 = new Staff("linhcuteeee", "Linh Doan", "Helpdesk");
        Staff staff3 = new Staff("beducdenday", "Huu Duc", "Cashier");

        // Add staffs to the observable list
        staffList.addAll(staff1, staff2, staff3);

        // Loop through staffList and create AnchorPanes dynamically
        for (Staff staff : staffList) {
            AnchorPane staffItem = createStaffItem(staff);
            staffListVBox.getChildren().add(staffItem);
        }
    }
    private AnchorPane createStaffItem(Staff staff) {
        AnchorPane staffItem = new AnchorPane();
        staffItem.prefHeight(50.0);
        staffItem.prefWidth(650.0);
        staffItem.getStyleClass().add("anchor-pane");

        // Create Labels for ID, Status, and Username
        Label staffIdLabel = new Label(staff.getUsername());
        staffIdLabel.setLayoutX(25);
        staffIdLabel.setLayoutX(16);
        staffIdLabel.getStyleClass().add("computer-id");

        Label staffNameLabel = new Label(staff.getName());
        staffNameLabel.setLayoutX(216);
        staffNameLabel.setLayoutY(16);
        staffNameLabel.getStyleClass().add("computer-id");

        Label staffRoleLabel = new Label(staff.getRole());
        staffRoleLabel.setLayoutX(400);
        staffRoleLabel.setLayoutY(16);
        staffRoleLabel.getStyleClass().add("computer-id");

        AnchorPane.setLeftAnchor(staffIdLabel, 25.0);
        AnchorPane.setTopAnchor(staffIdLabel, 16.0);

        AnchorPane.setLeftAnchor(staffNameLabel, 200.0);
        AnchorPane.setTopAnchor(staffNameLabel, 16.0);

        AnchorPane.setLeftAnchor(staffRoleLabel, 400.0);
        AnchorPane.setTopAnchor(staffRoleLabel, 16.0);

        // Add Labels to the AnchorPane
        staffItem.getChildren().addAll(staffIdLabel, staffNameLabel, staffRoleLabel);

        return staffItem;
    }
}
