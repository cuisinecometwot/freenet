package Controller;

import Model.Computer;
import Model.Model;
import dbController.ComputerController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminComputersController implements Initializable {

    @FXML
    private TableView<Computer> AdminComputers;
    @FXML
    private TableColumn<Computer, Integer> idHostColumn;
    @FXML
    private TableColumn<Computer, String> usernameColumn;
    @FXML
    private TableColumn<Computer, String> statusColumn;
    @FXML
    private TableColumn<Computer, String> ipColumn;
    @FXML
    private TableColumn<Computer, String> configColumn;
    @FXML
    private TableColumn<Computer, Integer> costColumn;

    @FXML
    private TextField idTF;

    @FXML
    private TextField statusTF;
    @FXML
    private TextField ipTF;

    @FXML
    private TextField configTF;
    @FXML
    private TextField costTF;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Load data

        idHostColumn.setCellValueFactory(new PropertyValueFactory<>("hostID"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        ipColumn.setCellValueFactory(new PropertyValueFactory<>("ipAddress"));
        configColumn.setCellValueFactory(new PropertyValueFactory<>("config"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("costPerHour"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        // Set items to the table
        try {
            AdminComputers.setItems(Model.getInstance().getComputerList());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Set up listener for table row selection
        AdminComputers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> populateTextFields(newValue)
        );

    }

    @FXML
    public void add(javafx.event.ActionEvent actionEvent) {
        Computer newComputer = new Computer();
        newComputer.setHostID(Integer.parseInt(idTF.getText()));
        newComputer.setIpAddress(ipTF.getText());
        newComputer.setConfig(configTF.getText());
        newComputer.setCostPerHour(Integer.parseInt(costTF.getText()));
        newComputer.setStatus(statusTF.getText());
        System.out.println(newComputer.getCostPerHour());
        System.out.println(newComputer.getConfig());

        try {
            ComputerController.addComputer(newComputer);
            Model.getInstance().getComputerList().add(newComputer);
            AdminComputers.refresh();
            // Show success alert
            showAlert(Alert.AlertType.INFORMATION, "Success", "Computer added successfully!");

            // Clear text fields after adding
            clearTextFields();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void update(javafx.event.ActionEvent actionEvent) {
        // Get the selected computer from the table
        Computer selectedComputer = AdminComputers.getSelectionModel().getSelectedItem();

        if (selectedComputer != null) {
            // Update the status of the selected computer
            selectedComputer.setIpAddress(ipTF.getText());
            selectedComputer.setConfig(configTF.getText());
            selectedComputer.setCostPerHour(Integer.parseInt(costTF.getText()));
            selectedComputer.setStatus(statusTF.getText());
            selectedComputer.setStatus(statusTF.getText());
            try {
                // Update the computer in the database
                ComputerController.updateComputer(selectedComputer);
                AdminComputers.refresh();
                // Show success alert
                showAlert(Alert.AlertType.INFORMATION, "Success", "Computer updated successfully!");
                // Clear text fields after updating
                clearTextFields();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // Show an alert if no computer is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Computer Selected");
            alert.setContentText("Please select a computer in the table.");
            alert.showAndWait();
        }
    }
    private void populateTextFields(Computer computer) {
        if (computer != null) {
            idTF.setText(String.valueOf(computer.getHostID()));
            ipTF.setText(computer.getIpAddress());
            configTF.setText(computer.getConfig());
            costTF.setText(String.valueOf(computer.getCostPerHour()));
            statusTF.setText(computer.getStatus());
        } else {
            idTF.clear();
            ipTF.clear();
            configTF.clear();
            costTF.clear();
            statusTF.clear();
        }
    }
    private void clearTextFields() {
        idTF.clear();
        ipTF.clear();
        configTF.clear();
        costTF.clear();
        statusTF.clear();
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