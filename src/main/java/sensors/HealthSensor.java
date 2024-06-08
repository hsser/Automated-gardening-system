package sensors;

import io.GardenLogger;
import plant.Plant;
import controllers.HealthController;
import plant.PlantGroup;

public class HealthSensor {
    private PlantGroup plantGroup;
    private HealthController healthController;

    public HealthSensor(PlantGroup plantGroup) {
        this.plantGroup = plantGroup;
        this.healthController = new HealthController(plantGroup);
    }

    public void checkHealth() {
        if (plantGroup.getCurrentWaterLevel() < plantGroup.getMinWaterLevel() ||
                plantGroup.getCurrentWaterLevel() > plantGroup.getMaxWaterLevel()) {
            healthController.reduceHealth(10);
        }

        if (plantGroup.getNumOfPestsAttacking() > 0) {
            healthController.reduceHealth(20);
        }

        double currentTemperature = TemperatureSensor.getTemperature();
        if (currentTemperature < plantGroup.getMinTemperatureLevel() ||
                currentTemperature > plantGroup.getMaxTemperatureLevel()) {
            healthController.reduceHealth(15);
        }

        if (plantGroup.getHealth() <= 0) {
            GardenLogger.log("Health Sensor", plantGroup.getName() + " has died.");
        } else {
            healthController.recoverHealth();  // Recover health if conditions are good
        }
    }
}
