package organism.plant;

public class AppleTree extends Trees{
    public AppleTree(String name, double minWaterLevel, double maxWaterLevel, double minTemperatureLevel, double maxTemperatureLevel, double minTemperature, double currentWaterLevel) {
        super(name, minWaterLevel, maxWaterLevel, minTemperatureLevel, maxTemperatureLevel, minTemperature, currentWaterLevel);
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the apple tree with "+ amount);
    }

    @Override
    public void fertilize() {
        System.out.println("Fertilizing the apple tree");
    }
}
