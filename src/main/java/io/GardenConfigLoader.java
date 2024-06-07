package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GardenConfigLoader {
    private String configFilePath;

    public GardenConfigLoader(String configFilePath) { this.configFilePath = configFilePath; }

    public List<PlantConfig> loadPlantsConfigurations() throws IOException {
        List<PlantConfig> plants = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(configFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String type = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    System.out.println("TEST-GardenConfigLoader: Plant type: " + type + ", quantity: " + quantity);
                    plants.add(new PlantConfig(type, quantity));
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading plant configuration file: " + e.getMessage());
        }

        return plants;
    }

    // Class to hold plant configuration data
    public static class PlantConfig {
        String type;
        int quantity;

        public PlantConfig(String type, int quantity) {
            this.type = type;
            this.quantity = quantity;
        }

        public String getType() { return type; }
        public int getQuantity() { return quantity; }
    }
}
