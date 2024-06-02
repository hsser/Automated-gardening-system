package plant;

public class Hydrangea extends Flower {
    public Hydrangea() {
        super();
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the hydrangea with " + amount + " ml of water");
    }
}
