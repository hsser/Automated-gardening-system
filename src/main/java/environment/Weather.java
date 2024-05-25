package environment;

/**
 * Enum representing the possible weather conditions in the garden system.
 */
enum WeatherType {
    SUNNY("Sunny"),
    RAINY("Rainy");

    final private String name;
    WeatherType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class Weather {
    private WeatherType weatherType = WeatherType.SUNNY;

    public WeatherType getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(WeatherType weatherType) {
        this.weatherType = weatherType;
    }
}