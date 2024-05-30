package dbController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Order;
import model.OrderItem;
import model.Product;

public class OrderController {
    public static ObservableList<Product> getProducts () throws SQLException, ClassNotFoundException {
        ObservableList<Product> list = FXCollections.observableArrayList();
        String sql = "SELECT fdservice_id, name, cost FROM \"FDService\" ORDER BY fdservice_id";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            int cost = rs.getInt("cost");
            int id = rs.getInt("fdservice_id");
            Product product = new Product(id, name, cost);
            list.add(product);
        }
        return list;
    }

    /*public static void useBalance(Order order) throws SQLException, ClassNotFoundException {
    	String sql = "UPDATE \"User\" SET balance = balance - ? WHERE username = ?";
    	Connection conn = DBConnection.getDBConnection().getConnection();
    	PreparedStatement stm = conn.prepareStatement(sql);
    	stm.setInt(1, order.getTotalCost());
    	stm.setString(2, order.getUsername());
    	stm.executeUpdate();
    	
    }*/
    
    public static void addOrder(Order order) throws SQLException, ClassNotFoundException {
    	//useBalance(order);
        String countSql = "SELECT COUNT(order_id) FROM public.\"Orders\"";
        String insertOrderSql = "INSERT INTO public.\"Orders\" (\"order_id\", \"username\", \"order_time\", \"status\", \"total_cost\") VALUES(?,?,?,?,?)";

        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement countStmt = null;
        PreparedStatement insertOrderStmt = null;

        ResultSet resultSet = null;

        try {
            // Get the count of orders
            countStmt = conn.prepareStatement(countSql);
            resultSet = countStmt.executeQuery();
            int res = 0;

            if (resultSet.next()) {
                res = resultSet.getInt(1);
            }

            // Insert into Orders table
            insertOrderStmt = conn.prepareStatement(insertOrderSql);
            insertOrderStmt.setInt(1, res +1); // Increment the order ID
            insertOrderStmt.setString(2, order.getUsername());
            insertOrderStmt.setObject(3, order.getTime());
            insertOrderStmt.setString(4, order.getStatus());
            insertOrderStmt.setInt(5, order.getTotalCost());
            insertOrderStmt.executeUpdate();
            //System.out.println(order);

            // Insert into OrderItems table
            for (OrderItem orderItem : order.getOrderItems()) {
                //System.
                addOrderItems(orderItem,res +1);
            }
        } finally {
            // Close all resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (countStmt != null) {
                countStmt.close();
            }
            if (insertOrderStmt != null) {
                insertOrderStmt.close();
            }
        }
    }
    public static void addOrderItems (OrderItem orderItem, int order_id) throws SQLException, ClassNotFoundException {
        String insertOrderItemSql = "INSERT INTO public.\"OrderItems\" (\"order_id\", \"quantity\", \"sub_total\", \"fdservice_id\") VALUES(?,?,?,?)";
        PreparedStatement insertOrderItemStmt = null;
        Connection conn = DBConnection.getDBConnection().getConnection();;
        insertOrderItemStmt = conn.prepareStatement(insertOrderItemSql);
        insertOrderItemStmt.setInt(1, order_id); // Use the same incremented order ID
        insertOrderItemStmt.setInt(2, orderItem.getQuantity());
        insertOrderItemStmt.setInt(3, orderItem.getCost());
        insertOrderItemStmt.setInt(4, orderItem.productObject().getId());
        insertOrderItemStmt.executeUpdate();
        insertOrderItemStmt.close(); // Close the statement after each use
    }

    public static ObservableList<OrderItem> getOrderItemList (int order_id) throws SQLException, ClassNotFoundException {
        ObservableList<OrderItem> list = FXCollections.observableArrayList();
        String sql = "SELECT quantity, sub_total, fdservice_id  FROM \"OrderItems\" WHERE order_id =?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, order_id);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            int quantity = rs.getInt("quantity");
            int fd_id = rs.getInt("fdservice_id");
            for (Product product:Model.getInstance().getProducts()) {
                if (product.getId() == fd_id) {
                    list.add(new OrderItem(product,quantity));
                }
            }
        }
        return list;

    }
    public static ObservableList<Order> getOrderList () throws SQLException, ClassNotFoundException {
        ObservableList<Order> list = FXCollections.observableArrayList();
        String sql = "SELECT *  FROM \"Orders\" ORDER BY order_time DESC ";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            int order_id = rs.getInt("order_id");
            list.add(new Order(String.valueOf(rs.getInt("order_id")), rs.getString("username"),
                    rs.getTimestamp("order_time").toLocalDateTime(), getOrderItemList(order_id),rs.getString("status")));
        }
        return list;
    }
    public static void changeOrderStatus (Order order, String status) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE public.\"Orders\" SET status = ? WHERE order_id =? ";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, status);
        stm.setInt(2,Integer.parseInt(order.getHostID()));
        stm.executeUpdate();
        
        if (status == "Canceled") { // refund
        	sql = "UPDATE public.\"User\" SET balance = balance+? WHERE username = ? ";
        	stm.setInt(1, 50000);//order.getTotalCost());
        	stm.setString(2, order.getUsername());
        	stm.executeUpdate();
        }
    }
}

