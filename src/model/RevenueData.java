package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

public class RevenueData {
	private String date;
    private int income;

    public RevenueData(String date, int income) {
        this.date = date;
        this.income = income;
    }

    public String getDate() {
        return date;
    }

    public int getIncome() {
        return income;
    }
    
    public static List<RevenueData> getRevenueData() throws SQLException, ClassNotFoundException {
        List<RevenueData> data = new ArrayList<>();

        try {
        	String sql = "SELECT * FROM \"Revenue\" ORDER BY day";
        	Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                data.add(new RevenueData(rs.getString(1), rs.getInt(2)));
            }
        } finally {
        }

        return data;
    }
}
