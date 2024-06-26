package controllers;

import io.GardenLogger;
import plant.PlantGroup;

/**
 * The PestController class provides specialized methods to manage pest attacks on plants.
 */
public class PestController {
    private PlantGroup plantGroup;

    public PestController(PlantGroup plantGroup) {
        this.plantGroup = plantGroup;
    }

    public void usePesticide() {
        // Specific logic to apply pesticide
        GardenLogger.log("Pest Controller", "Applying pesticide to help " + this.plantGroup.getName() + " restore health.");
        plantGroup.clearPest();
    }


    public void useLadybugs() {
        // Specific logic to release ladybugs
        GardenLogger.log("Pest Controller", "Deploying " + this.plantGroup.getNumOfPestsAttacking() +
                " ladybug" + ((plantGroup.getNumOfPestsAttacking() > 1) ? "s" : "") +
                " to help " + this.plantGroup.getName() + " fight off pests.");
        plantGroup.clearPest();
    }
}
