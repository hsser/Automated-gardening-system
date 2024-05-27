package organism.plant;

public abstract class Crop extends Plant {
    public Crop(String name, double waterRequirement, double sunlightRequirement,
                double temperatureRequirement, double growthRate, double minTemperature, double maxTemperature) {
        super(name, waterRequirement, sunlightRequirement, temperatureRequirement, growthRate, minTemperature, maxTemperature);
    }
}
