package controllers;

import io.GardenLogger;
import sensors.TemperatureSensor;

public class TemperatureController {
    private final int HIGH_TEMPERATURE_THRESHOLD = 104;
    private final int LOW_TEMPERATURE_THRESHOLD = 50;
    private final int OPTIMAL_TEMPERATURE = 77;

    private static TemperatureController instance;

    private TemperatureController() {}

    public static synchronized TemperatureController getInstance() {
        if (instance == null) {
            instance = new TemperatureController();
        }
        return instance;
    }

    public int adjustTemperature(int currentTemperature) {
        if (currentTemperature > HIGH_TEMPERATURE_THRESHOLD) {
            GardenLogger.log("Temperature Controller", "Warning: Temperature has exceeded the high limit of " + HIGH_TEMPERATURE_THRESHOLD + " degrees.");
            return coolDown(currentTemperature, OPTIMAL_TEMPERATURE);
        } else if (currentTemperature < LOW_TEMPERATURE_THRESHOLD) {
            GardenLogger.log("Temperature Controller","Warning: Temperature has fallen below the low limit of " + LOW_TEMPERATURE_THRESHOLD + " degrees.");
            return heatUp(currentTemperature, OPTIMAL_TEMPERATURE);
        } else {
            GardenLogger.log("Temperature Controller","The temperature is quite suitable for plants' growth.");
            return currentTemperature;
        }
    }

    private static int coolDown(int currentTemperature, int optimalTemperature) {
        GardenLogger.log("Temperature Controller","Cooler is on.");
        while (currentTemperature > optimalTemperature) {
            currentTemperature -= 1;
            if(currentTemperature%10 == 0 || currentTemperature == optimalTemperature){
                GardenLogger.log("Temperature Controller","Cooling: Current temperature is " + currentTemperature + " degrees.");
            }
        }
        GardenLogger.log("Temperature Controller","Cooler is off. Temperature adjusted to optimal level: " + optimalTemperature + " degrees.");
        return currentTemperature;
    }

    private static int heatUp(int currentTemperature, int optimalTemperature) {
        GardenLogger.log("Temperature Controller","Heater is on.");
        while (currentTemperature < optimalTemperature) {
            currentTemperature += 1;
            if(currentTemperature%10 == 0 || currentTemperature == optimalTemperature){
                GardenLogger.log("Temperature Controller","Heating: Current temperature is " + currentTemperature + " degrees.");
            }
        }
        GardenLogger.log("Temperature Controller","Heater is off. Temperature adjusted to optimal level: " + optimalTemperature + " degrees.");
        return currentTemperature;
    }
}
