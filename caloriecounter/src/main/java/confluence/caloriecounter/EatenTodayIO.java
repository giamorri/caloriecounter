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


public class EatenTodayIO {
    public boolean searchInDay(String foodItem) {
        String FILE_PATH = "./resources/FoodEatenToday.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(foodItem)) {
                    System.out.println("Item already added today: \n- " + formatOutput(line));
                    return true; // Item found in daily log
                }
            }
        } catch (IOException e) {
            System.out.println("Food not added today");
        }
        return false; // Item not found in daily log
    }

    public void saveToDay(String foodItem) {
        String FILE_PATH = "./resources/FoodEatenToday.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(foodItem);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving food item to file: " + e.getMessage());
        }
    }

    private String formatOutput(String line) {
        // Assuming the format of each line is "food,protein,carbs,calories"
        String[] parts = line.split(",");
        if (parts.length >= 4) {
            String foodName = parts[0];
            double protein = Double.parseDouble(parts[1]);
            double carbs = Double.parseDouble(parts[2]);
            double calories = Double.parseDouble(parts[3]);
            return foodName + "\n- protein: " + protein + "g\n- carbs: " + carbs + "g\n- calories: " + calories + "kcal";
        }
        return line; // Return original line if unable to parse
    }
}