package testlinechart;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

/**
 * Hier we have learned ,how to create LineChart ,Tooltips with Javafx
 */
public class Controller {
    @FXML
    LineChart<String, Number> lineChart;
    @FXML
    Label lbl;

    public void btn(ActionEvent event) {
        lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.getData().add(new XYChart.Data<String, Number>("Jan", 200));
        series.getData().add(new XYChart.Data<String, Number>("Feb", 500));
        series.getData().add(new XYChart.Data<String, Number>("Mar", 300));
        series.getData().add(new XYChart.Data<String, Number>("Apr", 600));
        series.setName("Pay 1");


        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.getData().add(new XYChart.Data<String, Number>("Jan", 400));
        series1.getData().add(new XYChart.Data<String, Number>("Feb", 100));
        series1.getData().add(new XYChart.Data<String, Number>("Mar", 800));
        series1.getData().add(new XYChart.Data<String, Number>("Apr", 500));
        series1.setName("Pay 2");

        XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
        series2.getData().add(new XYChart.Data<String, Number>("Jan", 500));
        series2.getData().add(new XYChart.Data<String, Number>("Feb", 900));
        series2.getData().add(new XYChart.Data<String, Number>("Mar", 450));
        series2.getData().add(new XYChart.Data<String, Number>("Apr", 650));
        series2.setName("Pay 3");

        lineChart.getData().addAll(series, series1, series2);

        for (final XYChart.Data<String, Number> data : series.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    lbl.setText("X :" + data.getXValue() + "\nY :   " + String.valueOf(data.getYValue()));
                    Tooltip.install(data.getNode(), new Tooltip("X :" + data.getXValue() + "\nY :   " + String.valueOf(data.getYValue())));
                }
            });
        }
    }
}
