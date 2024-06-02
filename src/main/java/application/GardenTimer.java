package application;
import javafx.application.Platform;

import java.util.concurrent.*;

public class GardenTimer {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private Runnable dailyTask;

    public GardenTimer(Runnable dailyTask) { this.dailyTask = dailyTask; }

    public void start() {
        scheduler.scheduleAtFixedRate(dailyTask, 0, 1, TimeUnit.MINUTES); //TODO: change to 1 hour
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
