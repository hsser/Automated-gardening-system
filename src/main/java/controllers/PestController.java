package controllers;

import io.GardenLogger;
import plant.Plant;
import plant.PlantGroup;

/**
 * The PestController class provides specialized methods to manage pest attacks on plants.
 */
public class PestController {
    private PlantGroup plantGroup;

    /**
     * Constructs a PestController for a specific plant.
     *
     * @param plantGroup the plants to be protected from pests
     */
    public PestController(PlantGroup plantGroup) {
        this.plantGroup = plantGroup;
    }

    /**
     * Treats the plant with pesticides if it is under pest attack.
     */
    public void usePesticide() {
        // Specific logic to apply pesticide
        GardenLogger.log("Pest Controller", "Applying pesticide to help " + this.plantGroup.getName() + " restore health.");
        plantGroup.clearPest();
    }

    /**
     * Treats the plant with ladybugs if it is under pest attack.
     */
    public void useLadybugs() {
        // Specific logic to release ladybugs
        GardenLogger.log("Pest Controller", "Deploying " + this.plantGroup.getNumOfPestsAttacking() +
                " ladybug" + ((plantGroup.getNumOfPestsAttacking() > 1) ? "s" : "") +
                " to help " + this.plantGroup.getName() + " fight off the pests.");
        plantGroup.clearPest();
    }
}
