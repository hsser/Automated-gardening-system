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
        int currentWaterLevel = plantGroup.getCurrentWaterLevel();
        GardenLogger.log("Water Controller","Current water level is " + currentWaterLevel);

        GardenLogger.log("Water Controller", """
        Warning: The water level of the plants is low!
        Auto watering system activates!
        """);
        increaseWaterLevel(plantGroup,currentWaterLevel);
    }

    /**
     * Increase the water level of the plant.
     *
     * @param currentWaterLevel the current water level of the plant
     */
    public static void increaseWaterLevel(PlantGroup plantGroup, int currentWaterLevel) {
        // Increase the water level
        for(Plant plant : plantGroup.getPlants()){
            plant.setCurrentWaterLevel(currentWaterLevel + 5);
        }
        GardenLogger.log("Water Controller","Now, water level of " + plantGroup.getName() + " is updated to " + plantGroup.getCurrentWaterLevel());
    }

    /**
     * Stops the watering of the plant and returns true.
     */
    public static boolean stopWatering(PlantGroup plantGroup) {
        GardenLogger.log("Water Controller",plantGroup.getName() + " water level is " + plantGroup.getCurrentWaterLevel() + ", this value plus rain amount should below " + plantGroup.getMaxWaterLevel() + ". Overwatering warning！Watering protection activated.");
        // TODO: Create a pretection animation
        return true;
    }

    /**
     * Decreases the water level of the plant daily.
     */
    public static void dailyWaterDecrease(PlantGroup plantGroup) {
        if(!plantGroup.isEmpty()){
            int currentWaterLevel = plantGroup.getCurrentWaterLevel();
            if (currentWaterLevel > plantGroup.getMinWaterLevel()) {
                GardenLogger.log("Event","Daily water level decrease applied. ");
                for(Plant plant : plantGroup.getPlants()){
                    plant.setCurrentWaterLevel(currentWaterLevel - 5);
                }

            }
        }
//        System.out.println("Daily water level decrease applied. New water level of " + plant.getName() + " is " + plant.getCurrentWaterLevel());
    }
}
