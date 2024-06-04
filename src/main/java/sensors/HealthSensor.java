package sensors;

import plant.Plant;
import controllers.HealthController;

public class HealthSensor {
    private Plant plant;
    private HealthController healthController;

    public HealthSensor(Plant plant) {
        this.plant = plant;
        this.healthController = new HealthController(plant);
    }

    public void checkHealth() {
        if (plant.getCurrentWaterLevel() < plant.getMinWaterLevel() ||
                plant.getCurrentWaterLevel() > plant.getMaxWaterLevel()) {
            healthController.reduceHealth(10);
        }

        if (plant.getNumOfPestsAttacking() > 0) {
            healthController.reduceHealth(20);
        }

        double currentTemperature = TemperatureSensor.getTemperature();
        if (currentTemperature < plant.getMinTemperatureLevel() ||
                currentTemperature > plant.getMaxTemperatureLevel()) {
            healthController.reduceHealth(15);
        }

        if (plant.getHealth() <= 0) {
            System.out.println(plant.getName() + " has died.");
        } else {
            healthController.recoverHealth();  // Recover health if conditions are good
        }
    }
}
