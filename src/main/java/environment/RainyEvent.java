package environment;

import io.GardenLogger;
import plant.Plant;
import plant.PlantGroup;

import java.util.List;

public class RainyEvent extends Event {
    private Weather weather;
    private WeatherType weatherType;
    private int rainAmount;
    private List<PlantGroup> plantGroups;
    public RainyEvent(Weather weather, WeatherType weatherType, int rainAmount, List<PlantGroup> plantGroups) {
        super("RainyEvent");
        this.weather = weather;
        this.weatherType = weatherType;
        this.rainAmount = rainAmount;
        this.plantGroups = plantGroups;
    }

    /**
     * Trigger weather change event and set the current weather to another.
     */
    public synchronized void trigger() {
        weather.setWeatherType(weatherType);
        GardenLogger.log("Event", "Weather change to " + weatherType.getName() + ", the amount of rain is " + rainAmount);
        for (PlantGroup plantGroup : plantGroups) {
            if(!plantGroup.isEmpty()) {
                plantGroup.updateWaterLevel(plantGroup.getCurrentWaterLevel() + rainAmount);
            }
        }
    }
}
