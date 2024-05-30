package controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
//import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Model;

public class LoginController implements Initializable{

    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Label lbError;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogin.setOnAction(event -> onLogin());
        // Handle focus movement with arrow keys
        tfUsername.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.DOWN) {
                tfPassword.requestFocus();
            } else if (keyEvent.getCode() == KeyCode.ENTER) {
                onLogin();
            }
        });

        tfPassword.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.UP) {
                tfUsername.requestFocus();
            } else if (keyEvent.getCode() == KeyCode.ENTER) {
                onLogin();
            }
        });
    }

    private void onLogin(){
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        if(username.isEmpty() || password.isEmpty()){
            lbError.setText("Error: Missing text field(s).");
            lbError.setVisible(true);
        }else{
            String result = dbController.LoginController.login(username, password);
            System.out.print(result);
            switch (result) {
                case "customer" -> {
                    LocalDateTime loginDate = LocalDateTime.now();
                    System.out.println(loginDate);
                    Stage stage =   (Stage) btnLogin.getScene().getWindow();
                    Model.getInstance().getViewFactory().closeStage(stage);
                    Model.getInstance().getViewFactory().showCustomerMenu();
                }
                case "staff" -> {
                    Stage stage =   (Stage) btnLogin.getScene().getWindow();
                    Model.getInstance().getViewFactory().closeStage(stage);
                    Model.getInstance().getViewFactory().showStaffWindow();
                }
                case "admin" -> {
                    Stage stage =   (Stage) btnLogin.getScene().getWindow();
                    Model.getInstance().getViewFactory().closeStage(stage);
                    Model.getInstance().getViewFactory().showAdminWindow();
                }
                default -> {
                    lbError.setText("Login Failed!");
                    lbError.setVisible(true);
                }
            }
        }
    }
}
