package environment;

import io.GardenLogger;
import plant.PlantGroup;

import java.util.List;
import java.util.Random;

public class WeatherChangeEvent extends Event{
    private Weather weather;
    private int rainAmount;
    private List<PlantGroup> plantGroups;
    public WeatherChangeEvent(Weather weather, int rainAmount, List<PlantGroup> plantGroups) {
        super("WeatherChangeEvent");
        this.weather = weather;
        this.rainAmount = rainAmount;
        this.plantGroups = plantGroups;
    }

    /**
     * Trigger weather change event and set the current weather to another.
     */
    @Override
    public void trigger() {
        Random random = new Random();
        boolean isRainy = random.nextBoolean();

        if (isRainy) {
            weather.setWeatherType(WeatherType.RAINY);
            GardenLogger.log("Event", "Weather is " + weather.getWeatherType().getName() + ", the amount of rain is " + rainAmount);

            for (PlantGroup plantGroup : plantGroups) {
                if(!plantGroup.isEmpty()) {
                    int increasedWaterLevel = plantGroup.getCurrentWaterLevel() + rainAmount;
                    if (increasedWaterLevel > plantGroup.getMaxWaterLevel()) {
                        increasedWaterLevel = plantGroup.getMaxWaterLevel();
                    }
                    plantGroup.setCurrentWaterLevel(increasedWaterLevel);
                    plantGroup.updateWaterLevel(increasedWaterLevel);
                }
            }
        } else {
            weather.setWeatherType(WeatherType.SUNNY);
            GardenLogger.log("Event", "Weather is " + weather.getWeatherType().getName());
        }
    }
}
