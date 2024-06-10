package controllers;

import application.SubsystemEffectAction;
import io.GardenLogger;
import sensors.TemperatureSensor;

import java.util.concurrent.atomic.AtomicInteger;

public class TemperatureController {
    private final int HIGH_TEMPERATURE_THRESHOLD = 104;
    private final int LOW_TEMPERATURE_THRESHOLD = 50;
    private final int OPTIMAL_TEMPERATURE = 77;

    private static TemperatureController instance;
    private static SubsystemEffectAction coolerOrHeaterOnAction;

    private AtomicInteger temperature;

    public TemperatureController(AtomicInteger temperature) {
        this.temperature = temperature;
    }

//    public static synchronized TemperatureController getInstance() {
//        if (instance == null) {
//            instance = new TemperatureController();
//            GardenLogger.log("Kindly Remind","Game time！Ha!");
//        }
//        return instance;
//    }

    public void adjustTemperature(int currentTemperature) {
        if (currentTemperature > HIGH_TEMPERATURE_THRESHOLD) {
            GardenLogger.log("Temperature Controller", "Warning: Temperature has exceeded the high limit of " + HIGH_TEMPERATURE_THRESHOLD + " °F.");
            // Update UI: show cooler
            if(coolerOrHeaterOnAction != null){
                coolerOrHeaterOnAction.run("cooler");
            }
            coolDown(OPTIMAL_TEMPERATURE);
        } else if (currentTemperature < LOW_TEMPERATURE_THRESHOLD) {
            GardenLogger.log("Temperature Controller","Warning: Temperature has fallen below the low limit of " + LOW_TEMPERATURE_THRESHOLD + " °F.");
            // Update UI: show heater
            if(coolerOrHeaterOnAction != null){
                coolerOrHeaterOnAction.run("heater");
            }
            heatUp(OPTIMAL_TEMPERATURE);
        }
    }

    private void coolDown(int optimalTemperature) {
        GardenLogger.log("Temperature Controller","Cooler is on.");
        while (temperature.get() > optimalTemperature) {
            temperature.set(temperature.get() - 1);
            int currentTemperature = temperature.get();
            if(currentTemperature % 10 == 0 || currentTemperature == optimalTemperature){
                GardenLogger.log("Temperature Controller","Cooling: Current temperature is " + currentTemperature + " °F.");
            }
        }
        GardenLogger.log("Temperature Controller","Cooler is off. Temperature adjusted to optimal level: " + optimalTemperature + " °F.");
    }

    private void heatUp(int optimalTemperature) {
        GardenLogger.log("Temperature Controller","Heater is on.");
        while (temperature.get() < optimalTemperature) {
            temperature.set(temperature.get() + 1);
            int currentTemperature = temperature.get();
            if(currentTemperature % 10 == 0 || currentTemperature == optimalTemperature){
                GardenLogger.log("Temperature Controller","Heating: Current temperature is " + currentTemperature + " °F.");
            }
        }
        GardenLogger.log("Temperature Controller","Heater is off. Temperature adjusted to optimal level: " + optimalTemperature + " °F.");
    }

    public static void setCoolerOrHeaterOnAction(SubsystemEffectAction action) {
        coolerOrHeaterOnAction = action;
    }
}
