package Controller;

import Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Label lblName;
    public Button btnComputers;
    public Button btnUsers;
    public Button btnStaff;

    public Button btnLogout;
    public Button btnSchedule;
    public Button btnRevenue;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }
    private void addListeners () {
        btnComputers.setOnAction(event -> onComputers());
        btnUsers.setOnAction(event -> onUsers());
        btnStaff.setOnAction(event -> onStaffs());
        btnRevenue.setOnAction(event -> onRevenue());
        btnSchedule.setOnAction(event -> onSchedule());
    }

    private void onComputers () {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Computers");
    }

    private void onUsers () {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Users");
    }
    private void onStaffs () {Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Staffs");}
    private void onRevenue () {Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Revenue");}
    private void onSchedule () {Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Schedule");}
}

