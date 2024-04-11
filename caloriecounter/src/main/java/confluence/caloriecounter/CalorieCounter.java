
 
 

package confluence.caloriecounter;

/**
 *
 * @author Taj
 */
import java.util.Scanner;


public class CalorieCounter {

   //public static void main(String[] args) { <-- had to remove this so i can call it from the startmenu main
    public void FoodReader() {
    FoodDatabase macroDatabase = new FoodDatabase();
    Scanner scanner = new Scanner(System.in);

    System.out.println("What did you have today?");
    System.out.println("Add servings and each item in the format \n- \"grams\" \"item\". \nSeparate each item by a \",\". \nPress enter or return when done)");

    while (true) {
        String foodName = scanner.nextLine().trim();

        if ("x".equalsIgnoreCase(foodName) || "exit".equalsIgnoreCase(foodName)) {
            System.out.println("No food entered...");
            break;
        }
        

        FoodItemInfo foodInfo = macroDatabase.getFoodItemInfo(foodName);

        if (foodInfo != null) {
            System.out.println("Food: " + foodName);
            System.out.println("Protein: " + foodInfo.getProtein());
            System.out.println("Carbohydrates: " + foodInfo.getCarbs());
            System.out.println("Calories: " + foodInfo.getCalories());
        } else {
            System.out.println("Food not found in the database.");
            System.out.println("Would you like to add this food item to the database? (yes/no)");
            String addFood = scanner.nextLine().toLowerCase();
            
            switch (addFood) {
                case "yes":
                    System.out.println("Enter the protein content (/100g):");
                    double protein = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Enter the carbohydrates content (/100g):");
                    double carbs = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Enter the calories content (/100g):");
                    double calories = scanner.nextDouble();
                    scanner.nextLine();
                    // Add the new food item to the database
                    macroDatabase.addFoodItem(foodName, protein, carbs, calories);
                    System.out.println("Food item added successfully.");
                    break;
                case "no":
                    System.out.println("Food item not added to the database.");
                    break;
                default:
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    continue;
            }
            System.out.println("Press 'x' to exit, or press Enter to return to the main menu.");
            String exitChoice = scanner.nextLine();
            if ("x".equalsIgnoreCase(exitChoice)) {
        System.out.println("Exiting program...");
        break;
    }
        }
    }
}
}


    
        
                
            

        
    

    

