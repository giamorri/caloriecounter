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
                    
                    System.out.println("found "+foodName+" in database: \n- " + formatOutput(foodName, proteinPer100g, carbsPer100g, caloriesPer100g));
                    found = true;
                    
                    System.out.println("how much of this (in grams) did you have?");
                    double gramsEaten = scanner.nextDouble();
                    scanner.nextLine();
                    
                    // Calculate adjusted macronutrients
                    double protein = (proteinPer100g * gramsEaten) / 100;
                    double carbs = (carbsPer100g * gramsEaten) / 100;
                    double calories = (caloriesPer100g * gramsEaten) / 100;
                    
                    // Display adjusted macronutrients
                    System.out.println("changed macros based on " + gramsEaten + " grams eaten:");
                    System.out.println("protein: " + protein + "g");
                    System.out.println("carbs: " + carbs + "g");
                    System.out.println("calories: " + calories + "kcal");
                    
                    day.saveToDay(foodName + "," + protein + "," + carbs + "," + calories);
                    break;
                }
            }
        }
        if (!found) {
            System.out.println(searchTerm+" not found in the database.");
            
            System.out.println("do you want to add "+searchTerm+" to the database? (y/n)");
            String addFood = scanner.nextLine().toLowerCase();
            
            if ("yes".equalsIgnoreCase(addFood) || "y".equalsIgnoreCase(addFood)) {
                addFoodToDatabase(searchTerm);
            } else if ("no".equalsIgnoreCase(addFood) || "n".equalsIgnoreCase(addFood)) {
                System.out.println("food item not added.");
            }
        }
    } catch (IOException | NumberFormatException e) {
        System.out.println("do you want to add "+searchTerm+" to the database? (y/n)");
        String addFood = scanner.nextLine().toLowerCase();
        
        if ("yes".equalsIgnoreCase(addFood) || "y".equalsIgnoreCase(addFood)) {
            addFoodToDatabase(searchTerm);
        } else if ("no".equalsIgnoreCase(addFood) || "n".equalsIgnoreCase(addFood)) {
            System.out.println(searchTerm+" not added.");
        }
    }
}


private String formatOutput(String foodName, double protein, double carbs, double calories) {
    return foodName + "\n- protein: " + protein + "g\n- carbs: " + carbs + "g\n- calories: " + calories + "kcal";
}


        
            public void addFoodToDatabase(String foodName) {
                try {
                    System.out.println("enter the protein content (/100g):");
                    double protein = scanner.nextDouble();
                    scanner.nextDouble();
                    
                    System.out.println("enter the carbohydrates content (/100g):");
                    double carbs = scanner.nextDouble();
                    scanner.nextDouble();
                    
                    System.out.println("enter the calories content (/100g):");
                    double calories = scanner.nextDouble();
                    scanner.nextDouble();

                    FoodDatabase macroDatabase = new FoodDatabase();
                    macroDatabase.addFoodItem(foodName, protein, carbs, calories);
                    saveToDatabase(foodName, protein, carbs, calories);
                    
                    System.out.println("food item added successfully.");
                    searchInDatabase(foodName);
                    
                } catch (InputMismatchException e) {
                    System.out.println("the input must be a number. try again? (y/n)");
                    scanner.nextLine();
                    String again = scanner.nextLine();
                    
                    if ("yes".equalsIgnoreCase(again) || "y".equalsIgnoreCase(again)) {
                        addFoodToDatabase(foodName);
                    } else if ("no".equalsIgnoreCase(again) || "n".equalsIgnoreCase(again)) {
                        System.out.println("food item not added.");
                    }
                }
            }
            public void saveToDatabase(String foodName, double protein, double carbs, double calories) {
                String FILE_PATH = "./resources/FoodDatabase.csv";

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                    writer.write(foodName + "," + protein + "," + carbs + "," + calories);
                    writer.newLine();
                } catch (IOException e) {
                    System.out.println("nuh uh, get outta here");
                }
            }

    }
