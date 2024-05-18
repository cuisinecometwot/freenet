package dbController;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public static boolean login(String username, String password) {
        String sql = "SELECT username, password FROM public.\"Customer\" WHERE username=? AND password=?";
        try {
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setObject(1, username);
            statement.setObject(2, password);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                if (!result.getString(1).equals(username)) {
                    return false;
                }
                String pwd = result.getString(2);
                if (pwd.equals(password)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}