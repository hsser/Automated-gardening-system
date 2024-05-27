package organism.plant;


import organism.Organism;
import sensors.HumiditySensor;

public abstract class Plant extends Organism {
    private double minWaterLevel;
    private double maxWaterLevel;
    private double minTemperatureLevel;
    private double maxTemperatureLevel;
    private double currentWaterLevel;
    private boolean isUnderAttack;  // Flag to indicate if the plant is being attacked
    private HumiditySensor humiditySensor;

    public Plant(String name, double minWaterLevel, double maxWaterLevel, double minTemperatureLevel,
                 double maxTemperatureLevel, double minTemperature, double currentWaterLevel) {
        super(name);
        this.minWaterLevel = minWaterLevel;
        this.maxWaterLevel = maxWaterLevel;
        this.minTemperatureLevel = minTemperatureLevel;
        this.maxTemperatureLevel = maxTemperatureLevel;
        this.currentWaterLevel = currentWaterLevel;
        this.isUnderAttack = false;
        this.humiditySensor = new HumiditySensor();
    }

    /**
     * Getters
     */
    public double getMinWaterLevel() { return this.minWaterLevel; }
    public double getMaxWaterLevel() { return this.maxWaterLevel; }
    public double getMinTemperatureLevel() { return this.minTemperatureLevel; }
    public double getMaxTemperatureLevel() { return this.maxTemperatureLevel; }
    public double getCurrentWaterLevel() { return this.currentWaterLevel; }
    public boolean isUnderAttack() { return isUnderAttack; }
    public double getHumidityLevel() {
        return this.humiditySensor.getHumidityLevel();
    }
    public boolean isHumidityLevelLow(){
        return this.humiditySensor.isHumidityLevelLow();
    }

    // update humidity level
    public void updateHumidityLevel(double newHumidityLevel){
        this.humiditySensor.setHumidityLevel(newHumidityLevel);
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
    public abstract void fertilize();
}

