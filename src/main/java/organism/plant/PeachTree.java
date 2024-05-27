package organism.plant;

public class PeachTree extends Trees{
    public PeachTree(String name, double minWaterLevel, double maxWaterLevel, double minTemperatureLevel, double maxTemperatureLevel, double minTemperature, double currentWaterLevel) {
        super(name, minWaterLevel, maxWaterLevel, minTemperatureLevel, maxTemperatureLevel, minTemperature, currentWaterLevel);
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the peach tree with "+ amount);
    }

    @Override
    public void fertilize() {
        System.out.println("Fertilizing the peach tree");
    }
}
