/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package confluence.caloriecounter;

/**
 *
 * @author Taj
 */
import java.util.Scanner;

public class Caloriecounter {

   //public static void main(String[] args) { <-- had to remove this so i can call it from the startmenu main
    public void FoodReader(){
        FoodDatabase macroDatabase = new FoodDatabase();
        
        System.out.println("What did you have today?");
        System.out.println("Add servings and each item in the format \n- \"grams\" \"item\". \nseparate each item by a \",\". \npress enter or return when done)");
        String foodName;
        Scanner scanner = new Scanner(System.in);
        foodName = scanner.nextLine();
        
        FoodItemInfo foodInfo = macroDatabase.getFoodItemInfo(foodName);
        
        
        if (foodInfo != null) {
            System.out.println("Food: " + foodName);
            System.out.println("Protein: " + foodInfo.getProtein());
            System.out.println("Carbohydrates: " + foodInfo.getCarbs());
            System.out.println("Calories: " + foodInfo.getCalories());
        } else {
            System.out.println("Food not found in the database. Would you like to add an item?");
        }
    }
}
    

