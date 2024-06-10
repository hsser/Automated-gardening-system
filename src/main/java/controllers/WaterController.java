package controllers;

import io.GardenLogger;
import plant.Plant;
import plant.PlantGroup;

/**
 * The WaterController class provides methods to manage the water levels of plants.
 */
public class WaterController {
    private PlantGroup plantGroup;

    public WaterController(PlantGroup plantGroup) {
        this.plantGroup = plantGroup;
    }

    /**
     * Auto-watering of the plant based on its current water level.
     */
    public static void autoWatering(PlantGroup plantGroup) {
        GardenLogger.log("Water Controller",plantGroup.getName() +" water level is " + plantGroup.getCurrentWaterLevel());
        GardenLogger.log("Water Controller", "Warning: " + plantGroup.getName() + " is too dry! Auto watering system activates!");
        increaseWaterLevel(plantGroup);
    }

    /**
     * Increase the water level of the plant.
     */
    public static void increaseWaterLevel(PlantGroup plantGroup) {
        // Increase the water level
        for(Plant plant : plantGroup.getPlants()){
            plant.setCurrentWaterLevel(plant.getLowWaterThreshold());
        }
        GardenLogger.log("Water Controller","Now, " + plantGroup.getName() + " water level is updated to " + plantGroup.getCurrentWaterLevel());
    }

    /**
     * Turn WaterProtection on / off.
     */
    public static void turnWaterProtection(PlantGroup plantGroup, boolean on) {
        if (on) {
            GardenLogger.log("Water Controller", plantGroup.getName() + " water level is " + plantGroup.getCurrentWaterLevel() + " >= " + plantGroup.getHighWaterThreshold() + ". Overwatered! Plant cover on.");
            plantGroup.setCurrentWaterLevel(plantGroup.getHighWaterThreshold());
        } else {
            GardenLogger.log("Water Controller", plantGroup.getName() + " water level is " + plantGroup.getCurrentWaterLevel() + " < " + plantGroup.getHighWaterThreshold() + ". Plant cover off.");
        }
        plantGroup.setWaterProtection(on);
    }

    /**
     * Decreases the water level of the plant daily.
     */
    public static void dailyWaterDecrease(PlantGroup plantGroup) {
        plantGroup.updateWaterLevel(plantGroup.getCurrentWaterLevel() - 5);
    }
}
