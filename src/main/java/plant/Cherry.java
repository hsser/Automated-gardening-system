package plant;

public class Cherry extends Tree {
    public Cherry() {
        super();
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the cherry tree with " + amount + " units of water.");
    }
}
