package environment;

/**
 * Enum representing the possible weather conditions in the garden system.
 */
public enum WeatherType {
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