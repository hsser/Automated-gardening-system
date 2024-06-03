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
        if (newWaterLevel < plant.getMinWaterLevel() || newWaterLevel > plant.getMaxWaterLevel()) {
            System.out.println("The plant will die due to inappropriate water level!");
            WaterController.stopWatering(plant);
        } else {
            this.waterLevel = newWaterLevel;
            System.out.println("The plant's water level has been updated");
            System.out.println("Plant's current water level is " + plant.getCurrentWaterLevel());
        }
    }

    public void checkWaterLevel(Plant plant) {
        if(waterLevel <= plant.getPlantLowWaterThreshold()){
            WaterController.autoWatering(plant);
        }else if (waterLevel >= plant.getMaxWaterLevel()) {
            WaterController.stopWatering(plant);
        }
    }
}