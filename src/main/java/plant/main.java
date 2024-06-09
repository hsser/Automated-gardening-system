package plant;

import sensors.TemperatureSensor;

public class main {
    public static void main(String[] args) {
        TemperatureSensor.getInstance().setTemperature(20);
        System.out.println(TemperatureSensor.getInstance().getTemperature());

    }
}
