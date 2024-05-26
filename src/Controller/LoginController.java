package Controller;

import Model.Customer;
import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    private AnchorPane login;
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Label lbError;

    dbController.LoginController log = new dbController.LoginController();

    /*public void initialize(){
        lbError.setVisible(false);
    }

    public void handleLoginButtonAction(ActionEvent actionEvent) {
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        if(username.isEmpty() || password.isEmpty()){
            lbError.setText("Error: Missing field text");
            lbError.setVisible(true);
        }else{
            String result = log.login(username, password);
            if(result.equals("invalid")){
                lbError.setText("Error: Invalid account");
                lbError.setVisible(true);
            }else if(result.equals("admin")){
                System.out.println("Admin");
            }else if(result.equals("staff")) {
                try{
                    AnchorPane user = FXMLLoader.load(getClass().getResource(("/sms/view/fxml/MainDashboard.fxml")));
                    login.getChildren().setAll(user);
                }catch (IOException e){
                    e.printStackTrace();
                }

            }else if(result.equals("user")) {
                System.out.println("User");
            }
        }
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogin.setOnAction(event -> onLogin());
    }

    private void onLogin(){
        Customer customer = new Customer("kazuma","Duy","duy@sonthomg","0123",25000, "123456");
        Model.getInstance().setCustomer(customer);
        LocalDateTime loginDate = LocalDateTime.now();
        System.out.println(loginDate);
        Stage stage =   (Stage) btnLogin.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showCustomerMenu();

    }
}
