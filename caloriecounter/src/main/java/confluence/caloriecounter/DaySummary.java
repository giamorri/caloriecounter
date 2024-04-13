package confluence.caloriecounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class DaySummary {
    private Map<String, Map<String, Double>> foodItems = new HashMap<>();
    private Map<String, Double> totalMacros = new HashMap<>();

    
    
    
    public DaySummary() {
        totalMacros.put("Calories", 0.0);
        totalMacros.put("Protein", 0.0);
        totalMacros.put("Carbs", 0.0);
    }
    

    public void readFoodData(String resourcePath) {
    // Access resource using the class loader
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
    if (inputStream == null) {
        // Resource not found
        System.out.println("File not found in the resources directory: " + resourcePath);
        return;
    }

    // Use BufferedReader to read the file from the InputStream
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.startsWith("#") && !line.isEmpty()) {
                processLine(line);  // Assuming processLine is a method that processes each line
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
}

    private void processLine(String line) {
        String[] parts = line.split(",");
        String foodName = parts[0];
        double calories = Double.parseDouble(parts[1]);
        double protein = Double.parseDouble(parts[2]);
        double carbs = Double.parseDouble(parts[3]);

        Map<String, Double> macros = new HashMap<>();
        macros.put("Calories", calories);
        macros.put("Protein", protein);
        macros.put("Carbs", carbs);
        foodItems.put(foodName, macros);

        totalMacros.put("Calories", totalMacros.get("Calories") + calories);
        totalMacros.put("Protein", totalMacros.get("Protein") + protein);
        totalMacros.put("Carbs", totalMacros.get("Carbs") + carbs);
    }

    public void displayDaySummary() {
        for (Map.Entry<String, Map<String, Double>> entry : foodItems.entrySet()) {
            System.out.println("Food: " + entry.getKey() + ", Calories: " + entry.getValue().get("Calories") +
                               ", Protein: " + entry.getValue().get("Protein") + ", Carbs: " + entry.getValue().get("Carbs"));
        }
        System.out.println("Total: Calories: " + totalMacros.get("Calories") +
                           ", Protein: " + totalMacros.get("Protein") +
                           ", Carbs: " + totalMacros.get("Carbs"));
    }
}