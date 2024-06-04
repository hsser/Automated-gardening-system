package api;

import application.GardenManager;
import plant.Plant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GardenSimulationAPI {
    private GardenManager gardenManager;

    public GardenSimulationAPI() {
        //gardenManager = new GardenManager("src/main/files/garden_config.txt");
    }

    public void initializeGarden() {
        gardenManager.initializeGarden();
    }

    public Map<String, Object> getPlants() {
        Map<String, Object> result = new HashMap<>();
        List<List<Plant>> plantGroups = gardenManager.getPlantGroups();
        List<String> plants = new ArrayList<>();
        List<Integer> waterRequirement = new ArrayList<>();
        List<List<String>> parasites = new ArrayList<>();

        for (List<Plant> plantGroup : plantGroups) {
            if (!plantGroup.isEmpty()) {
                plants.add(plantGroup.getFirst().getName());
                waterRequirement.add(plantGroup.getFirst().getMinWaterLevel()); // TODO: Check if this is correct
                List<String> plantParasites = plantGroup.getFirst().getPestList();
                parasites.add(plantParasites);
            }
        }
        result.put("plants", plants);
        result.put("waterRequirement", waterRequirement);
        result.put("parasites", parasites);
        return result;
    }

    public void rain(int amount) {

    }

    public void temperature(int temperature) {

    }

    public void parasite(String parasite) {

    }

    public void getState() {

    }
}
