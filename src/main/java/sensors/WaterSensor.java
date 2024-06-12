package sensors;

import application.WateringProtectionAction;
import application.SubsystemEffectAction;
import controllers.WaterController;
import io.GardenLogger;
import plant.PlantGroup;

public class WaterSensor {
    private PlantGroup plantGroup;
    SubsystemEffectAction sprinklerAction;

    public WaterSensor(PlantGroup plantGroup) {
        this.plantGroup = plantGroup;
    }

    public void setSprinklerAction(SubsystemEffectAction sprinklerAction) {
        this.sprinklerAction = sprinklerAction;
    }

    public void updateWaterLevel(int newWaterLevel) {
        // Sprinkler automation
        if (newWaterLevel < plantGroup.getLowWaterThreshold()){
            WaterController.autoWatering(plantGroup);
            if (this.sprinklerAction != null) {
                sprinklerAction.run("sprinkler");
            }
        }

        // WaterProtection automation
        if (newWaterLevel < plantGroup.getHighWaterThreshold() && plantGroup.isWaterProtection()) {
            WaterController.turnWaterProtection(plantGroup, false);
        }
        if (newWaterLevel >= plantGroup.getHighWaterThreshold()) {
            WaterController.turnWaterProtection(plantGroup, true);
        }
    }

}