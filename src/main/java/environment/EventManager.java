package environment;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import application.PestAttackAction;
import plant.PlantGroup;
import sensors.TemperatureSensor;

/**
 * Manages the creation and handling of events within the garden system.
 * Can create random events: weather changes, temperature changes and pest attacks.
 */
public class EventManager {
    private Weather weather;
    private AtomicInteger temperature;
    private List<PlantGroup> plantGroups;
    private List<String> pestType = new ArrayList<>(Arrays.asList("Aphid", "Spider", "Whitefly"));
    Map<String, List<Integer>> plotIndicesOfVulnerablePlantByPest;
    private TemperatureSensor temperatureSensor;

    // Random number generator for creating random events and choose random plant for creating pest attack event
    private Random random = new Random();

    private PestAttackAction pestAttackAction;

    final int LOWEST_TEMPERATURE = 40;
    final int LOWEST_RAIN_AMOUNT = 1;  // TEST: High rain amount; Previous: 5
    final int MAX_RANDOM_NUM_OF_PEST = 100;
    final int MIN_NUM_OF_PEST = 1;

    public EventManager(Weather weather, AtomicInteger temperature, List<PlantGroup> plantGroups, Map<String, List<Integer>> plotIndicesOfVulnerablePlantByPest, TemperatureSensor temperatureSensor) {
        this.weather = weather;
        this.temperature = temperature;
        this.plantGroups = plantGroups;
        this.plotIndicesOfVulnerablePlantByPest = plotIndicesOfVulnerablePlantByPest;
        this.temperatureSensor = temperatureSensor;
    }

    // Events for API use Only
    public RainyEvent createRainyEvent(int rainAmount) {
         return new RainyEvent(weather, WeatherType.RAINY, rainAmount, plantGroups);
    }

    // Events also for non API use
    public WeatherToggleEvent createWeatherToggleEvent() {
        return new WeatherToggleEvent(weather, LOWEST_RAIN_AMOUNT + random.nextInt(10), plantGroups);
    }

    public WeatherChangeEvent createWeatherChangeEvent() {
        return new WeatherChangeEvent(weather, LOWEST_RAIN_AMOUNT + random.nextInt(10), plantGroups);
    }

    public TemperatureChangeEvent createTemperatureChangeEvent(int targetTemperature, TemperatureSensor temperatureSensor) {
        return new TemperatureChangeEvent(temperature, targetTemperature, temperatureSensor);
    }

    public PestAttackEvent createPestAttackEvent(String pest) {
        PlantGroup plantGroup = null;
        int plantIndex = -1;
        int numOfPests = MIN_NUM_OF_PEST + random.nextInt(MAX_RANDOM_NUM_OF_PEST);
        List<Integer> plotIndices = plotIndicesOfVulnerablePlantByPest.get(pest);
        if (plotIndices != null) {
            plantIndex = plotIndices.get(random.nextInt(plotIndices.size()));
            plantGroup = plantGroups.get(plantIndex);
        }
        PestAttackEvent pestAttackEvent = new PestAttackEvent(plantGroup, plantIndex, numOfPests, pest);
        pestAttackEvent.setOnPestAttack(pestAttackAction);
        return pestAttackEvent;
    }

    // Daily Events
    public void triggerAllEvents() {
        List<Event> events = new ArrayList<>();

        // Create Events
        events.add(createWeatherChangeEvent());
        events.add(createTemperatureChangeEvent(LOWEST_TEMPERATURE + random.nextInt(80), temperatureSensor));
        events.add(createPestAttackEvent(pestType.get(random.nextInt(pestType.size()))));

        // Trigger Events
        for (Event event : events) {
            event.trigger();
        }
    }

    public void setOnPestAttack(PestAttackAction action) {
        pestAttackAction = action;
    }
}