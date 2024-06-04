package environment;

public class Weather {
    private WeatherType weatherType = WeatherType.SUNNY;

    public WeatherType getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(WeatherType weatherType) {
        this.weatherType = weatherType;
    }

    public boolean isSunny() { return weatherType == WeatherType.SUNNY; }
}