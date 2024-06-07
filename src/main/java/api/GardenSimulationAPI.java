package api;

import application.GardenManager;
import plant.Plant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GardenSimulationAPI {
    private GardenManager gardenManager;

    public GardenSimulationAPI() { gardenManager = new GardenManager("src/main/files/garden_config.txt"); }

    public void initializeGarden() { gardenManager.initializeGarden(); }
    public Map<String, Object> getPlants() { return gardenManager.getPlants(); }
    public void rain(int amount) { gardenManager.rain(amount); }
    public void temperature(int temperature) { gardenManager.temperature(temperature); }
    public void parasite(String parasite) { gardenManager.parasite(parasite); }
    public void getState() { gardenManager.getState(); }
}
