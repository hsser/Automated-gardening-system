package sensors;

import controllers.TemperatureController;

public class TemperatureSensor {

    private static final double HIGH_TEMPERATURE_THRESHOLD = 104.0;
    private static final double LOW_TEMPERATURE_THRESHOLD = 50.0;
    private static final double OPTIMAL_TEMPERATURE = 77.0;

    private static double temperature = OPTIMAL_TEMPERATURE; // Default temperature

    public static double getTemperature() {
        return temperature;
    }

    public static void updateTemperature(double newTemperature) {
        temperature = newTemperature;
        temperature = checkAndAdjustTemperature();
    }

    private static double checkAndAdjustTemperature() {
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
