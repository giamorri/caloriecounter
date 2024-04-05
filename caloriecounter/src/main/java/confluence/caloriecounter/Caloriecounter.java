/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package confluence.caloriecounter;

/**
 *
 * @author Taj
 */
public class Caloriecounter {

   public static void main(String[] args) {
        FoodDatabase macroDatabase = new FoodDatabase();
        
        String foodName = "Lol";
        
        
        FoodItemInfo foodInfo = macroDatabase.getFoodItemInfo(foodName);
        
        if (foodInfo != null) {
            System.out.println("Food: " + foodName);
            System.out.println("Protein: " + foodInfo.getProtein());
            System.out.println("Carbohydrates: " + foodInfo.getCarbs());
        } else {
            System.out.println("Food not found in the database.");
        }
    }
}
    

