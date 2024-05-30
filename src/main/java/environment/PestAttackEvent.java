package environment;

import organism.plant.Plant;

public class PestAttackEvent extends Event {
    final private Plant plant;
    public PestAttackEvent(Plant plant) {
        super("PestAttackEvent");
        this.plant = plant;
    }

    /**
     * Trigger pest attack event, set the target plant to be under attack, and involve pest control system to deal with the pest.
     * If there is no plant in the garden, print out the message accordingly
     */
    public void trigger() {
        if (plant != null) {
            plant.setUnderAttack(true);
            // TODO: Need to involve pest control module
            System.out.println("Event: Pest attack to " + plant.getName());
        } else {
            System.out.println("Event: Pest attack to the garden, but no plant is attacked");
        }
    }
}
