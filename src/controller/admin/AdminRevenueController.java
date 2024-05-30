package controller.admin;

import model.RevenueData;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
//import javafx.scene.chart.XYChart.Data;
//import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

public class AdminRevenueController implements Initializable {
	@FXML
    LineChart<String, Number> lineChart;
    @FXML
    Label lbl;

    public void initialize(URL url, ResourceBundle resourceBundle) {
	//XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
    XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
    try {
        List<RevenueData> revenueData = RevenueData.getRevenueData(); 
        for (RevenueData point : revenueData) {
        	series.getData().add(new XYChart.Data(point.getDate(), point.getIncome()));
        }
        lineChart.getData().add(series);
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
	series.setName("Revenue");


	lineChart.getData().add(series);

    }
}
