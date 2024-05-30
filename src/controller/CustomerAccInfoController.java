package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Model;

public class CustomerAccInfoController implements Initializable {
    public TextField tfUsername;
    public TextField tfName;
    public TextField tfEmail;
    public TextField tfPhoneNumber;
    public Button btnEdit;
    public Button btnSave;
    public Button btnCancel;
    public PasswordField tfOldPassword;
    public PasswordField tfNewPassword;
    public PasswordField tfConfirmNewPassword;
    public Button btnChangePassword;
    public Label lblBalance;
    public Label lblPassError;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();

        addListner();
    }
    private void addListner () {
        btnEdit.setOnAction(event -> editBtnPressed());
        btnSave.setOnAction(event -> saveBtnPressed());
        btnCancel.setOnAction(event -> cancelBtnPressed());
        btnChangePassword.setOnAction(event -> changePassBtnPressed());
    }

    public void init (){
        tfUsername.setText(Model.getInstance().getCustomer().getUsername());
        tfName.setText(Model.getInstance().getCustomer().getName());
        tfEmail.setText(Model.getInstance().getCustomer().getEmail());
        tfPhoneNumber.setText(Model.getInstance().getCustomer().getPhoneNum());
        lblBalance.setText(String.valueOf(Model.getInstance().getCustomer().getBalance()));
    }
    public void edited () {
        btnCancel.setVisible(false);
        btnSave.setVisible(false);
        btnEdit.setVisible(true);
        tfName.setEditable(false);
        tfEmail.setEditable(false);
        tfPhoneNumber.setEditable(false);
    }

    private void editBtnPressed () {
        tfEmail.setEditable(true);
        tfPhoneNumber.setEditable(true);
        tfName.setEditable(true);
        btnEdit.setVisible(false);
        btnCancel.setVisible(true);
        btnSave.setVisible(true);

        //Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        tfPhoneNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 10) {
                    tfPhoneNumber.setText(oldValue);
                }
            }
        });
    }

    private void cancelBtnPressed() {
        init();
        edited();
    }
    private void saveBtnPressed () {
        Model.getInstance().getCustomer().setEmail(tfEmail.getText());
        Model.getInstance().getCustomer().setName(tfName.getText());
        Model.getInstance().getCustomer().setPhoneNum(tfPhoneNumber.getText());
        init();
        edited();
    }

    private void changePassBtnPressed () {
        String oldPassword = tfOldPassword.getText();
        String newPassword = tfNewPassword.getText();
        String newPasswordConfirm = tfConfirmNewPassword.getText();
        if (oldPassword.isEmpty() || newPassword.isEmpty() || newPasswordConfirm.isEmpty() ) {
            lblPassError.setText("Missing input fields!");
        } else if (!Model.getInstance().getCustomer().getPassword().equals(oldPassword) ) {
            lblPassError.setText("Password incorrect!");
        } else if (!newPasswordConfirm.equals(newPassword)) {
            lblPassError.setText("Password confirmation incorrect!");
        } else  {
            Model.getInstance().getCustomer().setPassword(newPassword);
            lblPassError.setText("Password changed.");
            tfOldPassword.clear();
            tfNewPassword.clear();
            tfConfirmNewPassword.clear();
        }
    }
}
