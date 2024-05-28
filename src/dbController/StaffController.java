package dbController;

import Model.Staff;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffController {

    public static ObservableList<Staff> getAllStaff() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM \"User\" WHERE role='staff'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        ObservableList<Staff> staffArrayList = FXCollections.observableArrayList();
        while (rs.next()) {
            String username = rs.getString("username");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String phone_number = rs.getString("phone_number");
            int wage = rs.getInt("wage");
            String password = rs.getString("password");
            Staff staff = new Staff(username ,name,email,phone_number,wage,password);
            staffArrayList.add(staff);
        }
        return staffArrayList;
    }
    public static void updateStaff(Staff staff) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE \"User\" SET  name = ?,email=?,phone_number=?,wage=?,password=? WHERE username = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, staff.getName());
        stm.setString(2, staff.getEmail());
        stm.setString(3, staff.getPhoneNum());
        stm.setInt(4, staff.getWage());
        stm.setString(5, staff.getPassword());
        stm.setString(6,staff.getUsername());
        stm.executeUpdate();
    }
    public static void addStaff(Staff staff) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO \"Staffs\" (username,name,email,phone_number,wage,password) VALUES (?,?,?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setString(1,staff.getUsername());
        stm.setString(2, staff.getName());
        stm.setString(3, staff.getEmail());
        stm.setString(4, staff.getPhoneNum());
        stm.setInt(5, staff.getWage());
        stm.setString(6, staff.getPassword());

        stm.executeUpdate();
    }
    public static int deleteStaff(String username) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM \"User\" WHERE username = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, username);
        return stm.executeUpdate();
    }
}