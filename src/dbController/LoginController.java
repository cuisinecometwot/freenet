package dbController;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public static String login(String username, String password) {
        String sql = "SELECT username, password FROM public.\"Customer\" WHERE username like ?";
        try {
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setObject(1, '%' + username);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String usernameResult = result.getString(1);
                String pwd = result.getString(2);
                if (usernameResult.equals("usr." + username)) {
                    if (pwd.equals(password)) {
                        return "user";
                    }
                } else if (usernameResult.equals("sta." + username)) {
                    if (pwd.equals(password)) {
                        return "staff";
                    }
                } else if (usernameResult.equals("adm." + username)) {
                    if (pwd.equals(password)) {
                        return "admin";
                    }

                } else return "invalid";
            }
            return "invalid";
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return "invalid";
        }
    }
}