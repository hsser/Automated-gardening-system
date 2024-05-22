package Organism.Insect;

import Organism.Organism;

public class Aphid extends Insect{
    public Aphid(String name, double damage) {
        super(name, damage);
    }

    @Override
    public boolean isBeneficial() { return false; }

    /**
     * Aphids attack beneficial organisms that are not predators.
     * This typically includes plants, but excludes predatory insects like ladybugs.
     * @param o the organism being attacked
     */
    @Override
    public void attack(Organism o) {
        // Check that the target organism is beneficial and not a predator
        if (o.isBeneficial() && !o.isPredator()) {
            o.decreaseHealth(this.getDamage());
            System.out.println(this.getName() + " attacks " + o.getName() + " for " + this.getDamage() + " damage.");
        } else {
            System.out.println(this.getName() + " cannot attack " + o.getName() +
                    " because it is either not beneficial or it is a predator.");
        }
    }
}
