package sensors;

/**
 * The TemperatureSensor class simulates a temperature sensor.
 * The default temperature is set to 77.0 degrees Fahrenheit.
 */
public class TemperatureSensor {
    private double temperature;

    /**
     * Constructs a Temperature Sensor with a default temperature of 77.0 degrees Fahrenheit.
     */
    public TemperatureSensor (){
        this.temperature = 77.0;
    }

    /**
     * Gets the current temperature.
     *
     * @return the current temperature
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Updates the temperature.
     *
     * @param newTemperature the new temperature
     */
    public void updateTemperature(double newTemperature) {
        this.temperature = newTemperature;
    }
}
