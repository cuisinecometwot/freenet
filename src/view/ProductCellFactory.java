package view;

import controller.CustomerOrderController;
import controller.ProductCellController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import model.Product;

public class ProductCellFactory extends ListCell<Product> {
    public final CustomerOrderController customerOrderController;

    public ProductCellFactory(CustomerOrderController customerOrderController) {
        this.customerOrderController = customerOrderController;
    }

    @Override
    protected void updateItem(Product product, boolean empty) {
        super.updateItem(product, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ProductCell.fxml"));
            ProductCellController controller = new ProductCellController(product, customerOrderController);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
