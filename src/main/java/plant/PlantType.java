package plant;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public enum PlantType {
    TREE(20.0, 100.0, 30.0, 32.0, 122.0, new ArrayList<>(Arrays.asList("Aphid", "Spider"))),
    FLOWER(10.0, 50.0, 20.0, 32.0, 122.0, new ArrayList<>(Arrays.asList("Aphid", "Spider"))),
    CROP(30.0, 80.0, 40.0, 32.0, 122.0, new ArrayList<>(Arrays.asList("Aphid", "Spider", "Whitefly")));

    private final double minWaterLevel;
    private final double maxWaterLevel;
    private final double lowWaterThreshold;
    private final double minTemperature;
    private final double maxTemperature;
    private final List<String> pests;

    PlantType(double minWaterLevel, double maxWaterLevel, double lowWaterThreshold,
              double minTemperature, double maxTemperature, List<String> pests) {
        this.minWaterLevel = minWaterLevel;
        this.maxWaterLevel = maxWaterLevel;
        this.lowWaterThreshold = lowWaterThreshold;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.pests = pests;
    }

    public double getMinWaterLevel() {
        return minWaterLevel;
    }

    public double getMaxWaterLevel() {
        return maxWaterLevel;
    }

    public double getLowWaterThreshold() {
        return lowWaterThreshold;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public List<String> getPests() {
        return pests;
    }
}
