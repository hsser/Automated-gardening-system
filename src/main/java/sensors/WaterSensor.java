package sensors;

import application.OffWateringProtectionAction;
import application.OnWateringProtectionAction;
import application.SubsystemEffectAction;
import controllers.WaterController;
import io.GardenLogger;
import plant.PlantGroup;

public class WaterSensor {
    private PlantGroup plantGroup;
    SubsystemEffectAction sprinklerAction;
    OnWateringProtectionAction onWateringProtectionAction;
    OffWateringProtectionAction offWateringProtectionAction;
    private HealthCheckCallback healthCheckCallback;

    public WaterSensor(PlantGroup plantGroup) {
        this.plantGroup = plantGroup;
    }

    public void setSprinklerAction(SubsystemEffectAction sprinklerAction) {
        this.sprinklerAction = sprinklerAction;
    }

    public void setOnWateringProtection(OnWateringProtectionAction onWateringProtectionAction) {
        this.onWateringProtectionAction = onWateringProtectionAction;
    }

    public void setOffWateringProtection(OffWateringProtectionAction offWateringProtectionAction) {
        this.offWateringProtectionAction = offWateringProtectionAction;
    }

    public void updateWaterLevel(int newWaterLevel) {
        if (newWaterLevel < plantGroup.getLowWaterThreshold()){
            WaterController.autoWatering(plantGroup);
            // Update UI: hide watering protection and show sprinkler
            if (this.onWateringProtectionAction != null) {
                offWateringProtectionAction.run();
            }
            if (this.sprinklerAction != null) {
                sprinklerAction.run("sprinkler");
            }
        } else if (newWaterLevel > plantGroup.getMaxWaterLevel()) {
            WaterController.stopWatering(plantGroup);
            // Update UI: show watering protection
            if (this.onWateringProtectionAction != null) {
                onWateringProtectionAction.run();
            }
        } else {
            plantGroup.setCurrentWaterLevel(newWaterLevel);
            GardenLogger.log("Water Sensor", plantGroup.getName() + "'s water level has been updated, it's current water level is " + plantGroup.getCurrentWaterLevel());
        }
    }
    public void dailyWaterDecrease(PlantGroup plantGroup){
        WaterController.dailyWaterDecrease(plantGroup);

        if (healthCheckCallback != null) {
            healthCheckCallback.execute();
        }
    }

    public void setHealthCheckCallback(HealthCheckCallback callback) {
        this.healthCheckCallback = callback;
    }

}