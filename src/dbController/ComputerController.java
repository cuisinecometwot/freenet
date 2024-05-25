package dbController;

import Model.Computer;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ComputerController {
    public static int addComputer(Computer com) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO public.\"Computer\" VALUES (?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, com.getHostID());
        stm.setObject(2, com.getUsername());
        stm.setObject(3, com.getStatus());

        return stm.executeUpdate();
    }

    public static int changeStatus(Computer com) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE public.\"Computer\" SET username = ?, status = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, com.getUsername());
        stm.setObject(2, com.getStatus());

        return stm.executeUpdate();
    }
}
