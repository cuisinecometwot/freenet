package Controller;

import Model.Order;
import Model.OrderItem;
import Model.Product;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;

import java.net.URL;
import java.util.ResourceBundle;

public class StaffOrdersController implements Initializable {
    @FXML
    public VBox orderListVBox;

    private final ObservableList<Order> orderList = FXCollections.observableArrayList(); // ObservableList for ListView items

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create sample Order objects
        // TODO : query database
        Order order1 = new Order(
                Arrays.asList(
                        new OrderItem(new Product("Bread", 20000), 2),
                        new OrderItem(new Product("Fried Rice", 40000), 1)
                ));
        Order order2 = new Order(
                Arrays.asList(
                        new OrderItem(new Product("Juice", 15000), 1)
                ));
        Order order3 = new Order(
                Arrays.asList(
                        new OrderItem(new Product("Sausage", 10000), 3),
                        new OrderItem(new Product("Bottle of Beer", 20000), 3)
                ));

        // Add orders to the observable list
        orderList.addAll(order1, order2, order3);

        // Loop through orderList and create AnchorPanes dynamically
        for (Order order : orderList) {
            AnchorPane orderItem = createOrderItem(order);
            orderListVBox.getChildren().add(orderItem);
        }
    }
    private AnchorPane createOrderItem(Order order) {
        AnchorPane orderItem = new AnchorPane();
        orderItem.prefHeight(50.0);
        orderItem.prefWidth(650.0);
        orderItem.getStyleClass().add("anchor-pane");

        // Create Labels for ID, Status, and Username
        Label orderIdLabel = new Label(order.getHostID());
        orderIdLabel.setLayoutX(25);
        orderIdLabel.setLayoutX(16);
        orderIdLabel.getStyleClass().add("computer-id");

        Label orderUsernameLabel = new Label(order.getUsername());
        orderUsernameLabel.setLayoutX(216);
        orderUsernameLabel.setLayoutY(16);
        orderUsernameLabel.getStyleClass().add("computer-id");

        Label orderDetailLabel = new Label(order.toStringShort());
        orderDetailLabel.setLayoutX(400);
        orderDetailLabel.setLayoutY(16);
        orderDetailLabel.getStyleClass().add("computer-id");

        Label orderTotalLabel = new Label(""+order.getTotalCost());
        orderTotalLabel.setLayoutX(450);
        orderTotalLabel.setLayoutY(16);
        orderTotalLabel.getStyleClass().add("computer-id");

        /*
         *  Create See More Button for more information
         *  TODO: The popup looks awful! "See More" button itself too. Need correction.
         */
        Button moreButton = new Button("Handle");
        moreButton.setLayoutX(550);
        moreButton.setLayoutY(16);


        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setHeaderText("Confirm Order");
        confirmationAlert.setContentText("Are you sure you want to accept "
                + order.getUsername() + "'s order?\n"
                + order.toString() + "\nTotal: " + order.getTotalCost());
        ButtonType confirmButton = new ButtonType("Confirm");
        ButtonType cancelButton = new ButtonType("Cancel");
        ButtonType waitButton = new ButtonType("Not Now");
        confirmationAlert.getButtonTypes().setAll(confirmButton, cancelButton, waitButton);

        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setHeaderText(order.getUsername() + "'s order");
        infoAlert.setContentText(order.toString() + "\nTotal: " + order.getTotalCost());

        moreButton.setOnAction(event -> {
            if (moreButton.getText() == "Handle") {
                confirmationAlert.showAndWait().ifPresent(response -> {
                    if (response == confirmButton) {
                        // no delete. just move to last index
                        // and mark it as accepted
                        // No undo. But you can see order again.
                        orderList.remove(order);
                        orderList.add(order);
                        orderItem.getStyleClass().remove("anchor-pane");
                        orderItem.getStyleClass().add("anchor-pane-green");
                        orderListVBox.getChildren().remove(orderItem);
                        orderListVBox.getChildren().add(orderItem);
                        moreButton.setText("  View   ");
                    } else if (response == cancelButton) {
                        // same as above
                        orderList.remove(order);
                        orderList.add(order);
                        orderItem.getStyleClass().remove("anchor-pane");
                        orderItem.getStyleClass().add("anchor-pane-red");
                        orderListVBox.getChildren().remove(orderItem);
                        orderListVBox.getChildren().add(orderItem);
                        moreButton.setText("  View   ");
                    }
                });
            }
            else {
                infoAlert.showAndWait();
            }
        });

        AnchorPane.setLeftAnchor(orderIdLabel, 25.0);
        AnchorPane.setTopAnchor(orderIdLabel, 16.0);

        AnchorPane.setLeftAnchor(orderUsernameLabel, 150.0);
        AnchorPane.setTopAnchor(orderUsernameLabel, 16.0);

        AnchorPane.setLeftAnchor(orderDetailLabel, 300.0);
        AnchorPane.setTopAnchor(orderDetailLabel, 16.0);

        AnchorPane.setLeftAnchor(orderTotalLabel, 430.0);
        AnchorPane.setTopAnchor(orderTotalLabel, 16.0);

        AnchorPane.setLeftAnchor(moreButton, 550.0);
        AnchorPane.setTopAnchor(moreButton, 16.0);


        // Add Labels to the AnchorPane
        orderItem.getChildren().addAll(orderIdLabel, orderUsernameLabel,
                orderDetailLabel, orderTotalLabel,
                moreButton);

        return orderItem;
    }
}
