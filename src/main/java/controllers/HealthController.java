package controllers;

import io.GardenLogger;
import plant.Plant;
import plant.PlantGroup;
import sensors.TemperatureSensor;

public class HealthController {
    private PlantGroup plantGroup;

    public HealthController(PlantGroup plantGroup) {
        this.plantGroup = plantGroup;
    }

    public void reduceHealth(int amount) {
        for(Plant plant : plantGroup.getPlants()){
            plant.setHealth(plant.getHealth() - amount);
        }
        GardenLogger.log("Heath Controller", plantGroup.getName() + " health reduced by " + amount + ", current health: " + plantGroup.getHealth());
    }

    public void recoverHealth() {
        int currentTemperature = TemperatureSensor.getInstance().getTemperature();
        for(Plant plant : plantGroup.getPlants()){
            if (plant.getHealth() < 100 && plantGroup.getNumOfPestsAttacking() == 0 &&
                    plant.getCurrentWaterLevel() >= plant.getMinWaterLevel() &&
                    plant.getCurrentWaterLevel() <= plant.getMaxWaterLevel() &&
                    currentTemperature >= plant.getMinTemperatureLevel() &&
                    currentTemperature <= plant.getMaxTemperatureLevel()) {
                plant.setHealth(plant.getHealth() + 20);
                if (plant.getHealth() > 100) {
                    plant.setHealth(100);
                }
            }
        }

        GardenLogger.log("Heath Controller", plantGroup.getName() + " is recovering, current health: " + plantGroup.getHealth());
    }
}
