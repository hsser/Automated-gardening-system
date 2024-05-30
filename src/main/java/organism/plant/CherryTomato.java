package organism.plant;
public class CherryTomato extends Crop {
    public CherryTomato(String name, double waterRequirement, double sunlightRequirement, double temperatureRequirement,
                        double growthRate, double minTemperature, double maxTemperature) {
        super(name, waterRequirement, sunlightRequirement, temperatureRequirement, growthRate, minTemperature, maxTemperature);
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the CherryTomato with " + amount + " ml of water");
    }
}
