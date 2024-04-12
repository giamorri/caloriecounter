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

/**
 *
 * @author yash
 */
public class EatenTodayIO {
    public boolean searchInDay(String foodItem) {
        String FILE_PATH = "./resources/FoodEatenToday.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(foodItem)) {
                    System.out.println("Item already added today: " + line);
                    return true; // Item found in daily log
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
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
}
