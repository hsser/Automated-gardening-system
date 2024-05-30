package organism.plant;

public enum PlantType {
    TREE(20.0, 100.0, 30.0),
    FLOWER(10.0, 50.0, 20.0),
    CROP(15.0, 75.0, 25.0);

    private final double minWaterLevel;
    private final double maxWaterLevel;
    private final double lowWaterThreshold;

    PlantType(double minWaterLevel, double maxHumidityLevel, double lowHumidityThreshold) {
        this.minWaterLevel = minWaterLevel;
        this.maxWaterLevel = maxHumidityLevel;
        this.lowWaterThreshold = lowHumidityThreshold;
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
}
