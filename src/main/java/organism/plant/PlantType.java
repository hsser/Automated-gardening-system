package organism.plant;

public enum PlantType {
    TREE(20.0, 100.0, 30.0, 6.0, 1.5, 68.0, 86.0),
    FLOWER(10.0, 50.0, 20.0, 8.0, 2.0, 68.0, 86.0),
    CROP(15.0, 75.0, 25.0, 10.0, 3.0, 68.0, 86.0);

    private final double minWaterLevel;
    private final double maxWaterLevel;
    private final double lowWaterThreshold;
    private final double sunlightRequirement;
    private final double growthRate;
    private final double minTemperature;
    private final double maxTemperature;

    PlantType(double minWaterLevel, double maxWaterLevel, double lowWaterThreshold,
              double sunlightRequirement, double growthRate, double minTemperature, double maxTemperature) {
        this.minWaterLevel = minWaterLevel;
        this.maxWaterLevel = maxWaterLevel;
        this.lowWaterThreshold = lowWaterThreshold;
        this.sunlightRequirement = sunlightRequirement;
        this.growthRate = growthRate;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public double getMinWaterLevel() {
        return minWaterLevel;
    }

    public double getMaxWaterLevel() {
        return maxWaterLevel;
    }

    public double getLowWaterThreshold() {
        return lowWaterThreshold;
    }

    public double getSunlightRequirement() {
        return sunlightRequirement;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }
}
