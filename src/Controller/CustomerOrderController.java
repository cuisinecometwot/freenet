package Controller;

import Model.Model;
import Model.Order;
import Model.OrderItem;
import Model.Product;
import View2.OrderItemCellFactory;
import View2.ProductCellFactory;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

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

        initProduct();
        lvDrink.setSelectionModel(null);
        lvDrink.setItems(Model.getInstance().getProducts());
        lvDrink.setCellFactory(e -> new ProductCellFactory(this));
        lvOrder.setItems(Model.getInstance().getOrderItems());
        lvOrder.setCellFactory(e -> new OrderItemCellFactory());
        lblTotal.setText("0");
        addListener();
    }

    public void initProduct () {
        if (Model.getInstance().getProducts() == null) {
            Model.getInstance().setProducts();
        }

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
        btnOrder.setOnAction(event -> submitOrder());
    }

    public void submitOrder () {
        if (Model.getInstance().getCustomer().getBalance() < (Integer.parseInt(lblTotal.getText()) + 5000) ) {
            lblError.setText("Not enough balance!");
            PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));

            // When the pause transition finishes, clear the text of lblError
            visiblePause.setOnFinished(event -> lblError.setText(""));
            visiblePause.play();
        } else {
            Model.getInstance().setCustomerOrder(new Order(Model.getInstance().orderItems));
            Model.getInstance().getCustomer().setBalance(Model.getInstance().getCustomer().getBalance() - Integer.parseInt(lblTotal.getText()) );
            /*for (OrderItem orderItem:Model.getInstance().getCustomerOrder().getOrderItems()) {
                System.out.println(orderItem.productObject().getName());
            }*/

        }
    }
}
