package environment;

import java.util.function.Consumer;

public class Weather {
    private WeatherType weatherType = WeatherType.SUNNY;
    private Consumer<Weather> onWeatherChanged;

    public WeatherType getWeatherType() {
        return weatherType;
    }

    public void setOnWeatherChanged(Consumer<Weather> consumer) {
        onWeatherChanged = consumer;
    }

    public void setWeatherType(WeatherType weatherType) {
        this.weatherType = weatherType;
        if (onWeatherChanged != null) {
            onWeatherChanged.accept(this);
        }
    }

    public boolean isSunny() { return weatherType == WeatherType.SUNNY; }
}