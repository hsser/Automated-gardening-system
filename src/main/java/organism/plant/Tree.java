package organism.plant;

public abstract class Tree extends Plant {
    public Tree(String name, PlantType plantType, double currentWaterLevel) {
        super(name, plantType, currentWaterLevel);
    }

    @Override
    public void water(double amount) {
        System.out.println("Watering the Peach with "+ amount);
    }
}
