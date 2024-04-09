/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

import java.util.HashMap;


public class FoodDatabase {

    
    public final HashMap<String, FoodItemInfo> foodDatabase;
    
    public FoodDatabase(){
        
        foodDatabase = new HashMap<>();
        foodDatabase.put("Egg", new FoodItemInfo(1.1, 10.0, 5.0));
    }
    
    public FoodItemInfo getFoodItemInfo(String FoodName) {
        return foodDatabase.get(FoodName);
    }
    public void addFoodItem(String foodName, double protein, double carbs, double calories) {
        foodDatabase.put(foodName, new FoodItemInfo(carbs, protein, calories));
    }
}
