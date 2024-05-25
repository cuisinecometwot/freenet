package dbController;

import Model.Customer;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController {
    public static int AddStaff(Customer staff) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO public.\"Customer\" VALUES(?,?,?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, staff.getUsername());
        stm.setObject(2, staff.getName());
        stm.setObject(3, staff.getEmail());
        stm.setObject(4, staff.getPhoneNum());
        stm.setObject(5, staff.getBalance());
        stm.setObject(6, staff.getPassword());

        return stm.executeUpdate();
    }

    //public static int deleteStaff(String username) throws ClassNotFoundException, SQLException{
    //String sql = "DELETE FROM public.\"Customer\" WHERE username = "
    //}

    public static int updateStaff(Customer staff) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE public.\"Customer\" SET name = ?, email = ?, phone_num = ?, balance = ?, password = ?";

        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm  = conn.prepareStatement(sql);

        stm.setObject(1, staff.getName());
        stm.setObject(2, staff.getEmail());
        stm.setObject(3, staff.getPhoneNum());
        stm.setObject(4, staff.getBalance());
        stm.setObject(5, staff.getPassword());

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
