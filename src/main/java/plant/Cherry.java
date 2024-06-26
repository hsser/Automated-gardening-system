package plant;

public class Cherry extends Tree {
    public Cherry() {
        super("Cherry");
    }

    @Override
    public void water(int amount) {
        System.out.println("Watering the cherry tree with " + amount + " units of water.");
    }
}
