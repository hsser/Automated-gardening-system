package plant;

public abstract class Tree extends Plant {
    public Tree(String name) {
        super(name, PlantType.TREE, 80);
    }
}
