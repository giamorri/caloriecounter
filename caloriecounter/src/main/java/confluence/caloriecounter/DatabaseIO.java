/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author yash
 */
public class DatabaseIO {
    
    EatenTodayIO day = new EatenTodayIO();
    Scanner scanner = new Scanner(System.in);
    
    /*public void searchInDatabase(String searchTerm) {
    String FILE_PATH = "./resources/FoodDatabase.csv";

    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
        String line;
        boolean found = false;
        while ((line = reader.readLine()) != null) {
            if (line.contains(searchTerm)) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String foodName = parts[0];
                    double protein = Double.parseDouble(parts[1]);
                    double carbs = Double.parseDouble(parts[2]);
                    double calories = Double.parseDouble(parts[3]);
                    System.out.println("Found line in database: " + formatOutput(foodName, protein, carbs, calories));
                    found = true;
                    day.saveToDay(line);
                    System.out.println("Would you like to update the macros of this item?");
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("Food not found in the database.");
            System.out.println("Would you like to add this food item to the database? (y/n)");
            String addFood = scanner.nextLine().toLowerCase();
            if ("yes".equalsIgnoreCase(addFood) || "y".equalsIgnoreCase(addFood)) {
                addFoodToDatabase(searchTerm);
            } else if ("no".equalsIgnoreCase(addFood) || "n".equalsIgnoreCase(addFood)) {
                System.out.println("Food item not added.");
            }
        }
    } catch (IOException | NumberFormatException e) {
        System.out.println("Error reading from file: " + e.getMessage());
        System.out.println("Would you like to add this food item to the database? (y/n)");
        String addFood = scanner.nextLine().toLowerCase();
        if ("yes".equalsIgnoreCase(addFood) || "y".equalsIgnoreCase(addFood)) {
            addFoodToDatabase(searchTerm);
        } else if ("no".equalsIgnoreCase(addFood) || "n".equalsIgnoreCase(addFood)) {
            System.out.println("Food item not added.");
        }
    }
}*/
    public void searchInDatabase(String searchTerm) {
    String FILE_PATH = "./resources/FoodDatabase.csv";

    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
        String line;
        boolean found = false;
        while ((line = reader.readLine()) != null) {
            if (line.contains(searchTerm)) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String foodName = parts[0];
                    double proteinPer100g = Double.parseDouble(parts[1]);
                    double carbsPer100g = Double.parseDouble(parts[2]);
                    double caloriesPer100g = Double.parseDouble(parts[3]);
                    
                    System.out.println("Found line in database: " + formatOutput(foodName, proteinPer100g, carbsPer100g, caloriesPer100g));
                    found = true;
                    
                    System.out.println("Enter the amount of grams consumed:");
                    double gramsConsumed = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    
                    // Calculate adjusted macronutrients
                    double protein = (proteinPer100g * gramsConsumed) / 100;
                    double carbs = (carbsPer100g * gramsConsumed) / 100;
                    double calories = (caloriesPer100g * gramsConsumed) / 100;
                    
                    // Display adjusted macronutrients
                    System.out.println("Adjusted macronutrients based on " + gramsConsumed + " grams consumed:");
                    System.out.println("Protein: " + protein + "g");
                    System.out.println("Carbs: " + carbs + "g");
                    System.out.println("Calories: " + calories + "kcal");
                    
                    day.saveToDay(foodName + "," + protein + "," + carbs + "," + calories);
                    System.out.println("Would you like to update the macros of this item?");
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("Food not found in the database.");
            System.out.println("Would you like to add this food item to the database? (y/n)");
            String addFood = scanner.nextLine().toLowerCase();
            if ("yes".equalsIgnoreCase(addFood) || "y".equalsIgnoreCase(addFood)) {
                addFoodToDatabase(searchTerm);
            } else if ("no".equalsIgnoreCase(addFood) || "n".equalsIgnoreCase(addFood)) {
                System.out.println("Food item not added.");
            }
        }
    } catch (IOException | NumberFormatException e) {
        System.out.println("Error reading from file: " + e.getMessage());
        System.out.println("An error occurred while reading the database. Would you like to add this food item to the database? (y/n)");
        String addFood = scanner.nextLine().toLowerCase();
        if ("yes".equalsIgnoreCase(addFood) || "y".equalsIgnoreCase(addFood)) {
            addFoodToDatabase(searchTerm);
        } else if ("no".equalsIgnoreCase(addFood) || "n".equalsIgnoreCase(addFood)) {
            System.out.println("Food item not added.");
        }
    }
}


private String formatOutput(String foodName, double protein, double carbs, double calories) {
    return foodName + "\n- protein: " + protein + "g\n- carbs: " + carbs + "g\n- calories: " + calories + "kcal";
}


        
            public void addFoodToDatabase(String foodName) {
                try {
                    System.out.println("Enter the protein content (/100g):");
                    double protein = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter the carbohydrates content (/100g):");
                    double carbs = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter the calories content (/100g):");
                    double calories = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    FoodDatabase macroDatabase = new FoodDatabase();
                    macroDatabase.addFoodItem(foodName, protein, carbs, calories);
                    saveToDatabase(foodName, protein, carbs, calories);
                    System.out.println("Food item added successfully.");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    addFoodToDatabase(foodName);
                    //scanner.nextLine(); // Consume newline
        }
    }
               public void saveToDatabase(String foodName, double protein, double carbs, double calories) {
        String FILE_PATH = "./resources/FoodDatabase.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(foodName + "," + protein + "," + carbs + "," + calories);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving food item to file: " + e.getMessage());
        }
    }

}
