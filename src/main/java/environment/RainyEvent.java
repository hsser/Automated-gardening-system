package environment;

import plant.Plant;

import java.util.List;

public class RainyEvent extends Event {
    private Weather weather;
    private WeatherType weatherType;
    private int rainAmount;
    private List<List<Plant>> plantGroups;
    public RainyEvent(Weather weather, WeatherType weatherType, int rainAmount, List<List<Plant>> plantGroups) {
        super("RainyEvent");
        this.weather = weather;
        this.weatherType = weatherType;
        this.rainAmount = rainAmount;
        this.plantGroups = plantGroups;
    }

    /**
     * Trigger weather change event and set the current weather to another.
     */
    public void trigger() {
        weather.setWeatherType(weatherType);
        for (List<Plant> plantGroup : plantGroups) {
            for (Plant plant : plantGroup) {
                plant.updateWaterLevel(plant.getCurrentWaterLevel() + rainAmount);
            }
        }
        System.out.println("Event: Weather change to " + weatherType.getName() + ", the amount of rain is " + rainAmount);
    }
}
