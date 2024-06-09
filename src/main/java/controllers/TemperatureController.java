package controllers;

import application.SubsystemEffectAction;
import io.GardenLogger;
import sensors.TemperatureSensor;

public class TemperatureController {
    private final int HIGH_TEMPERATURE_THRESHOLD = 104;
    private final int LOW_TEMPERATURE_THRESHOLD = 50;
    private final int OPTIMAL_TEMPERATURE = 77;

    private static TemperatureController instance;
    private static SubsystemEffectAction coolerOrHeaterOnAction;

    private TemperatureController() {}

    public static synchronized TemperatureController getInstance() {
        if (instance == null) {
            instance = new TemperatureController();
            GardenLogger.log("Kindly Remind","Game time！Ha!");
        }
        return instance;
    }

    public int adjustTemperature(int currentTemperature) {
        if (currentTemperature > HIGH_TEMPERATURE_THRESHOLD) {
            GardenLogger.log("Temperature Controller", "Warning: Temperature has exceeded the high limit of " + HIGH_TEMPERATURE_THRESHOLD + " °F.");
            // Update UI: show cooler
            if(coolerOrHeaterOnAction != null){
                coolerOrHeaterOnAction.run("cooler");
            }
            return coolDown(currentTemperature, OPTIMAL_TEMPERATURE);
        } else if (currentTemperature < LOW_TEMPERATURE_THRESHOLD) {
            GardenLogger.log("Temperature Controller","Warning: Temperature has fallen below the low limit of " + LOW_TEMPERATURE_THRESHOLD + " °F.");
            // Update UI: show heater
            if(coolerOrHeaterOnAction != null){
                coolerOrHeaterOnAction.run("heater");
            }
            return heatUp(currentTemperature, OPTIMAL_TEMPERATURE);
        } else {
            //GardenLogger.log("Temperature Controller","The temperature is quite suitable for plants' growth.");
            return currentTemperature;
        }
    }

    private static int coolDown(int currentTemperature, int optimalTemperature) {
        GardenLogger.log("Temperature Controller","Cooler is on.");
        while (currentTemperature > optimalTemperature) {
            currentTemperature -= 1;
            if(currentTemperature%10 == 0 || currentTemperature == optimalTemperature){
                GardenLogger.log("Temperature Controller","Cooling: Current temperature is " + currentTemperature + " °F.");
            }
        }
        GardenLogger.log("Temperature Controller","Cooler is off. Temperature adjusted to optimal level: " + optimalTemperature + " °F.");
        return currentTemperature;
    }

    private static int heatUp(int currentTemperature, int optimalTemperature) {
        GardenLogger.log("Temperature Controller","Heater is on.");
        while (currentTemperature < optimalTemperature) {
            currentTemperature += 1;
            if(currentTemperature%10 == 0 || currentTemperature == optimalTemperature){
                GardenLogger.log("Temperature Controller","Heating: Current temperature is " + currentTemperature + " °F.");
            }
        }
        GardenLogger.log("Temperature Controller","Heater is off. Temperature adjusted to optimal level: " + optimalTemperature + " °F.");
        return currentTemperature;
    }

    public static void setCoolerOrHeaterOnAction(SubsystemEffectAction action) {
        coolerOrHeaterOnAction = action;
    }
}
