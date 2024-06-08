package environment;

import io.GardenLogger;
import plant.Plant;

import java.util.List;

public class WeatherChangeEvent extends Event {
    private Weather weather;
    private int rainAmount;
    private List<List<Plant>> plantGroups;
    public WeatherChangeEvent(Weather weather, int rainAmount, List<List<Plant>> plantGroups) {
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
            for (List<Plant> plantGroup : plantGroups) {
                for (Plant plant : plantGroup) {
                    GardenLogger.log("Event", "Weather change to " + weather.getWeatherType().getName() + ", the amount of rain is " + rainAmount);
                    plant.updateWaterLevel(plant.getCurrentWaterLevel() + rainAmount);
                }
            }
        } else {
            weather.setWeatherType(WeatherType.SUNNY);
            GardenLogger.log("Event", "Weather change to " + weather.getWeatherType().getName());
        }
    }
}
