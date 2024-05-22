package main;

import Model.Model;
import View2.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.text.View;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        /*Parent root = FXMLLoader.load(getClass().getResource("/View2/fxml/StaffMenu.fxml"));
        stage.setTitle("Log in");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();*/
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    public static void main(String[] args){
        launch(args);
    }
}
