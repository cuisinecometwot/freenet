package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.OrderItem;

public class OrderItemsCellController implements Initializable {
    public Label lblOrderItemName;
    public Label lblQuantity;
    public final OrderItem orderItem;

    public OrderItemsCellController(OrderItem orderItem) {
        this.orderItem = orderItem;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblOrderItemName.setText(orderItem.productObject().getName());
        lblQuantity.setText(String.valueOf(orderItem.getQuantity()));
    }
}
