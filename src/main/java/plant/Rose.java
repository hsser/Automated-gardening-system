package plant;

public class Rose extends Flower {
    public Rose(String name, PlantType plantType, double currentWaterLevel) {
        super(name, plantType, currentWaterLevel);
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the rose with " + amount + " ml of water");
    }
}
