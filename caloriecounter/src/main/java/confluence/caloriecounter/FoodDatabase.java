/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FoodDatabase {
    private final Map<String, FoodItemInfo> foodDatabase = new HashMap<>();

    //public FoodDatabase() {
    //    foodDatabase.put("Egg", new FoodItemInfo(1.1, 10.0, 5.0));
    //}

    public FoodItemInfo getFoodItemInfo(String foodName) {
        return foodDatabase.get(foodName);
    }

        // pls check this out, input checking
    public void addFoodItem(String foodName, double protein, double carbs, double calories) {
        if (Objects.nonNull(foodName) && !foodName.trim().isEmpty() && protein >= 0 && carbs >= 0 && calories >= 0) {
            foodDatabase.put(foodName, new FoodItemInfo(protein, carbs, calories));
            //System.out.println("food item data added successfully.");
        }
          //else...  //throw new IllegalArgumentException("Invalid food item data");
                     //System.out.println("Invalid food item data");
            
            
        }
    

    // this is to save the food item to the file if its all good
    public void saveToFile(String foodName, double protein, double carbs, double calories) {
        String FILE_PATH = "./resources/FoodDatabase.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(foodName + " " + protein+"(g)/100g" + " " + carbs +"(g)/100g"+ " " + calories+"kcal/100g");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving food item to file: " + e.getMessage());
        }
    }
}
//need a bufferReader

