package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import plant.GardenManager;

import java.io.IOException;

public class GardenApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GardenApplication.class.getResource("farm-view.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        GardenController controller = fxmlLoader.getController();
        GardenManager gardenManager = new GardenManager();
        GardenTimer gardenTimer = new GardenTimer(gardenManager,controller);
        controller.setGardenManager(gardenManager);
        gardenTimer.start();
        Scene scene = new Scene(anchorPane);
        stage.setTitle("Automated Gardening System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}