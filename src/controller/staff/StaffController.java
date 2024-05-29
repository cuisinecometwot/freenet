package controller.staff;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import model.Model;

public class StaffController implements Initializable {

    public BorderPane staff_view;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getStaffSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case "Users" -> staff_view.setCenter(Model.getInstance().getViewFactory().getStaffUsersView());
                case "Staffs" -> staff_view.setCenter(Model.getInstance().getViewFactory().getStaffStaffsView());
                case "Orders" -> staff_view.setCenter(Model.getInstance().getViewFactory().getStaffOrdersView());
                case "Schedule" -> staff_view.setCenter(Model.getInstance().getViewFactory().getStaffScheduleView());
                default -> staff_view.setCenter(Model.getInstance().getViewFactory().getStaffComputersView());
            }

        } );
    }



}
