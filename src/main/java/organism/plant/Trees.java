package organism.plant;

public class Trees extends Plant{
    public Trees(String name, double minWaterLevel, double maxWaterLevel, double minTemperatureLevel, double maxTemperatureLevel, double minTemperature, double currentWaterLevel) {
        super(name, minWaterLevel, maxWaterLevel, minTemperatureLevel, maxTemperatureLevel, minTemperature, currentWaterLevel);
    }

    @Override
    public void water(double amount) {

    }

    @Override
    public void fertilize() {

    }
}
