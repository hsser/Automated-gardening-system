package plant;

public class CherryTomato extends Crop {
    public CherryTomato() {
        super();
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the cherry tomato with " + amount + " ml of water");
    }
}
