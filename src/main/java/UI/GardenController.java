package UI;

import environment.Weather;
import environment.WeatherChangeEvent;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.ImageCursor;
import javafx.scene.Cursor;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.util.Duration;
import javafx.scene.paint.Color;


enum Mode { // Enum to represent the current mode of the garden controller
    WATERING, PLANTING, NONE
}

public class GardenController {
    @FXML
    private Group soilGroup, plantGroup;
    @FXML
    private Button waterButton, rainButton, plantButton, // for watering, raining, and planting
                   cancelButton, confirmButton, // for canceling and confirming planting
                   closeButton; // for closing the popup
    @FXML
    private Label soilInfoLabel, plantTypeValue, plantNumberValue, humidityValue, temperatureValue, attackStatusValue, healthStatusValue;
    @FXML
    private Pane rainPane, overlayPane;
    @FXML
    private AnchorPane popupSelectionPane, popupStatusPane;
    @FXML
    private ImageView sunny;
    @FXML
    private Spinner<Integer> seedQuantitySpinner;
    @FXML
    private GridPane seedSelectionGrid;

    private Mode currentMode = Mode.NONE;  // The current mode of the garden controller
    private String currentSeedType;  // The type of seed currently selected;

    private Image grassSoil = new Image(getClass().getResourceAsStream("/image/soil/1.png"));
    private Image normalSoil = new Image(getClass().getResourceAsStream("/image/soil/3.png"));
    private Image wetSoil = new Image(getClass().getResourceAsStream("/image/soil/4.png"));
    //private Image drySoil = new Image(getClass().getResourceAsStream("/image/soil/5.png"));

    private Weather weather = new Weather();  // System's current weather, default is sunny

    @FXML
    private void initialize() {
        // Initialize the soil images
        setAllSoils(grassSoil);
        // Configure the spinner for integer values with a min, max, and step
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        seedQuantitySpinner.setValueFactory(valueFactory);
    }

    /**
     * Sets the visibility and managed state of a given JavaFX Node.
     *
     * @param node The JavaFX Node to set visibility and managed state.
     * @param isVisible boolean to set specific visibility. If true, the node will be visible and managed, otherwise hidden.
     */
    private void setNodeVisibility(Node node, Boolean isVisible) {
        node.setVisible(isVisible);
        node.setManaged(isVisible);
    }

    /**
     * Handles the click event on the water button.
     */
    @FXML
    private void handleWaterButtonClick() {
        // Toggle watering mode
        currentMode = (currentMode == Mode.WATERING) ? Mode.NONE : Mode.WATERING;
        if (currentMode == Mode.WATERING) {
            // Change cursor to indicate watering mode
            soilGroup.getScene().setCursor(new ImageCursor(new Image(getClass().getResourceAsStream("/image/icon/water_cursor.png")), 0, 0));
            plantButton.setDisable(true);  // Disable the plant button
            rainButton.setDisable(true);  // Disable the rain button
        } else {
            // Reset cursor to default when disabling watering mode
            soilGroup.getScene().setCursor(Cursor.DEFAULT);
            // TODO: Add logic to handle watering mode deactivation, might be needed to disable more buttons
            plantButton.setDisable(false);  // Enable the plant button
            rainButton.setDisable(false);  // Enable the rain button
        }
    }

    /**
     * Handles the watering of a soil when clicked.
     * @param soilId The id of the soil to water.
     */
    private void handleWatering(String soilId) {
        if (currentMode != Mode.WATERING) {
            return;
        }

        // Get the x, y coordinates of the soil
        ImageView clickedSoil = getSoilById(soilId);
        if (clickedSoil == null || clickedSoil.getImage() == grassSoil) {
            return;
        }

        Bounds boundsInScene = clickedSoil.localToScene(clickedSoil.getBoundsInLocal());
        double x = boundsInScene.getMinX();
        double y = boundsInScene.getMinY();
        // Adjust the x, y coordinates to the center of the soil
        x += clickedSoil.getImage().getWidth() / 2;
        y += clickedSoil.getImage().getHeight() / 2;
        // Create a droplet animation at the center of the soil
        createDroplet(x, y);
        clickedSoil.setImage(wetSoil);
        //TODO: Add logic to handle watering of the soil
    }

