package environment;

public class WeatherChangeEvent extends Event {
    private Weather weather;
    public WeatherChangeEvent(Weather weather) {
        super("Weather change");
        this.weather = weather;
    }

    /**
     * Trigger weather change event and set the current weather to another.
     */
    public void trigger() {
        WeatherType newWeatherType = (weather.getWeatherType() == WeatherType.SUNNY) ? WeatherType.RAINY : WeatherType.SUNNY;
        weather.setWeatherType(newWeatherType);
        System.out.println("Event: Weather change to " + newWeatherType.getName());
    }
}
