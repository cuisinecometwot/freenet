package controller.admin;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Model;

public class AdminMenuController implements Initializable {
    public Label lblName;
    public Button btnComputers;
    public Button btnUsers;
    public Button btnStaff;
    public Button btnSchedule;
    public Button btnRevenue;
    public Button btnLogout;
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblName.setText(Model.getInstance().getAdmin().getUsername());
        addListeners();
    }
    private void addListeners () {
        btnComputers.setOnAction(event -> onComputers());
        btnUsers.setOnAction(event -> onUsers());
        btnStaff.setOnAction(event -> onStaffs());
        btnRevenue.setOnAction(event -> onRevenue());
        btnSchedule.setOnAction(event -> onSchedule());
        btnLogout.setOnAction(event -> onLogout());
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
    private void onLogout() {
        LocalDateTime logoutDate = LocalDateTime.now();
        System.out.println(logoutDate);
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}

