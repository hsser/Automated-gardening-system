package environment;

import java.util.Random;
import java.util.List;
import Organism.Plant.Plant;
import Organism.Plant.PlantManager;

/**
 * Manages the creation and handling of events within the garden system.
 * Can create random events: weather changes and pest attacks.
 */
public class EventManager {
    private Weather weather;  // Used for creating weather change event.
    private PlantManager plantManager;  // Used to get plants for creating pest attack event.
    private Random random = new Random(8);  // Random number generator for creating random events and choose random plant for creating pest attack event.

    public EventManager(Weather weather, PlantManager plantManager) {
        this.weather = weather;
        this.plantManager = plantManager;
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
                randomEvent = new WeatherChangeEvent(weather);
                break;
            case PEST_ATTACK:
                List<Plant> plants = plantManager.getPlants();
                Plant victim = null;
                if (plants.isEmpty()) victim = plants.get(random.nextInt(plants.size()));
                randomEvent = new PestAttackEvent(victim);
        }
        return randomEvent;
    }
}