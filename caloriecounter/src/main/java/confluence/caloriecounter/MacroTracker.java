/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Taj
 */
public class MacroTracker {
    
    private  CalorieTracker calorieTarget;
    private  Map<String, Integer> totalMacros = new HashMap<>();
     public MacroTracker (CalorieTracker  calorieTarget) {
        this.calorieTarget = calorieTarget;
        totalMacros.put("calories", 0);
        totalMacros.put("protein", 0);
        totalMacros.put("carbs", 0);
    }
    public void addFoodItem(String foodName, double protein, double carbs, double calories) {
        totalMacros.put("calories", totalMacros.get("calories") + (int) calories);
        totalMacros.put("protein", totalMacros.get("protein") + (int) protein);
        totalMacros.put("carbs", totalMacros.get("carbs") + (int) carbs);
       
    }
     public void setCalorieTarget(CalorieTracker calorieTarget) {
        this.calorieTarget = calorieTarget;
    }

    public CalorieTracker getCalorieTarget() {
        return calorieTarget;
    }
    public void displayTotalMacros() {
        System.out.println("Total calories consumed today: " + totalMacros.get("calories"));
        System.out.println("Total protein consumed today: " + totalMacros.get("protein"));
        System.out.println("Total carbs consumed today: " + totalMacros.get("carbs"));
        System.out.println("Remaining calories for the day: " + (calorieTarget.getCalorieTarget() - totalMacros.get("calories")));
    }
}
