package Controller;

import Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class StaffMenuController implements Initializable {
    public Label lblName;
    public Button btnComputers;
    public Button btnUsers;
    public Button btnStaff;
    public Button btnOrders;
    public Button btnSchedules;
    public Button btnLogout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }
    private void addListeners () {
        btnComputers.setOnAction(event -> onComputers());
        btnUsers.setOnAction(event -> onUsers());
        btnStaff.setOnAction(event -> onStaffs());
        btnOrders.setOnAction(event -> onOrders());
        btnSchedules.setOnAction(event -> onSchedule());
    }

    private void onComputers () {
        Model.getInstance().getViewFactory().getStaffSelectedMenuItem().set("Computers");
    }

    private void onUsers () {
        Model.getInstance().getViewFactory().getStaffSelectedMenuItem().set("Users");
    }
    private void onStaffs () {Model.getInstance().getViewFactory().getStaffSelectedMenuItem().set("Staffs");}
    private void onOrders () {Model.getInstance().getViewFactory().getStaffSelectedMenuItem().set("Orders");}
    private void onSchedule () {Model.getInstance().getViewFactory().getStaffSelectedMenuItem().set("Schedule");}
}
