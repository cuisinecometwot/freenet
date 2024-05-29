package dbController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Computer;

public class ComputerController {
//    public static int addComputer(Computer com) throws SQLException, ClassNotFoundException {
//        String sql = "INSERT INTO public.\"Computers\" VALUES (?,?,?)";
//        Connection conn = DBConnection.getDBConnection().getConnection();
//        PreparedStatement stm = conn.prepareStatement(sql);
//        stm.setObject(1, com.getHostID());
//        stm.setObject(2, com.getUsername());
//        stm.setObject(3, com.getStatus());
//
//        return stm.executeUpdate();
//    }

    public static int changeStatus(Computer com) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE public.\"Computers\" SET username = ?, status = ? WHERE ";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, com.getUsername());
        stm.setObject(2, com.getStatus());

        return stm.executeUpdate();
    }

    public static ObservableList<Computer> getAllComputers() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM \"Computers\"";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        ObservableList<Computer> computers = FXCollections.observableArrayList();
        while (rs.next()) {
            int hostID = rs.getInt("host_id");

            String status = rs.getString("status");
            String username = rs.getString("username");

            String ip = rs.getString("ip_address");
            String config = rs.getString("configuration");

            int cost = rs.getInt("cost_perhour");

            Computer computer = new Computer(hostID, username,ip,config, cost, status);
            computers.add(computer);
        }

        return computers;
    }
    public static void updatesStatusComputer(Computer computer) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE \"Computers\" SET  status = ? WHERE host_id = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, computer.getStatus());
        stm.setInt(2, computer.getHostID());
        stm.executeUpdate();
    }
    public static void updateComputer (Computer computer) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE \"Computers\" SET ip_address =?, configuration=?,cost_perhour=?,  status = ? WHERE host_id = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, computer.getIpAddress());
        stm.setString(2, computer.getConfig());
        stm.setString(4, computer.getStatus());
        stm.setInt(3, computer.getCostPerHour());
        stm.setInt(5, computer.getHostID());
        stm.executeUpdate();
    }
    public static void addComputer(Computer computer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO \"Computers\" (host_id,ip_address,configuration,cost_perhour, status) VALUES (?, ?, ?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setInt(1, computer.getHostID());
        stm.setString(2, computer.getIpAddress());
        stm.setString(3, computer.getConfig());
        stm.setInt(4, computer.getCostPerHour());
        stm.setString(5, computer.getStatus());
        stm.executeUpdate();
    }
}