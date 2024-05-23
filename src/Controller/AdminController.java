package Controller;

import Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_view;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case "Users" -> admin_view.setCenter(Model.getInstance().getViewFactory().getAdminUsersView());
                case "Staffs" -> admin_view.setCenter(Model.getInstance().getViewFactory().getAdminStaffsView());
                case "Revenue" -> admin_view.setCenter(Model.getInstance().getViewFactory().getAdminRevenueView());
                case "Schedule" -> admin_view.setCenter(Model.getInstance().getViewFactory().getAdminScheduleView());
                default -> admin_view.setCenter(Model.getInstance().getViewFactory().getAdminComputersView());
            }

        } );
    }
}
