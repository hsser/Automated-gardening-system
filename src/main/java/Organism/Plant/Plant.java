package Organism.Plant;


import Organism.Organism;

public abstract class Plant extends Organism {
    private double minWaterLevel;
    private double maxWaterLevel;
    private double minTemperatureLevel;
    private double maxTemperatureLevel;
    private double currentWaterLevel;
    private boolean isUnderAttack;  // Flag to indicate if the plant is being attacked

    public Plant(String name, double minWaterLevel, double maxWaterLevel, double minTemperatureLevel,
                 double maxTemperatureLevel, double minTemperature, double currentWaterLevel) {
        super(name);
        this.minWaterLevel = minWaterLevel;
        this.maxWaterLevel = maxWaterLevel;
        this.minTemperatureLevel = minTemperatureLevel;
        this.maxTemperatureLevel = maxTemperatureLevel;
        this.currentWaterLevel = currentWaterLevel;
        this.isUnderAttack = false;
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

