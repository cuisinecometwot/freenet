package Model;

import java.util.List;
import java.time.LocalDateTime;


public class Order {

    private List<OrderItem> orderItems;
    private String hostID;
    private String status;
    private String username;
    private int totalCost = 0;

    private LocalDateTime time;


    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        for (OrderItem orderItem:orderItems) {
            totalCost += orderItem.getCost();
        }
        this.time = LocalDateTime.now();
        this.status = "Pending";
        this.username = Model.getInstance().getCustomer().getUsername();
    }

    public Order(String hostID, String username,LocalDateTime time, List<OrderItem> list, String status) {
        this.hostID = hostID;
        this.username = username;
        this.orderItems = list;
        int totalCost = 0;
        for (OrderItem item : list) {
            totalCost += item.getCost();
        }
        this.totalCost = totalCost;
        this.status = status;
        this.time = time;
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getHostID() {
        return this.hostID;
    }public void setHostID(String id) {
        this.hostID = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setOrderItems(List<OrderItem> list){
        this.orderItems = list;
    }

    public int getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String toString() { // Full detail
        String str = "";
        for (OrderItem orderItem : orderItems) {
            str = str + orderItem.getQuantity() + "x";
            str = str + orderItem.productObject().getName();
            str = str + " + ";
        }
        return str.substring(0, str.length() - 3);
    }

    public String toStringShort() { // Shorten version
        String str = "";
        for (OrderItem orderItem : orderItems) {
            str += orderItem.getQuantity() + "x";
            str += orderItem.productObject().getName();
            str += " + ";
        }
        str = str.substring(0, str.length()-3); // remove last " + "
        if (str.length()>10) return str.substring(0, 10) + "...";
        return str;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
