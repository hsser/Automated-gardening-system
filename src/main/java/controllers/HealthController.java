package controllers;

import plant.Plant;
import sensors.TemperatureSensor;

public class HealthController {
    private Plant plant;

    public HealthController(Plant plant) {
        this.plant = plant;
    }

    public void reduceHealth(int amount) {
        plant.setHealth(plant.getHealth() - amount);
        System.out.println(plant.getName() + " health reduced by " + amount + ", current health: " + plant.getHealth());
    }

    public void recoverHealth() {
        if (plant.getHealth() < 100 && plant.getNumOfPestsAttacking() == 0 &&
                plant.getCurrentWaterLevel() >= plant.getMinWaterLevel() &&
                plant.getCurrentWaterLevel() <= plant.getMaxWaterLevel() &&
                TemperatureSensor.getTemperature() >= plant.getMinTemperatureLevel() &&
                TemperatureSensor.getTemperature() <= plant.getMaxTemperatureLevel()) {
            plant.setHealth(plant.getHealth() + 20);
            if (plant.getHealth() > 100) {
                plant.setHealth(100);
            }
            System.out.println(plant.getName() + " is recovering, current health: " + plant.getHealth());
        }
    }
}
