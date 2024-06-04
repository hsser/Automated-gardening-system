package sensors;

import plant.Plant;
import controllers.PestController;
import java.util.Random;

/**
 * The PestSensor class simulates a sensor that monitors plants for pest attacks.
 */
public class PestSensor {
    private Plant plant;
    private PestController pestController;
    private Random random;  // Add a Random instance for method selection

    /**
     * Constructs a PestSensor with a specific plant and initializes the associated pest controller.
     *
     * @param plant the plant to be monitored for pest attacks
     */
    public PestSensor(Plant plant) {
        this.plant = plant;
        this.pestController = new PestController(plant);  // Initialize PestController with the same plant
        this.random = new Random();  // Initialize the Random instance
    }

    /**
     * Monitors and handles pest attacks if detected.
     */
    public void monitorForPestAttack() {
        if (plant.getNumOfPestsAttacking() > 0) {
            System.out.println("Warning! " + plant.getName() + " is being attacked by " +
                    plant.getNumOfPestsAttacking() + " " +
                    plant.getTypeOfPestsAttacking() + ((plant.getNumOfPestsAttacking() > 1) ? "s!" : "!"));
            if (random.nextBoolean()) {
                pestController.usePesticide();  // Randomly use pesticide
            } else {
                pestController.useLadybugs();  // Or randomly use ladybugs
            }
        } else {
            System.out.println("No pest attack detected on " + plant.getName() + ".");
        }
    }
}

