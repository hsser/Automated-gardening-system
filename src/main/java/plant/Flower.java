package plant;

public abstract class Flower extends Plant {
    public Flower(String name) {
        super(name, PlantType.FLOWER, 30);
    }
}
