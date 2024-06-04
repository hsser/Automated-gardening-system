package plant;

import sensors.TemperatureSensor;
import sensors.WaterSensor;
import java.util.List;

public abstract class Plant{
    private String name;
    private int currentWaterLevel;
    private boolean isUnderAttack;  // Flag to indicate if the plant is being attacked
    private WaterSensor waterSensor;
    private PlantType plantType;
    private int health;  // Health of the plant

    public Plant(String name, PlantType plantType, int currentWaterLevel) {
        this.name = name;
        this.plantType = plantType;
        this.currentWaterLevel = currentWaterLevel;
        this.isUnderAttack = false;
        this.waterSensor = new WaterSensor(this);
        this.health = 100;  // Starting health
    }

    // Getter
    public String getName() { return this.name; }
    public int getMinWaterLevel() { return this.plantType.getMinWaterLevel(); }
    public int getMaxWaterLevel() { return this.plantType.getMaxWaterLevel(); }
    public int getMinTemperatureLevel() { return this.plantType.getMinTemperature(); }
    public int getMaxTemperatureLevel() { return this.plantType.getMaxTemperature(); }
    public int getCurrentWaterLevel() { return this.currentWaterLevel; }
    public int getPlantLowWaterThreshold() {return this.plantType.getLowWaterThreshold();}
    public List<String> getPestList(){ return this.plantType.getPests();}
    public boolean isUnderAttack() { return isUnderAttack; }
    public int getHealth() { return health; }
    public boolean isAlive() { return this.health > 0; }

    // Setter
    public void setHealth(int health){ this.health = health;}
    public void setCurrentWaterLevel(int currentWaterLevel) { this.currentWaterLevel = currentWaterLevel;}

    /**
     * Update water level
     */
    public void updateWaterLevel(int newWaterLevel){
        this.waterSensor.updateWaterLevel(newWaterLevel);
    }

    /**
     * Sets the plant's under attack flag.
     * @param isUnderAttack whether the plant is currently being attacked.
     */
    public void setUnderAttack(boolean isUnderAttack) { this.isUnderAttack = isUnderAttack; }

    /**
     * Abstract methods to be implemented by subclasses
     */
    public abstract void water(int amount);
}
