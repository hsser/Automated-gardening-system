package organism.insect;

import organism.Organism;

public abstract class Insect extends Organism {
    private double damage; // damage caused by the insect



    public Insect(String name, double damage) {
        super(name);
        this.damage = damage;
    }

    /**
     * Getter
     */
    public double getDamage() { return damage; }

    /**
     * Abstract methods to be implemented by subclasses
     * @param o organism to attack
     */
    public abstract void attack(Organism o);

}
