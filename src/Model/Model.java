package Model;

import View2.ViewFactory;
import dbController.ComputerController;
import dbController.CustomerController;
import dbController.OrderController;
import dbController.StaffController;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class Model {

    private ObservableList<Customer> customerList;

    public ObservableList<Customer> getCustomerList() {
        if (customerList == null) {
            setCustomerList();
        }
        return customerList;
    }
    public void setCustomerList () {
        this.customerList = CustomerController.listCustomer();
    }

    private ObservableList<Computer> computerList;
    public void setComputerList () throws SQLException, ClassNotFoundException {
        this.computerList = ComputerController.getAllComputers();
    }

    public ObservableList<Computer> getComputerList () throws SQLException, ClassNotFoundException {
        if (computerList == null) {
            setComputerList();
        }
        return this.computerList;
    }

    private ObservableList<Staff> staffList;
    public void setStaffList () throws SQLException, ClassNotFoundException {
        this.staffList = StaffController.getAllStaff();
    }

    public ObservableList<Staff> getStaffList () throws SQLException, ClassNotFoundException {
        if (staffList == null) {
            setStaffList();
        }
        return this.staffList;
    }
    /*
    /   Admin
     */
    private Admin admin ;
    public Admin getAdmin(){
        return admin;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }



    /*
            Staff
    /
     */
    private Staff staff;

    public Staff getStaff(){
        return staff;
    }
    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    private ObservableList<Order> orderList;

    public void setOrderList() throws SQLException, ClassNotFoundException {
        this.orderList = OrderController.getOrderList();
    }

    public ObservableList<Order> getOrderList() throws SQLException, ClassNotFoundException {
        if (orderList ==null) {
            setOrderList();
        }
        return orderList;
    }
    /*

    /               For Customer
     */


    private Customer customer;
    private Computer computer;

    private Order customerOrder;

    public void setCustomerOrder (Order order) {
        this.customerOrder = order;
    }
    public Order getCustomerOrder() {
        return customerOrder;
    }


    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public Computer getComputer() {
        return this.computer;
    }




    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
    public ObservableList<OrderItem> orderItems =FXCollections.observableArrayList();

    private ObservableList<Product> products;

    public ObservableList<Product> getProducts() throws SQLException, ClassNotFoundException {
        if (products==null) {
            setProducts();
        }
        return products;
    }
    public void setProducts () throws SQLException, ClassNotFoundException {
        products = OrderController.getProducts();
    }
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

    public void addOrderItem (OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    public ObservableList<OrderItem> getOrderItems() {
        if (orderItems == null) {
            orderItems = FXCollections.observableArrayList();
        }
        return orderItems;
    }


}
