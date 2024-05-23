package View2;

import Controller.AdminController;
import Controller.StaffController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    private AnchorPane adminComputersView;
    private AnchorPane adminUsersView;

    private AnchorPane adminStaffsView;
    private AnchorPane adminRevenueView;
    private AnchorPane adminScheduleView;
    private AnchorPane staffComputersView;
    private AnchorPane staffUsersView;

    private AnchorPane staffStaffsView;
    private AnchorPane staffOrdersView;
    private AnchorPane staffScheduleView;
    private final StringProperty staffSelectedMenuItem;
    private final StringProperty adminSelectedMenuItem;
    public ViewFactory() {
        this.staffSelectedMenuItem = new SimpleStringProperty("");
        this.adminSelectedMenuItem = new SimpleStringProperty("");
    }

    public StringProperty getStaffSelectedMenuItem (){
        return staffSelectedMenuItem;
    }
    public StringProperty getAdminSelectedMenuItem (){
        return adminSelectedMenuItem;
    }


    /*
    * Staff Screen and View
    */

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

    /*
            Admin Screen and View

     */
    public AnchorPane getAdminComputersView() {
        if (adminComputersView == null) {
            try {
                adminComputersView = new FXMLLoader(getClass().getResource("fxml/AdminComputers.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return adminComputersView;
    }

    public AnchorPane getAdminStaffsView() {
        if (adminStaffsView == null) {
            try {
                adminStaffsView = new FXMLLoader(getClass().getResource("fxml/AdminStaffs.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return adminStaffsView;
    }
    public AnchorPane getAdminUsersView() {
        if (adminUsersView == null) {
            try {
                adminUsersView = new FXMLLoader(getClass().getResource("fxml/AdminUsers.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return adminUsersView;
    }
    public AnchorPane getAdminRevenueView() {
        if (adminRevenueView == null) {
            try {
                adminRevenueView = new FXMLLoader(getClass().getResource("fxml/AdminRevenue.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return adminRevenueView;
    }
    public AnchorPane getAdminScheduleView() {
        if (adminScheduleView == null) {
            try {
                adminScheduleView = new FXMLLoader(getClass().getResource("fxml/AdminSchedule.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return adminScheduleView;
    }




    public void showLoginWindow () {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Login.fxml"));
        createStage(loader);
    }

    public void showAdminWindow () {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/AdminView.fxml"));
        AdminController adminController = new AdminController();
        loader.setController(adminController);
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
