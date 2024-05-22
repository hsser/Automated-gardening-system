package Organism.Plant;


import Organism.Organism;

public abstract class Plant extends Organism {
    private double waterRequirement; // water requirement of the plant
    private double sunlightRequirement; // sunlight requirement of the plant
    private double temperatureRequirement; // temperature requirement of the plant
    private double growthRate; // growth rate of the plant
    private double[] TemperatureRange; // temperature range of the plant

    public Plant(String name, double waterRequirement, double sunlightRequirement,
                 double temperatureRequirement, double growthRate, double minTemperature, double maxTemperature) {
        super(name);
        this.waterRequirement = waterRequirement;
        this.sunlightRequirement = sunlightRequirement;
        this.temperatureRequirement = temperatureRequirement;
        this.growthRate = growthRate;
        this.TemperatureRange = new double[]{minTemperature, maxTemperature};
    }

    /**
     * Getters
     */
    public double getWaterRequirement() { return this.waterRequirement; }
    public double getSunlightRequirement() { return this.sunlightRequirement; }
    public double getTemperatureRequirement() { return this.temperatureRequirement; }
    public double getGrowthRate() { return this.growthRate; }
    public double[] getTemperatureRange() { return this.TemperatureRange; }

    /**
     * Abstract methods to be implemented by subclasses
     */
    public abstract void water(double amount);
    public abstract void fertilize();
}

