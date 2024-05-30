package organism.plant;

import organism.Organism;
import sensors.WaterSensor;

public abstract class Plant extends Organism {
    private double minTemperatureLevel;
    private double maxTemperatureLevel;
    private double currentWaterLevel;
    private boolean isUnderAttack;  // Flag to indicate if the plant is being attacked
    private WaterSensor waterSensor;
    private PlantType plantType;

    public Plant(String name, PlantType plantType, double currentWaterLevel) {
        super(name);
        this.plantType = plantType;
        //this.minTemperatureLevel = minTemperatureLevel;
        //this.maxTemperatureLevel = maxTemperatureLevel;
        this.currentWaterLevel = currentWaterLevel;
        this.isUnderAttack = false;
        this.waterSensor = new WaterSensor(plantType);
    }

    /**
     * Getters
     */
    public double getMinWaterLevel() { return this.plantType.getMinWaterLevel(); }
    public double getMaxWaterLevel() { return this.plantType.getMaxWaterLevel(); }
    public double getMinTemperatureLevel() { return this.minTemperatureLevel; }
    public double getMaxTemperatureLevel() { return this.maxTemperatureLevel; }
    public double getCurrentWaterLevel() { return this.currentWaterLevel; }
    public boolean isUnderAttack() { return isUnderAttack; }
    public double getWaterLevel() {
        return this.waterSensor.getWaterLevel();
    }
    public boolean isWaterLevelLow(){
        return this.waterSensor.isWaterLevelLow();
    }

    /**
     * Update water level
     */
    public void updateWaterLevel(double newWaterLevel){
        this.waterSensor.updateWaterLevel(newWaterLevel);
    }

    /**
     * Sets the plant's under attack flag.
     * @param isUnderAttack whether the plant is currently being attacked.
     */
    public void setUnderAttack(boolean isUnderAttack) { this.isUnderAttack = isUnderAttack; }

    /**
     * Abstract methods to be implemented by subclasses
     */
    public abstract void water(double amount);
}
