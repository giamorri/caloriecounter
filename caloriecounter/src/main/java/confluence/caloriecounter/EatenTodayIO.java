/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

/**
 *
 * @author yash
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class EatenTodayIO {
//search for a food item in the FoodEatenToday.csv file
    public boolean searchInDay(String foodItem) {
        String FILE_PATH = "./resources/FoodEatenToday.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(foodItem)) {
                    System.out.println("Item already added today: \n- " + formatOutput(line));
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Food not added");
        }
        return false; 
    }
//save a specific food item to the FoodEatenToday.csv file
    public void saveToDay(String foodItem) {
        String FILE_PATH = "./resources/FoodEatenToday.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(foodItem);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Ahhhhhhhhh didnt work (has to work) ");
        }
    }

    private String formatOutput(String line) {
        
        String[] parts = line.split(",");
        if (parts.length >= 4) {
            String foodName = parts[0];
            double protein = Double.parseDouble(parts[1]);
            double carbs = Double.parseDouble(parts[2]);
            double calories = Double.parseDouble(parts[3]);
            return foodName + "\n- protein: " + protein + "g\n- carbs: " + carbs + "g\n- calories: " + calories + "kcal";
        }
        return line; 
    }
    //clear day by overwriting file with empty text
   public void clearEatenToday() {
        try {
            
            FileWriter fileWriter = new FileWriter("./resources/FoodEatenToday.csv", false);
            fileWriter.write("");  
            fileWriter.close();  
            System.out.println("Food log cleared successfully.");
        } catch (IOException e) {
            System.out.println("try again!");
        }
    }
}