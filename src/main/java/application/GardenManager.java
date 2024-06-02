package application;

import io.GardenConfigLoader;
import environment.EventManager;
import environment.Weather;
import javafx.application.Platform;
import plant.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Manages the garden system, including weather, temperature, plants and events.
 */
// TODO: Add timer, sensor and controller
// TODO: Delete all TEST output
public class GardenManager {
    private Weather weather = new Weather();  // System's current weather, default is sunny
    private AtomicInteger temperature = new AtomicInteger(80);  // System's current temperature, default is 80
    private List<List<Plant>> plantGroups = new ArrayList<>();
    private EventManager eventManager = new EventManager(weather, temperature, plantGroups);
    private final int MAX_PLOT = 15;
    private int numberOfPlants = 0;

    // For loading plants from config file
    private GardenConfigLoader loader;
    private List<GardenConfigLoader.PlantConfig> plantConfigs;

    // UI
    private GardenController controller;

    // Timer
    private GardenTimer timer;
    private int currentDay = 1;

    public GardenManager(GardenController controller, String configPath) {
        System.out.println("TEST-GardenManager: Construct GardenManager");
        for (int i = 0; i < MAX_PLOT; i++) {
            plantGroups.add(i, new ArrayList<>());
        }
        this.controller = controller;
        loader = new GardenConfigLoader(configPath);
        this.timer = new GardenTimer(this::simulateDay);
    }

    // TODO: Add script planting mode.
    public void plantFromLoader() {
        try {
            plantConfigs = loader.loadPlantsConfigureations();// Start from 1
            for (GardenConfigLoader.PlantConfig plantConfig : plantConfigs) {
                List<Plant> plantGroup = createPlantGroup(plantConfig.getType(), plantConfig.getQuantity());
                int plotIndex = placePlantGroup(plantGroup, 0, true); // Always start from 0
                if (plotIndex != -1) {
                    // Update UI
                    String soilId = String.valueOf(plotIndex + 1);
                    this.controller.showPlantingEffect(soilId, plantConfig.getType());
                }
            }
        } catch (IOException e) {
            System.out.println("TEST-GardenManager: Error loading plant configurations: " + e.getMessage());
        }
    }


    // TODO: Match API (void initializeGarden(), Map<String, Object> getPlants(), void temperature(int), void parasite(str), void getState()).
    /**
     * Create plants in a group.
     * @param name The name of the plant.
     * @param quantity The quantity of the plant.
     */
    public List<Plant> createPlantGroup(String name, int quantity) {
        List<Plant> plantGroup = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            switch (name) {
                case "CherryTomato":
                    plantGroup.add(new CherryTomato());
                    break;
                case "ChiliPepper":
                    plantGroup.add(new ChiliPepper());
                    break;
                case "Cherry":
                    plantGroup.add(new Cherry());
                    break;
                case "Peach":
                    plantGroup.add(new Peach());
                    break;
                case "Rose":
                    plantGroup.add(new Rose());
                    break;
                case "Hydrangea":
                    plantGroup.add(new Hydrangea());
                    break;
                default:
                    System.out.println("TEST-GardenManager: No such type of plant.");
            }
        }

        return plantGroup;
    }

    public int placePlantGroup(List<Plant> plantGroup, int plotIndex, boolean isScriptMode) {
        int quantity = plantGroup.size();
        String type = plantGroup.get(0).getName();

        // Check if the plant group is empty
        if (quantity == 0) {
            System.out.println("TEST-GardenManager: Plant group is empty.");
            return -1;
        }

        // Check if the plot index is valid
        if (plotIndex < 0 || plotIndex >= MAX_PLOT) {
            System.out.println("TEST-GardenManager: Invalid plot index.");
            return -1;
        }

        // Check if the plot is occupied
        if (plantGroups.get(plotIndex).size() == 0) {
            plantGroups.set(plotIndex, plantGroup);
            System.out.println("Planting " + quantity + " " + type + " seed" +
                    ((quantity > 1) ? "s" : "") + " in plot " + (plotIndex + 1));
            numberOfPlants += quantity;
            System.out.println("TEST-GardenManager: Current number of plant is " + numberOfPlants);
            return plotIndex;
        }

        // Check if all plots are occupied, if finding an empty plot, plant the group
        if(isScriptMode) {
            for (int i = 0; i < MAX_PLOT; i++) {
                if (plantGroups.get(i).size() == 0) {
                    plantGroups.set(i, plantGroup);
                    System.out.println("Planting " + quantity + " " + type + " seed" +
                            ((quantity > 1) ? "s" : "") + " in plot " + (i + 1));
                    numberOfPlants += quantity;
                    System.out.println("TEST-GardenManager: Current number of plant is " + numberOfPlants);
                    return i;
                }
            }
        }

        return -1;
    }

    public void initializeGarden() {
        startTimer();
        plantFromLoader();
    }

    public List<List<Plant>> getPlantGroups() { return plantGroups; }
    public Weather getWeather() { return weather; }


    public void startTimer() { timer.start(); }
    public void stopTimer() { timer.stop(); }
    public void simulateDay() {
        Platform.runLater(() -> {
            //TODO: Add daily events here and update UI
            controller.showCurrentDay(currentDay);
            System.out.println("TEST-GardenManager: Day " + currentDay + " starts.");
            currentDay++;
        });
    }
}
