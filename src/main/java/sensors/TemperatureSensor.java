package sensors;

import application.SubsystemEffectAction;
import controllers.TemperatureController;

public class TemperatureSensor {
    private static TemperatureSensor instance;
    private int temperature;
    private static SubsystemEffectAction coolerOrHeaterOnAction;

    private TemperatureSensor() {}

    public static synchronized TemperatureSensor getInstance() {
        if (instance == null) {
            instance = new TemperatureSensor();
        }
        return instance;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int newTemperature) {
        TemperatureController.setCoolerOrHeaterOnAction(coolerOrHeaterOnAction);
        this.temperature = TemperatureController.getInstance().adjustTemperature(newTemperature);
    }

    public static void setSubsystemsEffectAction(SubsystemEffectAction action) {
        coolerOrHeaterOnAction = action;
    }


    /*private final int HIGH_TEMPERATURE_THRESHOLD = 104;
    private final int LOW_TEMPERATURE_THRESHOLD = 50;
    private final int OPTIMAL_TEMPERATURE = 77;

    private int temperature; // Instance variable for temperature

    public TemperatureSensor(int initialTemperature) {
        this.temperature = initialTemperature;
    }

    public int getTemperature() {
        return temperature;
    }

    public void updateTemperature(int newTemperature) {
        temperature = newTemperature;
        temperature = checkAndAdjustTemperature();
    }

    private int checkAndAdjustTemperature() {
        if (temperature > HIGH_TEMPERATURE_THRESHOLD || temperature < LOW_TEMPERATURE_THRESHOLD) {
            temperature = TemperatureController.adjustTemperature(temperature);
        }
        return temperature;
    }

    public int getHighTemperatureThreshold() {
        return HIGH_TEMPERATURE_THRESHOLD;
    }

    public int getLowTemperatureThreshold() {
        return LOW_TEMPERATURE_THRESHOLD;
    }

    public int getOptimalTemperature() {
        return OPTIMAL_TEMPERATURE;
    }*/
}
