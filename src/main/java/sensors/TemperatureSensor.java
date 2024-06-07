package sensors;

import controllers.TemperatureController;

public class TemperatureSensor {

    private static final int HIGH_TEMPERATURE_THRESHOLD = 104;
    private static final int LOW_TEMPERATURE_THRESHOLD = 50;
    private static final int OPTIMAL_TEMPERATURE = 77;

    private static int temperature = OPTIMAL_TEMPERATURE; // Default temperature

    public static int getTemperature() {
        return temperature;
    }

    public static void updateTemperature(int newTemperature) {
        temperature = newTemperature;
        temperature = checkAndAdjustTemperature();
    }

    private static int checkAndAdjustTemperature() {
        if (temperature > HIGH_TEMPERATURE_THRESHOLD || temperature < LOW_TEMPERATURE_THRESHOLD) {
            temperature = TemperatureController.adjustTemperature(temperature);
        }
        return temperature;
    }

    public static int getHighTemperatureThreshold() {
        return HIGH_TEMPERATURE_THRESHOLD;
    }

    public static int getLowTemperatureThreshold() {
        return LOW_TEMPERATURE_THRESHOLD;
    }

    public static int getOptimalTemperature() {
        return OPTIMAL_TEMPERATURE;
    }
}
