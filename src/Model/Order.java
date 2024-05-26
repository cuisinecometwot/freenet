package Model;

import java.util.List;

public class Order {

    private List<OrderItem> orderItems;

    private int totalCost = 0;

    public Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        for (OrderItem orderItem:orderItems) {
            totalCost += orderItem.getCost();
        }
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

}
