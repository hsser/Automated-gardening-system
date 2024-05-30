package environment;

import java.util.Random;
import java.util.List;
import organism.plant.Plant;
import organism.plant.GardenManager;

/**
 * Manages the creation and handling of events within the garden system.
 * Can create random events: weather changes, temperature changes and pest attacks.
 */
public class EventManager {
    // Used to get weather for creating weather change event, get temperature for creating temperature event, and plants
    // for creating pest attack event
    private GardenManager gardenManager;

    // Random number generator for creating random events and choose random plant for creating pest attack event
    private Random random = new Random(8);

    final int LOWEST_TEMPERATURE = 40;

    public EventManager(GardenManager gardenManager) {
        this.gardenManager = gardenManager;
    }

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
                randomEvent = new WeatherChangeEvent(gardenManager.getWeather());
                break;
            case TEMPERATURE_CHANGE:
                randomEvent = new TemperatureChangeEvent(gardenManager.getTemperature(), LOWEST_TEMPERATURE + random.nextInt(80));
                break;
            case PEST_ATTACK:
                List<Plant> plants = gardenManager.getPlants();
                Plant victim = null;
                if (plants.isEmpty()) victim = plants.get(random.nextInt(plants.size()));
                randomEvent = new PestAttackEvent(victim);
        }
        return randomEvent;
    }
}