package plant;

import environment.EventManager;
import environment.Weather;

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

    public GardenManager() {
        System.out.println("TEST-GardenManager: Construct GardenManager");
        for (int i = 0; i < MAX_PLOT; i++) {
            plantGroups.add(i, new ArrayList<>());
        }
    }

    // TODO: Add script planting mode.
    // TODO: Match API (void initializeGarden(), Map<String, Object> getPlants(), void temperature(int), void parasite(str), void getState()).
    /**
     * Create plants for UI planting mode.
     */
    public void createPlants(String name, int quantity, String soilId) {
        List<Plant> plantGroup = new ArrayList<>();
        // TODO: What is the initial current water level?
        for (int i = 0; i < quantity; i++) {
            switch (name) {
                case "CherryTomato":
                    plantGroup.add(new CherryTomato(name, PlantType.CROP, 0.0));
                    break;
                case "ChiliPepper":
                    plantGroup.add(new ChiliPepper(name, PlantType.CROP, 0.0));
                    break;
                case "Cherry":
                    plantGroup.add(new Cherry(name, PlantType.TREE, 0.0));
                    break;
                case "Peach":
                    plantGroup.add(new Peach(name, PlantType.TREE, 0.0));
                    break;
                case "Rose":
                    plantGroup.add(new Rose(name, PlantType.FLOWER, 0.0));
                    break;
                case "Hydrangea":
                    plantGroup.add(new Hydrangea(name, PlantType.FLOWER, 0.0));
                    break;
                default:
                    System.out.println("TEST-GardenManager: No such type of plant.");
            }
        }
        if (plantGroup != null) {
            int index = Integer.parseInt(soilId) - 1;
            plantGroups.set(index, plantGroup);
            System.out.println("Planting " + quantity + " " + name + " seed" +
                    ((quantity > 1) ? "s" : "") + " in plot " + (index + 1));
            numberOfPlants += quantity;
            System.out.println("TEST-GardenManager: Current number of plant is " + numberOfPlants);
        }
    }

    public Weather getWeather() { return weather; }
}
