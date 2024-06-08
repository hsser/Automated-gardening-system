package controllers;

import io.GardenLogger;
import sensors.TemperatureSensor;

public class TemperatureController {

    public static int adjustTemperature(int currentTemperature) {
        int highThreshold = TemperatureSensor.getHighTemperatureThreshold();
        int lowThreshold = TemperatureSensor.getLowTemperatureThreshold();
        int optimalTemperature = TemperatureSensor.getOptimalTemperature();

        if (currentTemperature > highThreshold) {
            GardenLogger.log("Temperature Controller", "Warning: Temperature has exceeded the high limit of " + highThreshold + " degrees.");
            return coolDown(currentTemperature, optimalTemperature);
        } else if (currentTemperature < lowThreshold) {
            GardenLogger.log("Temperature Controller","Warning: Temperature has fallen below the low limit of " + lowThreshold + " degrees.");
            return heatUp(currentTemperature, optimalTemperature);
        } else {
            GardenLogger.log("Temperature Controller","The temperature is quite suitable for plants' growth.");
            return currentTemperature;
        }
    }

    private static int coolDown(int currentTemperature, int optimalTemperature) {
        GardenLogger.log("Temperature Controller","Cooler is on.");
        while (currentTemperature > optimalTemperature) {
            currentTemperature -= 1;
            GardenLogger.log("Temperature Controller","Cooling: Current temperature is " + currentTemperature + " degrees.");
        }
        GardenLogger.log("Temperature Controller","Cooler is off. Temperature adjusted to optimal level: " + optimalTemperature + " degrees.");
        return currentTemperature;
    }

    private static int heatUp(int currentTemperature, int optimalTemperature) {
        GardenLogger.log("Temperature Controller","Heater is on.");
        while (currentTemperature < optimalTemperature) {
            currentTemperature += 1;
            GardenLogger.log("Temperature Controller","Heating: Current temperature is " + currentTemperature + " degrees.");
        }
        GardenLogger.log("Temperature Controller","Heater is off. Temperature adjusted to optimal level: " + optimalTemperature + " degrees.");
        return currentTemperature;
    }
}
