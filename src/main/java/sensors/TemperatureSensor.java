package sensors;

import application.SubsystemEffectAction;
import controllers.TemperatureController;

import java.util.concurrent.locks.ReentrantLock;

public class TemperatureSensor {
    private static TemperatureSensor instance;
    private int temperature;
    private static SubsystemEffectAction coolerOrHeaterOnAction;
    private HealthCheckCallback healthCheckCallback;
    private final ReentrantLock lock = new ReentrantLock();

    private TemperatureSensor() {}

    public static synchronized TemperatureSensor getInstance() {
        if (instance == null) {
            instance = new TemperatureSensor();
        }
        return instance;
    }

    public int getTemperature() {
        lock.lock();
        try {
            return temperature;
        } finally {
            lock.unlock();
        }
    }

    public void setTemperature(int newTemperature) {
        lock.lock();
        try {
            this.temperature = newTemperature;

            if (healthCheckCallback != null) {
                healthCheckCallback.execute();
            }

            TemperatureController.setCoolerOrHeaterOnAction(coolerOrHeaterOnAction);
            this.temperature = TemperatureController.getInstance().adjustTemperature(newTemperature);
        } finally {
            lock.unlock();
        }
    }

    public void setHealthCheckCallback(HealthCheckCallback callback) {
        this.healthCheckCallback = callback;
    }

    public static void setSubsystemsEffectAction(SubsystemEffectAction action) {
        coolerOrHeaterOnAction = action;
    }

}
