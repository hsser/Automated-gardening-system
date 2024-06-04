package environment;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import plant.Plant;

/**
 * Manages the creation and handling of events within the garden system.
 * Can create random events: weather changes, temperature changes and pest attacks.
 */
public class EventManager {
    private Weather weather;
    private AtomicInteger temperature;
    private List<List<Plant>> plantGroups;
    private List<String> pestType = new ArrayList<>(Arrays.asList("Aphid", "Spider", "Whitefly"));
    Map<String, List<Integer>> pestToPlotIndex;

    // Random number generator for creating random events and choose random plant for creating pest attack event
    private Random random = new Random(8);

    final int LOWEST_TEMPERATURE = 40;
    final int LOWEST_RAIN_AMOUNT = 1;
    final int MAX_NUM_OF_PEST = 100;

    public EventManager(Weather weather, AtomicInteger temperature, List<List<Plant>> plantGroups, Map<String, List<Integer>> pestToPlotIndex) {
        this.weather = weather;
        this.temperature = temperature;
        this.plantGroups = plantGroups;
        this.pestToPlotIndex = pestToPlotIndex;
    }

    // Events for API use Only
    public RainyEvent createRainyEvent(int rainAmount) {
         return new RainyEvent(weather, WeatherType.RAINY, rainAmount, plantGroups);
    }

    // Events also for non API use
    public WeatherChangeEvent createWeatherChangeEvent() {
        return new WeatherChangeEvent(weather, LOWEST_RAIN_AMOUNT + random.nextInt(5), plantGroups);
    }

    public TemperatureChangeEvent createTemperatureChangeEvent(int targetTemperature) {
        return new TemperatureChangeEvent(temperature, targetTemperature);
    }

    public PestAttackEvent createPestAttackEvent(String predator) {
        List<Plant> prey = null;
        int preyIndex = -1;
        int numOfPredators = random.nextInt(MAX_NUM_OF_PEST);
        List<Integer> preyIndexs = pestToPlotIndex.get(predator);
        if (preyIndexs != null) {
            preyIndex = preyIndexs.get(random.nextInt(preyIndexs.size()) - 1);
            prey = plantGroups.get(preyIndex);
            numOfPredators += prey.size();
        }
        return new PestAttackEvent(prey, preyIndex, numOfPredators, predator);
    }

    // Daily Events
    public void triggerAllEvents() {
        List<Event> events = new ArrayList<>();

        // Create Events
        events.add(createWeatherChangeEvent());
        events.add(createTemperatureChangeEvent(LOWEST_TEMPERATURE + random.nextInt(80)));
        events.add(createPestAttackEvent(pestType.get(random.nextInt(pestType.size()))));

        // Trigger Events
        for (Event event : events) {
            event.trigger();
        }
    }
}