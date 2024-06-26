package view;

import controller.admin.AdminController;
import controller.staff.StaffController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
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





    /*
    *
    * Customer part
    * */

    public void showCustomerAccInfo () {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CustomerAccInfo.fxml"));
        createStageCanBeClosed(loader);
    }

    public void showCustomerOrderView () {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CustomerOrder.fxml"));
        createStageCanBeClosed(loader);
    }


    /*
            Role GUI


     */

    public void showCustomerMenu () {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CustomerMenu.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("FreeNet");
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(screenBounds.getWidth() - 400);
        stage.setY(20);
        stage.setOnCloseRequest(event -> {
            event.consume();
        });
        stage.show();
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
        stage.setTitle("FreeNet");
        
        // Prevent closing from hitting "X" button
        // Only Logout is accepted
        stage.setOnCloseRequest(event -> {
            event.consume();
        });
        stage.show();
    }
    
    private void createStageCanBeClosed(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("FreeNet");
        
        stage.show();
    }
    
    
    public void closeStage (Stage stage) {
        stage.close();
    }

}
