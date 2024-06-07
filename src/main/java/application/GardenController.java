package application;

import environment.Weather;
import javafx.animation.PauseTransition;
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
import plant.Plant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


enum Mode { // Enum to represent the current mode of the garden controller
    WATERING, PLANTING, PARASITE, NONE
}

public class GardenController {
    @FXML
    private Group soilGroup, plantGroup, ladybugGroup, aphidGroup, spiderGroup, whiteflyGroup;
    @FXML
    private Button waterButton, rainButton, plantButton, parasiteButton, // for watering, raining, parasite, and planting
            cancelButton, confirmButton, // for canceling and confirming planting
            cancelButton1, confirmButton1, // for confirming parasite
            closeButton; // for closing the status pane
    @FXML
    private Label soilInfoLabel, plantTypeValue, plantNumberValue, humidityValue, temperatureValue,
            attackStatusValue, healthStatusValue, currentDay;
    @FXML
    private Pane rainPane, overlayPane;
    @FXML
    private AnchorPane plantSelectionPane, popupStatusPane, parasiteSelectionPane;
    @FXML
    private ImageView sunny, rainy, plantCover, sprinkler, heater, cooler, pesticide;
    @FXML
    private Spinner<Integer> plantQuantitySpinner;
    @FXML
    private GridPane plantSelectionGrid, parasiteSelectionGrid;

    private Map<String, ImageView> imageviews = new HashMap<>();
    private Mode currentMode = Mode.NONE;  // The current mode of the garden controller
    private String currentPlantType = null;  // The type of seed currently selected;
    private String currentParasiteType = null;  // The type of parasite currently selected;

    private Image grassSoil = new Image(getClass().getResourceAsStream("/image/soil/1.png"));
    private Image normalSoil = new Image(getClass().getResourceAsStream("/image/soil/3.png"));
    private Image wetSoil = new Image(getClass().getResourceAsStream("/image/soil/4.png"));
    private Image deadPlant = new Image(getClass().getResourceAsStream("/image/crops/crop7/cron_end.png"));

    private GardenManager gardenManager;

    public GardenController(GardenManager gardenManager) {
        this.gardenManager = gardenManager;
        System.out.println("TEST-GardenManager: Set GardenManager to garden controller");

        // Register UI callbacks
        gardenManager.setOnWeatherChanged((Weather weather) -> showWeatherChangeEffect(weather));
        gardenManager.setOnPlantingChanged((String soilId, String plantType) -> showPlantingEffect(soilId, plantType));
        gardenManager.setOnDayChanged((Integer day) -> showCurrentDay(day));
    }

    @FXML
    private void initialize() {
        imageviews.put("sunny", sunny);
        imageviews.put("plantCover", plantCover);
        imageviews.put("sprinkler", sprinkler);
        imageviews.put("heater", heater);
        imageviews.put("cooler", cooler);
        imageviews.put("pesticide", pesticide);

        // Set the default plant type to null
        setAllSoils(grassSoil);

        // Configure the spinner for integer values with a min, max, and step
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);
        plantQuantitySpinner.setValueFactory(valueFactory);
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

