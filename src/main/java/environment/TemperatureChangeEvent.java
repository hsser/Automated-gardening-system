package environment;

import io.GardenLogger;
import sensors.TemperatureSensor;

import java.util.concurrent.atomic.AtomicInteger;

public class TemperatureChangeEvent extends Event{
    private AtomicInteger temperature;
    int targetTemperature;
    TemperatureSensor temperatureSensor;

    public TemperatureChangeEvent(AtomicInteger temperature, int targetTemperature, TemperatureSensor temperatureSensor) {
        super("TemperatureChangeEvent");
        this.temperature = temperature;
        this.targetTemperature = targetTemperature;
        this.temperatureSensor = temperatureSensor;
    }

    /**
     * Trigger temperature change event and set the current temperature to the input value.
     */
    public void trigger() {
        synchronized (temperature) {
            int previousTemperature = temperature.get();
            temperature.set(targetTemperature);
            GardenLogger.log("Event", "Temperature change from " + previousTemperature + " °F to " + targetTemperature + " °F");
            temperatureSensor.setTemperature(targetTemperature);
        }
    }
}
