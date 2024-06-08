package plant;

import sensors.PestSensor;
import sensors.WaterSensor;

import java.util.List;

public class PlantGroup {
    private List<Plant> plants;
    private int currentPlotIndex;
    private int numOfPestsAttacking;
    private String typeOfPestsAttacking;
    private WaterSensor waterSensor;
    private PestSensor pestSensor;

    public PlantGroup(List<Plant> plants) {
        this.plants = plants;
        currentPlotIndex = -1;
        this.numOfPestsAttacking = 0;
        waterSensor = new WaterSensor(this);
        pestSensor = new PestSensor(this);
    }

    // Getters
    public List<Plant> getPlants() { return plants; }
    public boolean isEmpty() { return plants.isEmpty(); }
    public String getName() { return plants.getFirst().getName(); }
    public int getHealth() { return plants.getFirst().getHealth(); }
    public int getCurrentWaterLevel() { return plants.getFirst().getCurrentWaterLevel(); }
    public int getMinWaterLevel() { return plants.getFirst().getMinWaterLevel(); }
    public int getMaxWaterLevel() { return plants.getFirst().getMaxWaterLevel(); }
    public int getMinTemperatureLevel() { return plants.getFirst().getMinTemperatureLevel(); }
    public int getMaxTemperatureLevel() { return plants.getFirst().getMaxTemperatureLevel(); }
    public int getLowWaterThreshold() { return plants.getFirst().getPlantLowWaterThreshold(); }
    public List<String> getPestList() { return plants.getFirst().getPestList(); }
    public int getNumOfPestsAttacking() { return numOfPestsAttacking; }
    public String getTypeOfPestsAttacking() { return typeOfPestsAttacking; }
    public Plant get(int index) { return plants.get(index); }
    // Setters
    public void setPlants(List<Plant> plants) { this.plants = plants; }
    public void setCurrentPlotIndex(int currentPlotIndex) { this.currentPlotIndex = currentPlotIndex; }
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
        pestSensor.monitorForPestAttack();
    }
    public void clearPest() {
        setPest(null, 0);
    }
    // Other methods
    public void add(Plant plant) { plants.add(plant); }
    public int size() { return plants.size(); }
    public void updateWaterLevel(int waterLevel) { this.waterSensor.updateWaterLevel(waterLevel); }

}
