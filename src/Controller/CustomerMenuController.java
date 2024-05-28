package Controller;

import Model.Model;
import dbController.CustomerController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CustomerMenuController implements Initializable {
    public Label timerLabel;
    public Label usernameLabel;
    public Label balanceLabel;
    public Button btnOrder;
    public Button btnAccountInfo;
    public Button btnLogout;

    private int secondsRemaining;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTimer();
        usernameLabel.setText(Model.getInstance().getCustomer().getUsername());
        balanceLabel.setText(String.valueOf(Model.getInstance().getCustomer().getBalance()));
        addListener();
    }
    public void updateTimer () {
        secondsRemaining = Model.getInstance().getCustomer().getBalance()* 3600 /5000;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            secondsRemaining--;
            updateTimerLabel();
            if (secondsRemaining <= 0) {
                try {
                    onLogout();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                ((Timeline)event.getSource()).stop();
            } else if (secondsRemaining % 30 == 0) {
                Model.getInstance().getCustomer().setBalance(Model.getInstance().getCustomer().getBalance() - 30*5000/3600);
                balanceLabel.setText(String.valueOf(Model.getInstance().getCustomer().getBalance()));
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    private void updateTimerLabel() {
        int hours = secondsRemaining / 3600;
        int minutes = (secondsRemaining % 3600) / 60;
        int seconds = secondsRemaining % 60;
        timerLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    public void addListener () {
        btnAccountInfo.setOnAction(event -> onInfo());
        btnOrder.setOnAction(event -> onOrder());
        btnLogout.setOnAction(event -> {
            try {
                onLogout();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        balanceLabel.textProperty().addListener((observable, oldValue, newValue) -> {
            secondsRemaining = Model.getInstance().getCustomer().getBalance()* 3600 /5000;
            updateTimerLabel();
        });
    }

    private void onInfo () {
        Model.getInstance().getViewFactory().showCustomerAccInfo();
    }
    private void onOrder () {
        Model.getInstance().getViewFactory().showCustomerOrderView();
    }

    private void onLogout() throws SQLException, ClassNotFoundException {
        LocalDateTime logoutDate = LocalDateTime.now();
        CustomerController.updateCustomer(Model.getInstance().getCustomer());
        System.out.println(logoutDate);
        Stage stage =   (Stage) btnLogout.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}
