package controllers;

import plant.Plant;

/**
 * The WaterController class provides methods to manage the water levels of plants.
 */
public class WaterController {
    private Plant plant;

    /**
     * Constructs a WaterController for a specific plant.
     *
     * @param plant the plant to be monitored and controlled
     */
    public WaterController(Plant plant) {
        this.plant = plant;
    }

    /**
     * Auto-watering of the plant based on its current water level.
     */
    public static void autoWatering(Plant plant) {
        double currentWaterLevel = plant.getCurrentWaterLevel();
        System.out.println("Current water level is " + currentWaterLevel);

        System.out.println("""
        Warning: The water level of the plant is low!
        Auto watering system activates!
        """);
        increaseWaterLevel(plant,currentWaterLevel);
    }

    /**
     * Increase the water level of the plant.
     *
     * @param currentWaterLevel the current water level of the plant
     */
    public static void increaseWaterLevel(Plant plant, double currentWaterLevel) {
        // Increase the water level
        plant.updateWaterLevel(currentWaterLevel + 5.0);
        System.out.println("Now, water level of the plant is updated to " + plant.getCurrentWaterLevel());
    }

    /**
     * Stops the watering of the plant and returns true.
     */
    public static boolean stopWatering() {
        System.out.println("Overwatering warning！Watering protection activated.");
        // TODO: Create a pretection animation
        return true;
    }

    /**
     * Decreases the water level of the plant daily.
     */
    public void dailyWaterDecrease() {
        double currentWaterLevel = plant.getCurrentWaterLevel();
        if (currentWaterLevel > plant.getMinWaterLevel()) {
            plant.updateWaterLevel(currentWaterLevel - 5);
        }
        System.out.println("Daily water level decrease applied. New water level of the plant is " + plant.getCurrentWaterLevel());
    }
}
