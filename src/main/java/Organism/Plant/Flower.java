package Organism.Plant;

public abstract class Flower extends Plant{
    public Flower(String name, double waterRequirement, double sunlightRequirement, double temperatureRequirement, double growthRate, double minTemperature, double maxTemperature) {
        super(name, waterRequirement, sunlightRequirement, temperatureRequirement, growthRate, minTemperature, maxTemperature);
    }

    public abstract void water(double amount);
    public abstract void fertilize();
}
