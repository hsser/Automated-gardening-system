package Plant;

public class Flower extends Plant{
    public Flower(String name, double health, double waterRequirement, double sunlightRequirement, double temperatureRequirement, double growthRate, double minTemperature, double maxTemperature) {
        super(name, health, waterRequirement, sunlightRequirement, temperatureRequirement, growthRate, minTemperature, maxTemperature);
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the flower with " + amount + " ml of water");
    }

    @Override
    public void fertilize() {
        System.out.println("Fertilizing the flower");
    }
}