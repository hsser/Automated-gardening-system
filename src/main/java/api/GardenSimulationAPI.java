package api;

import application.GardenManager;
import io.GardenLogger;

import java.util.Map;

public class GardenSimulationAPI {
    private GardenManager gardenManager;

    public GardenSimulationAPI() {
        GardenLogger.clearLog();
        gardenManager = new GardenManager("src/main/files/garden_config.txt");
    }

    public void initializeGarden() { gardenManager.initializeGarden(); }

    public Map<String, Object> getPlants() { return gardenManager.getPlants(); }

    public void rain(int amount) {
        GardenLogger.log("API", "Rain Event: Rain amount is " + amount);
        gardenManager.rain(amount);
    }

    public void temperature(int temperature) {
        GardenLogger.log("API", "Temperature Change Event: Temperature is set to " + temperature  + " °F");
        gardenManager.temperature(temperature);
    }

    public void parasite(String parasite) {
        GardenLogger.log("API", "Pest Attack Event: Releasing " + parasite);
        gardenManager.parasite(parasite);
    } // Can be "Aphid", "Spider", "Whitefly"

    public void getState() {
        GardenLogger.log("API", "Get state of the garden");
        gardenManager.getState();
    }

    public void stopSimulation() {
        gardenManager.stopTimer();
    }
}
