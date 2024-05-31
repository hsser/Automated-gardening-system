package plant;

public class ChiliPepper extends Crop {
    public ChiliPepper(String name, PlantType plantType, double currentWaterLevel) {
        super(name, plantType, currentWaterLevel);
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the chili pepper with " + amount + " ml of water");
    }
}
