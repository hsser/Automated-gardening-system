package controllers;

import plant.Plant;

/**
 * The PestController class provides specialized methods to manage pest attacks on plants.
 */
public class PestController {
    private Plant plant;

    /**
     * Constructs a PestController for a specific plant.
     *
     * @param plant the plant to be protected from pests
     */
    public PestController(Plant plant) {
        this.plant = plant;
    }

    /**
     * Treats the plant with pesticides if it is under pest attack.
     */
    public void usePesticide() {
        if (this.plant.isUnderAttack()) {
            System.out.println("Warning! " + this.plant.getName() + " is under pest attack using pesticides!");
            applyPesticide();
        } else {
            System.out.println(this.plant.getName() + " is not currently affected by pests.");
        }
    }

    /**
     * Treats the plant with ladybugs if it is under pest attack.
     */
    public void useLadybugs() {
        if (this.plant.isUnderAttack()) {
            System.out.println("Warning! " + this.plant.getName() + " is under pest attack using ladybugs!");
            applyLadybugs();
        } else {
            System.out.println(this.plant.getName() + " is not currently affected by pests.");
        }
    }

    /**
     * Applies pesticide to the plant and logs the action.
     */
    private void applyPesticide() {
        // Specific logic to apply pesticide
        System.out.println("Applying pesticide to help " + this.plant.getName() + " restore health.");
        this.plant.setUnderAttack(false);
    }

    /**
     * Releases ladybugs to the plant and logs the action.
     */
    private void applyLadybugs() {
        // Specific logic to release ladybugs
        System.out.println("Releasing ladybugs to help " + this.plant.getName() + " combat pests.");
        this.plant.setUnderAttack(false);
    }
}
