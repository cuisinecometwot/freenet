package dbController;

import Model.Customer;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController {
    public static boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO public.\"User\" (\"username\", \"name\", \"email\", \"phone_number\", \"role\", \"password\",\"balance\") VALUES(?,?,?,?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, customer.getUsername());
        stm.setObject(2, customer.getName());
        stm.setObject(3, customer.getEmail());
        stm.setObject(4, customer.getPhoneNum());
        stm.setString(5,"customer");
        stm.setObject(7, customer.getBalance());
        stm.setObject(6, customer.getPassword());
        return stm.executeUpdate() == 1;
    }


    public static ObservableList<Customer> listCustomer () {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        String sql = "SELECT username, name, email, phone_number, password, balance FROM public.\"User\" WHERE role='customer'";

        try {
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                customerList.add(new Customer(result.getString(1),result.getString(2),result.getString(3),
                        result.getString(4),result.getInt(6), result.getString(5)));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }

        //public static int deleteStaff(String username) throws ClassNotFoundException, SQLException{
    //String sql = "DELETE FROM public.\"Customer\" WHERE username = "
    //}

    public static int updateCustomer(Customer customer) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE public.\"User\" SET name = ?, email = ?, phone_number = ?, balance = ?, password = ? WHERE username =?" ;

        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm  = conn.prepareStatement(sql);

        stm.setObject(1, customer.getName());
        stm.setObject(2, customer.getEmail());
        stm.setObject(3, customer.getPhoneNum());
        stm.setObject(4, customer.getBalance());
        stm.setObject(5, customer.getPassword());
        stm.setObject(6,customer.getUsername());

        return stm.executeUpdate();
    }

    public static int searchUsername(String username) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(username) FROM public.\"Customer\" WHERE username LIKE ?";

        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, "%." + username);
        ResultSet result = stm.executeQuery();
        int res = 0;

        while (result.next()){
            res = result.getInt(1);
        }

        return res;
    }
}
