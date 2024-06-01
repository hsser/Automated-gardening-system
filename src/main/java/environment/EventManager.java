package environment;

import java.util.Random;
import java.util.List;
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


    // Random number generator for creating random events and choose random plant for creating pest attack event
    private Random random = new Random(8);

    final int LOWEST_TEMPERATURE = 40;

    public EventManager(Weather weather, AtomicInteger temperature, List<List<Plant>> plantGroups) {
        this.weather = weather;
        this.temperature = temperature;
        this.plantGroups = plantGroups;
    }

    // TODO: Change to provide specific event, random with parameters.
    /**
     * Creates a random event, either a weather change or a pest attack.
     * @return A new random event.
     */
    public Event createRandomEvent() {
        Event randomEvent = null;
        EventType[] eventTypes = EventType.values();
        EventType eventType = eventTypes[random.nextInt(eventTypes.length)];
        switch (eventType) {
            case WEATHER_CHANGE:
                randomEvent = new WeatherChangeEvent(weather);
                break;
            case TEMPERATURE_CHANGE:
                randomEvent = new TemperatureChangeEvent(temperature, LOWEST_TEMPERATURE + random.nextInt(80));
                break;
            case PEST_ATTACK:
                List<Plant> victim = null;
                if (plantGroups.isEmpty()) {
                    victim = plantGroups.get(random.nextInt(plantGroups.size()));
                    randomEvent = new PestAttackEvent(victim);
                }
        }
        return randomEvent;
    }
}