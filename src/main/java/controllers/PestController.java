package controllers;

import organism.plant.Plant;

/**
 * The PestController class provides methods to manage pest attacks on plants.
 */
public class PestController {
    private Plant plant;

    /**
     * Constructs a PestController for a specific plant.
     *
     * @param plant the plant to be protected from pests
     */
    public PestController(Plant plant){
        this.plant = plant;
    }

    /**
     * Uses pesticide to help the plant restore health if it is under pest attack.
     */
    public void pesticideMethod() {
        handlePestAttack("pesticide");
    }

    /**
     * Uses ladybugs to help the plant restore health if it is under pest attack.
     */
    public void ladybugMethod() {
        handlePestAttack("ladybug");
    }

    /**
     * Handles pest attack using the specified method.
     *
     * @param method the method used to handle the pest attack (pesticide or ladybug)
     */
    private void handlePestAttack(String method) {
        if (this.plant.isUnderAttack()) {
            System.out.println("Warning! " + this.plant.getName() + " is under pest attack! ");
            System.out.println("Using " + method + " to help plant restore health!");
            this.plant.setUnderAttack(false);
        } else {
            System.out.println(this.plant.getName() + " is not affected by pests now.");
        }
    }
}
