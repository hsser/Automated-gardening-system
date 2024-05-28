package controllers;

import sensors.TemperatureSensor;

/**
 * The TemperatureController class manages the temperature adjustments base on the readings from the TemperatureSensor
 */
public class TemperatureController {
    private static final double HIGH_TEMPERATURE_THRESHOLD = 86.0;
    private static final double LOW_TEMPERATURE_THRESHOLD = 68.0;
    private static final double OPTIMAL_TEMPERATURE = 77.0;

    private TemperatureSensor temperatureSensor;

    /**
     * Constructs a TemperatureController with a default TemperatureSensor
     */
    public TemperatureController(){
        this.temperatureSensor = new TemperatureSensor();
    }

    /**
     * Adjust the temperature based on the current reading from the TemperatureSensor.
     * If the temperature is too high, it will cool down.
     * If the temperature is too low, it will heat up.
     * If the temperature is optimal, no action is needed.
     */
    public void adjustTemperature(){
        double currentTemperature = this.temperatureSensor.getTemperature();
        logCurrentTemperature(currentTemperature);

        if(currentTemperature > HIGH_TEMPERATURE_THRESHOLD){ // The temperature is too high
            System.out.println("Cooling down ...");
            temperatureToOptimal();
        }else if(currentTemperature < LOW_TEMPERATURE_THRESHOLD){
            System.out.println("Heating up ...");
            temperatureToOptimal();
        }else{
            System.out.println("The temperature is quit suitable for plants' growth!");
        }
    }

    /**
     * Logs the current temperature.
     *
     * @param temperature
     */
    private void logCurrentTemperature(double temperature){
        System.out.println("The current real-time temperature is " + temperature + " degrees");
    }

    /**
     * Adjust the temperature to the optimal level
     */
    private void temperatureToOptimal() {
        double currentTemperature = this.temperatureSensor.getTemperature();
        if (currentTemperature > OPTIMAL_TEMPERATURE) {
            while (currentTemperature > OPTIMAL_TEMPERATURE) {
                // Simulate cooling down
                currentTemperature -= 1.0;
                System.out.println("Cooling: Current temperature is " + currentTemperature + " degrees.");
            }
        } else if (currentTemperature < OPTIMAL_TEMPERATURE) {
            while (currentTemperature < OPTIMAL_TEMPERATURE) {
                // Simulate heating up
                currentTemperature += 1.0;
                System.out.println("Heating: Current temperature is " + currentTemperature + " degrees.");
            }
        }
        this.temperatureSensor.updateTemperature(OPTIMAL_TEMPERATURE);
        System.out.println("Temperature adjusted to optimal level: " + OPTIMAL_TEMPERATURE + " degrees.");
    }
}
