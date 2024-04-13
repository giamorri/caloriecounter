/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FoodDatabase {
    private final Map<String, FoodItemInfo> foodDatabase = new HashMap<>();

    

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
    


}
//need a bufferReader

