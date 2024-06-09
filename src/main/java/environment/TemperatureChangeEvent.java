package environment;

import io.GardenLogger;
import sensors.TemperatureSensor;

import java.util.concurrent.atomic.AtomicInteger;

public class TemperatureChangeEvent extends Event{
    private AtomicInteger temperature;
    int targetTemperature;

    public TemperatureChangeEvent(AtomicInteger temperature, int targetTemperature) {
        super("TemperatureChangeEvent");
        this.temperature = temperature;
        this.targetTemperature = targetTemperature;
    }

    /**
     * Trigger temperature change event and set the current temperature to the input value.
     */
    public void trigger() {
        int previousTemperature = temperature.get();
        temperature.set(targetTemperature);
        TemperatureSensor.getInstance().setTemperature(targetTemperature);
        GardenLogger.log("Event", "Temperature change from " + previousTemperature + " to " + targetTemperature);
    }
}
