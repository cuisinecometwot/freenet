package Controller;

import Model.Computer;
import Model.Customer;
import Model.Model;
import dbController.ComputerController;
import dbController.CustomerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminUsersController implements Initializable {
    @FXML
    public TextField userTF;
    @FXML
    public TextField nameTF;
    @FXML
    public TextField emailTF;
    @FXML
    public TextField phoneTF;
    @FXML
    public TextField balanceTF;
    @FXML
    public TextField passTF;
    @FXML
    public Button addBTN;
    @FXML
    public Button updateBTN;
    @FXML
    public TableView<Customer> AdminUsers;
    @FXML
    public TableColumn<Customer,String> passCol;
    @FXML
    public TableColumn<Customer,Integer> balanceCol;
    @FXML
    public TableColumn<Customer,String> phoneCol;
    @FXML
    public TableColumn<Customer,String> emailCol;
    @FXML
    public TableColumn<Customer,String> nameCol;
    @FXML
    public TableColumn<Customer,String> userCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Load data
        //loadCusomerData();

        userCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));
        passCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        // Set items to the table
        AdminUsers.setItems(Model.getInstance().getCustomerList());

        // Set up listener for table row selection
        AdminUsers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> populateTextFields(newValue)
        );

    }




    @FXML
    public void add(javafx.event.ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer newcusCustomer = new Customer();
        newcusCustomer.setUsername(userTF.getText());
        newcusCustomer.setName(nameTF.getText());
        newcusCustomer.setEmail(emailTF.getText());
        newcusCustomer.setPhoneNum(phoneTF.getText());
        newcusCustomer.setBalance(Integer.parseInt(balanceTF.getText()));
        newcusCustomer.setPassword(passTF.getText());
        // try {
        //  UserController.addSCustomer(newcusCustomer);
        if (CustomerController.addCustomer(newcusCustomer)) {
            Model.getInstance().getCustomerList().add(newcusCustomer);
            AdminUsers.refresh();
            showAlert(Alert.AlertType.INFORMATION, "Success", "User added successfully!");
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Fail", "Can't add user.");

        }
        // Clear text fields after adding
        clearTextFields();
        //   } catch (SQLException | ClassNotFoundException e) {
        //      e.printStackTrace();
        // }
    }

    @FXML
    public void update(javafx.event.ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        // Get the selected computer from the table
        Customer selectedCustomer = AdminUsers.getSelectionModel().getSelectedItem();

        if (selectedCustomer != null) {
            // Update the status of the selected computer
            selectedCustomer.setName(nameTF.getText());
            selectedCustomer.setEmail(emailTF.getText());
            selectedCustomer.setPhoneNum(phoneTF.getText());
            selectedCustomer.setBalance(Integer.parseInt(balanceTF.getText()));
            selectedCustomer.setPassword(passTF.getText());
            //      try {
            // Update the user in the database
            if (CustomerController.updateCustomer(selectedCustomer)==1) {
                for (Customer customer:Model.getInstance().getCustomerList()){
                    if (customer.getUsername().equals(selectedCustomer.getUsername())) {
                        customer.setName(selectedCustomer.getName());
                        customer.setEmail(selectedCustomer.getEmail());
                        customer.setPhoneNum(selectedCustomer.getPhoneNum());
                        customer.setBalance(selectedCustomer.getBalance());
                        customer.setPassword(selectedCustomer.getPassword());
                        break;
                    }
                }
                AdminUsers.refresh();
                showAlert(Alert.AlertType.INFORMATION, "Success", "User updated successfully!");
            }
                ;
            userTF.setEditable(true);
            // Clear text fields after updating
            clearTextFields();
            //   } catch (SQLException | ClassNotFoundException e) {
            //     e.printStackTrace();
            //    }
        } else {
            // Show an alert if no computer is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No User Selected");
            alert.setContentText("Please select a user in the table.");
            alert.showAndWait();
        }
    }
    private void populateTextFields(Customer customer) {
        if (customer != null) {
            userTF.setText(String.valueOf(customer.getUsername()));
            userTF.setEditable(false);
            nameTF.setText(customer.getName());
            emailTF.setText(customer.getEmail());
            phoneTF.setText(customer.getPhoneNum());
            balanceTF.setText(String.valueOf(customer.getBalance()));
            passTF.setText(customer.getPassword());
        } else {
            userTF.clear();
            nameTF.clear();
            emailTF.clear();
            phoneTF.clear();
            balanceTF.clear();
            passTF.clear();
        }
    }
    private void clearTextFields() {
        userTF.clear();
        nameTF.clear();
        emailTF.clear();
        phoneTF.clear();
        balanceTF.clear();
        passTF.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText) {
        showAlert(alertType, title, headerText, null);
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        if (contentText != null) {
            alert.setContentText(contentText);
        }
        alert.showAndWait();
    }
}