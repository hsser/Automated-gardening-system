package plant;

public class ChiliPepper extends Crop {
    public ChiliPepper() {
        super("ChiliPepper");
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the chili pepper with " + amount + " ml of water");
    }
}
