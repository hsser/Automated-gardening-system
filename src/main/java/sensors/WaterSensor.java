package sensors;

import plant.PlantType;

public class WaterSensor {
    private double waterLevel;
    private PlantType plantType;

    /**
     * Constructs a WaterSensor with a default water level based on plant type.
     */
    public WaterSensor(PlantType plantType) {
        this.plantType = plantType;
        this.waterLevel = plantType.getMinWaterLevel();
    }

    /**
     * Sets the water level.
     *
     * @param newWaterLevel the new water level
     */
    public void setWaterLevel(double newWaterLevel) {
        this.waterLevel = newWaterLevel;
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
        if (newWaterLevel < plantType.getMinWaterLevel() || newWaterLevel > plantType.getMaxWaterLevel()) {
            System.out.println("The plant will die due to inappropriate water levels!");
        } else {
            this.waterLevel = newWaterLevel;
        }
    }

    /**
     * Checks if the water level is low.
     *
     * @return true if the water level is below the low water threshold for the plant type
     */
    public boolean isWaterLevelLow() {
        return waterLevel <= plantType.getLowWaterThreshold();
    }
}
