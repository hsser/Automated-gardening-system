package plant;

public class Hydrangea extends Flower {
    public Hydrangea() {
        super("Hydrangea");
    }

    @Override
    public void water(int amount) {
        System.out.println("Watering the hydrangea with " + amount + " ml of water");
    }
}
