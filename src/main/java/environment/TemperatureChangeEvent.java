package environment;

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
        temperature.set(targetTemperature);
        System.out.println("Event: Weather change to " + targetTemperature);
    }
}
