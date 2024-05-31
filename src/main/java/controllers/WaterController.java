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
    public void autoWatering() {
        double currentWaterLevel = plant.getWaterLevel();
        System.out.println("Current water level is " + currentWaterLevel);

        if (plant.isWaterLevelLow()) {
            System.out.println("Warning: The water level of " + plant.getName() + " is low!");
            increaseWaterLevel(currentWaterLevel);
        } else {
            System.out.println("The water level for " + plant.getName() + " is sufficient!");
        }

        if (currentWaterLevel >= plant.getMaxWaterLevel()) {
            System.out.println("Protection: The water level of " + plant.getName() + " is too high!");
            System.out.println(stopWatering());
        }
    }

    /**
     * Increase the water level of the plant.
     *
     * @param currentWaterLevel the current water level of the plant
     */
    public void increaseWaterLevel(double currentWaterLevel) {
        // Increase the water level
        plant.updateWaterLevel(currentWaterLevel + 5.0);
        System.out.println("Now, new water level of " + plant.getName() + " is " + plant.getWaterLevel());
    }

    /**
     * Stops the watering of the plant and returns true.
     */
    public boolean stopWatering() {
        String protectionMessage = "Overwatering warning！Watering protection activated.";
        System.out.println(protectionMessage);
        return true;
    }

    /**
     * Decreases the water level of the plant daily.
     */
    public void dailyWaterDecrease() {
        double currentWaterLevel = plant.getWaterLevel();
        if (currentWaterLevel > plant.getMinWaterLevel()) {
            plant.updateWaterLevel(currentWaterLevel - 5);
        }
        System.out.println("Daily water level decrease applied. New water level of " + plant.getName() + " is " + plant.getWaterLevel());
    }
}
