
 
 

package confluence.caloriecounter;


import java.util.Scanner;

public class CalorieCounter {
    
    DatabaseIO dataIO = new DatabaseIO();
    EatenTodayIO dayIO = new EatenTodayIO();

    Scanner scanner = new Scanner(System.in);
    
    private CalorieTracker calorieTracker;

    public void addFood(String foodName, int calories) {
        this.calorieTracker.addCalories(calories);
        System.out.println("Added " + foodName + " with " + calories + " calories.");
    }
    public void foodReader() {
    //FoodDatabase macroDatabase = new FoodDatabase();

        while (true) {
            System.out.println("What did you have today?");
            String foodInput = scanner.nextLine().toLowerCase().trim();
            String foodName = foodInput.replaceAll("[^a-zA-Z]", "");

            if ("x".equalsIgnoreCase(foodName) || "exit".equalsIgnoreCase(foodName) || "".equalsIgnoreCase(foodName)) {
                System.out.println("No food entered...");
                break;
            }

            boolean foundInDay = dayIO.searchInDay(foodName);
            if (!foundInDay) {
                dataIO.searchInDatabase(foodName);
            }

            System.out.println("Enter 'x' to exit food logger, or any other key to add more food");
            String exitChoice = scanner.nextLine();
            if ("x".equalsIgnoreCase(exitChoice) || "exit".equalsIgnoreCase(exitChoice)) {
                System.out.println("Exiting program...");
                break;
            }
        }
    }
    
    



 

}
