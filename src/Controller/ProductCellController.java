package Controller;

import Model.Product;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductCellController implements Initializable {

    public Label lblName;
    public Label lblCost;
    public TextField tfQuantity;
    public Button btnDecrease;
    public Button btnIncrease;

    public final Product product;
    private final CustomerOrderController customerOrderController;

    public ProductCellController(Product product, CustomerOrderController customerOrderController) {
        this.product = product;
        this.customerOrderController = customerOrderController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    public void init () {
        lblName.setText(product.getName());
        tfQuantity.setText("0");
        lblCost.setText(String.valueOf(product.getCost()));
        addListener();
    }

    public void addListener () {
        btnDecrease.setOnAction(event -> {
            if (Integer.parseInt(tfQuantity.getText()) > 0) {
                tfQuantity.setText(String.valueOf(Integer.parseInt(tfQuantity.getText()) - 1));
                customerOrderController.updateOrder(product, Integer.parseInt(tfQuantity.getText()));
            }
        });
        btnIncrease.setOnAction(event -> {
                tfQuantity.setText(String.valueOf(Integer.parseInt(tfQuantity.getText()) + 1));
                customerOrderController.updateOrder(product, Integer.parseInt(tfQuantity.getText()));

        });

    }
}
