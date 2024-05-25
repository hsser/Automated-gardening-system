package Organism.Plant;

public class ChiliPepper extends Crop {
    public ChiliPepper(String name, double waterRequirement, double sunlightRequirement, double temperatureRequirement,
                       double growthRate, double minTemperature, double maxTemperature) {
        super(name, waterRequirement, sunlightRequirement, temperatureRequirement, growthRate, minTemperature, maxTemperature);
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the ChiliPepper with " + amount + " ml of water");
    }

    @Override
    public void fertilize() {
        System.out.println("Fertilizing the ChiliPepper");
    }
}