package plant;

import environment.Weather;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Manages the garden system, including weather, temperature, plants and events.
 * Provides methods to access system's current weather and temperature.
 * Provides methods to access and manipulate plants.
 */
public class GardenManager {
    private Weather weather = new Weather();  // System's current weather, default is sunny
    private AtomicInteger temperature = new AtomicInteger(80);  // System's current temperature, default is 80
    private List<Plant> plants = new ArrayList<>();

    public GardenManager() {
        System.out.println("TEST-GardenManager: Construct GardenManager");
    }

    public void createPlants(String name, int quantity) {
        Plant plant = null;
        // TODO: Add real values to construct plant
        // TODO: Change plant to plantGroup, need to discuss with team before
        switch (name) {
            case "CherryTomato":
                plant = new CherryTomato(name, PlantType.CROP, 0.0);
                break;
            case "ChiliPepper":
                plant = new ChiliPepper(name,PlantType.CROP, 0.0);
                break;
            case "Cherry":
                plant = new Cherry(name, PlantType.TREE, 0.0);
                break;
            case "Peach":
                plant = new Peach(name, PlantType.TREE, 0.0);
                break;
            case "Rose":
                plant = new Rose(name, PlantType.FLOWER, 0.0);
                break;
            case "Hydrangea":
                plant = new Hydrangea(name, PlantType.FLOWER, 0.0);
                break;
            default:
                System.out.println("TEST-GardenManager: No such type of plant.");
        }
        if (plants != null) {
            this.plants.add(plant);
            System.out.println("Planting " + quantity + " " + name + " seed" +
                    ((quantity > 1) ? "s" : ""));
        }

        System.out.println("TEST-GardenManager: Current bunches of plant are " + plants.size());
    }

    public Weather getWeather() { return weather; }
    public AtomicInteger getTemperature() { return temperature; }
    public List<Plant> getPlants() {
        return plants;
    }
}
