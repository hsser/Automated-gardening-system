package plant;

public class Peach extends Tree {
    public Peach() {
        super("Peach");
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the peach tree with " + amount + " units of water.");
    }
}
