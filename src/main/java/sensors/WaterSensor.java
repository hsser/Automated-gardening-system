package sensors;

import controllers.WaterController;
import plant.Plant;

public class WaterSensor {
    private double waterLevel;
    private Plant plant;

    public WaterSensor(Plant plant) {
        this.plant = plant;
        this.waterLevel = plant.getCurrentWaterLevel();
    }

    public double getWaterLevel() {
        return waterLevel;
    }

    public void updateWaterLevel(double newWaterLevel) {
        if (newWaterLevel < plant.getPlantLowWaterThreshold()){
            WaterController.autoWatering(plant);
        } else if (newWaterLevel > plant.getMaxWaterLevel()) {
            WaterController.stopWatering(plant);
        } else {
            this.waterLevel = newWaterLevel;
            System.out.println("The plant's water level has been updated");
            System.out.println("Plant's current water level is " + plant.getCurrentWaterLevel());
        }
    }
}