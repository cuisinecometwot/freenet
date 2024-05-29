package dbController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;
import model.Admin;
import model.Customer;
import model.Model;
import model.Staff;

public class LoginController {
    public static String login(String username, String password) {
        String sql = "SELECT username, name, email, phone_number, password, role, wage, balance FROM public.\"User\" WHERE username = ?";
        try {
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setObject(1,username);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String usernameResult = result.getString(1);
                String pwd = result.getString(5);
                String role = result.getString(6);
                if (usernameResult.equals(username) && pwd.equals(password) ) {
                    switch (role) {
                        case "customer" -> {
                            Model.getInstance().setCustomer(new Customer(usernameResult, result.getString(2), result.getString(3),
                                    result.getString(4), result.getInt(8), result.getString(5)));
                            return "customer";
                        }
                        case "staff" -> {
                            Model.getInstance().setStaff(new Staff(usernameResult, result.getString(2), result.getString(3),
                                    result.getString(4), result.getInt(7), result.getString(5)));
                            return "staff";
                        }
                        case "admin" -> {
                            Model.getInstance().setAdmin(new Admin(usernameResult, result.getString(2), pwd));
                            return "admin";
                        }
                    }
                } else return "Invalid"; // what is the diff between Invalid and null?
            }
            return null;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}