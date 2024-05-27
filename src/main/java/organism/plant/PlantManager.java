package organism.plant;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of plants that user creates in the garden system.
 * Provides methods to access and manipulate the plants.
 */
public class PlantManager {
    private List<Plant> plants = new ArrayList<>();
    public List<Plant> getPlants() {
        return plants;
    }
}
