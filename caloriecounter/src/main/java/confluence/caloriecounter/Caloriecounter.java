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

   public static void main(String[] args) {
        FoodDatabase macroDatabase = new FoodDatabase();
        
        System.out.println("What did you have today?");
        System.out.println("(add servings and each item in the format - \"grams\" \"item\". separate each item by a \",\". press enter or return when done)");
        String foodName;
        Scanner scanner = new Scanner(System.in);
        foodName = scanner.nextLine();
        
        FoodItemInfo foodInfo = macroDatabase.getFoodItemInfo(foodName);
        
        
        if (foodInfo != null) {
            System.out.println("Food: " + foodName);
            System.out.println("Protein: " + foodInfo.getProtein());
            System.out.println("Carbohydrates: " + foodInfo.getCarbs());
        } else {
            System.out.println("Food not found in the database. Would you like to add an item?");
        }
    }
}
    

