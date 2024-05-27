package sensors;

/**
 * The TemperatureSensor class simulates a temperature sensor.
 * The default temperature is set to 25.0 degrees Celsius.
 */
public class TemperatureSensor {
    private double temperature;

    //Celsius or Fahrenheit ?
    public TemperatureSensor (){
        this.temperature = 25.0;
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
