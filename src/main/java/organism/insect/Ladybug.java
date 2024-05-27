package organism.insect;

import organism.Organism;

public class Ladybug extends Insect{
    public Ladybug(String name, double damage) {
        super(name, damage);
    }

    @Override
    public boolean isPredator() { return true; }

    /**
     * Ladybugs attack aphids specifically. We determine if the target is an aphid
     * by checking if it is not beneficial and not a predator.
     * @param o the organism being attacked
     */
    @Override
    public void attack(Organism o) {
        // Check if the target organism is not beneficial and not a predator, which aligns with being an aphid
        if (!o.isBeneficial() && !o.isPredator()) {
            o.decreaseHealth(this.getDamage());
            System.out.println(this.getName() + " attacks " + o.getName() + " for " + this.getDamage() + " damage.");
        } else {
            System.out.println(this.getName() + " cannot attack " + o.getName() + " as it is not a valid target.");
        }
    }
}
