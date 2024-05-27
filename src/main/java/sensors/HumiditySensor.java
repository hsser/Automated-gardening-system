package sensors;

public class HumiditySensor {
    private static final double MIN_HUMIDITY_LEVEL = 1.0;
    private static final double MAX_HUMIDITY_LEVEL = 5.0;

    private double humidityLevel;

    /**
     * Constructs a HumiditySensor with a default humidity level of 1.0.
     */
    public HumiditySensor(){
        this.humidityLevel = 1.0;
    }

    /**
     * Sets the humidity level.
     *
     * @param newHumidityLevel the new humidity level
     */
    public void setHumidityLevel(double newHumidityLevel){
        this.humidityLevel = newHumidityLevel;
    }

    /**
     * Gets the current humidity level.
     *
     * @return the current humidity level
     */
    public double getHumidityLevel() {
        return humidityLevel;
    }

    /**
     * Updates the humidity level. The new humidity level must be between 0.0 and 5.0.
     * If the level is outside this range, it is assumed the plant will die.
     *
     * @param newHumidityLevel the new humidity level
     */
    public void updateHumidityLevel(double newHumidityLevel) {
        if(newHumidityLevel < MIN_HUMIDITY_LEVEL || newHumidityLevel > MAX_HUMIDITY_LEVEL){
            System.out.println("The plant has died due to inappropriate humidity levels!");
        }
        this.humidityLevel = newHumidityLevel;
    }

    /**
     * Checks if the humidity level is low.
     *
     * @return true if the humidity level is equal to or below 2.0
     */
    public boolean isHumidityLevelLow(){
        if(humidityLevel <= 2.0){
            return true;
        }else{
            return false;
        }
    }
}
