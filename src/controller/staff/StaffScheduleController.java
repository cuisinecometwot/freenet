package controller.staff;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Model;
import model.Schedule;

public class StaffScheduleController implements Initializable{
	@FXML
    public VBox schedListVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for (Schedule sched : Model.getInstance().getScheduleList()) {
                AnchorPane schedItem = createSchedItem(sched);
                schedListVBox.getChildren().add(schedItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private AnchorPane createSchedItem(Schedule sched) {
        AnchorPane schedItem = new AnchorPane();
        schedItem.prefHeight(50.0);
        schedItem.prefWidth(650.0);
        schedItem.getStyleClass().add("anchor-pane");

        // Create Labels
        Label schedDateLabel = new Label(sched.getDay().toString());
        schedDateLabel.setLayoutX(25);
        schedDateLabel.setLayoutX(16);
        schedDateLabel.getStyleClass().add("computer-id");

        Label schedShiftLabel = new Label(sched.getShift());
        schedShiftLabel.setLayoutX(216);
        schedShiftLabel.setLayoutY(16);
        schedShiftLabel.getStyleClass().add("computer-id");

        AnchorPane.setLeftAnchor(schedDateLabel, 25.0);
        AnchorPane.setTopAnchor(schedDateLabel, 16.0);

        AnchorPane.setLeftAnchor(schedShiftLabel, 200.0);
        AnchorPane.setTopAnchor(schedShiftLabel, 16.0);
        
        schedItem.getChildren().addAll(schedDateLabel, schedShiftLabel);

        return schedItem;
    }
}
