package plant;

public enum PlantType {
    TREE(20.0, 100.0, 30.0, 68.0, 86.0),
    FLOWER(10.0, 50.0, 20.0, 68.0, 86.0),
    CROP(30.0, 80.0, 40.0, 68.0, 86.0);

    private final double minWaterLevel;
    private final double maxWaterLevel;
    private final double lowWaterThreshold;
    private final double minTemperature;
    private final double maxTemperature;

    PlantType(double minWaterLevel, double maxWaterLevel, double lowWaterThreshold,
              double minTemperature, double maxTemperature) {
        this.minWaterLevel = minWaterLevel;
        this.maxWaterLevel = maxWaterLevel;
        this.lowWaterThreshold = lowWaterThreshold;
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

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }
}
