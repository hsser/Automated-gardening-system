package sensors;

import controllers.WaterController;
import plant.Plant;

public class WaterSensor {
    private double waterLevel;
    private Plant plant;

    /**
     * Constructs a WaterSensor with a default water level based on plant type.
     */
    public WaterSensor(Plant plant) {
        this.plant = plant;
        this.waterLevel = plant.getCurrentWaterLevel();
    }

    /**
     * Gets the current water level.
     *
     * @return the current water level
     */
    public double getWaterLevel() {
        return waterLevel;
    }

    /**
     * Updates the water level. The new water level must be between the min and max levels for the plant type.
     * If the level is outside this range, it is assumed the plant will die.
     *
     * @param newWaterLevel the new water level
     */
    public void updateWaterLevel(double newWaterLevel) {
        if (newWaterLevel < plant.getMinWaterLevel() || newWaterLevel > plant.getMaxWaterLevel()) {
            System.out.println("The plant will die due to inappropriate water level!");
            WaterController.stopWatering();
        } else {
            this.waterLevel = newWaterLevel;
            System.out.println("The plant's water level has been updated");
            System.out.println("Plant's current water level is " + plant.getCurrentWaterLevel());
        }
    }

    /**
     * Check water level.
     */
    public void checkWaterLevel(Plant plant) {
        if(waterLevel <= plant.getPlantLowWaterThreshold()){
            WaterController.autoWatering(plant);
        }else if (waterLevel >= plant.getMaxWaterLevel()) {
            WaterController.stopWatering();
        }
    }
}