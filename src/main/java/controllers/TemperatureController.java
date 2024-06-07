package controllers;

import sensors.TemperatureSensor;

public class TemperatureController {

    public static int adjustTemperature(int currentTemperature) {
        int highThreshold = TemperatureSensor.getHighTemperatureThreshold();
        int lowThreshold = TemperatureSensor.getLowTemperatureThreshold();
        int optimalTemperature = TemperatureSensor.getOptimalTemperature();

        if (currentTemperature > highThreshold) {
            System.out.println("Warning: Temperature has exceeded the high limit of " + highThreshold + " degrees.");
            return coolDown(currentTemperature, optimalTemperature);
        } else if (currentTemperature < lowThreshold) {
            System.out.println("Warning: Temperature has fallen below the low limit of " + lowThreshold + " degrees.");
            return heatUp(currentTemperature, optimalTemperature);
        } else {
            System.out.println("The temperature is quite suitable for plants' growth.");
            return currentTemperature;
        }
    }

    private static int coolDown(int currentTemperature, int optimalTemperature) {
        System.out.println("Cooler is on.");
        while (currentTemperature > optimalTemperature) {
            currentTemperature -= 1;
            System.out.println("Cooling: Current temperature is " + currentTemperature + " degrees.");
        }
        System.out.println("Cooler is off. Temperature adjusted to optimal level: " + optimalTemperature + " degrees.");
        return currentTemperature;
    }

    private static int heatUp(int currentTemperature, int optimalTemperature) {
        System.out.println("Heater is on.");
        while (currentTemperature < optimalTemperature) {
            currentTemperature += 1;
            System.out.println("Heating: Current temperature is " + currentTemperature + " degrees.");
        }
        System.out.println("Heater is off. Temperature adjusted to optimal level: " + optimalTemperature + " degrees.");
        return currentTemperature;
    }
}
