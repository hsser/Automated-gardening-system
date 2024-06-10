package environment;

import io.GardenLogger;
import plant.PlantGroup;

import java.util.List;
import java.util.Random;

public class WeatherChangeEvent extends Event{
    private Weather weather;
    private int rainAmount;
    private List<PlantGroup> plantGroups;
    private Random random;
    public WeatherChangeEvent(Weather weather, int rainAmount, List<PlantGroup> plantGroups, Random random) {
        super("WeatherChangeEvent");
        this.weather = weather;
        this.rainAmount = rainAmount;
        this.plantGroups = plantGroups;
        this.random = random;
    }

    /**
     * Trigger weather change event and set the current weather to another.
     */
    @Override
    public synchronized void trigger() {
        boolean isRainy = random.nextBoolean();

        if (isRainy) {
            weather.setWeatherType(WeatherType.RAINY);
            GardenLogger.log("Event", "Weather is " + weather.getWeatherType().getName() + ", the amount of rain is " + rainAmount);

            for (PlantGroup plantGroup : plantGroups) {
                if(!plantGroup.isEmpty()) {
                    plantGroup.updateWaterLevel(plantGroup.getCurrentWaterLevel() + rainAmount);
                }
            }
        } else {
            weather.setWeatherType(WeatherType.SUNNY);
            GardenLogger.log("Event", "Weather is " + weather.getWeatherType().getName());
        }
    }
}
