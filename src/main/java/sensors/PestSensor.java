package sensors;

import application.PestAttackHandlingAction;
import io.GardenLogger;
import plant.Plant;
import controllers.PestController;
import plant.PlantGroup;

import java.util.Random;

/**
 * The PestSensor class simulates a sensor that monitors plants for pest attacks.
 */
public class PestSensor {
    private PlantGroup plantGroup;
    private PestController pestController;
    private Random random;  // Add a Random instance for method selection
    PestAttackHandlingAction handlingAction;
    private HealthCheckCallback healthCheckCallback;


    /**
     * Constructs a PestSensor with a specific plant and initializes the associated pest controller.
     *
     * @param plantGroup the plantGroup to be monitored for pest attacks
     */
    public PestSensor(PlantGroup plantGroup) {
        this.plantGroup= plantGroup;
        this.pestController = new PestController(plantGroup);  // Initialize PestController with the same plant
        this.random = new Random();  // Initialize the Random instance
    }

    public void setHealthCheckCallback(HealthCheckCallback callback) {
        this.healthCheckCallback = callback;
    }

    public void setOnPestAttackHandling(PestAttackHandlingAction handlingAction) {
        this.handlingAction = handlingAction;
    }

    /**
     * Monitors and handles pest attacks if detected.
     */
    public void monitorForPestAttack() {
        if (plantGroup.getNumOfPestsAttacking() > 0) {

            GardenLogger.log("Pest Sensor", plantGroup.getName() + " is being attacked by " +
                    plantGroup.getNumOfPestsAttacking() + " " +
                    plantGroup.getTypeOfPestsAttacking() + ((plantGroup.getNumOfPestsAttacking() > 1) ? "s!" : "!"));

//            pestController.pestAttacking();

            /*if (healthCheckCallback != null) {
                GardenLogger.log("Pest Sensor", "Executing health check callback for " + plantGroup.getName());
                healthCheckCallback.execute();
            }*/

            if (random.nextBoolean()) {
                pestController.usePesticide();  // Randomly use pesticide
                // Update UI: show pesticide
                if(handlingAction != null)
                    handlingAction.run(plantGroup.getCurrentPlotIndex(), "pesticide");
            } else {
                pestController.useLadybugs();  // Or randomly use ladybugs
                // Update UI: show ladybugs
                if(handlingAction != null)
                    handlingAction.run(plantGroup.getCurrentPlotIndex(), "ladybug");
            }
        } else {
            //GardenLogger.log("Pest Sensor", "No pest attack detected on " + plantGroup.getName() + ".");
        }
    }
}

