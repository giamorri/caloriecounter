import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DaySummary {

    public static void main(String[] args) {
        Map<String, Map<String, Double>> foodItems = new HashMap<>();
        Map<String, Double> totalMacros = new HashMap<>();
        totalMacros.put("Calories", 0.0);
        totalMacros.put("Protein", 0.0);
        totalMacros.put("Carbs", 0.0);

        // this is for loading food items from the CSV
        try (BufferedReader reader = new BufferedReader(new FileReader("FoodEatenToday.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("#") && !line.isEmpty()) {  // this is so that the empty lines are ignored
                    String[] parts = line.split(",");
                    String foodName = parts[0];
                    //for reading the input and what is before and after each comma
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
            }
        } catch (IOException e) {
            System.out.println("Input in the right order please!");
        }

        // to show the actual day summary
        for (Map.Entry<String, Map<String, Double>> entry : foodItems.entrySet()) {
            System.out.println("Food: " + entry.getKey() + ", Calories: " + entry.getValue().get("Calories") +
                               ", Protein: " + entry.getValue().get("Protein") + ", Carbs: " + entry.getValue().get("Carbs"));
        }
        System.out.println("Total: Calories: " + totalMacros.get("Calories") +
                           ", Protein: " + totalMacros.get("Protein") +
                           ", Carbs: " + totalMacros.get("Carbs"));
    }
}
