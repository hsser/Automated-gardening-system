package plant;

public abstract class Crop extends Plant {
    public Crop(String name, PlantType plantType, double currentWaterLevel) {
        super(name, plantType, currentWaterLevel);
    }
}
