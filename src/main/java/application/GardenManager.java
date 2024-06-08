package application;

import controllers.WaterController;
import environment.*;
import io.GardenConfigLoader;
import io.GardenLogger;
import plant.*;
import sensors.TemperatureSensor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Manages the garden system, including weather, temperature, plants and events.
 */
// TODO: Add timer, sensor and controller
// TODO: Delete all TEST output
public class GardenManager {
    private Weather weather = new Weather();  // System's current weather, default is sunny
    private AtomicInteger temperature = new AtomicInteger(80);  // System's current temperature, default is 80
    private List<PlantGroup> plantGroups = new ArrayList<>();
    private Map<String, List<Integer>> plotIndicesOfVulnerablePlantByPest = new HashMap<>();
    private EventManager eventManager = new EventManager(weather, temperature, plantGroups, plotIndicesOfVulnerablePlantByPest);
    private final int MAX_PLOT = 15;
    private int numberOfPlants = 0;
    private PlantChangeAction onPlantingChanged;
    private Consumer<Integer> onDayChanged;
    private TemperatureSensor temperatureSensor;

    // For loading plants from config file
    private GardenConfigLoader loader;
    private List<GardenConfigLoader.PlantConfig> plantConfigs;

    // Timer
    private GardenTimer timer;
    private int currentDay = 0;

    public GardenManager(String configPath) {
        for (int i = 0; i < MAX_PLOT; i++) {
            plantGroups.add(i, new PlantGroup(new ArrayList<>()));
        }
        loader = new GardenConfigLoader(configPath);
        this.timer = new GardenTimer(this::simulateDay);
        this.temperatureSensor = TemperatureSensor.getInstance();
        temperatureSensor.setTemperature(temperature.get());
    }

    /************************* API *************************/

    public void initializeGarden() {
        plantFromLoader();
        startTimer();
    }

    public Map<String, Object> getPlants() {
        Map<String, Object> result = new HashMap<>();
        List<String> plants = new ArrayList<>();
        List<Integer> waterRequirement = new ArrayList<>();
        List<List<String>> parasites = new ArrayList<>();

        for (PlantGroup plantGroup : plantGroups) {
            if (!plantGroup.isEmpty()) {
                plants.add(plantGroup.getName());
                waterRequirement.add(plantGroup.getCurrentWaterLevel()); // TODO: Check if this is correct
                List<String> plantParasites = plantGroup.getPestList();
                parasites.add(plantParasites);
            }
        }
        result.put("plants", plants);
        result.put("waterRequirement", waterRequirement);
        result.put("parasites", parasites);
        return result;
    }

    public void rain(int rainAmount) {
        RainyEvent rainyEvent = eventManager.createRainyEvent(rainAmount);
        rainyEvent.trigger();
    }

    public void temperature(int temperature) {
        TemperatureChangeEvent temperatureChangeEvent =  eventManager.createTemperatureChangeEvent(temperature);
        temperatureChangeEvent.trigger();
    }

    public void parasite(String pest) {
        PestAttackEvent pestAttackEvent = eventManager.createPestAttackEvent(pest);
        pestAttackEvent.trigger();
    }

    public void getState() {
        StringBuilder state = new StringBuilder();
        state.append("===================================\n");
        state.append("Garden State of Day ").append(currentDay).append(":\n");
        state.append("-----------------------------------\n");
        state.append("Weather: ").append(weather.getWeatherType()).append("\n");
        state.append("Temperature: ").append(temperature).append("\n");
        state.append("Number of Plants in total: ").append(numberOfPlants).append("\n");
        state.append("-----------------------------------\n");
        for (int i = 0; i < MAX_PLOT; i++) {
            PlantGroup plantGroup = plantGroups.get(i);
            state.append("Plot " + (i + 1) + ": ");
            if (!plantGroup.isEmpty()) {
                state.append(plantGroup.getName());
                int alive = 0, dead = 0;
                for (Plant plant : plantGroups.get(i).getPlants()) {
                    if (plant.isAlive()) {
                        alive++;
                    } else {
                        dead++;
                    }
                }
                state.append(", Alive: " + alive + ", Dead: " + dead + "\n");

            } else {
                state.append("Empty Plot\n");
            }
        }
        state.append("===================================\n");
        System.out.println(state.toString());
    }

    /************************* PLANTING *************************/

    public void setOnPlantingChanged(PlantChangeAction action) {
        this.onPlantingChanged = action;
    }

    // TODO: Add script planting mode.
    public void plantFromLoader() {
        try {
            plantConfigs = loader.loadPlantsConfigurations();
            int plotIndex = 0;
            for (GardenConfigLoader.PlantConfig plantConfig : plantConfigs) {
                PlantGroup plantGroup = createPlantGroup(plantConfig.getType(), plantConfig.getQuantity());
                placePlantGroup(plantGroup, plotIndex); // Always start from 0
                plotIndex++;
            }
        } catch (IOException e) {
            GardenLogger.log("Error","Error loading plant configurations: " + e.getMessage());
        }
    }

