package controller.admin;

import model.RevenueData; // Assuming this class handles data retrieval

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class AdminRevenueController implements Initializable {

    @FXML
    private CategoryAxis xAxis = new CategoryAxis();

    @FXML
    private NumberAxis yAxis = new NumberAxis();

    @FXML
    private LineChart<String, Number> revenueChart = new LineChart<String,Number>(xAxis,yAxis);

    @FXML
    private Series<String, Number> revenueSeries = new XYChart.Series();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	revenueSeries.setName("Daily Revenue");
        try {
            List<RevenueData> revenueData = RevenueData.getRevenueData(); 
            for (RevenueData point : revenueData) {
                revenueSeries.getData().add(new XYChart.Data(point.getDate(), point.getIncome()));
            }
            revenueChart.getData().add(revenueSeries);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}