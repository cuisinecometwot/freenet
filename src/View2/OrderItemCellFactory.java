package View2;

import Controller.OrderItemsCellController;
import Model.OrderItem;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class OrderItemCellFactory extends ListCell<OrderItem> {
    @Override
    protected void updateItem(OrderItem orderItem, boolean empty) {
        super.updateItem(orderItem, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/OrderItemsCell.fxml"));
            OrderItemsCellController controller = new OrderItemsCellController(orderItem);
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
