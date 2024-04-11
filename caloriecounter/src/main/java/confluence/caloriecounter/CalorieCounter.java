
 
 

package confluence.caloriecounter;

/**
 *
 * @author Taj
 *
import java.util.Scanner;
import java.util.InputMismatchException;


public class CalorieCounter {

   //public static void main(String[] args) { <-- had to remove this so i can call it from the startmenu main
    public void FoodReader() {
        
        StartMenu menu = new StartMenu();
        FoodDatabase macroDatabase = new FoodDatabase();
        Scanner scanner = new Scanner(System.in);

        

        while (true) {
            System.out.println("What did you have today?");
            //this part might be too much to code --> System.out.println("Add servings and each item in the format \n- \"grams\" \"item\". \nSeparate each item by a \",\". \nPress enter or return when done)");
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


                //this switch case can be replaced by the code taj wrote
                switch (addFood) {
                    case "yes":
                        try {
                            System.out.println("Enter the protein content (/100g):");
                        double protein = scanner.nextDouble();
                        //scanner.nextLine();
                        System.out.println("Enter the carbohydrates content (/100g):");
                        double carbs = scanner.nextDouble();
                        //scanner.nextLine();
                        System.out.println("Enter the calories content (/100g):");
                        double calories = scanner.nextDouble();
                        //scanner.nextLine();
                        // Add the new food item to the database
                            macroDatabase.addFoodItem(foodName, protein, carbs, calories);
                            System.out.println("Food item added successfully.");
                        break;
                        }
                        catch(InputMismatchException e) {
                            //System.out.println("Error: " + e.getMessage());
                            String yesorno;
                            System.out.println("There is missing information for this item");
                            System.out.println("Would you like to try again? (y/n)");
                            yesorno = scanner.nextLine().toLowerCase();
                            if ("y".equalsIgnoreCase(yesorno)){ 
                                FoodReader(); 
                            } else if ("n".equalsIgnoreCase(yesorno)){
                                break; 
                            }
                        }
                    case "no":
                        System.out.println("Food item not added to the database.");
                        break;
                    default:
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                        continue;
                }
                System.out.println("Press 'x' to exit food logger, or any other key to add more food");
                String exitChoice = scanner.nextLine();
                if (("x".equalsIgnoreCase(exitChoice))||("exit".equalsIgnoreCase(exitChoice))) {
                    System.out.println("Exiting program...");
                    break;
                } else {
                    FoodReader(); 
                }
            }
        }
    }
    }


    import java.util.Scanner;
import java.util.InputMismatchException;

public class CalorieCounter {

    public void FoodReader() {
        
        StartMenu menu = new StartMenu();
        FoodDatabase macroDatabase = new FoodDatabase();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("What did you have today?");
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
                        boolean validInput = false;
                        while (!validInput) {
                            try {
                                System.out.println("Enter the protein content (/100g):");
                                double protein = scanner.nextDouble();
                                scanner.nextLine(); // consume newline
                                System.out.println("Enter the carbohydrates content (/100g):");
                                double carbs = scanner.nextDouble();
                                scanner.nextLine(); // consume newline
                                System.out.println("Enter the calories content (/100g):");
                                double calories = scanner.nextDouble();
                                scanner.nextLine(); // consume newline
                                macroDatabase.addFoodItem(foodName, protein, carbs, calories);
                                System.out.println("Food item added successfully.");
                                validInput = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scanner.nextLine(); // consume newline
                            }
                        }
                        break;
                    case "no":
                        System.out.println("Food item not added to the database.");
                        break;
                    default:
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                        break;
                }
            }
            System.out.println("\nEnter 'x' to exit food logger, \nor any other key if you wish to add more food");
            String exitChoice = scanner.nextLine();
            if ("x".equalsIgnoreCase(exitChoice) || "exit".equalsIgnoreCase(exitChoice)) {
                System.out.println("Exiting program...");
                break;
            }
        }
    }
}
*/
        
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CalorieCounter {

    Scanner scanner = new Scanner(System.in);
    
    public void FoodReader() {
        //StartMenu menu = new StartMenu();
        FoodDatabase macroDatabase = new FoodDatabase();
        

        while (true) {
            System.out.println("What did you have today?");
            String foodInput = scanner.nextLine().toLowerCase().trim();
            String foodName = foodInput.replaceAll("[^a-zA-Z]", "");

            if ("x".equalsIgnoreCase(foodName) || "exit".equalsIgnoreCase(foodName) || "".equalsIgnoreCase(foodName)) {
                System.out.println("No food entered...");
                break;
            }

            //FoodItemInfo foodInfo = macroDatabase.getFoodItemInfo(foodName);
            searchInDatabase(foodName);
// need to change this if else thing so that the macros are read from the csv file instead of the fooditeminfo class
 /*           if (foodInfo != null) {
                System.out.println("Food: " + foodName);
                System.out.println("Protein: " + foodInfo.getProtein());
                System.out.println("Carbohydrates: " + foodInfo.getCarbs());
                System.out.println("Calories: " + foodInfo.getCalories());
            } else {
                System.out.println("Food not found in the database.");
                System.out.println("Would you like to add this food item to the database? (yes/no)");
*/              String addFood = scanner.nextLine().toLowerCase();

                switch (addFood) {
                    case "yes":
                    case "y":
                        boolean validInput = false;
                        while (!validInput) {
                            try {
                                System.out.println("Enter the protein content (/100g):");
                                double protein = scanner.nextDouble();
                                scanner.nextLine(); // consume newline
                                System.out.println("Enter the carbohydrates content (/100g):");
                                double carbs = scanner.nextDouble();
                                scanner.nextLine(); // consume newline
                                System.out.println("Enter the calories content (/100g):");
                                double calories = scanner.nextDouble();
                                scanner.nextLine(); // consume newline
                                macroDatabase.addFoodItem(foodName, protein, carbs, calories);
                                saveToDatabase(foodName, protein, carbs, calories); // Call saveToFile method
                                System.out.println("Food item added successfully.");
                                validInput = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scanner.nextLine(); // consume newline
                            }
                        }
                        break;
                    case "no":
                    case "n":
                        System.out.println("Food item not added to the database.");
                        break;
                    default:
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                        break;
                }
        
            System.out.println("Press 'x' to exit food logger, or any other key to add more food");
            String exitChoice = scanner.nextLine();
            if ("x".equalsIgnoreCase(exitChoice) || "exit".equalsIgnoreCase(exitChoice)) {
                System.out.println("Exiting program...");
                break;
            }
        }
    }
    public void saveToDatabase(String foodName, double protein, double carbs, double calories) {
        String FILE_PATH = "./resources/FoodDatabase.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(foodName + " " + protein + "(g)/100g" + " " + carbs + "(g)/100g" + " " + calories + "kcal/100g");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving food item to file: " + e.getMessage());
        }
    }

    public void searchInDatabase(String searchTerm) {
        String FILE_PATH = "./resources/FoodDatabase.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchTerm)) {
                    System.out.println("Found line: " + line);
                    found = true;
                    saveToDay(line);
                    System.out.println("Would you like to update the macros of this item?");
                }
            }
            if (!found) {
                System.out.println("Food not found in the database.");
                System.out.println("Would you like to add this food item to the database? (yes/no)");
                
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
    public void saveToDay(String foodItem) {
        String FILE_PATH = "./resources/FoodEatenToday.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(foodItem);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving food item to file: " + e.getMessage());
        }
    }

}

    

    

