package Controller;

import Model.Customer;
import Model.Model;
import dbController.CustomerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StaffUserController implements Initializable {
    @FXML
    public VBox customerListVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create sample customer objects
        // TODO : query database

        // Loop through customerList and create AnchorPanes dynamically
        for (Customer customer : Model.getInstance().getCustomerList()) {
            AnchorPane customerItem = createCustomerItem(customer);
            customerListVBox.getChildren().add(customerItem);
        }
    }
    private AnchorPane createCustomerItem(Customer customer) {
        AnchorPane customerItem = new AnchorPane();
        customerItem.prefHeight(50.0);
        customerItem.prefWidth(650.0);
        customerItem.getStyleClass().add("anchor-pane");

        // Create Labels
        Label customerUSRNLabel = new Label(customer.getUsername());
        customerUSRNLabel.setLayoutX(25);
        customerUSRNLabel.setLayoutX(16);
        customerUSRNLabel.getStyleClass().add("computer-id");

        Label customerNameLabel = new Label(customer.getName());
        customerNameLabel.setLayoutX(216);
        customerNameLabel.setLayoutY(16);
        customerNameLabel.getStyleClass().add("computer-id");

        Label customerBalanceLabel = new Label(""+customer.getBalance());
        customerBalanceLabel.setLayoutX(400);
        customerBalanceLabel.setLayoutY(16);
        customerBalanceLabel.getStyleClass().add("computer-id");

        // Create button for "Balance Add"
        Button balanceAddButton = new Button("+NẠP");
        balanceAddButton.setLayoutX(600);
        balanceAddButton.setLayoutY(16);

        // Text field for entering amount
        TextField amountTextField = new TextField();
        amountTextField.setLayoutX(500);
        amountTextField.setLayoutY(16);
        amountTextField.setPrefWidth(100);

        // Handle button click
        balanceAddButton.setOnAction(event -> {
            try {
                int amountToAdd = Integer.parseInt(amountTextField.getText());
                if (amountToAdd <= 0) {
                    throw new NumberFormatException("Please enter a positive amount.");
                }

                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setHeaderText("Confirm Balance Add");
                confirmationAlert.setContentText("Are you sure you want to add " + amountToAdd + "(VND) to " + customer.getName() + "'s balance?");

                confirmationAlert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        customer.addBalance(amountToAdd);
                        customerBalanceLabel.setText("" + customer.getBalance());
                        amountTextField.setText("");
                        // TODO: Update customer balance in database (assuming database connection exists)
                        try {
                            CustomerController.updateCustomer(customer);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

            } catch (NumberFormatException e) {
                // Show error message if invalid amount entered
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Invalid Amount");
                alert.setContentText("Please enter a valid number for amount.");
                alert.showAndWait();
            }
        });

        AnchorPane.setLeftAnchor(customerUSRNLabel, 25.0);
        AnchorPane.setTopAnchor(customerUSRNLabel, 16.0);

        AnchorPane.setLeftAnchor(customerNameLabel, 200.0);
        AnchorPane.setTopAnchor(customerNameLabel, 16.0);

        AnchorPane.setLeftAnchor(customerBalanceLabel, 400.0);
        AnchorPane.setTopAnchor(customerBalanceLabel, 16.0);

        AnchorPane.setLeftAnchor(balanceAddButton, 600.0);
        AnchorPane.setTopAnchor(balanceAddButton, 16.0);

        customerItem.getChildren().addAll(customerUSRNLabel, customerNameLabel, customerBalanceLabel, amountTextField, balanceAddButton);

        return customerItem;
    }
}
