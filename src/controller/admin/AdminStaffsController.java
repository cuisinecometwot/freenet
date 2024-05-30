package controller.admin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dbController.StaffController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Model;
import model.Staff;

public class AdminStaffsController implements Initializable {

    public Button delBtn;
    @FXML
    private TableView<Staff> AdminStaffs;
    @FXML
    private TableColumn<Staff, String> idStaffColumn;
    @FXML
    private TableColumn<Staff, String> nameColumn;
    @FXML
    private TableColumn<Staff, String> emailColumn;
    @FXML
    private TableColumn<Staff, String> phoneColumn;
    @FXML
    private TableColumn<Staff, Integer> wageColumn;
    @FXML
    private TableColumn<Staff, String> passColumn;
    public TextField idTF;
    public TextField nameTF;
    public TextField emailTF;
    public TextField phoneTF;
    public TextField wageTF;
    public TextField passTF;
    public Button updateBTN;
    public Button addBTN;
    public Button deleteBTN;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idStaffColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("username"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("phoneNum"));
        wageColumn.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("wage"));
        passColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("password"));
        try {
            AdminStaffs.setItems(Model.getInstance().getStaffList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        AdminStaffs.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> populateTextFields(newValue)
        );
        delBtn.setOnAction(event -> delete());
    }



    @FXML
    public void add(javafx.event.ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Staff newStaff = new Staff();
        newStaff.setUsername(idTF.getText());
        newStaff.setName(nameTF.getText());
        newStaff.setEmail(emailTF.getText());
        newStaff.setPhoneNum(phoneTF.getText());
        newStaff.setWage(Integer.parseInt(wageTF.getText()));
        newStaff.setPassword(passTF.getText());
        //try {
        StaffController.addStaff(newStaff);
        Model.getInstance().getStaffList().add(newStaff);
        AdminStaffs.refresh();
        showAlert(Alert.AlertType.INFORMATION, "Success", "Added successfully!");
        clearTextFields();
//}
    }

    @FXML
    public void update(javafx.event.ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        // Get the selected computer from the table
        Staff selectedStaff = AdminStaffs.getSelectionModel().getSelectedItem();

        if (selectedStaff != null) {
            // Update the status of the selected computer
            selectedStaff.setName(nameTF.getText());
            selectedStaff.setEmail(emailTF.getText());
            selectedStaff.setPhoneNum(phoneTF.getText());
            selectedStaff.setWage(Integer.parseInt(wageTF.getText()));
            selectedStaff.setPassword(passTF.getText());
            StaffController.updateStaff(selectedStaff);
            AdminStaffs.refresh();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Updated successfully!");
            clearTextFields();
        } else {
            // Show an alert if no computer is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Computer Selected");
            alert.setContentText("Please select in the table.");
            alert.showAndWait();
        }
    }

       @FXML
    public void delete() {
       // Get the selected staff from the table
        Staff selectedStaff = AdminStaffs.getSelectionModel().getSelectedItem();

        if (selectedStaff != null) {
            try {
                // Delete the computer from the database
               StaffController.deleteStaff(selectedStaff.getUsername());
                Model.getInstance().getStaffList().remove(selectedStaff);
                AdminStaffs.refresh();

                // Show success alert
                showAlert(Alert.AlertType.INFORMATION, "Success", "Deleted successfully!");

                // Clear text fields after deleting
                clearTextFields();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // Show an alert if no computer is selected
            showAlert(Alert.AlertType.WARNING, "No Selection", "No Selected", "Please select in the table.");
        }
    }
    private void populateTextFields(Staff staff) {
        if (staff != null) {
            idTF.setText(staff.getUsername());
            nameTF.setText(staff.getName());
            emailTF.setText(staff.getEmail());
            phoneTF.setText(staff.getPhoneNum());
            wageTF.setText(String.valueOf(staff.getWage()));
            passTF.setText(staff.getPassword());
        } else {
            idTF.clear();
            nameTF.clear();
            emailTF.clear();
            phoneTF.clear();
            wageTF.clear();
            passTF.clear();
        }
    }
    private void clearTextFields() {
        idTF.clear();
        nameTF.clear();
        emailTF.clear();
        phoneTF.clear();
        wageTF.clear();
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