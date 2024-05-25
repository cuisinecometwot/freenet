package Controller;

import Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerMenuController implements Initializable {
    public Label timerLabel;
    public Label usernameLabel;
    public Label balanceLabel;
    public Button btnOrder;
    public Button btnAccountInfo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    public void addListener () {
        btnAccountInfo.setOnAction(event -> onInfo());
        btnOrder.setOnAction(event -> onOrder());
    }

    private void onInfo () {
        Model.getInstance().getViewFactory().showCustomerAccInfo();
    }
    private void onOrder () {
        Model.getInstance().getViewFactory().showCustomerOrderView();
    }

}
