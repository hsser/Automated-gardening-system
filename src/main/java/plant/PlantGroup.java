package plant;

import application.WateringProtectionAction;
import application.PestAttackHandlingAction;
import application.SubsystemEffectAction;
import io.GardenLogger;
//import sensors.HealthSensor;
import sensors.PestSensor;
import sensors.TemperatureSensor;
import sensors.WaterSensor;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PlantGroup {
    private List<Plant> plants;
    private int currentPlotIndex;
    private int numOfPestsAttacking;
    private String typeOfPestsAttacking;
    private WaterSensor waterSensor;
    private PestSensor pestSensor;
//    private HealthSensor healthSensor;
    private WateringProtectionAction wateringProtectionAction;
    private PestAttackHandlingAction pestAttackHandlingAction;
    private boolean waterProtection;
    private final int HEALTH_REDUCE_BY_HUMIDITY = 10;
    private final int HEALTH_REDUCE_BY_TEMPERATURE = 15;
    private final int HEALTH_REDUCE_BY_PEST = 20;

    public boolean isWaterProtection() {
        return waterProtection;
    }

    public void setWaterProtection(boolean waterProtection) {
        boolean oldValue = this.waterProtection;
        this.waterProtection = waterProtection;
        if (waterProtection != oldValue && wateringProtectionAction != null) {
            wateringProtectionAction.run(waterProtection);
        }
    }

    public PlantGroup(List<Plant> plants) {
        this.plants = plants;
        currentPlotIndex = -1;
        this.numOfPestsAttacking = 0;
        waterSensor = new WaterSensor(this);
        pestSensor = new PestSensor(this);
    }

    // Getters
    public List<Plant> getPlants() {
        return plants;
    }

    public int getCurrentPlotIndex() {
        return currentPlotIndex;
    }

    public boolean isEmpty() {
        return plants.isEmpty();
    }

    public String getName() {
        return plants.getFirst().getName();
    }

    public int getHealth() {
        return plants.getFirst().getHealth();
    }

    public int getCurrentWaterLevel() {
        return plants.getFirst().getCurrentWaterLevel();
    }

    public int getMinWaterLevel() {
        return plants.getFirst().getMinWaterLevel();
    }

    public int getMaxWaterLevel() {
        return plants.getFirst().getMaxWaterLevel();
    }

    public int getMinTemperatureLevel() {
        return plants.getFirst().getMinTemperatureLevel();
    }

    public int getMaxTemperatureLevel() {
        return plants.getFirst().getMaxTemperatureLevel();
    }

    public int getLowWaterThreshold() {
        return plants.getFirst().getLowWaterThreshold();
    }

    public int getHighWaterThreshold() {
        return plants.getFirst().getHighWaterThreshold();
    }

    public List<String> getPestList() {
        return plants.getFirst().getPestList();
    }

    public int getNumOfPestsAttacking() {
        return numOfPestsAttacking;
    }

    public String getTypeOfPestsAttacking() {
        return typeOfPestsAttacking;
    }

    public WaterSensor getWaterSensor() {
        return waterSensor;
    }

//    public HealthSensor getHealthSensor() {
//        return healthSensor;
//    }

    public Plant get(int index) {
        return plants.get(index);
    }

    // Setters
    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public void setCurrentPlotIndex(int currentPlotIndex) {
        this.currentPlotIndex = currentPlotIndex;
    }

    public void setHealth(int health) {
        for (Plant plant : plants) {
            plant.setHealth(health);
        }
    }

    public void setCurrentWaterLevel(int currentWaterLevel) {
        for (Plant plant : plants) {
            plant.setCurrentWaterLevel(currentWaterLevel);
        }
    }

    public void setPest(String typeOfPestsAttacking, int numOfPestsAttacking) {
        this.typeOfPestsAttacking = typeOfPestsAttacking;
        this.numOfPestsAttacking = numOfPestsAttacking;
        pestSensor.setOnPestAttackHandling(pestAttackHandlingAction);
        pestSensor.monitorForPestAttack();
        if (getNumOfPestsAttacking() > 0) {
            GardenLogger.log("PlantGroup", getName() + " 'health is reduced because the pest is not handling.");
            healthReduce(HEALTH_REDUCE_BY_PEST);
        }
    }

    public void clearPest() {
        setPest(null, 0);
    }

    // Other methods
    public void add(Plant plant) {
        plants.add(plant);
    }

    public int size() {
        return plants.size();
    }

    public void updateWaterLevel(int waterLevel) {
        setCurrentWaterLevel(waterLevel);
        this.waterSensor.updateWaterLevel(waterLevel);
        GardenLogger.log("Water Sensor", getName() + "'s water level has been updated, it's current water level is " + getCurrentWaterLevel());
        if (getCurrentWaterLevel() < getMinWaterLevel() || getCurrentWaterLevel() > getMaxWaterLevel()) {
            GardenLogger.log("PlantGroup", getName() + "' health is reduced because the water level is abnormal.");
            healthReduce(HEALTH_REDUCE_BY_HUMIDITY);
        }
    }

    public void updateStatusByTemperatureChange(AtomicInteger temperature) {
        if (temperature.get() < getMinTemperatureLevel() ||
                temperature.get() > getMaxTemperatureLevel()) {
            healthReduce(HEALTH_REDUCE_BY_TEMPERATURE);
            GardenLogger.log("PlantGroup", getName() + " health is reduced because the temperature level is abnormal.");
        }
    }

    public void healthReduce(int healthReduce) {
        int previousHealth = getHealth();
        setHealth(previousHealth - healthReduce);
        GardenLogger.log("PlantGroup", getName() + " health reduced from " + previousHealth + " to " + getHealth());
        if (getHealth() <= 0) {
            GardenLogger.log("PlantGroup", getName() + " has died.");
        }
    }

    // UI
    public void setOnSubsystemsEffect(SubsystemEffectAction action) {
        this.waterSensor.setSprinklerAction(action);
    }
    public void setOnWateringProtection(WateringProtectionAction action) {
        wateringProtectionAction = action;
    }
    public void setOnPestAttackHandling(PestAttackHandlingAction action) {
        pestAttackHandlingAction = action;
    }
}
