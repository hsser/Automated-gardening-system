package plant;

public class Rose extends Flower {
    public Rose() {
        super();
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the rose with " + amount + " ml of water");
    }
}
