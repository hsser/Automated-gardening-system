package UI;

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
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class GardenController {
    @FXML
    private Group soilGroup;
    @FXML
    private Button waterButton, rainButton;
    @FXML
    private Pane rainPane;

    private boolean isWateringMode = false;// Flag to track watering mode, which is toggled by the water button.
    private boolean isWatering = false;  // Flag to track watering state
    private boolean isRaining = false;  // Flag to track raining state

    private Image normalSoil = new Image(getClass().getResourceAsStream("/image/soil/3.png"));
    private Image wetSoil = new Image(getClass().getResourceAsStream("/image/soil/4.png"));
    //private Image drySoil = new Image(getClass().getResourceAsStream("/image/soil/5.png"));




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
        if(!isRaining) {
            createRaindrop(rainPane);
            isRaining = true;
            setAllSoils(wetSoil);
        } else {
            rainPane.getChildren().clear();
            isRaining = false;
        }
        // Reset cursor to default every time the rain button is clicked
        soilGroup.getScene().setCursor(Cursor.DEFAULT);
        // Also, ensure watering mode is deactivated when rain toggled
        isWateringMode = false;
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

}