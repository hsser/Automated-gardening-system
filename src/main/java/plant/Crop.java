package plant;

public abstract class Crop extends Plant {
    public Crop(String name) {
        super(name, PlantType.CROP, 60);
    }
}
