package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class GardenApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GardenApplication.class.getResource("garden-view.fxml"));
        GardenManager gardenManager = new GardenManager("src/main/files/garden_config.txt");
        fxmlLoader.setControllerFactory((Class<?> c) -> new GardenController(gardenManager));
        AnchorPane anchorPane = fxmlLoader.load();
        // TODO: Where should we put this initializeGarden() ????
        gardenManager.initializeGarden();
        Scene scene = new Scene(anchorPane);
        stage.setTitle("Automated Gardening System");
        stage.setScene(scene);
        stage.show();
        // TEST BEGIN: Weather change event with UI change without press button
        gardenManager.changeWeather(gardenManager.getWeather());
        // TEST BEGIN: Weather change event with UI change without press button
    }

    public static void main(String[] args) {
        launch();
    }
}