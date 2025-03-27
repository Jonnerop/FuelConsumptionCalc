package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../hello-view.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Fuel Consumption Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
