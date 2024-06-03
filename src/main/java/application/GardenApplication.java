package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GardenApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GardenApplication.class.getResource("garden-view.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        GardenController controller = fxmlLoader.getController();
        GardenManager gardenManager = new GardenManager(controller, "src/main/files/garden_config.txt");
        controller.setGardenManager(gardenManager);
        // TODO: Where should we put this initializeGarden() ????
        gardenManager.initializeGarden();
        Scene scene = new Scene(anchorPane);
        stage.setTitle("Automated Gardening System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}