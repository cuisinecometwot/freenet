package Model;

import View2.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Model {

    private List<Customer> customerList;

    public List<Customer> getCustomerList() {
        if (customerList == null) {
            setCustomerList();
        }
        return customerList;
    }


    public void setCustomerList(){
        //Lay tu database
    }
    private Staff staff;
    /*

    /               For Customer
     */
    private Customer customer;
    private Order customerOrder;

    public void setCustomerOrder(Order order) {
        this.customerOrder = order;
    }

    public Order getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
    public ObservableList<OrderItem> orderItems =FXCollections.observableArrayList();

    private ObservableList<Product> products;
    private final ViewFactory viewFactory;
    private static Model model;
    public Model() {
        this.viewFactory = new ViewFactory();
    }


    public static synchronized Model getInstance(){
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory(){
        return viewFactory;
    }

    public ObservableList<Product> getProducts() {
        return products;
    }
    public void addOrderItem (OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    public ObservableList<OrderItem> getOrderItems() {
        if (orderItems == null) {
            orderItems = FXCollections.observableArrayList();
        }
        return orderItems;
    }

    public void setProducts () {
        products = FXCollections.observableArrayList(
                new Product("Burger", 10000),
                new Product("Fries", 15000),
                new Product("Soda", 10000),
                new Product("Caffe", 10000)
        );
    }
}