    /**
     * Create plants in a group.
     * @param name The name of the plant.
     * @param quantity The quantity of the plant.
     */
    public PlantGroup createPlantGroup(String name, int quantity) {
        List<Plant> plantList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            switch (name) {
                case "CherryTomato":
                    plantList.add(new CherryTomato());
                    break;
                case "ChiliPepper":
                    plantList.add(new ChiliPepper());
                    break;
                case "Cherry":
                    plantList.add(new Cherry());
                    break;
                case "Peach":
                    plantList.add(new Peach());
                    break;
                case "Rose":
                    plantList.add(new Rose());
                    break;
                case "Hydrangea":
                    plantList.add(new Hydrangea());
                    break;
                default:
                    GardenLogger.log("Warning", "No such type of plant.");
            }
        }
        PlantGroup plantGroup = new PlantGroup(plantList);

        return plantGroup;
    }

    private void setPlotIndicesOfVulnerablePlantByPest(int plotIndex, List<String> pests) {
        for (String pest : pests) {
            if (plotIndicesOfVulnerablePlantByPest.containsKey(pest)) {
                plotIndicesOfVulnerablePlantByPest.get(pest).add(plotIndex);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(plotIndex);
                plotIndicesOfVulnerablePlantByPest.put(pest, list);
            }
        }
    }

    public void placePlantGroup(PlantGroup plantGroup, int plotIndex) {
        int quantity = plantGroup.size();
        String type = plantGroup.getName();

        // Check if the plant group is empty
        if (quantity == 0) {
            GardenLogger.log("Warning", "Plant group is empty.");
            return;
        }

        // Check if the plot index is valid
        if (plotIndex < 0 || plotIndex >= MAX_PLOT) {
            GardenLogger.log("Warning", "Invalid plot index.");
            return;
        }

        List<String> pests = plantGroup.getPestList();

        // Check if the plot is occupied
        if (plantGroups.get(plotIndex).size() == 0) {
            plantGroups.set(plotIndex, plantGroup);
            plantGroup.setCurrentPlotIndex(plotIndex);
            numberOfPlants += quantity;
            GardenLogger.log("Event", "Planting " + quantity + " " + type + " seed" +
                    ((quantity > 1) ? "s" : "") + " in plot " + (plotIndex + 1) +
                    ". Current number of plants is " + numberOfPlants);

            // Set pestToPlotIndex after place plantGroup
            setPlotIndicesOfVulnerablePlantByPest(plotIndex, pests);
            if (onPlantingChanged != null) {
                onPlantingChanged.run(plotIndex, type);
            }
        }
    }

    // TEST BEGIN: printPestToPlotIndex
    public void printPestToPlotIndex() {
        for (Map.Entry<String, List<Integer>> entry : plotIndicesOfVulnerablePlantByPest.entrySet()) {
            String pest = entry.getKey();
            List<Integer> plotIndices = entry.getValue();
            System.out.println("TEST-GardenManager: ");
            System.out.println("Pest: " + pest);
            System.out.print("Plot Indices: ");
            for (Integer index : plotIndices) {
                System.out.print(index + " ");
            }
            System.out.println(); // New line after each pest's plot indices
        }
    }
    // TEST END: printPestToPlotIndex

    /************************* WEATHER *************************/

    public void setOnWeatherChanged(Consumer<Weather> consumer) {
        weather.setOnWeatherChanged(consumer);
    }

    public void setOnPestAttack(PestAttackAction action) {
        eventManager.setOnPestAttack(action);
    }

    public void changeWeather() {
        WeatherChangeEvent weatherChangeEvent = eventManager.createWeatherChangeEvent();
        weatherChangeEvent.trigger();
    }

    public List<PlantGroup> getPlantGroups() { return plantGroups; }
    public Weather getWeather() { return weather; }
    public int getCurrentDay() { return currentDay; }
    public void startTimer() { /*timer.start();*/ }
    public void stopTimer() { timer.stop(); }

    public void setOnDayChanged(Consumer<Integer> consumer) {
        this.onDayChanged = consumer;
    }

    public void dayChange() {
        currentDay++;
        if (onDayChanged != null) {
            onDayChanged.accept(currentDay);
        }
    }

    public void simulateDay() {
        // Day change
        dayChange();
        GardenLogger.log("Event","Day " + currentDay + " starts.");

        if (currentDay != 0) {
            for (PlantGroup plantGroup : plantGroups) {
                WaterController.dailyWaterDecrease(plantGroup);
            }
        }

        // Day start, trigger daily events
        eventManager.triggerAllEvents();
    }

    public void setOnSubsystemsEffect(SubsystemEffectAction action) {
        // pass the action to the plantGroup
    }

    public void setOnWateringProtection(OnWateringProtectionAction action) {
        // pass the action to the plantGroup
    }

    public void setOffWateringProtection(OffWateringProtectionAction action) {
        // pass the action to the plantGroup
    }

    public void setOnPestAttackHandling(PestAttackHandlingAction action) {
        // pass the action to the plantGroup
    }
}
