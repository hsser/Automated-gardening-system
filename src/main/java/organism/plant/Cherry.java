package organism.plant;

public class Cherry extends Tree {
    public Cherry(String name, PlantType plantType, double currentWaterLevel) {
        super(name, plantType, currentWaterLevel);
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the cherry tree with " + amount + " units of water.");
    }
}