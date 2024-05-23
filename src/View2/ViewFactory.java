package View2;

import Controller.StaffController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    private AnchorPane staffComputersView;
    private AnchorPane staffUsersView;

    private AnchorPane staffStaffsView;
    private AnchorPane staffOrdersView;
    private AnchorPane staffScheduleView;
    private final StringProperty staffSelectedMenuItem;
    public ViewFactory() {
        this.staffSelectedMenuItem = new SimpleStringProperty("");
    }

    public StringProperty getStaffSelectedMenuItem (){
        return staffSelectedMenuItem;
    }

    public AnchorPane getStaffStaffsView() {
        if (staffStaffsView == null) {
            try {
                staffStaffsView = new FXMLLoader(getClass().getResource("fxml/StaffStaffs.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return staffStaffsView;
    }

    public AnchorPane getStaffOrdersView() {
        if (staffOrdersView == null) {
            try {
                staffOrdersView = new FXMLLoader(getClass().getResource("fxml/StaffOrders.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return staffOrdersView;
    }

    public AnchorPane getStaffScheduleView () {
        if (staffScheduleView == null) {
            try {
                staffScheduleView = new FXMLLoader(getClass().getResource("fxml/StaffSchedule.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return staffScheduleView;
    }

    public AnchorPane getStaffComputersView() {
        if (staffComputersView == null) {
            try {
                staffComputersView = new FXMLLoader(getClass().getResource("fxml/StaffComputers.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return staffComputersView;
    }

    public AnchorPane getStaffUsersView() {
        if (staffUsersView == null) {
            try {
                staffUsersView = new FXMLLoader(getClass().getResource("fxml/StaffUsers.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return staffUsersView;
    }

    public void showLoginWindow () {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Login.fxml"));
        createStage(loader);
    }

    public void showStaffWindow () {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/StaffView.fxml"));
        StaffController staffController = new StaffController();
        loader.setController(staffController);
        createStage(loader);
    }


    private void createStage (FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Freenet");
        stage.show();
    }
    public void closeStage (Stage stage) {
        stage.close();
    }

}
