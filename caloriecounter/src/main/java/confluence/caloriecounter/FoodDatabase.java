/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

import java.util.HashMap;


public class FoodDatabase {

    
    public final HashMap<String, FoodItemInfo> FoodDatabase;
    
    public FoodDatabase(){
        
        FoodDatabase = new HashMap<>();
        FoodDatabase.put("Egg", new FoodItemInfo(1.1, 10.0, 5.0));
    }
    
    public FoodItemInfo getFoodItemInfo(String FoodName) {
        return FoodDatabase.get(FoodName);
    }
}