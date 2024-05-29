package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dbController.OrderController;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.util.Duration;
import model.Model;
import model.Order;
import model.OrderItem;
import model.Product;
import view.OrderItemCellFactory;
import view.ProductCellFactory;

public class CustomerOrderController implements Initializable {
    public ListView<OrderItem> lvOrder;
    @FXML
    public Label lblTotal;
    public ListView<Product> lvDrink;
    public Button btnOrder;
    public Label lblError;
    public CustomerMenuController customerMenuController = new CustomerMenuController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lvDrink.setSelectionModel(null);
        try {
            lvDrink.setItems(Model.getInstance().getProducts());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        lvDrink.setCellFactory(e -> new ProductCellFactory(this));
        lvOrder.setItems(Model.getInstance().getOrderItems());
        lvOrder.setCellFactory(e -> new OrderItemCellFactory());
        lblTotal.setText("0");
        addListener();
    }

    public void initProduct () throws SQLException, ClassNotFoundException {
        Model.getInstance().setProducts();
    }

    public void updateOrder(Product product, int quantity) {
        OrderItem item = new OrderItem(product, quantity);
        int sum = 0;
        int check = 0;
            for (OrderItem orderItem: Model.getInstance().getOrderItems()){
                if (orderItem.productObject().equals(product)) {
                        Model.getInstance().getOrderItems().remove(orderItem);
                        if (quantity != 0) {
                            Model.getInstance().getOrderItems().add(item);
                        }
                    check++;
                    break;}
            }
            if (check == 0 ) {
                Model.getInstance().addOrderItem(item);
            }
            for (OrderItem orderItem: Model.getInstance().getOrderItems()){
                sum += orderItem.getCost();
            }
            lblTotal.setText(String.valueOf(sum));
    }

    public void addListener () {
        btnOrder.setOnAction(event -> {
            try {
                submitOrder();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            // new
            lblTotal.setText("0");
        });
    }

    public void submitOrder () throws SQLException, ClassNotFoundException {
        if (Model.getInstance().getCustomer().getBalance() < (Integer.parseInt(lblTotal.getText()) + 5000) ) {
            lblError.setText("Not enough balance!");
            PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));

            // When the pause transition finishes, clear the text of lblError
            visiblePause.setOnFinished(event -> lblError.setText(""));
            visiblePause.play();
        } else {
        	// new
        	lblError.setText("Done!");
            Model.getInstance().setCustomerOrder(new Order(Model.getInstance().orderItems));
            Model.getInstance().getCustomer().setBalance(Model.getInstance().getCustomer().getBalance() - Integer.parseInt(lblTotal.getText()) );
            
            dbController.CustomerController.updateBalance(Model.getInstance().getCustomer());
            
            OrderController.addOrder(Model.getInstance().getCustomerOrder());
            Model.getInstance().orderItems.clear();
            Model.getInstance().setCustomerOrder(null);
        }
    }
}
