package plant;

public class Peach extends Tree {
    public Peach() {
        super("Peach");
    }

    @Override
    public void water(int amount) {
        System.out.println("Watering the peach tree with " + amount + " units of water.");
    }
}
