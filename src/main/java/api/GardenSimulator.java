package api;

import java.util.Map;

public class GardenSimulator {
    public static void main(String[] args) {
        GardenSimulationAPI api = new GardenSimulationAPI();
        api.initializeGarden();
        Map<String, Object> initialPlantDetails = api.getPlants();
        System.out.println("Initial plant details: " + initialPlantDetails);

        // day 1
        api.rain(10);
        api.temperature(10);
        api.parasite("Aphid");
        //api.getState();
        sleepOneHour();
        // day 2
        api.temperature(60);
        api.parasite("Aphid");
        //api.getState();
        sleepOneHour();
        // day 3
        api.temperature(100);
        api.parasite("Aphid");
        sleepOneHour();
        // day 4
        api.temperature(120);
        api.rain(20);
        sleepOneHour();
        // day 5
        api.temperature(40);
        api.rain(20);
        sleepOneHour();

        // day 6
        api.temperature(40);
        sleepOneHour();

        // day 7
        api.temperature(40);
        sleepOneHour();

        // day 8
        api.temperature(40);
        sleepOneHour();

        // day 9
        api.temperature(40);
        sleepOneHour();

        // day 10
        api.temperature(40);
        sleepOneHour();

        // day 11
        api.temperature(40);
        sleepOneHour();

        // day 12
        api.temperature(40);
        sleepOneHour();

        // day 13
        api.temperature(40);
        sleepOneHour();

        // day 14
        api.temperature(40);
        sleepOneHour();

        // day 15
        api.temperature(40);
        sleepOneHour();

        // day 16
        api.temperature(40);
        sleepOneHour();

        // day 17
        api.temperature(40);
        sleepOneHour();

        // day 18
        api.temperature(40);
        sleepOneHour();

        // day 19
        api.temperature(40);
        sleepOneHour();

        // day 20
        api.temperature(40);
        sleepOneHour();

        // day 21
        api.temperature(40);
        sleepOneHour();

        // day 22
        api.temperature(40);
        sleepOneHour();

        // day 23
        api.temperature(40);
        sleepOneHour();

        // day 24
        api.temperature(40);
        sleepOneHour();

        api.getState();
        api.stopSimulation();


    }

    /**
     * Sleeps the current thread for one hour.
     */
    private static void sleepOneHour() {
        try {
            // Currently sleeping for 20 seconds for testing purposes
            Thread.sleep(10000); // 3600000 milliseconds = 1 hour
            //System.out.println("One hour has passed.");
        } catch (InterruptedException e) {
            System.out.println("Sleep was interrupted.");
        }
    }
}
