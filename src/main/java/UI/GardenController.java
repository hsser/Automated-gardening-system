package UI;

import environment.Weather;
import environment.WeatherChangeEvent;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.ImageCursor;
import javafx.scene.Cursor;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class GardenController {
    @FXML
    private Group soilGroup;
    @FXML
    private Button waterButton, rainButton, plantButton, exitButton;
    @FXML
    private Pane rainPane, overlayPane;
    @FXML
    private AnchorPane popupPane;
    @FXML
    private ImageView sunny;

    private boolean isWateringMode = false;// Flag to track watering mode, which is toggled by the water button.
    private boolean isWatering = false;  // Flag to track watering state

    private Image normalSoil = new Image(getClass().getResourceAsStream("/image/soil/3.png"));
    private Image wetSoil = new Image(getClass().getResourceAsStream("/image/soil/4.png"));
    //private Image drySoil = new Image(getClass().getResourceAsStream("/image/soil/5.png"));

    private Weather weather = new Weather();  // System's current weather, default is sunny

    @FXML
    private void initialize() {
        // Optionally initialize something
    }

    @FXML
    private void handleWaterButtonClick() {
        isWateringMode = !isWateringMode;  // Toggle watering mode
        if (isWateringMode) {
            // Change cursor to indicate watering mode
            soilGroup.getScene().setCursor(new ImageCursor(new Image(getClass().getResourceAsStream("/image/icon/water_cursor.png")), 0, 0));
        } else {
            // Reset cursor to default when disabling watering mode
            soilGroup.getScene().setCursor(Cursor.DEFAULT);
        }
    }

    @FXML
    private void handleSoilClick(MouseEvent event) {
        if (isWateringMode) {
            ImageView clickedSoil = (ImageView) event.getSource();
            clickedSoil.setImage(wetSoil);
        }

    }

    private void setAllSoils(Image soilImage) {
        for (Node node : soilGroup.getChildren()) {
            if (node instanceof ImageView) {
                ImageView soil = (ImageView) node;
                soil.setImage(soilImage);
            }
        }
    }

    @FXML
    protected void handleRainButtonClick() {
        if(weather.isSunny()) {
            createRaindrop(rainPane);
            setAllSoils(wetSoil);
            animateSunnyImage(1.0, 0.1, false);
        } else {
            rainPane.getChildren().clear();
            animateSunnyImage(0.1, 1.0, true);
        }
        WeatherChangeEvent weatherChangeEvent = new WeatherChangeEvent(weather);
        weatherChangeEvent.trigger();
        // Reset cursor to default every time the rain button is clicked
        soilGroup.getScene().setCursor(Cursor.DEFAULT);
        // Also, ensure watering mode is deactivated when rain toggled
        isWateringMode = false;
    }

    private void animateSunnyImage(double fromScale, double toScale, boolean setVisibleAfter) {
        sunny.setVisible(true);

        // Create a scale transition for the ImageView
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.5), sunny);
        scaleTransition.setFromX(fromScale);
        scaleTransition.setFromY(fromScale);
        scaleTransition.setToX(toScale);
        scaleTransition.setToY(toScale);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(false);

        if (!setVisibleAfter) {
            scaleTransition.setOnFinished(event -> sunny.setVisible(false));
        }

        scaleTransition.play();
    }

    private void createRaindrop(Pane pane) {
        for (int i = 0; i < 100; i++) {  // Number of raindrops
            Rectangle drop = new Rectangle(2, 20);  // Creating a drop as a small rectangle
            drop.setX(Math.random() * pane.getWidth());  // Randomize the starting position x
            drop.setY(-10);  // Start above the pane
            drop.setStyle("-fx-fill: rgba(255, 255, 255, 0.6);");  // Set color and partial transparency

            pane.getChildren().add(drop);

            // Create the animation for the drop
            TranslateTransition animation = new TranslateTransition(Duration.seconds(1 + Math.random()), drop);
            animation.setFromY(20);
            animation.setToY(pane.getHeight() + 20);  // Let the drop fall below the pane
            animation.setCycleCount(TranslateTransition.INDEFINITE);  // Repeat indefinitely
            animation.play();
        }
    }

    @FXML
    private void handlePlantButtonClick(){
        boolean isVisible = popupPane.isVisible();
        popupPane.setVisible(!isVisible);
        popupPane.setManaged(!isVisible);
        overlayPane.setVisible(!isVisible);
        overlayPane.setManaged(!isVisible);

    }

    @FXML
    private void handleExitButtonClick(){
        popupPane.setVisible(false);
        overlayPane.setVisible(false);
    }

}