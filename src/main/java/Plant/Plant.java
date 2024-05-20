package Plant;


public abstract class Plant {
    private String name; // name of the plant
    private double health; // health of the plant
    private double waterRequirement; // water requirement of the plant
    private double sunlightRequirement; // sunlight requirement of the plant
    private double temperatureRequirement; // temperature requirement of the plant
    private double growthRate; // growth rate of the plant
    private double[] TemperatureRange; // temperature range of the plant

    // Constructor
    public Plant(String name, double health, double waterRequirement, double sunlightRequirement, double temperatureRequirement, double growthRate, double minTemperature, double maxTemperature) {
        this.name = name;
        this.health = health;
        this.waterRequirement = waterRequirement;
        this.sunlightRequirement = sunlightRequirement;
        this.temperatureRequirement = temperatureRequirement;
        this.growthRate = growthRate;
        this.TemperatureRange = new double[]{minTemperature, maxTemperature};
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public double getWaterRequirement() {
        return waterRequirement;
    }

    public double getSunlightRequirement() {
        return sunlightRequirement;
    }

    public double getTemperatureRequirement() {
        return temperatureRequirement;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public double[] getTemperatureRange() {
        return TemperatureRange;
    }

    // Abstract methods to be implemented by subclasses
    public abstract void water(double amount);
    public abstract void fertilize();
}

