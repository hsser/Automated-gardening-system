package organism.plant;

public class Rose extends Flower{
    public Rose(String name, double waterRequirement, double sunlightRequirement, double temperatureRequirement, double growthRate, double minTemperature, double maxTemperature) {
        super(name, waterRequirement, sunlightRequirement, temperatureRequirement, growthRate, minTemperature, maxTemperature);
    }

    @Override
    public void water(double amount) {

        System.out.println("Watering the Rose with " + amount + " ml of water");
    }
}
