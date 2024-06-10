package sensors;

import application.SubsystemEffectAction;
import controllers.TemperatureController;
import io.GardenLogger;
import plant.PlantGroup;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TemperatureSensor {
    private AtomicInteger temperature;
    private static SubsystemEffectAction coolerOrHeaterOnAction;
    private HealthCheckCallback healthCheckCallback;
    private final ReentrantLock lock = new ReentrantLock();
    private List<PlantGroup> plantGroups;
    private TemperatureController temperatureController;

    public TemperatureSensor(List<PlantGroup> plantGroups, AtomicInteger temperature) {
        this.plantGroups = plantGroups;
        this.temperature = temperature;
        temperatureController = new TemperatureController(temperature);
    }

    public AtomicInteger getTemperature() {
        return temperature;
    }

    public void setTemperature(int newTemperature) {
        lock.lock();
        try {
//            this.temperature = newTemperature;

//            if (healthCheckCallback != null) {
//                healthCheckCallback.execute();
//            }

            TemperatureController.setCoolerOrHeaterOnAction(coolerOrHeaterOnAction);
            temperatureController.adjustTemperature(newTemperature);

            // plantGroup will reduce health if temperature controller fails working
            for (PlantGroup plantGroup : plantGroups) {
                if (!plantGroup.isEmpty()) {
                    plantGroup.updateStatusByTemperatureChange(temperature);
                }
            }
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
