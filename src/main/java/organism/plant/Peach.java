package organism.plant;

public class Peach extends Tree {
    public Peach(String name, double minWaterLevel, double maxWaterLevel, double minTemperatureLevel, double maxTemperatureLevel, double minTemperature, double currentWaterLevel) {
        super(name, minWaterLevel, maxWaterLevel, minTemperatureLevel, maxTemperatureLevel, minTemperature, currentWaterLevel);
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the Peach with "+ amount);
    }
}
