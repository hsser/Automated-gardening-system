package organism.plant;

public class Peach extends Tree {
    public Peach(String name, PlantType plantType, double currentWaterLevel) {
        super(name, plantType, currentWaterLevel);
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the peach tree with " + amount + " units of water.");
    }
}
