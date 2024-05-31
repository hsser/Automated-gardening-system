package plant;

public class Hydrangea extends Flower {
    public Hydrangea(String name, PlantType plantType, double currentWaterLevel) {
        super(name, plantType, currentWaterLevel);
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the hydrangea with " + amount + " ml of water");
    }
}
