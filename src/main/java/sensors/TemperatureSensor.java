package sensors;

import controllers.TemperatureController;

public class TemperatureSensor {

    private static final double HIGH_TEMPERATURE_THRESHOLD = 104.0;
    private static final double LOW_TEMPERATURE_THRESHOLD = 50.0;
    private static final double OPTIMAL_TEMPERATURE = 77.0;

    private static double temperature;

    public TemperatureSensor() {
        this.temperature = 77.0; // default temperature
    }

    public static double getTemperature() {
        return temperature;
    }

    public void updateTemperature(double newTemperature) {
        this.temperature = newTemperature;
        this.temperature = checkAndAdjustTemperature();
    }

    private double checkAndAdjustTemperature() {
        if (temperature > HIGH_TEMPERATURE_THRESHOLD || temperature < LOW_TEMPERATURE_THRESHOLD) {
            temperature = TemperatureController.adjustTemperature(temperature);
        }
        return temperature;
    }

    public static double getHighTemperatureThreshold() {
        return HIGH_TEMPERATURE_THRESHOLD;
    }

    public static double getLowTemperatureThreshold() {
        return LOW_TEMPERATURE_THRESHOLD;
    }

    public static double getOptimalTemperature() {
        return OPTIMAL_TEMPERATURE;
    }
}
