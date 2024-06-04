package plant;

public class CherryTomato extends Crop {
    public CherryTomato() {
        super("CherryTomato");
    }

    @Override
    public void water(int amount) {
        System.out.println("Watering the cherry tomato with " + amount + " ml of water");
    }
}
