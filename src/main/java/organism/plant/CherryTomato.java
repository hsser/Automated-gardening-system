package organism.plant;

public class CherryTomato extends Crop {
    public CherryTomato(String name, PlantType plantType, double currentWaterLevel) {
        super(name, plantType, currentWaterLevel);
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the cherry tomato with " + amount + " ml of water");
    }
}
