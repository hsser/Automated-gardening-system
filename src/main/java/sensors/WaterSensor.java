package sensors;

import controllers.WaterController;
import plant.Plant;

public class WaterSensor {
    private Plant plant;

    public WaterSensor(Plant plant) {
        this.plant = plant;
    }

    public void updateWaterLevel(int newWaterLevel) {
        if (newWaterLevel < plant.getPlantLowWaterThreshold()){
            WaterController.autoWatering(plant);
        } else if (newWaterLevel > plant.getMaxWaterLevel()) {
            WaterController.stopWatering(plant);
        } else {
            plant.setCurrentWaterLevel(newWaterLevel);
            System.out.println("The plant's water level has been updated, it's current water level is " + plant.getCurrentWaterLevel());
        }
    }
}