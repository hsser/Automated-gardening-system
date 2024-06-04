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
        // Specific logic to apply pesticide
        System.out.println("Applying pesticide to help " + this.plant.getName() + " restore health.");
        this.plant.setNumOfPestsAttacking(0);
    }

    /**
     * Treats the plant with ladybugs if it is under pest attack.
     */
    public void useLadybugs() {
        // Specific logic to release ladybugs
        System.out.println("Deploying " + this.plant.getNumOfPestsAttacking() +
                " ladybug" + ((plant.getNumOfPestsAttacking() > 1) ? "s" : "") +
                " to help " + this.plant.getName() + " fight off the pests.");
        this.plant.setNumOfPestsAttacking(0);
    }
}
