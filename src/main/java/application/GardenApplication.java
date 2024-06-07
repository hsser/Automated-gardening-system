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
        gardenManager.startTimer();
        Scene scene = new Scene(anchorPane);
        stage.setTitle("Automated Gardening System");
        stage.setScene(scene);
        stage.show();

        // TEST BEGIN: WeatherChangeEvent with UI change without press button
        gardenManager.changeWeather(gardenManager.getWeather());
        // TEST BEGIN: WeatherChangeEvent with UI change without press button

        // TEST BEGIN: For PestAttackEvent
        gardenManager.printPestToPlotIndex();
        gardenManager.parasite("Whitefly");
        gardenManager.parasite("Spider");
        gardenManager.parasite("Aphid");
        // TEST END: For PestAttackEvent

        // TEST BEGIN: For TemperatureEvent
        gardenManager.temperature(60);
        // TEST BEGIN: For TemperatureEvent

        // TEST BEGIN: For RainyEvent
        gardenManager.rain(10);
        // TEST BEGIN: For RainyEvent
    }

    public static void main(String[] args) {
        launch();
    }
}