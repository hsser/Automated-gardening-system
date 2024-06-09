package api;

import java.util.HashMap;
import java.util.Map;

public class GardenSimulator {
    public static void main(String[] args) {
        GardenSimulationAPI api = new GardenSimulationAPI();
        api.initializeGarden();
        Map<String, Object> initialPlantDetails = api.getPlants();
        System.out.println("Initial plant details: " + initialPlantDetails);

        // day 1
        api.rain(10);
        sleepOneHour();

        // day 2
        api.temperature(60);
        api.parasite("Aphid");
        sleepOneHour();
        // day 3
        api.temperature(100);
        api.rain(20);
        sleepOneHour();
        // day 4


        // day 5


        // day 6


        // day 7


        // day 8


        // day 9


        // day 10


        // day 11


        // day 12


        // day 13


        // day 14


        // day 15


        // day 16


        // day 17


        // day 18


        // day 19


        // day 20


        // day 21


        // day 22


        // day 23


        // day 24
        sleepOneHour();

        api.getState();

    }

    /**
     * Sleeps the current thread for one hour.
     */
    private static void sleepOneHour() {
        try {
            // Currently sleeping for 20 seconds for testing purposes
            Thread.sleep(20000); // 3600000 milliseconds = 1 hour
            System.out.println("One hour has passed.");
        } catch (InterruptedException e) {
            System.out.println("Sleep was interrupted.");
        }
    }
}
