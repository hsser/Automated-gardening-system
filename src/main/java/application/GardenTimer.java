package application;
import javafx.application.Platform;
import plant.GardenManager;

import java.util.concurrent.*;

public class GardenTimer {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private GardenManager gardenManager;
    private GardenController controller;
    private int currentDay = 1; //TODO: might need to move this to GardenManager

    public GardenTimer(GardenManager gardenManager, GardenController gardenController) {
        this.gardenManager = gardenManager;
        this.controller = gardenController;
    }

    public void start() {
        final Runnable task = this::simulateDay;
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.MINUTES); //TODO: change to 1 hour
    }

    private void simulateDay() {
        //TODO: gardenManager.updateDailyTasks();
        //TODO: notify UI to update,might be done in the GardenManager
        Platform.runLater(() -> {
            controller.showCurrentDay(currentDay++);
        });
    }

    public void stop() {
        if (!scheduler.isShutdown()) {
            scheduler.shutdown();
        }
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException ie) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