    /**
     * Handles the planting of a seed when clicked.
     * @param soilId The id of the soil to plant.
     */
    private void handlePlanting(String soilId) {
        if (currentSeedType == null) {
            return;
        }
        ImageView clickedSoil = getSoilById(soilId);
        ImageView plantImageView = (ImageView) plantGroup.lookup("#" + soilId);
        if (plantImageView != null && plantImageView.getImage() == null) {
            clickedSoil.setImage(normalSoil);
            int seedQuantity = seedQuantitySpinner.getValue();
            plantImageView.setImage(new Image(getClass().getResourceAsStream("/image/plants/" + currentSeedType + ".png")));
            // Check if it is raining
            if (!weather.isSunny()) {
                clickedSoil.setImage(wetSoil);
            }
            //TODO: Add logic to handle planting of the seed
            System.out.println("Planting " + seedQuantity + " " + currentSeedType + " seed" +
                    ((seedQuantity > 1) ? "s" : ""));
        }
    }

    /**
     * Sets the values of the plant status labels.
     * @param plantType The type of the plant.
     * @param plantNumber The number of the plant.
     * @param humidity The humidity of the plant.
     * @param temperature The temperature of the plant.
     * @param attackStatus The attack status of the plant.
     * @param healthStatus The health status of the plant.
     */
    private void setLabelValues(String plantType, String plantNumber, String humidity, String temperature, String attackStatus, String healthStatus) {
        plantTypeValue.setText(plantType);
        plantNumberValue.setText(plantNumber);
        humidityValue.setText(humidity);
        temperatureValue.setText(temperature);
        attackStatusValue.setText(attackStatus);
        healthStatusValue.setText(healthStatus);
    }

    /**
     * Shows the information of a soil when clicked.
     * @param soilId The id of the soil to show information of.
     */
    private void showSoilInfo(String soilId) {
        soilInfoLabel.setText("Plot " + soilId + " Conditions");
        ImageView plantImageView = (ImageView) plantGroup.lookup("#" + soilId);
        if (plantImageView != null && plantImageView.getImage() != null) {
            String plantType = plantImageView.getId();
            //TODO: Add logic to get the plant number, humidity, temperature, attack status, and health status

        } else {
            setLabelValues("N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
        }
        setNodeVisibility(popupStatusPane, true);
        setNodeVisibility(overlayPane, true);
    }

    /**
     * Handles the click event on a soil.
     * @param event The MouseEvent that triggered the click.
     */
    @FXML
    private void handleSoilClick(MouseEvent event) {
        ImageView clickedSoil = (ImageView) event.getSource();
        String soilId = clickedSoil.getId();

        switch (currentMode) {
            case WATERING:
                handleWatering(soilId);
                break;
            case PLANTING:
                handlePlanting(soilId);
                currentMode = Mode.NONE;
                break;
            case NONE:
                showSoilInfo(soilId);
                break;
            // TODO: Add more cases for other modes
        }

    }

    /**
     * Sets all soils to a specific image.
     * @param soilImage The image to set all soils to.
     */
    private void setAllSoils(Image soilImage) {
        for (Node node : soilGroup.getChildren()) {
            if (node instanceof ImageView) {
                ImageView soil = (ImageView) node;
                soil.setImage(soilImage);
            }
        }
    }

    /**
     * Gets the soil ImageView by its id.
     * @param soilId The id of the soil to get.
     * @return The ImageView of the soil with the given id.
     */
    private ImageView getSoilById(String soilId) {
        for (Node node : soilGroup.getChildren()) {
            if (node instanceof ImageView) {
                ImageView soil = (ImageView) node;
                if (soil.getId().equals(soilId)) {
                    return soil;
                }
            }
        }
        return null;
    }

    /**
     * Handles the click event on the rain button.
     */
    @FXML
    protected void handleRainButtonClick() {
        if(weather.isSunny()) {
            createRaindrop(rainPane);
            // Only set the normal soils to wet, not the grass soils
            for (Node node : soilGroup.getChildren()) {
                if (node instanceof ImageView) {
                    ImageView soil = (ImageView) node;
                    if (soil.getImage() == grassSoil) {
                        continue;
                    }
                    soil.setImage(wetSoil);
                }
            }

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
        currentMode = Mode.NONE;
    }

    /**
     * Animates the sunny image to appear or disappear.
     * @param fromScale The starting scale of the image.
     * @param toScale The ending scale of the image.
     * @param setVisibleAfter Whether to set the image to invisible after the animation.
     */
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

    /**
     * Creates a raindrop animation on the given pane.
     * @param pane The Pane to create the raindrop animation on.
     */
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


    private void createDroplet(double x, double y) {
        Pane sceneRoot = (Pane) soilGroup.getScene().getRoot(); // Assuming the root is a Pane

        for (int i = 0; i < 35; i++) {
            // Create the droplet at the cursor location
            Circle droplet = new Circle(x, y, 3);
            droplet.setStyle("-fx-fill: rgba(255, 255, 255, 0.6);");

            // Adjust position if sceneRoot has transformations or offsets
            droplet.setTranslateX(sceneRoot.getTranslateX());
            droplet.setTranslateY(sceneRoot.getTranslateY());

            sceneRoot.getChildren().add(droplet);

            // Define the animation for droplet movement
            double endX = x + Math.random() * 40 - 20; // Random endpoint for x
            double endY = y + Math.random() * 40 - 20; // Random endpoint for y

            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), droplet);
            transition.setToX(endX - x);
            transition.setToY(endY - y);
            transition.setOnFinished(e -> sceneRoot.getChildren().remove(droplet));
            transition.play();

        }
    }

