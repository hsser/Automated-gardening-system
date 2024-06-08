package environment;

import io.GardenLogger;
import plant.Plant;
import plant.PlantGroup;

import java.util.List;

public class WeatherChangeEvent extends Event {
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
    public void trigger() {
        if (weather.isSunny()) {
            weather.setWeatherType(WeatherType.RAINY);
            GardenLogger.log("Event", "Weather change to " + weather.getWeatherType().getName() + ", the amount of rain is " + rainAmount);

            for (PlantGroup plantGroup : plantGroups) {
                if(!plantGroup.isEmpty())
                    plantGroup.updateWaterLevel(plantGroup.getCurrentWaterLevel() + rainAmount);
            }
        } else {
            weather.setWeatherType(WeatherType.SUNNY);
            GardenLogger.log("Event", "Weather change to " + weather.getWeatherType().getName());
        }
    }
}
