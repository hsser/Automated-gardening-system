package plant;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public enum PlantType {
    TREE(20, 100, 30, 90,32, 122, new ArrayList<>(Arrays.asList("Aphid", "Spider"))),
    FLOWER(10, 50, 20, 40, 32, 122, new ArrayList<>(Arrays.asList("Aphid", "Spider"))),
    CROP(30, 80, 40, 70, 32, 122, new ArrayList<>(Arrays.asList("Aphid", "Spider", "Whitefly")));

    private final int minWaterLevel;
    private final int maxWaterLevel;
    private final int lowWaterThreshold;
    private final int highWaterThreshold;
    private final int minTemperature;
    private final int maxTemperature;
    private final List<String> pests;

    PlantType(int minWaterLevel, int maxWaterLevel, int lowWaterThreshold, int highWaterThreshold,
              int minTemperature, int maxTemperature, List<String> pests) {
        this.minWaterLevel = minWaterLevel;
        this.maxWaterLevel = maxWaterLevel;
        this.lowWaterThreshold = lowWaterThreshold;
        this.highWaterThreshold = highWaterThreshold;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.pests = pests;
    }

    public int getMinWaterLevel() {
        return minWaterLevel;
    }

    public int getMaxWaterLevel() {
        return maxWaterLevel;
    }

    public int getLowWaterThreshold() {
        return lowWaterThreshold;
    }

    public int getHighWaterThreshold() {
        return highWaterThreshold;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public List<String> getPests() {
        return pests;
    }
}
