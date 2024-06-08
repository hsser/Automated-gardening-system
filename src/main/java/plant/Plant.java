package plant;

import sensors.PestSensor;
import sensors.WaterSensor;
import java.util.List;

public abstract class Plant{
    private String name;
    private int currentWaterLevel;
    private int numOfPestsAttacking;
    private String typeOfPestsAttacking;
    private WaterSensor waterSensor;
    private PestSensor pestSensor;
    private PlantType plantType;
    private int health;  // Health of the plant

    public Plant(String name, PlantType plantType, int currentWaterLevel) {
        this.name = name;
        this.plantType = plantType;
        this.currentWaterLevel = currentWaterLevel;
        this.numOfPestsAttacking = 0;
        this.waterSensor = new WaterSensor(this);
        this.pestSensor = new PestSensor(this);
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
    public int getNumOfPestsAttacking() { return numOfPestsAttacking; }
    public String getTypeOfPestsAttacking() {
        return typeOfPestsAttacking;
    }
    public PlantType getPlantType() {
        return plantType;
    }
    public int getHealth() { return health; }
    public boolean isAlive() { return this.health > 0; }

    // Setter
    public void setHealth(int health){ this.health = health;}
    public void setCurrentWaterLevel(int currentWaterLevel) { this.currentWaterLevel = currentWaterLevel;}

    public void setPest(String typeOfPestsAttacking, int numOfPestsAttacking) {
        this.typeOfPestsAttacking = typeOfPestsAttacking;
        this.numOfPestsAttacking = numOfPestsAttacking;
        pestSensor.monitorForPestAttack();
    }

    public void clearPest() {
        setPest(null, 0);
    }

    /**
     * Update water level
     */
    public void updateWaterLevel(int newWaterLevel){
        this.waterSensor.updateWaterLevel(newWaterLevel);
    }

    /**
     * Abstract methods to be implemented by subclasses
     */
    public abstract void water(int amount);
}
