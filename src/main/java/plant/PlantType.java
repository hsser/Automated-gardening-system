package plant;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public enum PlantType {
    TREE(20, 100, 30, 32, 122, new ArrayList<>(Arrays.asList("Aphid", "Spider"))),
    FLOWER(10, 50, 20, 32, 122, new ArrayList<>(Arrays.asList("Aphid", "Spider"))),
    CROP(30, 80, 40, 32, 122, new ArrayList<>(Arrays.asList("Aphid", "Spider", "Whitefly")));

    private final int minWaterLevel;
    private final int maxWaterLevel;
    private final int lowWaterThreshold;
    private final int minTemperature;
    private final int maxTemperature;
    private final List<String> pests;

    PlantType(int minWaterLevel, int maxWaterLevel, int lowWaterThreshold,
              int minTemperature, int maxTemperature, List<String> pests) {
        this.minWaterLevel = minWaterLevel;
        this.maxWaterLevel = maxWaterLevel;
        this.lowWaterThreshold = lowWaterThreshold;
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
