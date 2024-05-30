package organism.plant;

public class Cherry extends Tree {
    public Cherry(String name, double minWaterLevel, double maxWaterLevel, double minTemperatureLevel, double maxTemperatureLevel, double minTemperature, double currentWaterLevel) {
        super(name, minWaterLevel, maxWaterLevel, minTemperatureLevel, maxTemperatureLevel, minTemperature, currentWaterLevel);
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the Cherry with "+ amount);
    }
}
