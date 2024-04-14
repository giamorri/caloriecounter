package confluence.caloriecounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DaySummary {
    
    private Map<String, Map<String, Double>> foodItems = new HashMap<>();
    private Map<String, Double> totalMacros = new HashMap<>();

    
    
    
    public DaySummary() {
        totalMacros.put("Calories", 0.0);
        totalMacros.put("Protein", 0.0);
        totalMacros.put("Carbs", 0.0);
    }
    Scanner exitScanner;
    ExitProgram exit;
    StartMenu menu;

    //this is for reading the data from our FoodEatenToday.csv 
    public void readFoodData(String filePath) {
        
        File file = new File(filePath);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("no food added yet!");
            System.out.println("go back to main menu? (y/n)");
            
            String enter;
            if (exitScanner != null) {
                enter = exitScanner.nextLine();
                if ("y".equalsIgnoreCase(enter) || "yes".equalsIgnoreCase(enter)) {
                    menu.displayMenu();
                } else {
                    exit.exitProgram();
                }
            }
        }
    }
    //this is to parse the information into the FoodEatenToday.cssv file by splitting at ','
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
            System.out.println(entry.getKey() + ", Calories: " + entry.getValue().get("Calories") +", Protein: " + entry.getValue().get("Protein") + ", Carbs: " + entry.getValue().get("Carbs"));
        }
        System.out.println("Total: Calories: " + totalMacros.get("Calories") +", Protein: " + totalMacros.get("Protein") +", Carbs: " + totalMacros.get("Carbs"));
    }
}