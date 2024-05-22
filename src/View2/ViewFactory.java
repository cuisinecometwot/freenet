package View2;

import Controller.StaffComputers;
import Controller.StaffController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    private AnchorPane computersView;

    public ViewFactory() {}

    public AnchorPane getComputersView() {
        if (computersView == null) {
            try {
                computersView = new FXMLLoader(getClass().getResource("/fxml/StaffComuters.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return computersView;
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
}