    /**
     * Removes the droplet animation from the scene, if it exists.
     */
    private void resetSeedButtonStyle() {
        for (Node node : seedSelectionGrid.getChildren()) {
            if (node instanceof Button) {
                Button seedButton = (Button) node;
                seedButton.setEffect(null);
            }
        }
    }

    /**
     * Handles the click event on the plant button.
     */
    @FXML
    private void handlePlantButtonClick(){
        currentMode = Mode.PLANTING;
        setNodeVisibility(popupSelectionPane, true);
        setNodeVisibility(overlayPane, true);
        resetSeedButtonStyle();
    }

    /**
     * Handles the click event on a seed selection.
     */
    @FXML
    private void handleSeedSelection(MouseEvent event) {
        resetSeedButtonStyle();
        // Extract which seed was selected
        Button selectedSeedButton = (Button) event.getSource();
        selectedSeedButton.setEffect(new DropShadow(10, Color.BLACK));
        currentSeedType = selectedSeedButton.getText();

        // Configure and show the quantity spinner
        seedQuantitySpinner.getValueFactory().setValue(1); // Reset to default value
        setNodeVisibility(seedQuantitySpinner, true);
        setNodeVisibility(confirmButton, true);
    }

    /**
     * Handles the click event on the confirm planting button.
     */
    @FXML
    private void handleConfirmPlanting(){
        setNodeVisibility(seedQuantitySpinner, false);
        setNodeVisibility(confirmButton, false);
        popupSelectionPane.setVisible(false);
        overlayPane.setVisible(false);
    }

    /**
     * Handles the click event on the cancel planting button.
     */
    @FXML
    private void handleCancelPlanting(){
        currentMode = Mode.NONE;
        setNodeVisibility(seedQuantitySpinner, false);
        setNodeVisibility(confirmButton, false);
        setNodeVisibility(popupSelectionPane, false);
        setNodeVisibility(overlayPane, false);
    }

    /**
     * Handles the click event on the close button of the popup.
     */
    @FXML
    private void handleCloseButtonClick() {
        setNodeVisibility(popupStatusPane, false);
        setNodeVisibility(overlayPane, false);
    }


}