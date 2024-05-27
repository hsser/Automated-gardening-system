package controllers;

import organism.plant.Plant;

/**
 * The HumidityController class provides methods to manage the humidity levels of plants.
 */
public class HumidityController {
    private Plant plant;

    /**
     * Constructs a HumidityController for a specific plant.
     *
     * @param plant the plant to be monitored and controlled
     */
    public HumidityController(Plant plant){
        this.plant = plant;
    }

    /**
     * Auto-watering of the plant based on its current humidity level.
     */
    public void autoWatering(){
        double currentHumidityLevel = plant.getHumidityLevel();
        System.out.println("Current humidity level is " + currentHumidityLevel);

        if(plant.isHumidityLevelLow()){
            System.out.println("Warning: The humidity level of " + plant.getName() + " is low!");
            increaseHumidityLevel(currentHumidityLevel);
        }else{
            System.out.println("The humidity level for " + plant.getName() + " is sufficient!");
        }
    }

    /**
     * Increase the humidity level of the plant.
     *
     * @param currentHumidityLevel the current humidity level of the plant
     */
    public void increaseHumidityLevel(double currentHumidityLevel){
        // Increase the humidity level
        plant.updateHumidityLevel(currentHumidityLevel + 1.0);
        System.out.println("Now, new humidity level of " + plant.getName() + " is " + plant.getHumidityLevel());
    }
}
