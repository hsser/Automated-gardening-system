package plant;

public class Rose extends Flower {
    public Rose() {
        super("Rose");
    }

    @Override
    public void water(int amount) {
        System.out.println("Watering the rose with " + amount + " ml of water");
    }
}
