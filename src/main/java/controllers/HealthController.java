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

    public void reduceHealth(int amount, int parameter) {
        int newHealth;
        for (Plant plant : plantGroup.getPlants()) {
            newHealth = plant.getHealth() - amount;
            if (newHealth > 0) {
                plant.setHealth(plant.getHealth() - amount);
            } else {
                plant.setHealth(0);
            }
        }
        switch (amount) {
            case 10:
                GardenLogger.log("Heath Controller", "Because of low water level, " + plantGroup.getName() + " health reduced by " + amount + ", current health: " + plantGroup.getHealth());
                break;
            case 20:
                GardenLogger.log("Heath Controller", "Because of pests' attacking, " + plantGroup.getName() + " health reduced by " + amount + ", current health: " + plantGroup.getHealth());
                break;
            case 15:
                GardenLogger.log("Heath Controller", "Because of extreme temperature " + parameter + " °F, " + plantGroup.getName() + " health reduced by " + amount + ", current health: " + plantGroup.getHealth());
                break;
        }
    }

    public void recoverHealth() {
        boolean isRecovering = false;
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
                isRecovering = true;
            }
        }
        if(isRecovering){
            GardenLogger.log("Heath Controller", plantGroup.getName() + " is recovering, current health: " + plantGroup.getHealth());
        }
    }
}
