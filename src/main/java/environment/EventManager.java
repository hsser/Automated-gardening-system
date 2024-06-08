package environment;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import application.PestAttackAction;
import plant.Plant;
import plant.PlantGroup;

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

    // Random number generator for creating random events and choose random plant for creating pest attack event
    private Random random = new Random(8);

    private PestAttackAction pestAttackAction;

    final int LOWEST_TEMPERATURE = 40;
    final int LOWEST_RAIN_AMOUNT = 20;
    final int MAX_NUM_OF_PEST = 100;

    public EventManager(Weather weather, AtomicInteger temperature, List<PlantGroup> plantGroups, Map<String, List<Integer>> plotIndicesOfVulnerablePlantByPest) {
        this.weather = weather;
        this.temperature = temperature;
        this.plantGroups = plantGroups;
        this.plotIndicesOfVulnerablePlantByPest = plotIndicesOfVulnerablePlantByPest;
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

    public PestAttackEvent createPestAttackEvent(String pest) {
        PlantGroup plantGroup = null;
        int plantIndex = -1;
        int numOfPests = random.nextInt(MAX_NUM_OF_PEST);
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
        events.add(createTemperatureChangeEvent(LOWEST_TEMPERATURE + random.nextInt(80)));
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