    /************************* WATERING *************************/

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
            parasiteButton.setDisable(true);  // Disable the parasite button
        } else {
            // Reset cursor to default when disabling watering mode
            soilGroup.getScene().setCursor(Cursor.DEFAULT);
            // TODO: Add logic to handle watering mode deactivation, might be needed to disable more buttons
            plantButton.setDisable(false);  // Enable the plant button
            rainButton.setDisable(false);  // Enable the rain button
            parasiteButton.setDisable(false);  // Enable the parasite button
        }
    }

    /**
     * Handles the watering of a soil when clicked.
     * @param soilId The id of the soil to water.
     */
    protected void showWateringEffect(String soilId) {
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
    }

    /**
     * Creates a droplet animation at the given x, y coordinates.
     * @param x The x coordinate of the droplet.
     * @param y The y coordinate of the droplet.
     */
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

    /************************* PLANTING *************************/

    /**
     * Handles the click event on the plant button.
     */
    @FXML
    private void handlePlantButtonClick(){
        // Toggle planting mode
        currentMode = (currentMode == Mode.PLANTING) ? Mode.NONE : Mode.PLANTING;
        if (currentMode == Mode.PLANTING) {
            setNodeVisibility(plantSelectionPane, true);
            setNodeVisibility(overlayPane, true);
            resetSelectionButtonStyle(plantSelectionGrid);
        }
        else {
            // Reset cursor to default when disabling watering mode
            soilGroup.getScene().setCursor(Cursor.DEFAULT);
            // Enable other buttons
            waterButton.setDisable(false);
            rainButton.setDisable(false);
            parasiteButton.setDisable(false);
        }
    }

    /**
     * Handles the click event on a plant selection.
     */
    @FXML
    private void handlePlantSelection(MouseEvent event) {
        resetSelectionButtonStyle(plantSelectionGrid);
        // Extract which plant was selected
        Button selectedPlant = (Button) event.getSource();
        selectedPlant.setEffect(new DropShadow(10, Color.BLACK));
        currentPlantType = selectedPlant.getText();

        // Configure and show the quantity spinner
        plantQuantitySpinner.getValueFactory().setValue(1); // Reset to default value
        setNodeVisibility(plantQuantitySpinner, true);
        setNodeVisibility(confirmButton, true);
    }

    /**
     * Handles the click event on the confirm planting button.
     */
    @FXML
    private void handleConfirmPlanting(){
        setNodeVisibility(plantQuantitySpinner, false);
        setNodeVisibility(confirmButton, false);
        plantSelectionPane.setVisible(false);
        overlayPane.setVisible(false);
        soilGroup.getScene().setCursor(new ImageCursor(new Image(getClass().getResourceAsStream("/image/icon/plant_cursor.png")), 0, 0));
        waterButton.setDisable(true);  // Disable the water button
        rainButton.setDisable(true);  // Disable the rain button
        parasiteButton.setDisable(true);  // Disable the parasite button
    }

    /**
     * Handles the click event on the cancel planting button.
     */
    @FXML
    private void handleCancelPlanting(){
        currentMode = Mode.NONE;
        setNodeVisibility(plantQuantitySpinner, false);
        setNodeVisibility(confirmButton, false);
        setNodeVisibility(plantSelectionPane, false);
        setNodeVisibility(overlayPane, false);
    }

    /**
     * Handles the planting UI effect when clicked on a certain soil/plot.
     * @param soilId The id of the soil to plant.
     * @param plantType The type of plant to show.
     */
    protected void showPlantingEffect(String soilId, String plantType) {
        if (currentPlantType == null) {
            currentPlantType = plantType;
        }

        ImageView clickedSoil = getSoilById(soilId);
        ImageView plantImageView = (ImageView) plantGroup.lookup("#" + soilId);
        if (plantImageView != null && plantImageView.getImage() == null) {
            clickedSoil.setImage(normalSoil);
            plantImageView.setImage(new Image(getClass().getResourceAsStream("/image/plants/" + currentPlantType + ".png")));
            // Check if it is raining
            if (!gardenManager.getWeather().isSunny()) {
                clickedSoil.setImage(wetSoil);
            }
        }
        currentPlantType = null;
    }

    /**
     * Handles the dead plant UI effect when a plant dies.
     * @param soilId The id of the soil to show the dead plant effect on.
     */
    protected void showDeadPlantEffect(String soilId) {
        ImageView plant = (ImageView) plantGroup.lookup("#" + soilId);
        if (plant != null) {
            plant.setImage(deadPlant);
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
    protected void setLabelValues(String plantType, String plantNumber, String humidity, String temperature, String attackStatus, String healthStatus) {
        plantTypeValue.setText(plantType);
        plantNumberValue.setText(plantNumber);
        humidityValue.setText(humidity);
        temperatureValue.setText(temperature);
        attackStatusValue.setText(attackStatus);
        healthStatusValue.setText(healthStatus);
    }

    /************************* WEATHER *************************/

    /**
     * Show UI according to changed weather data.
     */
    void showWeatherChangeEffect(Weather weather) {
        if (!weather.isSunny()) {
            createRaindrop();
            rainy.setImage(sunny.getImage());
            // Only set the normal soils to wet, not the grass soils
            for (Node node : soilGroup.getChildren()) {
                if (node instanceof ImageView) {
                    ImageView soil = (ImageView) node;
                    if (soil.getImage() == grassSoil) {
                        continue;
                    }
                    // TODO: Add humidity to all the plants in the plot
                    soil.setImage(wetSoil);
                }
            }
            animateImage("sunny",1.0, 0.1, false);
        } else {
            rainy.setImage(new Image(getClass().getResourceAsStream("/image/icon/rainButton.png")));
            rainPane.getChildren().clear();
            animateImage("sunny", 0.1, 1.0, true);
        }
        // Reset cursor to default every time the rain button is clicked
        soilGroup.getScene().setCursor(Cursor.DEFAULT);
        // Also, ensure watering mode is deactivated when rain toggled
        currentMode = Mode.NONE;
    }

    /**
     * Handles the click event on the rain button.
     */
    @FXML
    private void handleRainButtonClick() {
        Weather weather = gardenManager.getWeather();
        gardenManager.changeWeather(weather);  // Change data
    }

    /**
     * Animates the image to appear or disappear.
     * @param fromScale The starting scale of the image.
     * @param toScale The ending scale of the image.
     * @param setVisibleAfter Whether to set the image to invisible after the animation.
     */
    protected void animateImage(String imageName, double fromScale, double toScale, boolean setVisibleAfter) {
        ImageView image = imageviews.get(imageName);
        if (image == null) {
            System.err.println("Image not found: " + imageName);
            return;
        }

        image.setVisible(true);

        // Create a scale transition for the ImageView
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.5), image);
        scaleTransition.setFromX(fromScale);
        scaleTransition.setFromY(fromScale);
        scaleTransition.setToX(toScale);
        scaleTransition.setToY(toScale);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(false);

        if (!setVisibleAfter) {
            scaleTransition.setOnFinished(event -> image.setVisible(false));
        }

        scaleTransition.play();
    }

    /**
     * Creates a raindrop animation on the given pane.
     */
    protected void createRaindrop() {
        for (int i = 0; i < 100; i++) {  // Number of raindrops
            Rectangle drop = new Rectangle(2, 20);  // Creating a drop as a small rectangle
            drop.setX(Math.random() * rainPane.getWidth());  // Randomize the starting position x
            drop.setY(-10);  // Start above the pane
            drop.setStyle("-fx-fill: rgba(255, 255, 255, 0.6);");  // Set color and partial transparency

            rainPane.getChildren().add(drop);

            // Create the animation for the drop
            TranslateTransition animation = new TranslateTransition(Duration.seconds(1 + Math.random()), drop);
            animation.setFromY(20);
            animation.setToY(rainPane.getHeight() + 20);  // Let the drop fall below the pane
            animation.setCycleCount(TranslateTransition.INDEFINITE);  // Repeat indefinitely
            animation.play();
        }
    }

    /**
     * Removes the droplet animation from the scene, if it exists.
     */
    private void resetSelectionButtonStyle(GridPane grid) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setEffect(null);
            }
        }
    }



    /************************* PARASITE *************************/

    /**
     * Handles the click event on the parasite button.
     */
    @FXML
    private void handleParasiteButtonClick() {
        currentMode = Mode.PARASITE;
        setNodeVisibility(parasiteSelectionPane, true);
        setNodeVisibility(overlayPane, true);
        resetSelectionButtonStyle(parasiteSelectionGrid);
    }

    /**
     * Handles the click event on a parasite selection.
     */
    @FXML
    private void handleParasiteSelection(MouseEvent event) {
        resetSelectionButtonStyle(parasiteSelectionGrid);
        // Extract which parasite was selected
        Button selectedParasite = (Button) event.getSource();
        selectedParasite.setEffect(new DropShadow(10, Color.BLACK));
        currentParasiteType = selectedParasite.getText();

        // Configure and show the quantity spinner
        setNodeVisibility(confirmButton1, true);
    }

    /**
     * Handles the click event on the confirm parasite button.
     */
    @FXML
    private void handleConfirmParasite() {
        setNodeVisibility(confirmButton1, false);
        parasiteSelectionPane.setVisible(false);
        overlayPane.setVisible(false);
        //TODO: pass user selected parasite to Garden Manager, Garden Manager then calls showParasiteEffect()
    }

    /**
     * Handles the click event on the cancel parasite button.
     */
    @FXML
    private void handleCancelParasite() {
        currentMode = Mode.NONE;
        currentParasiteType = null;
        setNodeVisibility(confirmButton1, false);
        setNodeVisibility(parasiteSelectionPane, false);
        setNodeVisibility(overlayPane, false);
    }

    /**
     * Handles the parasite UI effect when clicked on a certain soil/plot.
     * @param soilId The id of the soil to plant.
     * @param parasiteType The type of parasite to show.
     */
    protected void showParasiteEffect(String soilId, String parasiteType) {
        if (currentParasiteType == null) {
            currentParasiteType = parasiteType;
        }
        ImageView clickedSoil = getSoilById(soilId);

        if (clickedSoil.getImage() == grassSoil) {
            return;
        }

        ImageView parasiteImageView = null;
        switch (currentParasiteType) {
            case "Ladybug":
                parasiteImageView = (ImageView) ladybugGroup.lookup("#" + soilId);
                break;
            case "Aphid":
                parasiteImageView = (ImageView) aphidGroup.lookup("#" + soilId);
                break;
            case "Spider":
                parasiteImageView = (ImageView) spiderGroup.lookup("#" + soilId);
                break;
            case "Whitefly":
                parasiteImageView = (ImageView) whiteflyGroup.lookup("#" + soilId);
                break;
        }

        if (!parasiteImageView.isVisible()) {
            parasiteImageView.setVisible(true);
        }

        currentParasiteType = null;
    }


    /************************* SOIL **************************/

    /**
     * Handles the click event on the close button of the popup.
     */
    @FXML
    private void handleCloseButtonClick() {
        setNodeVisibility(popupStatusPane, false);
        setNodeVisibility(overlayPane, false);
    }

    /**
     * Shows the information of a soil when clicked.
     * @param soilId The id of the soil to show information of.
     */
    private void showSoilInfo(String soilId) {
        soilInfoLabel.setText("Plot " + soilId + " Conditions");
        List<Plant> currentPlantGroup = gardenManager.getPlantGroups().get(Integer.parseInt(soilId) - 1);
        int size = currentPlantGroup.size();
        if (size > 0) {
            Plant plant = currentPlantGroup.get(0);
            //TODO: Add logic to get the plant number, humidity, temperature, attack status, and health status
            String type = plant.getName();
            String quantity = String.valueOf(size);
            String humidity = String.valueOf(plant.getCurrentWaterLevel());
            String temperature = "N/A"; // TODO: Add logic to get current temperature
            String attackStatus = plant.getNumOfPestsAttacking() > 0 ? "Yes" : "No";
            String healthStatus = plant.isAlive() ? "Alive" : "Dead";
            setLabelValues(type, quantity, humidity, temperature, attackStatus, healthStatus);
        } else {
            setLabelValues("N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
        }
        setNodeVisibility(popupStatusPane, true);
        setNodeVisibility(overlayPane, true);
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
     * Handles the click event on a soil.
     * @param event The MouseEvent that triggered the click.
     */
    @FXML
    private void handleSoilClick(MouseEvent event) {
        ImageView clickedSoil = (ImageView) event.getSource();
        String soilId = clickedSoil.getId();
        int plotIndex = Integer.parseInt(soilId) - 1;

        switch (currentMode) {
            case WATERING:
                //TODO: Need to add humidity to all the plants in the plot
                showWateringEffect(soilId);
                break;

            case PLANTING:
                int plantQuantity = plantQuantitySpinner.getValue();
                List<Plant> plantGroup = gardenManager.createPlantGroup(currentPlantType, plantQuantity);
                gardenManager.placePlantGroup(plantGroup, plotIndex, false);
                // Show the planting effect on the soil
                showPlantingEffect(soilId, currentPlantType);
                // Reset cursor to default after planting
                soilGroup.getScene().setCursor(Cursor.DEFAULT);
                // Enable the buttons after planting
                waterButton.setDisable(false);  // Enable the water button
                rainButton.setDisable(false);  // Enable the rain button
                parasiteButton.setDisable(false);  // Enable the parasite button
                currentMode = Mode.NONE;
                break;

            case NONE:
                showSoilInfo(soilId);
                break;
        }

    }

    /**
     * Update the current day label with the given day.
     * @param day The day to update the label with.
     */
    protected void showCurrentDay(int day) {
        currentDay.setText("Day " + day); // Change data
    }

    /**
     * Shows the subsystems effect for 5 seconds.
     * @param subsystem The subsystem to show the effect for.
     *                  Can be "sprinkler", "heater", or "cooler".
     */
    protected void showSubsystemsEffect(String subsystem) {
        animateImage(subsystem, 0.1, 1.0, true);
        PauseTransition pause = new PauseTransition(Duration.seconds(5)); // Adjust duration as needed
        pause.setOnFinished(event -> animateImage(subsystem, 1.0, 0.1, false)); // Hide the sprinkler after the pause
        pause.play();
    }

    // TODO: need to decide how long the effect should last
    protected void showPlantCover() { animateImage("plantCover", 0.1, 1.0, true); }
    protected void hidePlantCover() { animateImage("plantCover", 1.0, 0.1, false); }


    /**
     * Shows the pest attack handling effect on the given soil.
     * @param soilId The id of the soil to show the ladybug handling effect on.
     * @param handlerType The type of handler to show the effect for.
     *                    Can be "ladybug" or "pesticide".
     */
    protected void showPestAttackHandlingEffect(String soilId, String handlerType) {
        ImageView handler;
        if (handlerType.equals("ladybug")) {
            handler = (ImageView) ladybugGroup.lookup("#" + soilId);
        } else if (handlerType.equals("pesticide")) {
            handler = pesticide;
        } else {
            handler = null;
        }

        ImageView aphid = (ImageView) aphidGroup.lookup("#" + soilId);
        ImageView spider = (ImageView) spiderGroup.lookup("#" + soilId);
        ImageView whitefly = (ImageView) whiteflyGroup.lookup("#" + soilId);

        // Make the handler visible
        if (handler != null && !handler.isVisible()) {
            handler.setVisible(true);

            // Create a pause transition for the desired duration
            PauseTransition pause = new PauseTransition(Duration.seconds(5)); // Adjust duration as needed
            pause.setOnFinished(event -> handler.setVisible(false)); // Hide the handler after the pause
            pause.play();
        }

        // Hide pests
        aphid.setVisible(false);
        spider.setVisible(false);
        whitefly.setVisible(false);
    }

}