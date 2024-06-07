package api;

public class GardenSimulator {
    public static void main(String[] args) {
        GardenSimulationAPI gardenSimulationAPI = new GardenSimulationAPI();
        gardenSimulationAPI.initializeGarden();
        gardenSimulationAPI.getState();
        // Function to sleep for one hour
        sleepOneHour();

        // Additional code to execute after waking up
        System.out.println("Woke up after one hour.");
    }

    /**
     * Sleeps the current thread for one hour.
     */
    private static void sleepOneHour() {
        try {
            System.out.println("Going to sleep for one hour...");
            Thread.sleep(3600000); // 3600000 milliseconds = 1 hour
            System.out.println("One hour has passed.");
        } catch (InterruptedException e) {
            System.out.println("Sleep was interrupted.");
        }
    }
}
